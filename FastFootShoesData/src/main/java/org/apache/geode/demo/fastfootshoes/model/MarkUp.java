/**
 * 
 */
package org.apache.geode.demo.fastfootshoes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

/**
 * This class stores the details on how for a Mark Up. A mark up is used to calculate the Retail Cost of an item.
 * ie: Retail Cost = whole sale price * mark up
 * There are different marks up depending on the number of transactions made in a year.
 * Mark Ups are calculated with an in memory function within Geode
 * @author lshannon
 */

//Spring Data Repositories will be used to populate the data into the cluster. This must map exactly to a region name in the cluster
@Region("MarkUp")
public class MarkUp {

	private double rate;
	private String levelName;
	private int qualifyingTransactionCountFloor;
	private int qualifyingTransactionCountCeiling;
	
	//this ID will act as the key when doing puts/gets into the cluster
	@Id
	private String id;
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public int getQualifyingTransactionCountFloor() {
		return qualifyingTransactionCountFloor;
	}
	public void setQualifyingTransactionCountFloor(
			int qualifyingTransactionCountFloor) {
		this.qualifyingTransactionCountFloor = qualifyingTransactionCountFloor;
	}
	public int getQualifyingTransactionCountCeiling() {
		return qualifyingTransactionCountCeiling;
	}
	public void setQualifyingTransactionCountCeiling(
			int qualifyingTransactionCountCeiling) {
		this.qualifyingTransactionCountCeiling = qualifyingTransactionCountCeiling;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "MarkUp [rate=" + rate + ", levelName=" + levelName
				+ ", qualifyingTransactionCountFloor="
				+ qualifyingTransactionCountFloor
				+ ", qualifyingTransactionCountCeiling="
				+ qualifyingTransactionCountCeiling + ", id=" + id + "]";
	}
	
}
