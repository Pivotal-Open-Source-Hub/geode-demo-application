# geode-demo-application
This is an example of:
* Starting and managing a Spring Configured Geode Cluster
* Application(s) containing a Geode client that use Geode as a data store or leverage its compute capabilities

There are two ways to run this demo:

1. Single Node Stand Alone Mode
2. Geode Cluster Running On AWS

## Single Node Stand-Alone Mode

### Introduction
With this option all artifacts can be downloaded to run a Geode cluster as well as the sample application on Mac OSX or Linux machine. There is no support for Windows at this time.

### Prerequisites
1. All scripts to run the samples are written in Bash, as a result the target machine needs to be Mac OSX or Linux
2. Java JDK 1.7+ (Oracle is recommended) needs to be installed on the machine, and the JAVA_HOME variable should be set
3. Port 8080 is free for the demo application to run on (if port 8080 is not free the application can be configured to use another port, however its default in 8080)

### Set Up
1. Download and uncompress the project zip geode-demo-application-master.zip
2. `cd` into the directory geode-demo-application-master/demo/single-machine-mode
3. All scripts to run the demo and any dependencies are found in this folder
```shell
geode-demo-application-master$ cd demo/single-machine-mode
single-machine-mode$ ls
checkIfDemoIsRunning.sh client-apps   fastfootshoes   startDemo.sh    startTransactions.sh  stopRetailApp.sh
cleanUp.sh    derby-server    startApp.sh   startDerby.sh   stopDemo.sh
single-machine-mode$
```
4. Ensure all *.sh scripts are executable
```shell
single-machine-mode$ chmod +x *.sh
```
To run the demo you will need 2 terminal sessions open. These will be for

1. Running the Derby database. The cluster will use this for Write Through operations
2. Running the Geode Cluster, Demo Application and Transaction Simulator

### Starting The Cluster
1. If JAVA_HOME has not been set the `setJDK.sh` script can be run. It will look for a JDK version 1.7 to add to JAVA_HOME. It will look in the /usr/libexec/java_home folder. If JAVA_HOME is already set, this step can be skipped.
2. Execute ./startDemo.sh which will
  * Start Derby DB
  * Start a Geode cluster with 2 locators and 4 servers, on 127.0.0.1
  * Start a Spring Boot application that will connect to the cluster in step 1, and generate historic data

The follow messages will appear in a successful start (log output shortened for brevity):

This first part will show the members starting up and their classpaths
```shell
single-machine-mode$ ./startDemo.sh
Ensure that startDerby.sh has been run in a seperate window before running the demo
Is derby running? (type Y to confirm):
Y
Starting Cluster
Starting Up: Locator A, Locator B and Server A, Server B, Server C and Server D with the FastFoot Shoes Grid Configuration
.............................
Locator in /Users/lshannon/Downloads/geode-demo-application-master/demo/geode-server-package/locatorA on 127.0.0.1[10334] as locatorA is currently online.
Process ID: 22092
Uptime: 15 seconds
GemFire Version: 1.0.0.0-SNAPSHOT
Java Version: 1.7.0_71
Log File: /Users/lshannon/Downloads/geode-demo-application-master/demo/geode-server-package/locatorA/locatorA.log
JVM Arguments: -Dgemfire.enable-cluster-configuration=false -Dgemfire.load-cluster-configuration-from-dir=false -Dgemfire.log-level=config -Xms256m -Xmx256m -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=15666 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.local.only=false -Dgemfire.launcher.registerSignalHandlers=true -Djava.awt.headless=true -Dsun.rmi.dgc.server.gcInterval=9223372036854775806
Class-Path: /Users/lshannon/Downloads/geode-demo-application-master/demo/geode-server-package/geode-1.0.0.0-SNAPSHOT/lib/gemfire-core-1.0.0.0-SNAPSHOT.jar:/Users/lshannon/Downloads/geode-demo-application-master/demo/geode-server-package/geode-1.0.0.0-SNAPSHOT/lib/gemfire-core-dependencies.jar

Successfully connected to: [host=10.74.19.156, port=1099]

... more messages like this as the rest of the members start up...

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
The final message is a summary of the data that was loaded.

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
**********Loading Completed**********
**********Press Enter To Exit**********
```
Press enter to exit.

3. Execute `startTransactionSimulator.sh` to begin simulating transactions against the cluster.
```
single-machine-mode$ ./startTransactionSimulator.sh
Starting Transactions
...
```

### Starting the Demo Application

1. Run the `startClientApp.sh` script in another terminal window
2. This will start a Spring Boot Application that connects to the cluster and a client and demonstrates some of the functionality of Geode

