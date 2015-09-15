#FastFoot Shoes Historic Data Loader
This project connects to the running demo cluster as a client and inputs historic data into the cluster. This flow of data results in real time up dates to the dashboard of the application.

## Usage
To run this application the cluster must be running. With the cluster running in one terminal session, in a new session run the demo/single-machine-mode/startTransactionSimulator.sh. The output will look something like this.

```shell
[info 2015/09/14 23:50:27.520 EDT  <main> tid=0x9] Adding: PdxType[
      dsid=0typenum=1, name=org.apache.geode.demo.fastfootshoes.model.Customer, fields=[
          birthday:Date:0:idx0(relativeOffset)=0:idx1(vlfOffsetIndex)=0
          name:String:1:idx0(relativeOffset)=8:idx1(vlfOffsetIndex)=-1
          emailAddress:String:2:1:idx0(relativeOffset)=0:idx1(vlfOffsetIndex)=1
          city:String:3:2:idx0(relativeOffset)=0:idx1(vlfOffsetIndex)=2
          id:String:4:3:idx0(relativeOffset)=0:idx1(vlfOffsetIndex)=3]]

[info 2015/09/14 23:50:27.521 EDT  <main> tid=0x9] Auto serializer generating type for class org.apache.geode.demo.fastfootshoes.model.Customer for fields: 
      birthday: private java.util.Date org.apache.geode.demo.fastfootshoes.model.Customer.birthday
      name: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Customer.name
      emailAddress: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Customer.emailAddress
      city: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Customer.city
      id: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Customer.id
  

[info 2015/09/14 23:50:27.632 EDT  <main> tid=0x9] Auto serializer generating type for class org.apache.geode.demo.fastfootshoes.model.Transaction for fields: 
      transactionDate: private java.util.Date org.apache.geode.demo.fastfootshoes.model.Transaction.transactionDate
      quantity: private int org.apache.geode.demo.fastfootshoes.model.Transaction.quantity
      retailPrice: private double org.apache.geode.demo.fastfootshoes.model.Transaction.retailPrice
      markUp: private double org.apache.geode.demo.fastfootshoes.model.Transaction.markUp
      customerId: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Transaction.customerId
      productId: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Transaction.productId
      orderStatus: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Transaction.orderStatus
      id: private java.lang.String org.apache.geode.demo.fastfootshoes.model.Transaction.id
  

[info 2015/09/14 23:50:27.634 EDT  <main> tid=0x9] Defining: PdxType[
      dsid=0typenum=3, name=org.apache.geode.demo.fastfootshoes.model.Transaction, fields=[
          transactionDate:Date:0:idx0(relativeOffset)=0:idx1(vlfOffsetIndex)=0
          quantity:int:1:idx0(relativeOffset)=8:idx1(vlfOffsetIndex)=0
          retailPrice:double:2:idx0(relativeOffset)=12:idx1(vlfOffsetIndex)=0
          markUp:double:3:idx0(relativeOffset)=20:idx1(vlfOffsetIndex)=0
          customerId:String:4:idx0(relativeOffset)=28:idx1(vlfOffsetIndex)=-1
          productId:String:5:1:idx0(relativeOffset)=0:idx1(vlfOffsetIndex)=1
          orderStatus:String:6:2:idx0(relativeOffset)=0:idx1(vlfOffsetIndex)=2
          id:String:7:3:idx0(relativeOffset)=0:idx1(vlfOffsetIndex)=3]]
Added 25 transactions: 25 out of 100000
Added 25 transactions: 50 out of 100000
```