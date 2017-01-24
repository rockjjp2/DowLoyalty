package com.dowloyalty.entity;

import java.io.Serializable;

public class Admin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7684365155297166278L;
	private int id;
	private String chineseName;
	private boolean isActive;

	public Admin() {
		super();
	}

	public Admin(int id, String chineseName, boolean isActive) {
		super();
		this.id = id;
		this.chineseName = chineseName;
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

}
