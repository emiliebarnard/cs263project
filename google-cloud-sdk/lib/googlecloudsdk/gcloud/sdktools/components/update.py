# Copyright 2013 Google Inc. All Rights Reserved.

"""The command to install/update gcloud components."""

import argparse
import textwrap

from googlecloudsdk.calliope import base


class Update(base.Command):
  """Update or install one or more Cloud SDK components or packages.

  Ensure that the latest version of each specified component, and the latest
  version of all components upon which the specified components directly or
  indirectly depend, is installed on the local workstation. If the command
  includes one or more names of components or packages, the specified components
  are the named components and the components contained in the named packages;
  if the command does not name any components or packages, the specified
  components are all installed components.
  """
  detailed_help = {
      'DESCRIPTION': textwrap.dedent("""\
          Download the lastest version of each listed item, and the latest
          version of all components upon which the listed items directly or
          indirectly depend, if that version is not already installed on the
          local workstation. The items may be individual components or
          preconfigured packages. If a downloaded component was not previously
          installed, the downloaded version is installed. If an earlier version
          of the component was previously installed, that version is replaced by
          the downloaded version.

          If, for example, the component `unicorn-factory` depends on the
          component `horn-factory`, installing the latest version of
          `unicorn-factory` will cause the version of `horn-factory` upon which
          it depends to be installed as well, if it is not already installed.
          The command lists all components it is about to install, and asks for
          confirmation before proceeding.
      """),
      'EXAMPLES': textwrap.dedent("""\
          The following command ensures that the latest version is installed for
          `component-1`, `component-2`, and all components that depend, directly
          or indirectly, on either `component-1` or `component-2`:

            $ gcloud components update component-1 component-2
      """),
  }

  @staticmethod
  def Args(parser):
    parser.add_argument(
        'component_ids',
        metavar='COMPONENT-IDS',
        nargs='*',
        help='The IDs of the components to be updated or installed.')
    parser.add_argument(
        '--allow-no-backup',
        required=False,
        action='store_true',
        help=argparse.SUPPRESS)

  def Run(self, args):
    """Runs the list command."""
    self.group.update_manager.Update(
        args.component_ids, allow_no_backup=args.allow_no_backup)
