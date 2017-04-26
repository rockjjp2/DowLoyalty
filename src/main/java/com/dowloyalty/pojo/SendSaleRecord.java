package com.dowloyalty.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class SendSaleRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5942488030714929033L;

	private Timestamp submitDate;
	private String retailerId;
	private int saleRecordId;
	private String productId;
	private String productName;
	private int amount;
	private float totalPrice;

	public SendSaleRecord() {
		super();
	}

	public Timestamp getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Timestamp submitDate) {
		this.submitDate = submitDate;
	}

	public SendSaleRecord(Timestamp submitDate, String retailerId, int saleRecordId, String productId, String productName,
			int amount, float totalPrice) {
		super();
		this.submitDate = submitDate;
		this.retailerId = retailerId;
		this.saleRecordId = saleRecordId;
		this.productId = productId;
		this.productName = productName;
		this.amount = amount;
		this.totalPrice = totalPrice;
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

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getSaleRecordId() {
		return saleRecordId;
	}

	public void setSaleRecordId(int saleRecordId) {
		this.saleRecordId = saleRecordId;
	}

}
