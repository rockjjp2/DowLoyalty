package com.dowloyalty.pojo;

import java.io.Serializable;

public class ProductInfos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -166297406153354693L;
	private int productId;
	private String productName;
	private String productCategory;
	private String productFamily;
	private float salesAmount;

	public ProductInfos() {
		super();
	}

	public ProductInfos(int productId, String productName, String productCategory, String productFamily, float salesAmount) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productFamily = productFamily;
		this.salesAmount = salesAmount;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}

	public float getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(float salesAmount) {
		this.salesAmount = salesAmount;
	}

}
