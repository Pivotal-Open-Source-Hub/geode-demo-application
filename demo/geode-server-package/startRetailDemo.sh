#!/bin/sh
cd $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
nohup java -jar ./app/FastFootShoesRetail-1.2.2.RELEASE.jar --server.port=8282 > FastFootShoesRetail.log 2>&1&
echo $! > FastFootShoesRetail_pid-new.txt