package com.dowloyalty.pojo;

public class RetailerProvince {

	private int retailerId;
	private String retailerName;
	private String provinceName;
	public RetailerProvince() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RetailerProvince(int retailerId, String retailerName, String provinceName) {
		super();
		this.retailerId = retailerId;
		this.retailerName = retailerName;
		this.provinceName = provinceName;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public int getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}
	@Override
	public String toString() {
		return "RetailerProvince [retailerId=" + retailerId + ", retailerName=" + retailerName + ", provinceName="
				+ provinceName + "]";
	}
	
	
}
