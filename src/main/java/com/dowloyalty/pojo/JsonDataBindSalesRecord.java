package com.dowloyalty.pojo;

import java.util.List;

import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.entity.Retailer;

public class JsonDataBindSalesRecord {
	private String message;
	private List<Product> product;
	private List<ProductFamily> productFamilies;
	private List<ProductCategory> productCategories;
	private List<Retailer> retailers;

	
	public JsonDataBindSalesRecord() {
		super();
	}
	
	public JsonDataBindSalesRecord(List<Product> product, List<ProductFamily> productFamilies,
			List<ProductCategory> productCategories, List<Retailer> retailers) {
		super();
		this.message ="正常";
		this.product = product;
		this.productFamilies = productFamilies;
		this.productCategories = productCategories;
		this.retailers = retailers;
	}

	public JsonDataBindSalesRecord(String message, List<Product> product, List<ProductFamily> productFamilies,
			List<ProductCategory> productCategories, List<Retailer> retailers) {
		super();
		this.message = message;
		this.product = product;
		this.productFamilies = productFamilies;
		this.productCategories = productCategories;
		this.retailers = retailers;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public List<ProductFamily> getProductFamilies() {
		return productFamilies;
	}
	public void setProductFamilies(List<ProductFamily> productFamilies) {
		this.productFamilies = productFamilies;
	}
	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}
	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}
	public List<Retailer> getRetailers() {
		return retailers;
	}
	public void setRetailers(List<Retailer> retailers) {
		this.retailers = retailers;
	}
	@Override
	public String toString() {
		return "JsonDataBindSalesRecord [message=" + message + ", product=" + product + ", productFamilies="
				+ productFamilies + ", productCategories=" + productCategories + ", retailers=" + retailers + "]";
	}
	
}
