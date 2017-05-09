package com.dowloyalty.entity;

import java.io.Serializable;

public class FarmerSalesRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522830309770783938L;
	private int id;
	private int farmerId;
	private int projectId;
	private float totalPrice;
	private int importerId;
	private String submitDate;
	private String mobile;
	private boolean status;
	private String oppId;

	public FarmerSalesRecord() {
		super();
	}

	public FarmerSalesRecord(int id, int farmerId, int projectId, float totalPrice, int importerId,
			String submitDate, String mobile, boolean status, String oppId) {
		super();
		this.id = id;
		this.farmerId = farmerId;
		this.projectId = projectId;
		this.totalPrice = totalPrice;
		this.importerId = importerId;
		this.submitDate = submitDate;
		this.mobile = mobile;
		this.status = status;
		this.oppId = oppId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getImporterId() {
		return importerId;
	}

	public void setImporterId(int importerId) {
		this.importerId = importerId;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getOppId() {
		return oppId;
	}

	public void setOppId(String oppId) {
		this.oppId = oppId;
	}

}
