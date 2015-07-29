# geode-demo-application
This is an example of:
* Starting and managing a Spring Configured Geode Cluster
* An application containing a Geode client

There are three ways to run this demo:

1. Single Node Stand Alone Mode
2. Geode Cluster Running On AWS
3. Geode Cluster Running On Docker

## Single Node Stand Alone Mode

### Introduction
With this option the project can be downloaded and the Geode cluster as well as the demo application can be started on a single machine

### Prerequisites
1. All scripts to run the samples are written in Bash, as a result the target machine needs to be MAC OSX or Linux
2. Java JDK (Oracle is recommended) needs to be installed on the machine 1.7+ and the JAVA_HOME variable should be set

### Set Up
1. Download the project zip geode-demo-application-master.zip
2. Uncompress the zip
3. cd into the directory
4. cd into the scripts folder
5. All scripts to run the demo and any dependancies are found in this folder
```shell
piv-wifi-19-156:scripts lshannon$ pwd
/Users/lshannon/Downloads/geode-demo-application-master/scripts
piv-wifi-19-156:scripts lshannon$ ls
app			lib			startServerA.sh
clean_up.sh		setJDK.sh		stopCluster.sh
conf			setenv.sh		stopServerA.sh
geode-1.0.0.0-SNAPSHOT	startCluster.sh
gfsh.sh			startDemo.sh
piv-wifi-19-156:scripts lshannon$ 
```
### Usage

## Geode Cluster Running On AWS

## Geode Cluster Running On Docker
