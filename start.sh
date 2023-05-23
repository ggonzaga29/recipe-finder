#!/bin/bash

WRAPPER_PATH="./.mvn/wrapper"

if [ ! -f "$WRAPPER_PATH/maven-wrapper.jar" ]; then
    echo "Maven wrapper not found. Please make sure the Maven wrapper is set up in your project."
    exit 1
fi

mvn package
./mvnw exec:java -Dexec.mainClass=com.recipeFinder.Main
