package com.dowloyalty.entity;

import java.io.Serializable;

public class PointsLevel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2928934662752952943L;
	private int id;
	private int projectID;
	private String name;
	private int points;
	private boolean isActive;

	public PointsLevel() {
		super();
	}

	public PointsLevel(int id, int projectID, String name, int points, boolean isActive) {
		super();
		this.id = id;
		this.projectID = projectID;
		this.name = name;
		this.points = points;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
