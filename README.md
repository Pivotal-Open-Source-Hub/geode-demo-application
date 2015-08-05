# geode-demo-application
This is an example of:
* Starting and managing a Spring Configured Geode Cluster
* Application(s) containing a Geode client that use Geode as a data store or leverage its compute capabilities

There are two ways to run this demo:

1. Single Node Stand Alone Mode
2. Geode Cluster Running On AWS

## Single Node Stand Alone Mode

### Introduction
With this option all artifacts can be downloaded to run a Geode cluster as well as the sample application on Mac OSX or Linux machine. There is no support for Windows at this time

### Prerequisites
1. All scripts to run the samples are written in Bash, as a result the target machine needs to be MAC OSX or Linux
2. Java JDK (Oracle is recommended) needs to be installed on the machine 1.7+ and the JAVA_HOME variable should be set
3. Port 8080 is free for the demo application to run on (if port 8080 is not free the application can be configured to use another port, however its default in 8080)

### Set Up
1. Download the project zip geode-demo-application-master.zip
2. Uncompress the zip
3. cd into the directory geode-demo-application-master/demo
4. cd into the directory 'single-machine-mode'
5. All scripts to run the demo and any dependancies are found in this folder
```shell
piv-wifi-19-156:geode-demo-application-master lshannon$ pwd
/Users/lshannon/Downloads/geode-demo-application-master
piv-wifi-19-156:geode-demo-application-master lshannon$ cd demo/
piv-wifi-19-156:demo lshannon$ ls
build.xml		geode-server-package	single-machine-mode
piv-wifi-19-156:demo lshannon$ cd single-machine-mode/
piv-wifi-19-156:single-machine-mode lshannon$ ls
checkIfDemoIsRunning.sh	derby-server		startTransactions.sh
cleanUp.sh		startDemo.sh		stopDemo.sh
client-apps		startDerby.sh
piv-wifi-19-156:single-machine-mode lshannon$
```
Ensure all *.sh scripts are executable
```shell
piv-wifi-19-156:scripts lshannon$ chmod +x *.sh
```
To run the demo you will need 3 terminal sessions open. These will be for:
1. Running the Derby database. The cluster will use this for Write Through operations
2. Running the Geode Cluster and Demo Application
3. Starting the Transaction Simulator

### Starting the Derby DB
In one terminal window run the startDerby.sh script. This will start the Derby database listening on port 1527

```shell
Luke-Shannons-Macbook-Pro:single-machine-mode lshannon$ ./startDerby.sh 
Wed Aug 05 14:37:35 EDT 2015 : Security manager installed using the Basic server security policy.
Wed Aug 05 14:37:35 EDT 2015 : Apache Derby Network Server - 10.11.1.1 - (1616546) started and ready to accept connections on port 1527
```
The database will not be created until the Geode cluster starts up.

### Starting The Cluster
1. If JAVA_HOME has not been set the setJDK.sh script can be ran. It will look for a JDK version 1.7 to add to JAVA_HOME. It will look in the /usr/libexec/java_home folder. If JAVA_HOME is already set, this step can be skipped
2. Execute ./startCluster.sh which will
  * Start a Geode cluster with 2 locators and 4 servers
  * Start a Spring Boot application that will connect to the cluster in step 1 and put in historic data

The follow messages will appear in a successful start (log shortened for brevity):

This first part will show the members starting up and their classpaths
```shell
piv-wifi-19-156:scripts lshannon$ ./startCluster.sh
Using Java: /Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/
Starting Up: Locator A, Locator B and Server A, Server B, Server C and Server D with the FastFoot Shoes Grid Configuration
.............................
Locator in /Users/lshannon/Downloads/geode-demo-application-master/scripts/locatorA on 192.168.98.158[10334] as locatorA is currently online.
Process ID: 68544
Uptime: 15 seconds
GemFire Version: 1.0.0.0-SNAPSHOT
Java Version: 1.7.0_71
Log File: /Users/lshannon/Downloads/geode-demo-application-master/scripts/locatorA/locatorA.log
JVM Arguments: -Dgemfire.enable-cluster-configuration=false -Dgemfire.load-cluster-configuration-from-dir=false -Dgemfire.log-level=config -Xms256m -Xmx256m -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=15666 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.local.only=false -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/lshannon/Downloads/geode-demo-application-master/scripts/geode-1.0.0.0-SNAPSHOT/lib/gemfire-core-1.0.0.0-SNAPSHOT.jar:/Users/lshannon/Downloads/geode-demo-application-master/scripts/geode-1.0.0.0-SNAPSHOT/lib/gemfire-core-dependencies.jar

Successfully connected to: [host=192.168.98.158, port=1099]

```

After the members have started a message from gfsh will show the members:

