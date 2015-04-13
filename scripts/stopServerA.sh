#!/bin/bash
source ./setenv.sh
echo $STOP_SERVER_A_MESSAGE;
gfsh -e connect -e "stop server --dir=$SERVER_DIR_LOCATION/serverA"
echo $CLOSE_MESSAGE
read close_me
