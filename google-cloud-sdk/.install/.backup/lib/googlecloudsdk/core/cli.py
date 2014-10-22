# Copyright 2013 Google Inc. All Rights Reserved.

"""A module to make it easy to set up and run CLIs in the Cloud SDK."""

import logging
import os.path
import subprocess
import sys

import httplib2


from googlecloudsdk.calliope import cli as calliope
from googlecloudsdk.core import config
from googlecloudsdk.core.credentials import store as c_store


__all__ = ['CLILoader', 'GoogleCloudSDKPackageRoot', 'Credentials', 'Http']


class Error(Exception):
  """Exceptions for the cli module."""


class NoAuthException(Error):
  """An exception to be raised when there is no valid credentials object."""

  def __init__(self):
    auth_command = 'gcloud auth login'
    message = 'No valid credentials. Please run %s.' % auth_command
    super(NoAuthException, self).__init__(message)


class UnexpectedKeysException(Error):
  """An exception to be raised when CLI config files have unrecognized keys."""


class NoHelpFound(Error):
  """Raised when a help file cannot be located."""


def GetHelp(help_dir):
  """Returns a function that can display long help.

  Long help is displayed using the man utility if it's available on
  the user's platform. If man is not available, a plain-text version
  of help is written to standard out.

  Args:
    help_dir: str, The path to the directory containing help documents.

  Returns:
    func([str]), A function that can display help if help_dir exists,
    otherwise None.
  """
  def Help(path, default=None):
    """Displays help for the given subcommand.

    This function first attempts to display help using the man utility.
    If man is unavailable, a plain-text version of the help is printed
    to standard out.

    Args:
      path: A path representing the subcommand for which help is being
          requested (e.g., ['my-prog', 'my-subcommand'] if help is being
          requested for "my-prog my-subcommand").
      default: str, Text to print out if no help files can be found.

    Raises:
      HelpNotFound: If man is not available and no help exists for the
          given subcommand. Note that if man is available and no help exists,
          error reporting is deferred to man.
    """
    try:
      process = subprocess.Popen(
          ['man',
           '-M', os.path.join(help_dir, 'man'),  # Sets the man search path.
           '-'.join(path),
          ],
          stderr=subprocess.PIPE)
      _, stderr = process.communicate()
      if process.returncode == 0:
        return
      else:
        logging.debug('man process returned with exit code %s; stderr: %s',
                      process.returncode, stderr)
    except OSError as e:
      logging.debug('There was a problem launching man: %s', e)

    logging.debug('Falling back to plain-text help.')

    text_help_file_path = os.path.join(help_dir, 'text.long', '_'.join(path))
    try:
      with open(text_help_file_path) as f:
        sys.stdout.write(f.read())
    except IOError:
      if default:
        print default
      else:
        raise NoHelpFound(
            'no manual entry for command: {0}'.format(' '.join(path)))

  if help_dir and os.path.exists(help_dir):
    return Help
  else:
    return None


def CLILoader(name, command_root_directory, allow_non_existing_modules=False,
              version_func=None, help_dir=None):
  """Get a ready-to-go CLI for Cloud SDK tools.

  Args:
    name: str, The name of your CLI. Should probably be the same as the
        executable name.
    command_root_directory: str, The absolute path to the tools root.
    allow_non_existing_modules: bool, If true, module directories that don't
        exist will be ignored rather than cause errors.
    version_func: func, Function to call with -v, --version.
    help_dir: str, The path to the directory containing help documents or None
      if the CLI does not support man pages.

  Returns:
    calliope.CLILoader, An object that will run the tools from the command
        line.
  """
  paths = config.Paths()

  return calliope.CLILoader(
      name=name,
      command_root_directory=command_root_directory,
      load_context=None,
      config_file=paths.config_json_path,
      logs_dir=paths.logs_dir,
      allow_non_existing_modules=allow_non_existing_modules,
      version_func=version_func,
      help_func=GetHelp(help_dir),
  )


def GoogleCloudSDKPackageRoot():
  return config.GoogleCloudSDKPackageRoot()


def Credentials(allow_no_credentials=False):
  """Get the currently active credentials.

  This function inspects the config at CLOUDSDK_CONFIG_JSON to decide which of
  the credentials available in CLOUDSDK_CONFIG_CREDENTIALS to return.

  These credentials will be refreshed before being returned, so it makes sense
  to cache the value returned for short-lived programs.

  Args:
    allow_no_credentials: bool, If false, a NoAuthException will be thrown if
        there are no valid credentials. If true, None will be returned instead.

  Returns:
    An active, valid credentials object. Or None if allow_no_credentials is true
    and no credentials exist.

  Raises:
    NoAuthException: If there are no currently authorized credentials.
  """
  creds = c_store.Load()

  if not creds and not allow_no_credentials:
    raise NoAuthException()

  return creds


def Http(auth=True, creds=None, timeout=None):
  """Get an httplib2.Http object for working with the Google API.

  Args:
    auth: bool, True if the http object returned should be authorized.
    creds: oauth2client.client.Credentials, If auth is True and creds is not
        None, use those credentials to authorize the httplib2.Http object.
    timeout: double, The timeout in seconds to pass to httplib2.  This is the
        socket level timeout.  If timeout is None, timeout is infinite.

  Returns:
    An authorized httplib2.Http object, or a regular httplib2.Http object if no
    credentials are available.

  """

  # TODO(user): Have retry-once-if-denied logic, to allow client tools to not
  # worry about refreshing credentials.

  http = httplib2.Http(timeout=timeout)
  if auth:
    try:
      if not creds:
        creds = Credentials()
      http = creds.authorize(http)
    except NoAuthException:
      pass
  return http
