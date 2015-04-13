/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.clusterside.listeners;

import java.util.List;

import org.apache.geode.demo.fastfootshoes.clusterside.util.AlertsDerbyDAO;
import org.apache.geode.demo.fastfootshoes.clusterside.util.ReferenceHelper;
import org.apache.geode.demo.fastfootshoes.model.Alert;

import com.gemstone.gemfire.cache.asyncqueue.AsyncEvent;
import com.gemstone.gemfire.cache.asyncqueue.AsyncEventListener;
import com.gemstone.gemfire.pdx.internal.PdxInstanceImpl;

/**
 * This class is used to queue up Alert Objects and Asynchronously write them to an external DB
 * 
 * @author lshannon
 *
 */
public class AsyncAlertWriter implements AsyncEventListener {
	
	private AlertsDerbyDAO alertsDAO;

	public void close() {
	}

	/**
	 * This method is called by Gemfire. The frequency of which it is called, as well as the number
	 * of AsyncEvent objects in the events list depends on how the AsyncEventListener is configured in the
	 * Alert region on the cluster
	 */
	@SuppressWarnings("rawtypes")
	public boolean processEvents(List<AsyncEvent> events) {
		for (AsyncEvent asyncEvent : events) {
			Alert alert = null;
			//If Read Serialized is set, the cluster will return a PdxInstanceImpl, it needs to be cast to the Alert type in this case
			if (asyncEvent.getDeserializedValue() instanceof PdxInstanceImpl) {
				alert = ReferenceHelper.toObject(asyncEvent.getDeserializedValue(), Alert.class);
			}
			else {
				alert = (Alert) asyncEvent.getDeserializedValue();
			}
			//only when no alerts are created is the DB updated.
			//updates and deletes in the region are ignored for this example
			if (asyncEvent.getOperation().isCreate()) {
				alertsDAO.insert(alert);
			}
		}
		return true;
	}

	public AlertsDerbyDAO getAlertsDAO() {
		return alertsDAO;
	}

	public void setAlertsDAO(AlertsDerbyDAO alertsDAO) {
		this.alertsDAO = alertsDAO;
	}
	
	

}
