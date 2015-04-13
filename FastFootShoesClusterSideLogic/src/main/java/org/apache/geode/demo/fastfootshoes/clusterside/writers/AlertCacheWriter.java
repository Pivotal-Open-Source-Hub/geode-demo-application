package org.apache.geode.demo.fastfootshoes.clusterside.writers;

import java.util.Properties;

import org.apache.geode.demo.fastfootshoes.clusterside.util.AlertsDerbyDAO;
import org.apache.geode.demo.fastfootshoes.model.Alert;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.EntryEvent;
import com.gemstone.gemfire.cache.util.CacheWriterAdapter;

@Component
public class AlertCacheWriter extends CacheWriterAdapter<String, Alert> implements Declarable {
	
	private AlertsDerbyDAO alertsDAO;
	
	public void afterCreate(EntryEvent<String, Alert> event) {
	    Alert alert = (Alert)event.getNewValue();
	    alertsDAO.insert(alert);
	 }
	
	public void init(Properties props) {
		
	}
	
	public AlertsDerbyDAO getAlertsDAO() {
		return alertsDAO;
	}

	public void setAlertsDAO(AlertsDerbyDAO alertsDAO) {
		this.alertsDAO = alertsDAO;
	}

}
