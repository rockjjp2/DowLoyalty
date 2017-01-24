package com.dowloyalty.entity;

import java.io.Serializable;

public class RPromoterProvince implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1990192849950826724L;
	private int id;
	private int promoterID;
	private int provinceID;
	private boolean isActive;

	public RPromoterProvince() {
		super();
	}

	public RPromoterProvince(int id, int promoterID, int provinceID, boolean isActive) {
		super();
		this.id = id;
		this.promoterID = promoterID;
		this.provinceID = provinceID;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPromoterID() {
		return promoterID;
	}

	public void setPromoterID(int promoterID) {
		this.promoterID = promoterID;
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

	@Override
	public String toString() {
		return "RPromoterProvince [id=" + id + ", promoterID=" + promoterID + ", provinceID=" + provinceID
				+ ", isActive=" + isActive + "]";
	}
	
}
