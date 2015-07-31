/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

/**
 * This class contains all information around customers
 * @author lshannon
 */

//Spring Data Repositories will be used to populate the data into the cluster. This must map exactly to a region name in the cluster
@Region("Customer")
public class Customer {
	
	private String name;
	private String emailAddress;
	private String city;
	private Date birthday;
	
	//this ID will act as the key when doing puts/gets into the cluster
	@Id
	private String id;
	
	public Customer() {
		
	}
	
	
	
	public Customer(String name, String emailAddress, String city,
			Date birthday, String id) {
		super();
		this.name = name;
		this.emailAddress = emailAddress;
		this.city = city;
		this.birthday = birthday;
		this.id = id;
	}



	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", emailAddress=" + emailAddress
				+ ", city=" + city + ", birthday=" + birthday + ", id=" + id
				+ "]";
	}

}

