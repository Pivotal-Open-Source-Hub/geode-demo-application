#!/bin/bash
#Luke Shannon
source ./setenv.sh
echo "Starting Demo Application"
java -jar $APP_DIR/FastFootShoesRetail-1.2.2.RELEASE.jar
echo $CLOSE_MESSAGE
read close_me

