#!/bin/bash
# Randy May & Luke Shannon
source ./setenv.sh
export clean_yes='Y'
echo "This will remove all GemFire working directories (disk persisted data will be lost). Are you sure?"
echo "Type '$clean_yes' to confirm 'C' to cancel"
read input
if [ "$input" == "$clean_yes" ]; then
	pkill -f java
	rm -fr $SERVER_DIR_LOCATION/serverA
	rm -fr $SERVER_DIR_LOCATION/serverB
	rm -fr $SERVER_DIR_LOCATION/serverC
	rm -fr $SERVER_DIR_LOCATION/serverD
	rm -fr $SERVER_DIR_LOCATION/$LOCATOR_1
	rm -fr $SERVER_DIR_LOCATION/$LOCATOR_2
fi
