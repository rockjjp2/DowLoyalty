package com.dowloyalty.entity;

import java.io.Serializable;

public class RProjectFarmer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4973810190664879180L;
	private int id;
	private int farmerId;
	private int projectId;
	private boolean isActive;

	public RProjectFarmer() {
		super();
	}

	public RProjectFarmer(int id, int farmerId, int projectId, boolean isActive) {
		super();
		this.id = id;
		this.farmerId = farmerId;
		this.projectId = projectId;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
