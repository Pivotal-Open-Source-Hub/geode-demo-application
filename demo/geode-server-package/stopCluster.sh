#!/bin/bash
# Randy May & Luke Shannon
cd $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source setenv.sh
gfsh -e "connect" -e "shutdown --include-locators=true"
echo $CLOSE_MESSAGE
read close_me