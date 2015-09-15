#!/bin/bash

cd $(dirname $0)

echo "Stopping Derby DB"
../geode-server-package/stopDerby.sh

echo "Stopping the Cluster"
../geode-server-package/stopCluster.sh

echo "Deleting the Derby DB files"
rm -fr fastfootshoes

echo "Cleaning Up Working Folders"
rm -fr ../geode-server-package/locatorA
rm -fr ../geode-server-package/locatorB
rm -fr ../geode-server-package/serverA
rm -fr ../geode-server-package/serverB
rm -fr ../geode-server-package/serverC
rm -fr ../geode-server-package/serverD

echo "Shut Down & Clean Up Completed"
