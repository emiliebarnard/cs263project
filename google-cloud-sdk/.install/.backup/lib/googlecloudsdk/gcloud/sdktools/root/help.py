# Copyright 2013 Google Inc. All Rights Reserved.

"""A calliope command that calls a help function."""

from googlecloudsdk.calliope import base
from googlecloudsdk.calliope import cli as calliope
from googlecloudsdk.calliope import exceptions as c_exc
from googlecloudsdk.core import cli


class Help(base.Command):
  """Print a detailed help message.

  Provide a specific command group or subcommand, without flag arguments, to
  print detailed documentation about that command group or subcommand.
  """

  @staticmethod
  def Args(parser):
    parser.add_argument('command', nargs='*',
                        help='Command or group to get help for.')

  @c_exc.RaiseToolExceptionInsteadOf(cli.NoHelpFound)
  def Run(self, args):
    help_func = self.entry_point.HelpFunc()
    def ShowShortHelp():
      try:
        cur = self.entry_point
        for command_segment in args.command:
          cur = getattr(cur, command_segment)
          if type(cur) not in [calliope.UnboundCommandGroup, calliope.Command]:
            # They indexed into something weird, abort.
            raise AttributeError()
      except AttributeError:
        raise c_exc.ToolException(
            'Unknown command: {command}'.format(
                command='.'.join(args.command)))
      # pylint:disable=protected-access
      print cur.GetShortHelp()

    if not help_func:
      ShowShortHelp()
    else:
      try:
        help_func([self.entry_point.Name()] + (args.command or []))
      except cli.NoHelpFound:
        ShowShortHelp()
