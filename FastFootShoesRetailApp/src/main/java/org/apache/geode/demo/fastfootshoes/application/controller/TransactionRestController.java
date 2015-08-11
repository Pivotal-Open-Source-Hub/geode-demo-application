package org.apache.geode.demo.fastfootshoes.application.controller;

import org.apache.geode.demo.fastfootshoes.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionRestController {

	@Autowired
	TransactionRepository txnRepo;

	@RequestMapping("/transactionData")
	public long[] data() {
		long[] data = new long[2];
		data[1] = txnRepo.getCount();
		data[0] = new java.util.Date().getTime();
		return data;
	}
}