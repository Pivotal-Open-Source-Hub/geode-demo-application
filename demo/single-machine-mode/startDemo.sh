#!/bin/bash
# Luke Shannon

cd $(dirname $0)

echo "Starting Derby DB"
../geode-server-package/startDerby.sh

echo "Starting Cluster"
../geode-server-package/startCluster.sh 127.0.0.1

echo "Loading Historic Data"
cd ../geode-server-package
./addHistoricData.sh
