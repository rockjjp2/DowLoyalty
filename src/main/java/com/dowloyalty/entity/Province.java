package com.dowloyalty.entity;

import java.io.Serializable;

public class Province implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 411409424243701148L;
	private int id;
	private String name;
	private boolean isActive;

	public Province() {
		super();
	}

	public Province(int id, String name, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Province [id=" + id + ", name=" + name + ", isActive=" + isActive + "]";
	}
	
}
