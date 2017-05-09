package com.dowloyalty.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Project implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8683277422256712241L;
	private int id;
	private String name;
	private int provinceID;
	private byte[] placardPath;
	private byte[] backgroundPath;
	private int assistantID;
	private Timestamp startDate;
	private Timestamp endDate;
	private int adminID;
	private String description;
	private boolean isVisible;
	private boolean isActive;
	private boolean haveFarmer;
	private String placardBase64;
	private String backgroundBase64;

	public Project() {
		super();
	}

	public Project(int id, String name, int provinceID, byte[] placardPath, byte[] backgroundPath, int assistantID,
			Timestamp startDate, Timestamp endDate, int adminID, String description, boolean isVisible,
			boolean isActive, boolean haveFarmer, String placardBase64, String backgroundBase64) {
		super();
		this.id = id;
		this.name = name;
		this.provinceID = provinceID;
		this.placardPath = placardPath;
		this.backgroundPath = backgroundPath;
		this.assistantID = assistantID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adminID = adminID;
		this.description = description;
		this.isVisible = isVisible;
		this.isActive = isActive;
		this.haveFarmer = haveFarmer;
		this.placardBase64 = placardBase64;
		this.backgroundBase64 = backgroundBase64;
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

	public byte[] getPlacardPath() {
		return placardPath;
	}

	public void setPlacardPath(byte[] placardPath) {
		this.placardPath = placardPath;
	}

	public byte[] getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(byte[] backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public int getAssistantID() {
		return assistantID;
	}

	public void setAssistantID(int assistantID) {
		this.assistantID = assistantID;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", provinceID=" + provinceID + ", placardPath=" + placardPath
				+ ", backgroundPath=" + backgroundPath + ", assistantID=" + assistantID + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", adminID=" + adminID + ", description=" + description + ", isVisible="
				+ isVisible + ", isActive=" + isActive + "]";
	}

	public String getPlacardBase64() {
		return placardBase64;
	}

	public void setPlacardBase64(String placardBase64) {
		this.placardBase64 = placardBase64;
	}

	public String getBackgroundBase64() {
		return backgroundBase64;
	}

	public void setBackgroundBase64(String backgroundBase64) {
		this.backgroundBase64 = backgroundBase64;
	}

	public boolean isHaveFarmer() {
		return haveFarmer;
	}

	public void setHaveFarmer(boolean haveFarmer) {
		this.haveFarmer = haveFarmer;
	}

}
