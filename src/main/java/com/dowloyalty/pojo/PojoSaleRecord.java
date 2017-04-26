package com.dowloyalty.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.dowloyalty.entity.Retailer;

public class PojoSaleRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2059308991403547165L;
	private int id;
	private int retailerID;
	private int productID;
	private float totalPrice;
	private int importerID;
	private Timestamp submitDate;
	private int projectID;
	private int points;

	private Retailer retailer;
	private PojoProduct product;
	public PojoSaleRecord() {
		super();
	}

	public PojoSaleRecord(int id, int retailerID, int productID, float totalPrice, int importerID, Timestamp submitDate,
			int projectID, int points) {
		super();
		this.id = id;
		this.retailerID = retailerID;
		this.productID = productID;
		this.totalPrice = totalPrice;
		this.importerID = importerID;
		this.submitDate = submitDate;
		this.projectID = projectID;
		this.points = points;
	}

	public PojoSaleRecord(int id, int retailerID, int productID, float totalPrice, int importerID, Timestamp submitDate,
			int projectID, int points, Retailer retailer, PojoProduct product) {
		super();
		this.id = id;
		this.retailerID = retailerID;
		this.productID = productID;
		this.totalPrice = totalPrice;
		this.importerID = importerID;
		this.submitDate = submitDate;
		this.projectID = projectID;
		this.points = points;
		this.retailer = retailer;
		this.product = product;
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

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
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

	public Timestamp getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Timestamp submitDate) {
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


	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

	public PojoProduct getProduct() {
		return product;
	}

	public void setProduct(PojoProduct product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "SaleRecord [id=" + id + ", retailerID=" + retailerID + ", productID=" + productID + ", totalPrice="
				+ totalPrice + ", importerID=" + importerID + ", submitDate=" + submitDate + ", projectID=" + projectID
				+ ", points=" + points + "]";
	}
	
}
