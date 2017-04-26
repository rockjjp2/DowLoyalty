package com.dowloyalty.pojo;

import java.io.Serializable;

import com.dowloyalty.entity.ProductCategory;

public class PojoProductFamily implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5117445105403178390L;
	private int id;
	private String name;
	private String description;
	private int categoryID;
	private boolean isActive;

	private ProductCategory productCategory;
	public PojoProductFamily() {
		super();
	}

	public PojoProductFamily(int id, String name, String description, boolean isActive, int categoryID) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isActive = isActive;
		this.categoryID = categoryID;
	}

	public PojoProductFamily(int id, String name, String description, int categoryID, boolean isActive,
			ProductCategory productCategory) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryID = categoryID;
		this.isActive = isActive;
		this.productCategory = productCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", isActive=" + isActive
				+ ", categoryID=" + categoryID + "]";
	}
	
}
