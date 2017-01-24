package com.dowloyalty.pojo;

import java.util.List;

import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Retailer;

public class JsonDataBind {
	private String message;
	private List<Retailer> retailers;
	private List<Promoter> promoters;
	
	public JsonDataBind() {
		super();
	}
	public JsonDataBind(List<Retailer> retailers, List<Promoter> promoters) {
		super();
		this.message = "normal";
		this.retailers = retailers;
		this.promoters = promoters;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Retailer> getRetailers() {
		return retailers;
	}
	public void setRetailers(List<Retailer> retailers) {
		this.retailers = retailers;
	}
	public List<Promoter> getPromoters() {
		return promoters;
	}
	public void setPromoters(List<Promoter> promoters) {
		this.promoters = promoters;
	}
	@Override
	public String toString() {
		return "JsonDataBind [retailers=" + retailers + ", promoters=" + promoters + "]";
	}
	
}
