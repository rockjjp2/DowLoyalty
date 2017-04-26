package com.dowloyalty.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class PojoProduct implements Serializable {
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
	
	private PojoProductFamily productFamily;
	
	public PojoProduct() {
		super();
	}
	public PojoProduct(int id, int productFamilyID, String name, String description, String sfdcCode,
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
	
	
	public PojoProduct(int id, String sfdcCode, int productFamilyID, String name, String description,
			Timestamp lastModifiedDate, boolean isActive, PojoProductFamily productFamily) {
		super();
		this.id = id;
		this.sfdcCode = sfdcCode;
		this.productFamilyID = productFamilyID;
		this.name = name;
		this.description = description;
		this.lastModifiedDate = lastModifiedDate;
		this.isActive = isActive;
		this.productFamily = productFamily;
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
	
	public PojoProductFamily getProductFamily() {
		return productFamily;
	}
	public void setProductFamily(PojoProductFamily productFamily) {
		this.productFamily = productFamily;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productFamilyID=" + productFamilyID + ", name=" + name + ", description="
				+ description + ", sfdcCode=" + sfdcCode + ", lastModifiedDate=" + lastModifiedDate + ", isActive="
				+ isActive + "]";
	}
}
