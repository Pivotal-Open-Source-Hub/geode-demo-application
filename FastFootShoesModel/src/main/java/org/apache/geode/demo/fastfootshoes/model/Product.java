/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

/**
 * This class contains all the characteristic of a Product
 * @author lshannon
 */

//Spring Data Repositories will be used to populate the data into the cluster. This must map exactly to a region name in the cluster
@Region("Product")
public class Product {
	
	private int stockOnHand;
	private double wholeSalePrice;
	private String brand;
	private String name;
	private String type;
	private String color;
	private double size;
	private String gender;
	
	//this ID will act as the key when doing puts/gets into the cluster
	@Id
	private String id;
	
	public int getStockOnHand() {
		return stockOnHand;
	}
	public void setStockOnHand(int stockOnHand) {
		this.stockOnHand = stockOnHand;
	}
	public double getWholeSalePrice() {
		return wholeSalePrice;
	}
	public void setWholeSalePrice(double wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Product [stockOnHand=" + stockOnHand + ", wholeSalePrice="
				+ wholeSalePrice + ", brand=" + brand + ", name=" + name
				+ ", type=" + type + ", color=" + color + ", size=" + size
				+ ", gender=" + gender + ", id=" + id + "]";
	}
	
}
