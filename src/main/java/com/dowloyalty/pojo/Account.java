package com.dowloyalty.pojo;

public class Account {
	private Attributes attributes;
	private String Id;
	private String Name;
	private String ShippingState;
	private String Phone;
	private String LastModifiedDate;

	
	public Account() {
		super();
	}
	public Account(Attributes attributes, String id, String name, String shippingState, String phone,
			String lastModifiedDate) {
		super();
		this.attributes = attributes;
		Id = id;
		Name = name;
		ShippingState = shippingState;
		Phone = phone;
		LastModifiedDate = lastModifiedDate;
	}
	
	
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getShippingState() {
		return ShippingState;
	}
	public void setShippingState(String shippingState) {
		ShippingState = shippingState;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getLastModifiedDate() {
		return LastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}
	public String getAttributesUrl() {
		return attributes.getUrl();
	}
	@Override
	public String toString() {
		return "Account [attributes=" + attributes + ", Id=" + Id + ", Name=" + Name + ", ShippingState="
				+ ShippingState + ", Phone=" + Phone + ", LastModifiedDate=" + LastModifiedDate + "]";
	}
}
class Attributes{
	
	private String name;
	private String url;
	public Attributes(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}
	public Attributes() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Attributes [name=" + name + ", url=" + url + "]";
	}
	
}
