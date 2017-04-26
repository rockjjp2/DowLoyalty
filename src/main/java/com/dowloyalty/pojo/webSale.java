package com.dowloyalty.pojo;


public class webSale {

	private String projectName;
	private String submitDate;
	private String retaileName;
	private String categoryName;
	private String productName;
	private String familyName;
	private String totalPrice;
	private int amount;
	
	public webSale() {
		super();
		// TODO Auto-generated constructor stub
	}


	public webSale(String projectName, String submitDate, String retaileName, String categoryName, String productName,
			String familyName, String totalPrice, int amount) {
		super();
		this.projectName = projectName;
		this.submitDate = submitDate;
		this.retaileName = retaileName;
		this.categoryName = categoryName;
		this.productName = productName;
		this.familyName = familyName;
		this.totalPrice = totalPrice;
		this.amount = amount;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getSubmitDate() {
		return submitDate;
	}


	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}


	public String getRetaileName() {
		return retaileName;
	}


	public void setRetaileName(String retaileName) {
		this.retaileName = retaileName;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getFamilyName() {
		return familyName;
	}


	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(String totalPrice) {
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
		return "webSale [projectName=" + projectName + ", submitDate=" + submitDate + ", retaileName=" + retaileName
				+ ", categoryName=" + categoryName + ", productName=" + productName + ", familyName=" + familyName
				+ ", totalPrice=" + totalPrice + ", amount=" + amount + "]";
	}
	
	
}
