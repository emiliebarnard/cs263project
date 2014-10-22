#!/bin/sh
#
# Copyright 2013 Google Inc. All Rights Reserved.
#

export CLOUDSDK_COMPONENT_MANAGER_DISABLE_UPDATE_CHECK=1

SCRIPT_LINK=$( readlink "$0" )
WRAPPER_SCRIPT_DIR="$( cd -P "$( dirname "${SCRIPT_LINK:-$0}" )" && pwd -P )"

"${WRAPPER_SCRIPT_DIR}/gcloud" auth git-helper --ignore-unknown "$@"
