package com.dowloyalty.entity;

import java.io.Serializable;

public class InitPoints implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993381912563823722L;
	private int id;
	private int projectID;
	private String reason;
	private int retailerID;
	private int points;
	private boolean isActive;

	public InitPoints() {
		super();
	}
	
	public InitPoints(int projectID, String reason, int retailerID, int points) {
		super();
		this.projectID = projectID;
		this.reason = reason;
		this.retailerID = retailerID;
		this.points = points;
	}

	public InitPoints(int id, int projectID, String reason, int retailerID, int points, boolean isActive) {
		super();
		this.id = id;
		this.projectID = projectID;
		this.reason = reason;
		this.retailerID = retailerID;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getRetailerID() {
		return retailerID;
	}

	public void setRetailerID(int retailerID) {
		this.retailerID = retailerID;
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
		return "InitPoints [id=" + id + ", projectID=" + projectID + ", reason=" + reason + ", retailerID=" + retailerID
				+ ", points=" + points + ", isActive=" + isActive + "]";
	}


}
