#!/bin/bash
# Luke Shannon
# Source this script to set Java home, the path will need to be updated accordingly
export JAVA_HOME=$(/usr/libexec/java_home -v 1.7)
echo "Java home is set to: $JAVA_HOME"