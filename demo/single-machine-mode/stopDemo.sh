#!/bin/bash
echo "Stopping the Retail Demo"
./stopDemoApp.sh
echo "Stopping the Cluster"
../geode-server-package/stopCluster.sh
echo "Shut Down Completed"
echo "To Clean Up Generated Artifacts please run './cleanUp.sh'"