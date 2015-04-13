/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.clusterside.functions;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.geode.demo.fastfootshoes.clusterside.util.ReferenceHelper;
import org.apache.geode.demo.fastfootshoes.model.Customer;
import org.apache.geode.demo.fastfootshoes.model.Transaction;
import org.apache.geode.demo.fastfootshoes.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.function.annotation.GemfireFunction;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.pdx.internal.PdxInstanceImpl;

/**
 * 
 * This is a Geode function that runs in the Cluster to count Transactions.
 * The count is incremented for:
 * +1 all transactions this year
 * +1 if the transaction is on the Customers birthday
 * +1 if a day has 3 or more transactions in it
 * 
 * @author lshannon
 *
 */
@Component
public class OrderCounter {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	private Calendar now = Calendar.getInstance();
	private Calendar txn_date = Calendar.getInstance();
	
	@GemfireFunction
	public int countTransactions(Object customerObj) {
		Customer customer = resolveReferenceCust(customerObj);
		//System.out.println("Counting orders for: " + customer.toString());
		Collection<Transaction> completedTransactions = transactionRepository.findCompletedOrders(customer.getId());
		//System.out.println("Completed Transactions: " + completedTransactions.size());
		Map<Integer, AtomicInteger> dayOfTheYearCount = new HashMap<Integer,AtomicInteger>();
		int count = 0;
		//go through the orders and increment the count
		for (Object txnObj : completedTransactions) {
			Transaction txn = resolveReferenceTxn(txnObj);
			//did it occur this year
			now.setTime(new Date());
			txn_date.setTime(txn.getTransactionDate());
			if (now.get(Calendar.YEAR) == txn_date.get(Calendar.YEAR)) {
				//System.out.println("Transaction Id: " + txn.getId() + " Transaction Date: " + txn.getTransactionDate());
				//System.out.println("Less than a year increment: " + count);
				count++;
				//if its there birthday they get an extra count per order
				if (isBirthday(txn.getTransactionDate(),customer.getBirthday())) {
					//System.out.println("Birthday add one: " + count);
					count++;
				}
				int currentDayOfYear = getDayOfYear(txn.getTransactionDate());
				//build a map of transactions by day of the year
				if (dayOfTheYearCount.containsKey(currentDayOfYear)) {
					dayOfTheYearCount.get(currentDayOfYear).incrementAndGet();
				}
				else {
					dayOfTheYearCount.put(currentDayOfYear, new AtomicInteger());
				}
			}
			
		}
		//go through the map, for days with 5 or more transactions add an extra count
		for (Integer day : dayOfTheYearCount.keySet()) {
			if (dayOfTheYearCount.get(day).intValue() >= 3) {
				//System.out.println("Three on a day - add one: " + count);
				count++;
			}
		}
		return count;
	}

	public TransactionRepository getTransactionRepository() {
		return transactionRepository;
	}

	public void setTransactionRepository(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	/*
	 * Used to determine if the date is a client's birthday
	 */
	private boolean isBirthday(Date orderDate, Date customerDOB) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(orderDate);
		cal2.setTime(customerDOB);
		return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}
	
	/*
	 * Given a date this will determine the year
	 */
	private int getDayOfYear(Date orderDate) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(orderDate);
		return cal1.get(Calendar.DAY_OF_YEAR);
	}
	
	/*
	 * If Read Serialized is set, the cluster will return PdxInstanceImpl.
	 * Need to convert it
	 */
	private Transaction resolveReferenceTxn(Object obj) {
		if (obj instanceof PdxInstanceImpl) {
			return ReferenceHelper.toObject(obj, Transaction.class);
		}
		else {
			return (Transaction)obj;
		}
	}
	
	/*
	 * If Read Serialized is set, the cluster will return PdxInstanceImpl.
	 * Need to convert it
	 */
	private Customer resolveReferenceCust(Object obj) {
		if (obj instanceof PdxInstanceImpl) {
			return ReferenceHelper.toObject(obj, Customer.class);
		}
		else {
			return (Customer)obj;
		}
	}
	
}
