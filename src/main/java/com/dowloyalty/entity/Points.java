package com.dowloyalty.entity;

import java.io.Serializable;

public class Points implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6277453714556183327L;
	private int id;
	private int projectID;
	private int productSpecificationID;
	private int amount;
	private int points;
	private boolean isActive;

	public Points() {
		super();
	}

	public Points(int id, int projectID, int productSpecificationID, int amount, int points, boolean isActive) {
		super();
		this.id = id;
		this.projectID = projectID;
		this.productSpecificationID = productSpecificationID;
		this.amount = amount;
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

	public int getProductSpecificationID() {
		return productSpecificationID;
	}

	public void setProductSpecificationID(int productSpecificationID) {
		this.productSpecificationID = productSpecificationID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Points [id=" + id + ", projectID=" + projectID + ", productSpecificationID=" + productSpecificationID
				+ ", amount=" + amount + ", points=" + points + ", isActive=" + isActive + "]";
	}
	
}
