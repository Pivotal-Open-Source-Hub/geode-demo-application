# Cluster Side Logic
This project contains all the Java logic that is deployed to Geode cluster. This logic will be executed with in the Geode cluster. To get an idea of how each component works please refer to the test cases.

The project is broken into the following main packages:
## Functions
OrderCounter - This function counts the number of transactions a user has made within a calendar year. However this count is increased by +1 when transactions occur on the client's birthday or when there are more than 3 transactions in a day. This function is called when viewing a client profile and when they place an order (this 'count' is used to determine their retail cost)

ProductGroupCounter - This counts the number of products, grouping them by Brand or by Type. This function is called by the application dashboard.

### Functions Execution
Spring Data Gemfire Interfaces are used to execute functions. The org.apache.geode.demo.fastfootshoes.clusterside.test.TestFunctions class demonstrates how this can be called and configured on both the server and client.

## Listeners
TransactionalListener - This executes when the Transaction Region is updated. It takes the quantity of product for the transaction and then decrements stockOnHand attribute for the Product entry in the Product region (the case of if there is even enough stock to place to order is handled in the application business logic).
NOTE: This is for demonstrative purposes only. A listener is a synchronous event and poorly implemented logic can result in blocking cache functionality:
http://gemfire81.docs.pivotal.io/latest/userguide/index.html#developing/events/writing_callbacks_that_modify_the_cache.html#writing_callbacks_that_modify_the_cache
 
## Util
AlertsDerbyDAO - This is a Data Access Object for updating the Derby Data base with values obtained from the Geode Cache

ReferenceHelper - This is a convenience class for converting the PDXInstance type (which is how Gemfire/Geode stores values is serialized with PDX Serialization) to its original object type. This check should be done if 'read serialized' is enabled on the server

## Writer
AlertCacheWriter - This class uses the AlertsDerbyDAO to write each new entry to the Alerts region to the Derby Database. This is a synchronous operation