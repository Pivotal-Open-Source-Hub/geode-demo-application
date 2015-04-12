package org.apache.geode.demo.fastfootshoes.repositories;

import java.util.Collection;

import org.apache.geode.demo.fastfootshoes.model.Transaction;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.Query;

/**
 * This class is used to do all CRUD operations into the Product Repository
 * @author lshannon
 *
 */
public interface TransactionRepository extends GemfireRepository<Transaction, String> {
	
	Transaction findById(String id);
	
	@Query("SELECT * FROM /Transaction t WHERE t.customerId = $1")
	Collection<Transaction> findByCustomer(String id);
	
	@Query("SELECT * FROM /Transaction t where t.orderStatus = 'open'")
	Collection<Transaction> findOpenOrders();
	
	@Query("SELECT * FROM /Transaction t where (t.orderStatus = 'open' or t.orderStatus = 'shipped') and t.customerId = $1")
	Collection<Transaction> findCompletedOrders(String id);
	
	@Query("SELECT count(*) FROM /Transaction t where t.orderStatus = 'open'")
	Integer getCount();


}
