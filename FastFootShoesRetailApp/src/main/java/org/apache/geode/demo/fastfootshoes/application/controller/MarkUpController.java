package org.apache.geode.demo.fastfootshoes.application.controller;

import org.apache.geode.demo.fastfootshoes.model.MarkUp;
import org.apache.geode.demo.fastfootshoes.repositories.MarkUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * Controller for working with MarkUp values
 * @author lshannon
 *
 */
@Controller
public class MarkUpController {
	
	@Autowired
	private MarkUpRepository markUpRepository;
	
	@RequestMapping("/listMarkUps")
	public String getMarkUps(Model model) {
		Iterable<MarkUp> markUps = markUpRepository.findAll();
		model.addAttribute("markUps", markUps);
		return "listMarkUps";
	}

}
