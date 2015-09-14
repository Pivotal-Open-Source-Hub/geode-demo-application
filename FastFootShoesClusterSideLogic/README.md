# Cluster Side Logic
This project contains all the Java logic that is deployed to Geode cluster. This logic will be executed with in the Geode cluster.

To get an idea of how each component works please refer to the test cases.

The project is broken into the following main packages
## Functions
OrderCounter - This function counts the number of transactions a user has made within a calendar year. However this count is increased by +1 when transactions occur on the client's birthday, or when there are more than 3 transactions in a day. This function is called when viewing a client profile and when they place an order (this score is used to determine their retail cost)

ProductGroupCounter - This counts the number of products by Brand. It is called by the application dashboard.

### Functions Execution
Spring Data Gemfire Interfaces are used to execute functions. The org.apache.geode.demo.fastfootshoes.clusterside.test.TestFunctions class demonstrates how this can be called and configured on both the server and client.

## Listeners
## Util
## Writer