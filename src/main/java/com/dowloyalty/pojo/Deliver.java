package com.dowloyalty.pojo;

public class Deliver {
	private int exchangId;
	private String provinceName;
	private String retailerName;
	private String goodsName;
	private String exchangAmount;
	public Deliver() {
		super();
	}
	public Deliver(int exchangId, String provinceName, String retailerName, String goodsName, String exchangAmount) {
		super();
		this.exchangId = exchangId;
		this.provinceName = provinceName;
		this.retailerName = retailerName;
		this.goodsName = goodsName;
		this.exchangAmount = exchangAmount;
	}
	public int getExchangId() {
		return exchangId;
	}
	public void setExchangId(int exchangId) {
		this.exchangId = exchangId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getExchangAmount() {
		return exchangAmount;
	}
	public void setExchangAmount(String exchangAmount) {
		this.exchangAmount = exchangAmount;
	}
	@Override
	public String toString() {
		return "Deliver [exchangId=" + exchangId + ", provinceName=" + provinceName + ", retailerName=" + retailerName
				+ ", goodsName=" + goodsName + ", exchangAmount=" + exchangAmount + "]";
	}
	
	
}
