#!/bin/bash
#Luke Shannon
echo "Ensure that startDerby.sh has been ran in a seperate window before running the demo"
echo "Is derby running? (type Y to confirm):"
read answer
if [ "$answer" == "Y" ];
then
	echo "Starting Cluster"
	../geode-server-package/startCluster.sh
	echo "Loading Historic Data"
	java -jar ./client-apps/FastFootShoesHistoricDataLoader-0.0.1-SNAPSHOT.jar
	echo "Starting Demo Application - logs files will be written into this directory"
	./startApp.sh
	echo "Demo Start Up Complete. Press Any Key To Close This"
	read close_me
else
	echo "Exiting"
fi
