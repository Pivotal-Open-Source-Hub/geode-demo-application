/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.application.controller;

import java.util.Collection;
import java.util.Date;

import org.apache.geode.demo.fastfootshoes.application.services.TransactionDataService;
import org.apache.geode.demo.fastfootshoes.model.Customer;
import org.apache.geode.demo.fastfootshoes.model.Transaction;
import org.apache.geode.demo.fastfootshoes.repositories.CustomerRepository;
import org.apache.geode.demo.fastfootshoes.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * Controller for all Customer related operations
 * @author lshannon
 *
 */
@Controller
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionDataService transactionDataService;
	
	@RequestMapping("/findCustomerById")
	public String findCustomerById(@RequestParam(value="id", required=true) String id, Model model) {
		Customer cust = customerRepository.findById(id);
		populateModel(model, cust);
		return "customerDetails";
	}
	
	@RequestMapping("/findCustomerByEmail")
	public String findCustomerByEmail(@RequestParam(value="email", required=true) String email, Model model) {
		Customer cust = customerRepository.findByEmailAddress(email);
		populateModel(model, cust);
		return "customerDetails";
	}

	private void populateModel(Model model, Customer cust) {
		Collection<Transaction> transactions = transactionRepository.findByCustomer(cust.getId());
		long[] results = transactionDataService.getTransactionCount(cust);
		String discount_level = transactionDataService.getMarkUpName(results[1]);
		model.addAttribute("cust", cust);
		model.addAttribute("transactions", transactions);
		model.addAttribute("transaction_count", results[1]);
		model.addAttribute("discount_level", discount_level);
		model.addAttribute("discountprocessTime", results[0]);
	}
	
	@RequestMapping(value="/customerDetails", method = RequestMethod.GET)
	public String customerDetails(@RequestParam(value="id", required=true) String id,
			Model model) {
		Customer cust = customerRepository.findOne(id);
		System.out.println("Returning customer: " + cust);
		Collection<Transaction> transactions = transactionRepository.findByCustomer(cust.getId());
		model.addAttribute("cust", cust);
		model.addAttribute("transactions", transactions);
		return "customerDetails";
	}
	
	@RequestMapping(value="/updateCustomer", method = RequestMethod.POST)
	public String updateCustomer(
			@RequestParam(value="name") String name,
			@RequestParam(value="city") String city,
			@RequestParam(value="emailAddress") String emailAddress,
			@RequestParam(value="birthday") Date birthday,
			@RequestParam(value="id", required=true) String id,
			Model model) {
		Customer cust = new Customer(name,emailAddress,city,birthday,id);
		customerRepository.save(cust);
		return "home";
	}
	
	

}