```shell
(1) Executing - connect

Connecting to Locator at [host=localhost, port=10334] ..
Connecting to Manager at [host=192.168.98.158, port=1099] ..
Successfully connected to: [host=192.168.98.158, port=1099]


(2) Executing - list members

  Name   | Id
-------- | -----------------------------------------------------------
serverC  | Luke-Shannons-Macbook-Pro(serverC:68598)<v4>:6908
locatorA | Luke-Shannons-Macbook-Pro(locatorA:68544:locator)<v0>:10231
serverB  | Luke-Shannons-Macbook-Pro(serverB:68585)<v3>:29758
serverA  | Luke-Shannons-Macbook-Pro(serverA:68573)<v2>:36452
serverD  | Luke-Shannons-Macbook-Pro(serverD:68610)<v5>:39490
locatorB | Luke-Shannons-Macbook-Pro(locatorB:68561:locator)<v1>:60760
```
Next the Spring Boot application will start and connect as a cluster to the cluster to put in historic data.

```shell
Loading Historic Data

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.2.2.RELEASE)

2015-07-29 11:34:53.610  INFO 68623 --- [           main] o.a.g.d.f.dataloader.Application         : Starting Application v0.0.1-SNAPSHOT on Luke-Shannons-Macbook-Pro.local with PID 68623 (/Users/lshannon/Downloads/geode-demo-application-master/scripts/app/ShoeDataLoader-0.0.1-SNAPSHOT.jar started by lshannon in /Users/lshannon/Downloads/geode-demo-application-master/scripts)
2015-07-29 11:34:53.652  INFO 68623 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@4e4574df: startup date [Wed Jul 29 11:34:53 EDT 2015]; root of context hierarchy
2015-07-29 11:34:54.115  INFO 68623 --- [           main] o.s.b.f.xml.XmlBeanDefinitionReader      : Loading XML bean definitions from class path resource [client-cache.xml]

[info 2015/07/29 11:34:54.842 EDT  <main> tid=0x9] 
  ---------------------------------------------------------------------------
  
    Copyright (C) 1997-2014 Pivotal Software, Inc. All rights reserved. This
    product is protected by U.S. and international copyright and intellectual
    property laws. Pivotal products are covered by one or more patents listed
    at http://www.gopivotal.com/patents.  Pivotal is a registered trademark
    of trademark of GoPivotal, Inc. in the United States and/or other
    jurisdictions.  All other marks and names mentioned herein may be
    trademarks of their respective companies.
  
  ---------------------------------------------------------------------------
  ....
```

As the Boot Application loads data there will be messages that look like stack traces, these are infact messages generated as the objects are serialized into Geode and are totally normal:

```shell
Started loading the products

[info 2015/07/29 11:34:56.156 EDT  <main> tid=0x9] Auto serializer generating type for class org.apache.geode.demo.fastfootshoes.model.Product for fields: 
      stockOnHand: private int org.apache.geode.demo.fastfootshoes.model.Product.stockOnHand
      wholeSalePrice: private double org.apache.geode.demo.fastfootshoes.model.Product.wholeSalePrice
      size: private double org.apache.geode.demo.fastfootshoes.model.Product.size
      brand: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Product.brand
      name: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Product.name
      type: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Product.type
      color: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Product.color
      gender: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Product.gender
      id: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Product.id

```
The final message is a summary of the data that was loaded. Do not press Enter to close the windows as it will stop the cluster.

```shell
*************************************************************
********************DATA LOADING SUMMARY*********************
*************************************************************
Customers: Records Read: 1000 Records Added To GemFire: 1000
Products: Records Read: 1000 Records Added To GemFire: 1000
Transactions: Records Read: 10000 Records Added To GemFire: 10000
Mark Ups: Records Read: 6 Records Added To GemFire: 6
Total Loading Time: 10 seconds
*************************************************************
********************ERROR LOGS*******************************
*************************************************************
No errors were recorded
*************************************************************

Press Enter to continue.
```
Leave this terminal window open and start a new one to run the demo application from.

### Starting the Demo Application

1. In a new terminal window CD to the geode-demo-application-master/scripts folder
2. Run the ./startDemo.sh command
3. This will start a Spring Boot Application that connects to the cluster and a client and demonstrates some of the functionality of Geode

The final message should show that the application has started and is listening on port 8080
```shell
HandlerMapping     : Mapped "{[/dump],methods=[GET],params=[],headers=[],consumes=[],produces=[],custom=[]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2015-07-29 11:47:26.914  INFO 68731 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2015-07-29 11:47:26.921  INFO 68731 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
2015-07-29 11:47:27.062  INFO 68731 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2015-07-29 11:47:27.064  INFO 68731 --- [           main] o.a.g.d.f.application.Application        : Started Application in 5.246 seconds (JVM running for 5.677)

```
The application should now be started and can be tested by loading the http://127.0.0.1:8080/home url into a browser.
![demo application](/images/demo-application-home.png)

### 

### Teardown
1. Provided there are no other Java processes running, `killall java` will kill all running clusters and Spring Boot applications from this demo.

### Trouble Shooting
```shell
Error: Exception thrown by the agent : java.net.MalformedURLException: Local host name unknown: java.net.UnknownHostException: piv-wifi-19-156.pivotallabs.com: piv-wifi-19-156.pivotallabs.com: nodename nor servname provided, or not known
```

## Geode Cluster Running On AWS

## Geode Cluster Running On Docker
