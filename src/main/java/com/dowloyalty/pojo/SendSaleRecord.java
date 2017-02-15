package com.dowloyalty.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class SendSaleRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5942488030714929033L;
	private Timestamp submitDate;
	private String retailerCode;
	private String productCode;
	private int amount;
	private float totalPrice;

	public SendSaleRecord() {
		super();
	}

	public SendSaleRecord(Timestamp submitDate, String retailerCode, String productCode, int amount, float totalPrice) {
		super();
		this.submitDate = submitDate;
		this.retailerCode = retailerCode;
		this.productCode = productCode;
		this.amount = amount;
		this.totalPrice = totalPrice;
	}

	public Timestamp getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Timestamp submitDate) {
		this.submitDate = submitDate;
	}

	public String getRetailerCode() {
		return retailerCode;
	}

	public void setRetailerCode(String retailerCode) {
		this.retailerCode = retailerCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

}
