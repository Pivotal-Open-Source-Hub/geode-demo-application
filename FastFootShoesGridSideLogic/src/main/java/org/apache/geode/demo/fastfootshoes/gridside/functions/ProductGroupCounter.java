package org.apache.geode.demo.fastfootshoes.gridside.functions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.geode.demo.fastfootshoes.gridside.util.ReferenceHelper;
import org.apache.geode.demo.fastfootshoes.model.Product;
import org.apache.geode.demo.fastfootshoes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.function.annotation.GemfireFunction;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.pdx.internal.PdxInstanceImpl;
/**
 * 
 * This is a function that runs on the Cluster and counts product by Type or by Brand
 * 
 * @author lshannon
 *
 */
@Component
public class ProductGroupCounter {
	
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * Return a count of each Brand
	 * @return
	 */
	@GemfireFunction
	public Map<String, AtomicInteger> countByBrand() {
		Map<String,AtomicInteger> results = new HashMap<String,AtomicInteger>();
		Collection<Product> products = productRepository.findAll();
		for (Object objProduct : products) {
			Product product = resolveReference(objProduct);
			if (results.containsKey(product.getBrand())) {
				results.get(product.getBrand()).addAndGet(product.getStockOnHand());
			}
			else {
				results.put(product.getBrand(), new AtomicInteger(product.getStockOnHand()));
			}
		}
		return results;
	}
	
	/**
	 * Return a count of all the types
	 * @return
	 */
	@GemfireFunction
	public Map<String, AtomicInteger> countByType() {
		Map<String,AtomicInteger> results = new HashMap<String,AtomicInteger>();
		Collection<Product> products = productRepository.findAll();
		for (Object obj : products) {
			Product product = resolveReference(obj);
			if (results.containsKey(product.getType())) {
				results.get(product.getType()).addAndGet(product.getStockOnHand());
			}
			else {
				results.put(product.getType(), new AtomicInteger(product.getStockOnHand()));
			}
		}
		return results;
	}
	
	/*
	 * If Read Serialized is set, the cluster will return PdxInstanceImpl and will need to be cast
	 */
	private Product resolveReference(Object obj) {
			if (obj instanceof PdxInstanceImpl) {
				return ReferenceHelper.toObject(obj, Product.class);
			}
			else {
				return (Product)obj;
			}
	}

}
