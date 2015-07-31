#!/bin/bash
# Luke Shannon #
# Set the GemFire environment variables to be able to use GFSH #

if [ -n "$JAVA_HOME" ]; then
	export WORKING_DIRECTORY=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
	export GEMFIRE=$WORKING_DIRECTORY/geode-1.0.0.0-SNAPSHOT
	export CONF_DIR=$WORKING_DIRECTORY/conf
	export APP_DIR=$WORKING_DIRECTORY/app
	export LIB_DIR=$WORKING_DIRECTORY/lib
	export SERVER_DIR_LOCATION=$WORKING_DIRECTORY/
	export CLOSE_MESSAGE="Press any key to close this window..."
	export LOCATOR_1="locatorA";
	export LOCATOR_2="locatorB";
	export LOCATOR_IP=127.0.0.1
	export LOCATOR_PORT_1=10334
	export LOCATOR_PORT_2=10335
	export PATH=$PATH:$JAVA_HOME/bin:$GEMFIRE/bin
	export CLASSPATH=$CLASSPATH:$LIB_DIR/*
else
	echo "This requires the Java JDK and JAVA_HOME to be set"
	echo "Geode currently supports: http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html"
	echo "If you already have the JDK, there is a setJDK.sh script that can be used to set JAVA_HOME. Run 'source ./scripts/setJDK.sh'"
	echo "Press any key to terminate this script"
	read anykey
	exit 1
fi

