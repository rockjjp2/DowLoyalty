package com.dowloyalty.entity;

import java.io.Serializable;

public class RProjectPromoter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4617075352201239338L;
	private int id;
	private int projectID;
	private int promoterID;
	private boolean isActive;

	public RProjectPromoter() {
		super();
	}

	public RProjectPromoter(int id, int projectID, int promoterID, boolean isActive) {
		super();
		this.id = id;
		this.projectID = projectID;
		this.promoterID = promoterID;
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

	public int getPromoterID() {
		return promoterID;
	}

	public void setPromoterID(int promoterID) {
		this.promoterID = promoterID;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "RProjectPromoter [id=" + id + ", projectID=" + projectID + ", promoterID=" + promoterID + ", isActive="
				+ isActive + "]";
	}
	
}
