#!/bin/bash
# Randy May & Luke Shannon
cd $(dirname $0)
source setenv.sh
if [ ! -d "$SERVER_DIR_LOCATION/$LOCATOR_1" ]; then
	mkdir $SERVER_DIR_LOCATION/$LOCATOR_1
fi
if [ ! -d "$SERVER_DIR_LOCATION/$LOCATOR_2" ]; then
	mkdir $SERVER_DIR_LOCATION/$LOCATOR_2
fi

echo "Starting Up: Locator A, Locator B and Server A, Server B, Server C and Server D with the FastFoot Shoes Grid Configuration"
gfsh start locator --name=$LOCATOR_1 \
   --enable-cluster-configuration=false  \
  --dir=$SERVER_DIR_LOCATION/$LOCATOR_1 \
  --port=$LOCATOR_PORT_1 \
  --log-level=config \
  --J=-Xms256m \
  --J=-Xmx256m \
  --J=-Dcom.sun.management.jmxremote \
  --J=-Dcom.sun.management.jmxremote.port=15666 \
  --J=-Dcom.sun.management.jmxremote.ssl=false \
  --J=-Dcom.sun.management.jmxremote.authenticate=false \
  --J=-Dcom.sun.management.jmxremote.local.only=false

gfsh start locator \
   --name=$LOCATOR_2 \
   --enable-cluster-configuration=false  \
   --dir=$SERVER_DIR_LOCATION/$LOCATOR_2 \
   --locators=$LOCATOR_IP[$LOCATOR_PORT_1] \
   --port=$LOCATOR_PORT_2 \
   --log-level=config \
   --J=-Xms256m \
   --J=-Xmx256m \
   --J=-Dcom.sun.management.jmxremote \
   --J=-Dcom.sun.management.jmxremote.port=15667 \
   --J=-Dcom.sun.management.jmxremote.ssl=false \
   --J=-Dcom.sun.management.jmxremote.authenticate=false \
   --J=-Dcom.sun.management.jmxremote.local.only=false

for N in 1 2 3 4 ; do

   if [[ $N == "1" ]] ; then
      NAME="serverA"
   elif [[ $N == "2" ]] ; then
     NAME="serverB"
  elif [[ $N == "3" ]]; then
     NAME="serverC"
   elif [[ $N == "4" ]]; then
     NAME="serverD"
   fi

   if [ ! -d $SERVER_DIR_LOCATION/$NAME ]; then
	mkdir $SERVER_DIR_LOCATION/$NAME
   fi

   HTTP_PORT=$(( 8080 + $N ))
   
echo "Starting the server with config $CONF_DIR/fastfootshoes-spring-cache.xml"

gfsh start server \
   --name=$NAME \
   --use-cluster-configuration=false \
   --classpath=$CLASSPATH \
   --server-port=0 \
   --dir=$SERVER_DIR_LOCATION/$NAME \
   --locators=$LOCATOR_IP[$LOCATOR_PORT_1],$LOCATOR_IP[$LOCATOR_PORT_2] \
   --J=-Dgemfire.http-service-port=$HTTP_PORT \
   --J=-Dgemfire.start-dev-rest-api=true \
   --J=-Xms256m \
   --J=-Xmx256m \
   --properties-file=$CONF_DIR/gemfire.properties \
   --spring-xml-location=file:///$CONF_DIR/fastfootshoes-spring-cache.xml

done

gfsh -e connect -e "list members"
