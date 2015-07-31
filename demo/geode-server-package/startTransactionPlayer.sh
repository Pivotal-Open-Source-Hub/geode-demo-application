#!/bin/sh
cd $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
nohup java -jar ./app/FastFootShoesTransactionSimulator-0.0.1-SNAPSHOT.jar --server.port=8383 > FastFootShoesTransactionSimulator.log 2>&1&
echo $! > FastFootShoesTransactionSimulator_pid-new.txt