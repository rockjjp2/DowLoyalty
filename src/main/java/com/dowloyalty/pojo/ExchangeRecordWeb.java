package com.dowloyalty.pojo;

import java.io.Serializable;

public class ExchangeRecordWeb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2443596167944574595L;
	private String date;
	private String retailerName;
	private String provinceName;
	private String goodsName;
	private int amount;

	public ExchangeRecordWeb() {
		super();
	}

	public ExchangeRecordWeb(String date, String retailerName, String provinceName, String goodsName, int amount) {
		super();
		this.date = date;
		this.retailerName = retailerName;
		this.provinceName = provinceName;
		this.goodsName = goodsName;
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
