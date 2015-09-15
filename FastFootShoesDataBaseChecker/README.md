#Fast Foot Shoes DataBase Checker
This is a simple Spring Boot application that can be used to check the most recent entry added to the Derby Database.
It assumes that the startDemo.sh script has ran with in the single-machine-mode.

##Configuration
The JDBC connection details used by the application can be found in application.properties

##Usage
From within demo/single-machine-mode run the checkDB.sh file. The output will look something like this, listing the rows in the 'write-through' DB:
```shell
/\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.2.5.RELEASE)

2015-09-14 23:38:06.516  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : Starting FastFootShoesDataBaseCheckerApplication v0.0.1-SNAPSHOT on Luke-Shannons-Macbook-Pro.local with PID 9632 (/Users/lshannon/Documents/geode-demo-application/demo/geode-server-package/app/FastFootShoesDataBaseChecker-0.0.1-SNAPSHOT.jar started by lshannon in /Users/lshannon/Documents/geode-demo-application/demo/single-machine-mode)
2015-09-14 23:38:06.576  INFO 9632 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@729e0fbe: startup date [Mon Sep 14 23:38:06 EDT 2015]; root of context hierarchy
2015-09-14 23:38:07.455  INFO 9632 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2015-09-14 23:38:07.462  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : ============================================================
2015-09-14 23:38:07.462  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : Query the write through table
2015-09-14 23:38:07.462  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : ============================================================
2015-09-14 23:38:07.620  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : ****************************************************
2015-09-14 23:38:07.620  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : Here are the results:
2015-09-14 23:38:07.620  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : ****************************************************
2015-09-14 23:38:07.620  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : Message [id=1, message=Code:Lack of Stock; ProductId:369; Quantity:6753; CustomerId: 956]
2015-09-14 23:38:07.620  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : ****************************************************
2015-09-14 23:38:07.621  INFO 9632 --- [           main] .FastFootShoesDataBaseCheckerApplication : Started FastFootShoesDataBaseCheckerApplication in 1.667 seconds (JVM running for 2.055)
2015-09-14 23:38:07.621  INFO 9632 --- [       Thread-2] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@729e0fbe: startup date [Mon Sep 14 23:38:06 EDT 2015]; root of context hierarchy
2015-09-14 23:38:07.622  INFO 9632 --- [       Thread-2] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
Luke-Shannons-Macbook-Pro:single-machine-mode lshannon$ 

```
