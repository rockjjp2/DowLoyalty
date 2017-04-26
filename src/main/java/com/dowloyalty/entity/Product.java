package com.dowloyalty.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1319194997173375526L;
	private int id;
	private String sfdcCode;
	private int productFamilyID;
	private String name;
	private String description;
	private Timestamp lastModifiedDate;
	private boolean isActive;
	
	
	public Product() {
		super();
	}
	public Product(int id, int productFamilyID, String name, String description, String sfdcCode,
			Timestamp lastModifiedDate, boolean isActive) {
		super();
		this.id = id;
		this.productFamilyID = productFamilyID;
		this.name = name;
		this.description = description;
		this.sfdcCode = sfdcCode;
		this.lastModifiedDate = lastModifiedDate;
		this.isActive = isActive;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductFamilyID() {
		return productFamilyID;
	}
	public void setProductFamilyID(int productFamilyID) {
		this.productFamilyID = productFamilyID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSfdcCode() {
		return sfdcCode;
	}
	public void setSfdcCode(String sfdcCode) {
		this.sfdcCode = sfdcCode;
	}
	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", productFamilyID=" + productFamilyID + ", name=" + name + ", description="
				+ description + ", sfdcCode=" + sfdcCode + ", lastModifiedDate=" + lastModifiedDate + ", isActive="
				+ isActive + "]";
	}
}
