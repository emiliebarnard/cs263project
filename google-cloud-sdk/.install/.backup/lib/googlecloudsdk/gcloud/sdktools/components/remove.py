# Copyright 2013 Google Inc. All Rights Reserved.

"""The command to remove gcloud components."""

import argparse
import textwrap

from googlecloudsdk.calliope import base


class Remove(base.Command):
  """Remove one or more installed components.

  Uninstall all listed components, as well as all components that directly or
  indirectly depend on them.
  """

  detailed_help = {
      'DESCRIPTION': textwrap.dedent("""\
          Uninstall all listed components, as well as all components that
          directly or indirectly depend on them. For example, if the component
          `unicorn-factory` depends on the component `horn-factory`, removing
          `horn-factory` will cause `unicorn-factory` to be removed as well.
          The command lists all components it is about to remove, and asks for
          confirmation before proceeding.
      """),
      'EXAMPLES': textwrap.dedent("""\
          To remove `component-1`, `component-2`, and all components that
          directly or indirectly depend on `component-1` or  `component-2`,
          type the following:
  
            $ gcloud components remove component-1 component-2
      """),
  }

  @staticmethod
  def Args(parser):
    parser.add_argument(
        'component_ids',
        metavar='COMPONENT-ID',
        nargs='+',
        help='The IDs of the components to be removed.')
    parser.add_argument(
        '--allow-no-backup',
        required=False,
        action='store_true',
        help=argparse.SUPPRESS)

  def Run(self, args):
    """Runs the list command."""
    self.group.update_manager.Remove(
        args.component_ids, allow_no_backup=args.allow_no_backup)
