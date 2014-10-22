# Copyright 2013 Google Inc. All Rights Reserved.

"""Command to use a calliope tool in an interactive python shell.
"""

import code
import os
import textwrap

from googlecloudsdk import gcloud
from googlecloudsdk.calliope import base
from googlecloudsdk.core import config
from googlecloudsdk.core import log
from googlecloudsdk.core import properties


class Interactive(base.Command):
  """Use this tool in an interactive python shell.

  Run a Python shell where the gcloud CLI is represented by a collection of
  callable Python objects. For instance, to run the command "gcloud auth login",
  call the function "gcloud.auth.login()".
  """

  def Run(self, args):
    groot = config.GoogleCloudSDKPackageRoot()
    libroot, _ = os.path.split(groot)
    libroot = os.path.abspath(libroot)
    # Make the advertized import path more robust to refactoring.
    importpath = gcloud.__name__

    # If user output is on, and it was not set in any properties at startup
    # time, then it was turned an by calliope.  We turn it off.
    output_enabled = properties.VALUES.core.user_output_enabled.GetBool()
    set_output = output_enabled and log.INITIAL_USER_OUTPUT_ENABLED is None
    if set_output:
      properties.VALUES.core.user_output_enabled.Set(False)

    try:
      code.interact(
          banner=textwrap.dedent("""\
Google Cloud SDK interactive Python mode.

To use this mode in a Python script, add the following directory to your
PYTHONPATH.
  {pythonpath}

Visit https://developers.google.com/cloud/sdk/interactive for more information.

>>> from {importpath}.gcloud import gcloud
          """.format(
              importpath=importpath,
              pythonpath=libroot)),
          local={'gcloud': self.entry_point})
    finally:
      # Put this back how it was if we changed it.
      if set_output:
        properties.VALUES.core.user_output_enabled.Set(output_enabled)
