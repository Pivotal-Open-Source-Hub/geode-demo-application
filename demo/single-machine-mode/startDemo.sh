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
#	echo "Starting Demo Application"
#	java -jar ./client-apps/FastFootShoesRetail-0.0.1-RELEASE.jar
	echo $CLOSE_MESSAGE
	read close_me
else
	echo "Exiting"
fi
