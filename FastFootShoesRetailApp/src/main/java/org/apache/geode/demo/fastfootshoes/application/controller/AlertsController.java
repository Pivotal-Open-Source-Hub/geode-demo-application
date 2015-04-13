/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.application.controller;

import org.apache.geode.demo.fastfootshoes.model.Alert;
import org.apache.geode.demo.fastfootshoes.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for working with Alerts
 * @author lshannon
 *
 */
@Controller
public class AlertsController {
	
	@Autowired
	private AlertRepository alertRepository;
	
	@RequestMapping("/alerts")
    public String listOpenOrders(Model model) {
		Iterable<Alert> alerts = alertRepository.findAll();
		model.addAttribute("alerts",alerts);
        return "alerts";
    }

}
