package com.dowloyalty.pojo;

import java.io.Serializable;

public class AccountMapping implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String url;
	private String name;
	private String shippingState;
	private String phone;
	private String lastModifiedDate;
	
	
	public AccountMapping() {
		super();
	}
	public AccountMapping(String id, String url, String name, String shippingState, String phone,
			String lastModifiedDate) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
		this.shippingState = shippingState;
		this.phone = phone;
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShippingState() {
		return shippingState;
	}
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@Override
	public String toString() {
		return "AccountInsert [id=" + id + ", url=" + url + ", name=" + name + ", shippingState=" + shippingState
				+ ", phone=" + phone + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	
	
}
