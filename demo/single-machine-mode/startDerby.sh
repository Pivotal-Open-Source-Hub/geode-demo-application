#! /bin/bash

export WORKING_DIRECTORY=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
export DERBY_INSTALL=$WORKING_DIRECTORY/derby-server/db-derby-10.11.1.1-bin
export CLASSPATH=$DERBY_INSTALL/lib/derbytools.jar:$DERBY_INSTALL/lib/derbynet.jar:.

java -jar $DERBY_INSTALL/lib/derbyrun.jar server start

# $DERBY_INSTALL/bin/ij
# ij> connect 'jdbc:derby://localhost:1527/MyDbTest;create=true';