The final message in the window should show that the application has started and is listening on port 8080
```shell
HandlerMapping     : Mapped "{[/dump],methods=[GET],params=[],headers=[],consumes=[],produces=[],custom=[]}" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()
2015-07-29 11:47:26.914  INFO 68731 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2015-07-29 11:47:26.921  INFO 68731 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
2015-07-29 11:47:27.062  INFO 68731 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2015-07-29 11:47:27.064  INFO 68731 --- [           main] o.a.g.d.f.application.Application        : Started Application in 5.246 seconds (JVM running for 5.677)

```
The application should now be started and can be tested by loading http://127.0.0.1:8080 in a browser.
![demo application](/images/demo-application-home.png)

### Starting the Cluster and Client App Manually

#### Cluster 
```shell
geode-demo-application-master$ cd demo/geode-server-package
geode-server-package$ ./startCluster.sh 127.0.0.1
```
The parameter is the IP address of the locators.

#### Historic Data (Initializing inventory data)
```shell
geode-demo-application-master$ cd demo/geode-server-package/app
app$ java -jar FastFootShoesHistoricDataLoader-0.0.1-SNAPSHOT.jar 
````

#### Client app
```shell
geode-demo-application-master$ cd demo/geode-server-package/app
app$ java -jar FastFootShoesRetail-0.0.1-RELEASE.jar --cluster.location=127.0.0.1 [--server.port=8080]
```
Use the same IP address for the `cluster.location` as when starting the cluster.

#### Transactions
```shell
geode-demo-application-master$ cd demo/geode-server-package/app
app$ java -jar FastFootShoesTransactionSimulator-0.0.1-SNAPSHOT.jar --server.port=8383
```

### Shutdown
```
single-machine-mode$ ./stopDemo.sh
Stopping Derby DB
Stopping the Cluster

(1) Executing - connect

Connecting to Locator at [host=localhost, port=10334] ..
Connecting to Manager at [host=dx141.tor.pivotallabs.com, port=1099] ..
Successfully connected to: [host=dx141.tor.pivotallabs.com, port=1099]


(2) Executing - shutdown --include-locators=true

Shutdown is triggered

Press any key to close this window...
```

### Non-Graceful Full Teardown
If a situation arises were processes and running and the shutdown script is not working, run the interactiveShutdown.sh script. If there are Locators and Servers running it will list the PID(s) and offer a prompt to kill these processes.

```shell
single-machine-mode$ ./interactiveShutdown.sh
Locators Running:
22092 22108
Servers Running:
22121 22134 22147 22160
Would you like to kill these processes? ('Y' to kill)
Y
Locators have been killed
Servers have been killed
single-machine-mode$ 
```
Entering a 'Y' at the prompt will kill all these processes.


## Geode Cluster Running On AWS

This demonstration uses two Amazon Linux EC2 instances. One acts as the client, and one acts as the Geode cluster. Start by creating two high-memory instances in the AWS console.

### Instance Setup

In order for this demo to run correctly, specific ports need to be opened on at least the cluster instance. Open the following port range within the security group settings for the instance(s):
```
Protocol: TCP  Range: 1024-65535
Protocol: TCP  Port: 22
```

For the client app instance, the following ports are sufficient:
```
Protocol: TCP  Port: 22
Protocol: TCP  Port: 80
```

### Using provided AMIs

There are two AMIs provided to assist with running the demo on two EC2 instances:
* Client:
* Cluster: 


### Using the distribution package

1. Upload the `geode-server-package` to a suitable high-memory EC2 instance
```shell
$ cd demo
$ scp -r -i <certificate.pem> geode-server-package ec2-user@<instance location>:
```

2. Upload the client app on the other instance
```shell
$ scp -i <certificate.pem> geode-server-package/app/FastFootShoesRetail-0.0.1-RELEASE.jar ec2-user@<instance location>:
```

#### On the instance designated to be the cluster
```shell
$ cd ~/geode-server-package
$ ./startDerby
$ ./startCluster <public DNS for EC2 instance eg. ec2-xxx-xxx-xxx-xxx.compute-1.amazonaws.com>
...
$ ./addHistoricData.sh ... <enter>
$ ./startTransactionSimulator.sh
```
The log file for the transaction simulator is written in the same directory.

##### Shut down the cluster
```
$ ./stopCluster.sh
```


#### On the instance designated to be the client
```shell
$ sudo java -jar FastFootShoesRetail-0.0.1-RELEASE.jar --server.port=80 --cluster.location=<public DNS for EC2 instance above. eg. ec2-xxx-xxx-xxx-xxx.compute-1.amazonaws.com>
```
Visit the demo app in your browser via the public address of the client instance.
