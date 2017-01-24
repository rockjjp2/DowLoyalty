package com.dowloyalty.entity;

import java.io.Serializable;

public class Promoter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5992443665729243012L;
	private int id;
	private String chineseName;
	private String wechatID;
	private boolean isActive;

	public Promoter() {
		super();
	}

	public Promoter(int id, String chineseName, String wechatID, boolean isActive) {
		super();
		this.id = id;
		this.chineseName = chineseName;
		this.wechatID = wechatID;
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

	public String getWechatID() {
		return wechatID;
	}

	public void setWechatID(String wechatID) {
		this.wechatID = wechatID;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Promoter [id=" + id + ", chineseName=" + chineseName + ", wechatID=" + wechatID + ", isActive="
				+ isActive + "]";
	}
	
}
