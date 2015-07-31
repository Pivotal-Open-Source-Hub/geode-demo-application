#!/bin/sh
cd $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
kill -9 `cat FastFootShoesTransactionSimulator_pid-new.txt`
rm -f FastFootShoesTransactionSimulator.log
rm -f FastFootShoesTransactionSimulator_pid-new.txt