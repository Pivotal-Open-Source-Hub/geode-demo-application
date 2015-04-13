package org.apache.geode.demo.fastfootshoes.clusterside.functions;

import java.util.List;

import org.apache.geode.demo.fastfootshoes.model.Customer;
import org.springframework.data.gemfire.function.annotation.FunctionId;
import org.springframework.data.gemfire.function.annotation.OnRegion;

/**
 * Interface used to Execute the function
 * @author lshannon
 *
 */
@OnRegion(region="Transaction", id="orderCounterCaller")
public interface OrderCounterCaller {
	
	@FunctionId("countTransactions")
	public List<Integer> countTransactions(Customer customer);

}
