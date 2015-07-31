#!/bin/bash
cd $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source setenv.sh
echo "Starting Server A Back Up";
gfsh start server \
   --name=serverA \
   --use-cluster-configuration=false \
   --classpath=$CLASSPATH \
   --server-port=0 \
   --dir=$SERVER_DIR_LOCATION/serverA \
   --locators=$LOCATOR_IP[$LOCATOR_PORT_1],$LOCATOR_IP[$LOCATOR_PORT_2] \
   --J=-Dgemfire.http-service-port=8001 \
   --J=-Dgemfire.start-dev-rest-api=true \
   --properties-file=$CONF_DIR/gemfire.properties \
   --rebalance \
   --spring-xml-location=file:///$CONF_DIR/fastfootshoes-spring-cache.xml
echo $CLOSE_MESSAGE
read close_me


