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
	
	
	public Goods() {
		super();
	}
	public Goods(int id, String name, String description, String imagePath, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", description=" + description + ", imagePath=" + imagePath
				+ ", isActive=" + isActive + "]";
	}
	
	
}
