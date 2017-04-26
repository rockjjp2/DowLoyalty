package com.dowloyalty.entity;

import java.io.Serializable;

public class Admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7684365155297166278L;
	private int id;
	private String chineseName;
	private String userId;
	private boolean isActive;

	public Admin() {
		super();
	}

	public Admin(int id, String chineseName, String userId, boolean isActive) {
		super();
		this.id = id;
		this.chineseName = chineseName;
		this.userId = userId;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", chineseName=" + chineseName + ", userId=" + userId + ", isActive=" + isActive
				+ "]";
	}

}
