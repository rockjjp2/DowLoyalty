package com.dowloyalty.pojo;

public class PromoterProvince {

	private int promoterId;
	private String promoterName;
	private String provinceName;
	public PromoterProvince() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PromoterProvince(int promoterId, String promoterName, String provinceName) {
		super();
		this.promoterId = promoterId;
		this.promoterName = promoterName;
		this.provinceName = provinceName;
	}
	public int getPromoterId() {
		return promoterId;
	}
	public void setPromoterId(int promoterId) {
		this.promoterId = promoterId;
	}
	public String getPromoterName() {
		return promoterName;
	}
	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	@Override
	public String toString() {
		return "PromoterProvince [promoterId=" + promoterId + ", promoterName=" + promoterName + ", provinceName="
				+ provinceName + "]";
	}
	
	
}
