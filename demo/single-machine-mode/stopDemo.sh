#!/bin/bash

cd $(dirname $0)

echo "Stopping Derby DB"
../geode-server-package/stopDerby.sh

echo "Stopping the Cluster"
../geode-server-package/stopCluster.sh

echo "Deleting the Derby DB files"
rm -fr fastfootshoes

echo "Shut Down Completed"
