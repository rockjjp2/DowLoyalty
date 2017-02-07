package com.dowloyalty.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Retailer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4825192978140298512L;
	private int id;
	private String chineseName;
	private int provinceID;
	private String mobile;
	private String penID;
	private String email;
	private String loginCode;
	private String sfdcCode;
	private Timestamp lastUpdataData;
	private boolean isActive;
	
	
	public Retailer() {
		super();
	}
	public Retailer(int id, String chineseName, int provinceID, String mobile, String penID, String email,
			String loginCode, String sfdcCode, Timestamp lastUpdataData, boolean isActive) {
		super();
		this.id = id;
		this.chineseName = chineseName;
		this.provinceID = provinceID;
		this.mobile = mobile;
		this.penID = penID;
		this.email = email;
		this.loginCode = loginCode;
		this.sfdcCode = sfdcCode;
		this.lastUpdataData = lastUpdataData;
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
	public String getPenID() {
		return penID;
	}
	public void setPenID(String penID) {
		this.penID = penID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public String getSfdcCode() {
		return sfdcCode;
	}
	public void setSfdcCode(String sfdcCode) {
		this.sfdcCode = sfdcCode;
	}
	public Timestamp getLastUpdataData() {
		return lastUpdataData;
	}
	public void setLastUpdataData(Timestamp lastUpdataData) {
		this.lastUpdataData = lastUpdataData;
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
		return "Retailer [id=" + id + ", chineseName=" + chineseName + ", provinceID=" + provinceID + ", mobile="
				+ mobile + ", penID=" + penID + ", email=" + email + ", loginCode=" + loginCode + ", sfdcCode="
				+ sfdcCode + ", lastUpdataData=" + lastUpdataData + ", isActive=" + isActive + "]";
	}
	
}
