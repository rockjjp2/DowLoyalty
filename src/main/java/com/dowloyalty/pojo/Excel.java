package com.dowloyalty.pojo;
/**
 * excel import to database's property
 * @author xiafang
 *
 */
public class Excel {

	private String projectName;
	private String retailerName;
	private String category;
	private String productName;
	private String productFamilyName;
	private double totalPrice;
	private int amount;
	public Excel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Excel(String projectName, String retailerName, String category, String productName, String productFamilyName,
			double totalPrice,int amount) {
		super();
		this.projectName = projectName;
		this.retailerName = retailerName;
		this.category = category;
		this.productName = productName;
		this.productFamilyName = productFamilyName;
		this.totalPrice = totalPrice;
		this.amount = amount;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductFamilyName() {
		return productFamilyName;
	}
	public void setProductFamilyName(String productFamilyName) {
		this.productFamilyName = productFamilyName;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Excel [projectName=" + projectName + ", retailerName=" + retailerName + ", category=" + category
				+ ", productName=" + productName + ", productFamilyName=" + productFamilyName + ", totalPrice=" + totalPrice
				+ ", amount=" + amount
				+ "]";
	}
	
	
}
