#!/bin/sh

cd $(dirname $0)
java -jar app/FastFootShoesTransactionSimulator-0.0.1-SNAPSHOT.jar --server.port=8383
