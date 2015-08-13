#!/bin/bash
cd $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
nohup java -jar ../geode-server-package/app/FastFootShoesRetail-0.0.1-RELEASE.jar --server.port=8080 > FastFootShoesRetail.log 2>&1&
echo $! > FastFootShoesRetail_pid-new.txt