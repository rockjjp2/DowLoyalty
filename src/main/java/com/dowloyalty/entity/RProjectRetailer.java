package com.dowloyalty.entity;

import java.io.Serializable;

public class RProjectRetailer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6484315321278322445L;
	private int id;
	private int projectID;
	private int retailerID;
	private boolean isActive;

	public RProjectRetailer() {
		super();
	}

	public RProjectRetailer(int id, int projectID, int retailerID, boolean isActive) {
		super();
		this.id = id;
		this.projectID = projectID;
		this.retailerID = retailerID;
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

	public int getRetailerID() {
		return retailerID;
	}

	public void setRetailerID(int retailerID) {
		this.retailerID = retailerID;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "RProjectRetailer [id=" + id + ", projectID=" + projectID + ", retailerID=" + retailerID + ", isActive="
				+ isActive + "]";
	}
	
}
