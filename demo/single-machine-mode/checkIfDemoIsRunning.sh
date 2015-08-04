#!/bin/bash
echo "Locators Running:"
echo `ps -ef | grep LocatorLauncher | grep -v grep | awk '{print $2}'`
echo "Servers Running:"
echo `ps -ef | grep ServerLauncher | grep -v grep | awk '{print $2}'`
echo "Would you like to kill these processes? ('Y' to kill)"
read answer
if [ "$answer" == 'Y' ];
then
 ps -ef | grep LocatorLauncher | grep -v grep | awk '{print $2}' | xargs kill -9
 echo "Locators have been killed"
 ps -ef | grep ServerLauncher | grep -v grep | awk '{print $2}' | xargs kill -9
 echo "Servers have been killed"
fi

