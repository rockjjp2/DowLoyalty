package com.dowloyalty.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Farmer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3676232322020765149L;
	private int id;
	private String chineseName;
	private String mobile;
	private String openId;
	private Timestamp lastUpdateDate;
	private int area;
	private boolean isActive;

	public Farmer() {
		super();
	}

	public Farmer(int id, String chineseName, String mobile, String openId, Timestamp lastUpdateDate, int area,
			boolean isActive) {
		super();
		this.id = id;
		this.chineseName = chineseName;
		this.mobile = mobile;
		this.openId = openId;
		this.lastUpdateDate = lastUpdateDate;
		this.area = area;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
