package com.dowloyalty.entity;

import java.io.Serializable;

public class SaleRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2059308991403547165L;
	private int id;
	private int retailerID;
	private int productSepcificationID;
	private int amount;
	private float totalPrice;
	private int importerID;
	private String submitDate;
	private int projectID;
	private int points;

	public SaleRecord() {
		super();
	}

	public SaleRecord(int id, int retailerID, int productSepcificationID, int amount, float totalPrice, int importerID,
			String submitDate, int projectID, int points) {
		super();
		this.id = id;
		this.retailerID = retailerID;
		this.productSepcificationID = productSepcificationID;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.importerID = importerID;
		this.submitDate = submitDate;
		this.projectID = projectID;
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRetailerID() {
		return retailerID;
	}

	public void setRetailerID(int retailerID) {
		this.retailerID = retailerID;
	}

	public int getProductSepcificationID() {
		return productSepcificationID;
	}

	public void setProductSepcificationID(int productSepcificationID) {
		this.productSepcificationID = productSepcificationID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getImporterID() {
		return importerID;
	}

	public void setImporterID(int importerID) {
		this.importerID = importerID;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "SaleRecord [id=" + id + ", retailerID=" + retailerID + ", productSepcificationID="
				+ productSepcificationID + ", amount=" + amount + ", totalPrice=" + totalPrice + ", importerID="
				+ importerID + ", submitDate=" + submitDate + ", projectID=" + projectID + ", points=" + points + "]";
	}
	
}
