package com.dowloyalty.pojo;

public class ExchangeshopGoods {
	
	private int goodsId;
	private int exPoints;
	private String gName;
	public ExchangeshopGoods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExchangeshopGoods(int goodsId, int exPoints, String gName) {
		super();
		this.goodsId = goodsId;
		this.exPoints = exPoints;
		this.gName = gName;
	}
	public int getExPoints() {
		return exPoints;
	}
	public void setExPoints(int exPoints) {
		this.exPoints = exPoints;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	@Override
	public String toString() {
		return "ExchangeshopGoods [goodsId=" + goodsId +"exPoints=" + exPoints + ", gName=" + gName + "]";
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	
}
