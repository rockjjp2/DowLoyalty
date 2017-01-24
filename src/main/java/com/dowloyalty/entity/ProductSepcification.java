package com.dowloyalty.entity;

import java.io.Serializable;

public class ProductSepcification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1319194997173375526L;
	private int id;
	private int productID;
	private String sepcification;
	private float price;
	private boolean isActive;

	public ProductSepcification() {
		super();
	}

	public ProductSepcification(int id, int productID, String sepcification, float price, boolean isActive) {
		super();
		this.id = id;
		this.productID = productID;
		this.sepcification = sepcification;
		this.price = price;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getSepcification() {
		return sepcification;
	}

	public void setSepcification(String sepcification) {
		this.sepcification = sepcification;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "ProductSepcification [id=" + id + ", productID=" + productID + ", sepcification=" + sepcification
				+ ", price=" + price + ", isActive=" + isActive + "]";
	}

}
