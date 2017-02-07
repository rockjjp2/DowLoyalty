package com.dowloyalty.entity;

import java.io.Serializable;

public class Points implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6277453714556183327L;
	private int id;
	private int projectID;
	private int productID;
	private int salesAmount;
	private int points;
	private boolean isActive;
	
	
	public Points() {
		super();
	}
	public Points(int id, int projectID, int productID, int salesAmount, int points, boolean isActive) {
		super();
		this.id = id;
		this.projectID = projectID;
		this.productID = productID;
		this.salesAmount = salesAmount;
		this.points = points;
		this.isActive = isActive;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	@Override
	public String toString() {
		return "Points [id=" + id + ", projectID=" + projectID + ", productID=" + productID + ", salesAmount="
				+ salesAmount + ", points=" + points + ", isActive=" + isActive + "]";
	}

	
}
