package com.dowloyalty.entity;

import java.io.Serializable;

public class Promoter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5992443665729243012L;
	private int id;
	private String chineseName;
	private String userID;
	private boolean isActive;

	
	public Promoter() {
		super();
	}


	public Promoter(int id, String chineseName, String userID, boolean isActive) {
		super();
		this.id = id;
		this.chineseName = chineseName;
		this.userID = userID;
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


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
