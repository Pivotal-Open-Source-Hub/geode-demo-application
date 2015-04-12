/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

/**
 * This class is used to store system messages (ie: order placed that exceeds current quantity)
 * @author lshannon
 */

//Spring Data Repositories will be used to populate the data into the cluster. This must map exactly to a region name in the cluster
@Region("Alert")
public class Alert {
	
	private String message;
	private Date messageDate;
	
	//this ID will act as the key when doing puts/gets into the cluster
	@Id
	private String id;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Alert [message=" + message + ", messageDate=" + messageDate
				+ ", id=" + id + "]";
	}

}
