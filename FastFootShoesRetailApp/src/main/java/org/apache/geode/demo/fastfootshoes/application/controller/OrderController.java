package org.apache.geode.demo.fastfootshoes.application.controller;

import org.apache.geode.demo.fastfootshoes.application.services.TransactionDataService;
import org.apache.geode.demo.fastfootshoes.model.Product;
import org.apache.geode.demo.fastfootshoes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * Controller for collecting and view Orders 
 * @author lshannon
 *
 *
 */
@Controller
public class OrderController {
	
	@Autowired
	private TransactionDataService transactionDataService;
	
	@Autowired
	private ProductRepository productRepository;

	/**
	 * Display the order collection form
	 * @param productId
	 * @param model
	 * @return
	 */
	@RequestMapping("/collectOrder")
    public String collectOrder(@RequestParam(value="id", required=false) String productId, Model model) {
		if (productId != null) {
			Product product = productRepository.findOne(productId);
			if (product != null) {
				model.addAttribute("productString", " Brand: " + product.getBrand() + " Type: " + product.getType() + " Color: " + product.getColor() + " Size: " + product.getSize() + (product.getGender().equals("M") ? " Mens" : " Womens "));
				model.addAttribute("productId", productId);
			}
		}
        return "collectOrder";
    }

	/**
	 * 
	 * The Order collection form posts to this controller.
	 * 
	 * @param customerId
	 * @param productId
	 * @param quantity
	 * @param model
	 * @return
	 */
	@RequestMapping("/placeOrder")
    public String placeOrder(@RequestParam(value="customerId", required=true) String customerId, @RequestParam(value="productId", required=true) String productId, @RequestParam(value="quantity", required=true) int quantity, Model model) {
		System.out.println("Processing order for Customer: " + customerId + " Product: " + productId + " Quantity: " + quantity);
		if (transactionDataService.placeOrder(customerId, productId, quantity)) {
			model.addAttribute("id", customerId);
			return "completedOrder";
		}
		else {
			return "failedOrder";
		}
    }
	
	/**
	 * 
	 * Loads up the page with buttons to search all the orders
	 * @param model
	 * @return
	 * 
	 */
	@RequestMapping("/orderSearch")
    public String orderSearch(Model model) {
		return "orderSearch";
    }
	
	/**
	 * Controller for listing all the orders in the cluster
	 * @param model
	 * @return
	 */
	@RequestMapping("/listAllOrders")
    public String listAllOrders(Model model) {
		model.addAttribute("transactions", transactionDataService.getAllOrder());
        return "listTransactions";
    }
	
	/**
	 * Controller only for listing the Transactions that have an Opened status
	 * @param model
	 * @return
	 */
	@RequestMapping("/listAllOpenOrders")
    public String listOpenOrders(Model model) {
		model.addAttribute("transactions", transactionDataService.getOpenOrder());
        return "listTransactions";
    }
}
