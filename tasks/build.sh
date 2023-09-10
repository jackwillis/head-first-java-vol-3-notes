#!/usr/bin/env bash

if [[ "$@" == *"-h"* ]]; then
    echo "Usage: $0"
    echo "Builds the project."
    exit 0
fi

$(dirname $0)/clean.sh

set -x

javac -d build/classes/ src/**/*.java