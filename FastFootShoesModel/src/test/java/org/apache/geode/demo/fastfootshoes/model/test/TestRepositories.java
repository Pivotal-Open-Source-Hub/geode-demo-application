package org.apache.geode.demo.fastfootshoes.model.test;

import org.apache.geode.demo.fastfootshoes.model.Alert;
import org.apache.geode.demo.fastfootshoes.model.Customer;
import org.apache.geode.demo.fastfootshoes.model.MarkUp;
import org.apache.geode.demo.fastfootshoes.model.Product;
import org.apache.geode.demo.fastfootshoes.model.Transaction;
import org.apache.geode.demo.fastfootshoes.repositories.AlertRepository;
import org.apache.geode.demo.fastfootshoes.repositories.CustomerRepository;
import org.apache.geode.demo.fastfootshoes.repositories.MarkUpRepository;
import org.apache.geode.demo.fastfootshoes.repositories.ProductRepository;
import org.apache.geode.demo.fastfootshoes.repositories.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/cache-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRepositories {

	@Autowired
	private AlertRepository alertRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MarkUpRepository markUpRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	private String key = "1";
	
	@Test
	public void testAlertRepo() {
		Alert alert = new Alert();
		alert.setId(key);
		alertRepository.save(alert);
		assert(alertRepository.count() == 1);
		assert(alertRepository.findOne(key).equals(alert));
	}
	
	@Test
	public void testCustomerRepo() {
		Customer customer = new Customer();
		customer.setId(key);
		customerRepository.save(customer);
		assert(customerRepository.count() == 1);
		assert(customerRepository.findOne(key).equals(customer));
	}
	
	@Test
	public void testMarkUpRepo() {
		MarkUp markUp = new MarkUp();
		markUp.setId(key);
		markUpRepository.save(markUp);
		assert(markUpRepository.count() == 1);
		assert(markUpRepository.findOne(key).equals(markUp));
	}
	
	@Test
	public void testProductRepo() {
		Product product = new Product();
		product.setId(key);
		product.setBrand("Nike");
		product.setType("Running");
		product.setGender("Male");
		product.setStockOnHand(12);
		productRepository.save(product);
		assert(productRepository.count() == 1);
		assert(productRepository.findOne(key).equals(product));
	}
	
	@Test
	public void testTransactionRepo() {
		Transaction txn = new Transaction();
		txn.setId(key);
		transactionRepository.save(txn);
		assert(transactionRepository.count() == 1);
		assert(transactionRepository.findOne(key).equals(txn));
	}
	
}
