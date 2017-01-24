package com.dowloyalty.entity;

import java.io.Serializable;

public class Retailer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4825192978140298512L;
	private int id;
	private String chineseName;
	private boolean isActive;
	private int provinceID;
	private String mobile;
	private String wechatID;
	private String email;

	public Retailer() {
		super();
	}

	public Retailer(int id, String chineseName, boolean isActive, int provinceID, String mobile, String wechatID,
			String email) {
		super();
		this.id = id;
		this.chineseName = chineseName;
		this.isActive = isActive;
		this.provinceID = provinceID;
		this.mobile = mobile;
		this.wechatID = wechatID;
		this.email = email;
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

	public int getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWechatID() {
		return wechatID;
	}

	public void setWechatID(String wechatID) {
		this.wechatID = wechatID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Retailer [id=" + id + ", chineseName=" + chineseName + ", isActive=" + isActive + ", provinceID="
				+ provinceID + ", mobile=" + mobile + ", wechatID=" + wechatID + ", email=" + email + "]";
	}

}
