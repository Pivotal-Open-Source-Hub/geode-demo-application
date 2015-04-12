/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

/**
 * This class contains all the details around a transaction.
 * Rather than nesting Product and Customer objects, they are referenced by Id.
 * This approach allows for easier querying of Transactions in Geode as well as reduces the
 * memory foot print.
 * @author lshannon
 */

//Spring Data Repositories will be used to populate the data into the cluster. This must map exactly to a region name in the cluster
@Region("Transaction")
public class Transaction {

	private String customerId;
	private Date transactionDate;
	private String productId;
	private int quantity;
	private double retailPrice;
	private String orderStatus;
	@Id
	private String id;
	private double markUp;
	public static String ORDER_COMPLETED = "shipped";
	public static String ORDER_CANCELLED = "cancelled";
	public static String ORDER_OPEN = "open";
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}


	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public double getRetailPrice() {
		return retailPrice;
	}
	
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getMarkUp() {
		return markUp;
	}

	public void setMarkUp(double markUp) {
		this.markUp = markUp;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Transaction [customerId=" + customerId + ", transactionDate="
				+ transactionDate + ", productId=" + productId + ", quantity="
				+ quantity + ", retailPrice=" + retailPrice + ", orderStatus="
				+ orderStatus + ", id=" + id + ", markUp=" + markUp + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(markUp);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + quantity;
		temp = Double.doubleToLongBits(retailPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((transactionDate == null) ? 0 : transactionDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(markUp) != Double
				.doubleToLongBits(other.markUp))
			return false;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(retailPrice) != Double
				.doubleToLongBits(other.retailPrice))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}
	
	
	
}
