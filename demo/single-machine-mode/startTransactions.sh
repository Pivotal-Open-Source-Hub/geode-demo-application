#!/bin/bash
#Luke Shannon
echo "Starting Transaction Demo"
java -jar ./client-apps/FastFootShoesTransactionSimulator-0.0.1-SNAPSHOT.jar
echo $CLOSE_MESSAGE
read close_me
