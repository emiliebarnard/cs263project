# Copyright 2013 Google Inc. All Rights Reserved.

"""The super-group for the cloud CLI."""

import argparse
import os
import textwrap

from googlecloudsdk.calliope import base
from googlecloudsdk.core import properties


class Gcloud(base.Group):
  """Google Cloud Platform CLI/API.

  Manage Google Cloud Platform resources and your cloud developer workflow.
  """

  @staticmethod
  def Args(parser):
    parser.add_argument(
        '--project',
        help='Google Cloud Platform project to use for this invocation.')
    # Must have a None default so properties are not always overridden when the
    # arg is not provided.
    quiet_arg = parser.add_argument(
        '--quiet',
        '-q',
        action='store_true',
        default=None,
        help='Disable all interactive prompts.')
    quiet_arg.detailed_help = """\
Disable all interactive prompts when running gcloud commands. If input is
required, defaults will be used, or an error will be raised.
"""

Gcloud.detailed_help = {
    'brief': 'manage Google Cloud Platform resources',
    'DESCRIPTION': textwrap.dedent("""\
        The 'gcloud' CLI manages authentication and local configuration, as
        well as your interaction with the Google Cloud Platform APIs.
        """)
}
