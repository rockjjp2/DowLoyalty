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
	public JsonDataBind(List<Retailer> retailers,
			List<Promoter> promoters) {
		super();
		this.message = "normal";
		this.retailers = retailers;
		this.promoters = promoters;
	}
	
	
	public List<Retailer> getretailers() {
		return retailers;
	}
	public void setretailers(List<Retailer> retailers) {
		this.retailers = retailers;
	}
	public List<Promoter> getpromoters() {
		return promoters;
	}
	public void setpromoters(List<Promoter> promoters) {
		this.promoters = promoters;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "JsonDataBind [message=" + message + ", retailers=" + retailers + ", promoters="
				+ promoters + "]";
	}
	
}
