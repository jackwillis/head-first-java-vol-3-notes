#!/usr/bin/env bash

if [[ "$@" == *"-h"* ]]; then
    echo "Usage: $0"
    echo "Cleans the project."
    exit 0
fi

set -x

rm -rf build/
mkdir build/ build/classes/