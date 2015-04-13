package org.apache.geode.demo.fastfootshoes.application.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.geode.demo.fastfootshoes.model.Product;
import org.apache.geode.demo.fastfootshoes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for working with Products
 * @author lshannon
 *
 */
@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping("/productSearch")
	public String productSearch() {
		return "productSearch";
	}

	@RequestMapping(value = "/listSpecificProducts", method = RequestMethod.GET)
	public String listSpecificProducts(
			@RequestParam(value = "brand", required = true) String brand,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "gender", required = true) String gender,
			@RequestParam(value = "inStock", required = false) boolean inStock,
			Model model) {
		Collection<Product> products;
		if (inStock) {
			products = productRepository.findAllWithStockByBrandTypeGender(brand, type, gender);
		}
		else {
			products = productRepository.findAllByBrandTypeGender(brand, type, gender);
		}
		model.addAttribute("products", products);
		return "listProducts";
	}
	
	@RequestMapping(value = "/showProduct", method = RequestMethod.GET)
	public String showProduct(
			@RequestParam(value = "id", required = true) String id,
			Model model) {
		Collection<Product> products = new ArrayList<Product>();
		Product product = productRepository.findById(id);
		System.out.println("Adding Product: " + product);
		products.add(product);
		model.addAttribute("products", products);
		return "listProducts";
	}



	@RequestMapping(value = "/listAllProducts", method = RequestMethod.GET)
	public String listAllProducts(@RequestParam(value = "inStock", required = true) boolean inStock, Model model) {
		Collection<Product> products;
		if (inStock) {
			products = productRepository.findAllWithStock();
		}
		else {
			products = productRepository.findAll();
		}
		model.addAttribute("products", products);
		return "listProducts";
	}

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

}
