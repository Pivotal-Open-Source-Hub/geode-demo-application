#!/bin/bash

kill -9 `cat derby_pid.txt`
rm -f derby_pid.txt
rm -f derby.log