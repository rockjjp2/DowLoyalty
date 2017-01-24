package com.dowloyalty.entity;

import java.io.Serializable;

public class Project implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8683277422256712241L;
	private int id;
	private String name;
	private int provinceID;
	private boolean isActive;
	private String placardPath;
	private int assistantID;
	private String startDate;
	private String endDate;
	private int adminID;
	private boolean isVisible;
	
	public Project() {
		super();
	}


	public Project(int id, String name, int provinceID, boolean isActive, String placardPath, int assistantID,
			String startDate, String endDate, int adminID, boolean isVisible) {
		super();
		this.id = id;
		this.name = name;
		this.provinceID = provinceID;
		this.isActive = isActive;
		this.placardPath = placardPath;
		this.assistantID = assistantID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adminID = adminID;
		this.isVisible = isVisible;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPlacardPath() {
		return placardPath;
	}

	public void setPlacardPath(String placardPath) {
		this.placardPath = placardPath;
	}

	public int getAssistantID() {
		return assistantID;
	}

	public void setAssistantID(int assistantID) {
		this.assistantID = assistantID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", provinceID=" + provinceID + ", isActive=" + isActive
				+ ", placardPath=" + placardPath + ", assistantID=" + assistantID + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", adminID=" + adminID + ", isVisible=" + isVisible + "]";
	}

}
