package com.dowloyalty.entity;

import java.io.Serializable;

public class ProductCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2094971292687806696L;
	private int id;
	private String name;
	private String description;
	private boolean isActive;

	public ProductCategory() {
		super();
	}

	public ProductCategory(int id, String name, String description, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.isActive = isActive;
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

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", description=" + description + ", isActive="
				+ isActive + "]";
	}
	
}
