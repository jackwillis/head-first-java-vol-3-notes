#!/usr/bin/env bash

if [[ "$@" == *"-h"* ]]; then
    echo "Usage: $0"
    echo "Builds the project."
    exit 0
fi

$(dirname $0)/clean.sh

set -x

# Compile
javac -d build/classes/ src/quizcardbuilder/*.java

# Build jar

cd build/classes/
jar cvfe ../QuizCardBuilder.jar quizcardbuilder.QuizCardBuilder quizcardbuilder/*.class
cd -
