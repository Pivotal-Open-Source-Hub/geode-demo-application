package org.apache.geode.demo.fastfootshoes.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to load the home page 
 * @author lshannon
 *
 */
@Controller
public class HomeController {


	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}

}
