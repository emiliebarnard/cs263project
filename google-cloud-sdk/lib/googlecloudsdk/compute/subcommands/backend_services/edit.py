# Copyright 2014 Google Inc. All Rights Reserved.
"""Command for modifying backend services."""

from googlecloudsdk.compute.lib import base_classes


class Edit(base_classes.BaseEdit):
  """Modify backend services."""

  @staticmethod
  def Args(parser):
    base_classes.BaseEdit.Args(parser)
    parser.add_argument(
        'name',
        help='The name of the backend service to modify.')

  @property
  def service(self):
    return self.compute.backendServices

  @property
  def resource_type(self):
    return 'backendServices'

  @property
  def example_resource(self):
    uri_prefix = ('https://www.googleapis.com/compute/v1/projects/'
                  'my-project/')
    resource_views_uri_prefix = (
        'https://www.googleapis.com/resourceviews/v1beta1/projects/'
        'my-project/zones/')

    return self.messages.BackendService(
        backends=[
            self.messages.Backend(
                balancingMode=(
                    self.messages.Backend.BalancingModeValueValuesEnum.RATE),
                group=(
                    resource_views_uri_prefix +
                    'us-central1-a/resourceViews/group-1'),
                maxRate=100),
            self.messages.Backend(
                balancingMode=(
                    self.messages.Backend.BalancingModeValueValuesEnum.RATE),
                group=(
                    resource_views_uri_prefix +
                    'europe-west1-a/resourceViews/group-2'),
                maxRate=150),
        ],
        description='My backend service',
        healthChecks=[
            uri_prefix + 'global/httpHealthChecks/my-health-check'
        ],
        name='backend-service',
        port=80,
        portName='http',
        selfLink=uri_prefix + 'global/backendServices/backend-service',
        timeoutSec=30,
    )

  def CreateReference(self, args):
    return self.CreateGlobalReference(args.name)

  @property
  def reference_normalizers(self):
    def NormalizeHttpHealthCheck(value):
      return self.CreateGlobalReference(
          value, resource_type='httpHealthChecks').SelfLink()

    def NormalizeGroup(value):
      return self.CreateGlobalReference(
          value, resource_type='zoneViews').SelfLink()

    return [
        ('healthChecks[]', NormalizeHttpHealthCheck),
        ('backends[].group', NormalizeGroup),
    ]

  def GetGetRequest(self, args):
    return (
        self.service,
        'Get',
        self.messages.ComputeBackendServicesGetRequest(
            project=self.project,
            backendService=self.ref.Name()))

  def GetSetRequest(self, args, replacement, _):
    return (
        self.service,
        'Update',
        self.messages.ComputeBackendServicesUpdateRequest(
            project=self.project,
            backendService=self.ref.Name(),
            backendServiceResource=replacement))


Edit.detailed_help = {
    'brief': 'Modify backend services',
    'DESCRIPTION': """\
        *{command}* can be used to modify a backend service. The backend
        service resource is fetched from the server and presented in a text
        editor. After the file is saved and closed, this command will
        update the resource. Only fields that can be modified are
        displayed in the editor.

        The editor used to modify the resource is chosen by inspecting
        the ``EDITOR'' environment variable.
        """,
}
