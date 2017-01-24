package com.dowloyalty.entity;

import java.io.Serializable;

public class Goods implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7834765180896547069L;
	private int id;
	private String name;
	private String description;
	private String imagePath;
	private boolean isActive;
	private int goodsCategoryID;

	public Goods() {
		super();
	}

	public Goods(int id, String name, String description, String imagePath, boolean isActive, int goodsCategoryID) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
		this.isActive = isActive;
		this.goodsCategoryID = goodsCategoryID;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getGoodsCategoryID() {
		return goodsCategoryID;
	}

	public void setGoodsCategoryID(int goodsCategoryID) {
		this.goodsCategoryID = goodsCategoryID;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", description=" + description + ", imagePath=" + imagePath
				+ ", isActive=" + isActive + ", goodsCategoryID=" + goodsCategoryID + "]";
	}
	
}
