#!/bin/bash
# Randy May & Luke Shannon
source ./setenv.sh
gfsh -e "connect" -e "shutdown --include-locators=true"
echo $CLOSE_MESSAGE
read close_me


