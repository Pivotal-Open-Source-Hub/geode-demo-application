package org.apache.geode.demo.fastfootshoes.clusterside.functions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;

/**
 * Interface used to Execute the function
 * @author lshannon
 *
 */
@OnRegion(region="Product", id="productGroupCounterCaller")
public interface ProductGroupCounterCaller {
	
	@FunctionId("countByBrand")
	public List<Map<String, AtomicInteger>> countByBrand();
	
	@FunctionId("countByType")
	public List<Map<String, AtomicInteger>> countByType();

}
