#!/bin/bash

PROCESS_NAME="FlavorFinder"
WRAPPER_PATH="./.mvn/wrapper"

if [ ! -f "$WRAPPER_PATH/maven-wrapper.jar" ]; then
    echo "Maven wrapper not found. Please make sure the Maven wrapper is set up in your project."
    exit 1
fi

./mvnw exec:java -Dexec.mainClass=com.recipeFinder.Main
