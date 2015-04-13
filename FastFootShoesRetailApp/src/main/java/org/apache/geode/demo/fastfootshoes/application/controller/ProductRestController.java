package org.apache.geode.demo.fastfootshoes.application.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.geode.demo.fastfootshoes.clusterside.functions.ProductGroupCounterCaller;
import org.apache.geode.demo.fastfootshoes.model.Product;
import org.apache.geode.demo.fastfootshoes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    @RequestMapping("/products")
    Collection<Product> search(@RequestParam(required = false) String type ) {
        return this.productRepository.findAllWithStockByBrand(  type  + "%");
    }

    @Autowired
    ProductRepository productRepository;
    
	@Autowired
	@Qualifier("productGroupCounterCaller")
	ProductGroupCounterCaller productGroupCounterCaller;
	
	
	@RequestMapping("/productCounts")
	List<Map<String, AtomicInteger>> getProductCounts() {
		List<Map<String, AtomicInteger>> results = new ArrayList<Map<String, AtomicInteger>>();
		List<Map<String, AtomicInteger>> typeCountList = productGroupCounterCaller.countByType();
		results.add(typeCountList.get(0));
		List<Map<String, AtomicInteger>> brandCountList = productGroupCounterCaller.countByBrand();
		results.add(brandCountList.get(0));
		return results;
	}
    
}
