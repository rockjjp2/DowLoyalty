package com.dowloyalty.pojo;

import java.io.Serializable;

public class FarmerExcel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7889437402877461062L;
	private String projectName;
	private String farmerName;
	private String mobile;
	private float totalPrice;
	private String submitDate;

	public FarmerExcel() {
		super();
	}

	public FarmerExcel(String projectName, String farmerName, String mobile, float totalPrice, String submitDate) {
		super();
		this.projectName = projectName;
		this.farmerName = farmerName;
		this.mobile = mobile;
		this.totalPrice = totalPrice;
		this.submitDate = submitDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

}
