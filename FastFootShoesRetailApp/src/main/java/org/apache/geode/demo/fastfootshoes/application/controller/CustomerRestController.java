package org.apache.geode.demo.fastfootshoes.application.controller;

import java.util.Collection;

import org.apache.geode.demo.fastfootshoes.model.Customer;
import org.apache.geode.demo.fastfootshoes.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Get Customer values in JSON format
 * @author lshannon
 *
 */
@RestController
public class CustomerRestController {

	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping("/customers")
	Collection<Customer> search(@RequestParam String email) {
		return this.customerRepository.searchCustomers(email + "%");
	}
}
