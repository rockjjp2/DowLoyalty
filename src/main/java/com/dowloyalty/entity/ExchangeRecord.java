package com.dowloyalty.entity;

import java.io.Serializable;

public class ExchangeRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3320214017885122456L;
	private int id;
	private int retailerID;
	private int goodsID;
	private int amount;
	private String submitTime;
	private String sendOutTime;
	private String completeTime;
	private int status;
	private int exchangePoints;

	public ExchangeRecord() {
		super();
	}

	public ExchangeRecord(int id, int retailerID, int goodsID, int amount, String submitTime, String sendOutTime,
			String completeTime, int status, int exchangePoints) {
		super();
		this.id = id;
		this.retailerID = retailerID;
		this.goodsID = goodsID;
		this.amount = amount;
		this.submitTime = submitTime;
		this.sendOutTime = sendOutTime;
		this.completeTime = completeTime;
		this.status = status;
		this.exchangePoints = exchangePoints;
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

	public int getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getSendOutTime() {
		return sendOutTime;
	}

	public void setSendOutTime(String sendOutTime) {
		this.sendOutTime = sendOutTime;
	}

	public String getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getExchangePoints() {
		return exchangePoints;
	}

	public void setExchangePoints(int exchangePoints) {
		this.exchangePoints = exchangePoints;
	}

	@Override
	public String toString() {
		return "ExchangeRecord [id=" + id + ", retailerID=" + retailerID + ", goodsID=" + goodsID + ", amount=" + amount
				+ ", submitTime=" + submitTime + ", sendOutTime=" + sendOutTime + ", completeTime=" + completeTime
				+ ", status=" + status + ", exchangePoints=" + exchangePoints + "]";
	}
	
}
