package org.apache.geode.demo.fastfootshoes.application.controller;

import org.apache.geode.demo.fastfootshoes.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Used to get Alert values from the cluster in the form of JSON
 * @author lshannon
 *
 */
@RestController
public class AlertsRestController {
	
	@Autowired
	AlertRepository alertsRepository;
	
	@RequestMapping("/alertCount")
	public long count() {
		return alertsRepository.count();
	}
	

}
