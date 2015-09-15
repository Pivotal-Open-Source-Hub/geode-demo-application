#!/bin/bash

export DERBY_INSTALL=../geode-server-package/derby-server/db-derby-10.11.1.1-bin
export CLASSPATH=$DERBY_INSTALL/lib/derbytools.jar:$DERBY_INSTALL/lib/derbynet.jar:.

java -jar $DERBY_INSTALL/lib/derbyrun.jar server start > derby.log 2>&1&
echo "Derby Started"
