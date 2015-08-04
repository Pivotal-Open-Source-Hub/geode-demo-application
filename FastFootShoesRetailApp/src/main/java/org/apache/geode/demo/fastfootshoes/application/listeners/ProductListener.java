/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.application.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.geode.demo.fastfootshoes.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.cache.EntryEvent;
import com.gemstone.gemfire.cache.util.CacheListenerAdapter;

/**
 * @author lshannon
 *
 */
@Component
public class ProductListener extends CacheListenerAdapter<String, Product> {

	@Autowired
	SimpMessageSendingOperations sender;
	
	@Value("${product.interest.threshold}")
	private int display_threshold;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void afterCreate(EntryEvent<String, Product> entryEvent) {
		updateSocket(entryEvent);
	}

	@Override
	public void afterUpdate(EntryEvent<String, Product> entryEvent) {
		updateSocket(entryEvent);
	}

	private void updateSocket(EntryEvent<String, Product> entryEvent) {
		if (entryEvent.getNewValue().getStockOnHand() <= display_threshold) {
			System.out.println("Adding " + entryEvent.getNewValue().toString() + " to the web socket");
			sender.convertAndSend("/topic/alarms", new StockAlert(entryEvent));
		}
		
	}

	static class StockAlert {
		public final String key;
		public final Object value;
		public final String date;

		public StockAlert(EntryEvent<String, Product> event) {
			this.key = (String) event.getKey();
			this.value = event.getNewValue();
			this.date = dateFormat.format(new Date());

		}
	}
}
