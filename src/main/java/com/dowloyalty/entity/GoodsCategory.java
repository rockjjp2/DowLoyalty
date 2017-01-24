package com.dowloyalty.entity;

import java.io.Serializable;

public class GoodsCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3129441359446486789L;
	private int id;
	private String name;
	private String description;
	private boolean isActive;

	public GoodsCategory() {
		super();
	}

	public GoodsCategory(int id, String name, String description, boolean isActive) {
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "GoodsCategory [id=" + id + ", name=" + name + ", description=" + description + ", isActive=" + isActive
				+ "]";
	}
	
}
