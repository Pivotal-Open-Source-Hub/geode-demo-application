package org.apache.geode.demo.fastfootshoes.repositories;


import java.util.Collection;

import org.apache.geode.demo.fastfootshoes.model.Customer;
import org.apache.geode.demo.fastfootshoes.model.Transaction;
import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This class is used to do all CRUD operations into the Customer Repository
 * @author lshannon
 *
 */
public interface CustomerRepository extends GemfireRepository<Customer, String> {

	Customer findByEmailAddress(String emailAddress);

	Customer findById(String id);

	//TODO could this not be by attribute?
	@Query("SELECT * FROM /Transaction t WHERE t.customerId = $1")
	Collection<Transaction> findByCustomer(@Param("customerId") String id);

	@Query("SELECT * FROM /Customer t WHERE t.emailAddress LIKE $1")
	Collection<Customer> searchCustomers(String email);

}
