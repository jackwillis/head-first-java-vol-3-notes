#!/usr/bin/env bash

if [[ "$@" == *"-h"* ]]; then
    echo "Usage: $0"
    echo "Runs the project."
    exit 0
fi

set -x

java -cp build/classes/ quizcardbuilder/QuizCardBuilder