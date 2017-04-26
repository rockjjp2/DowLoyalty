package com.dowloyalty.pojo;

import java.io.Serializable;


/**
 * 积分明细类
 * 
 * @author wanguanjie
 *
 */
public class PointsDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5782214787016404901L;
	private String name;
	private int points;
	private String submitTime;

	public PointsDetails() {
		super();
	}

	public PointsDetails(String name, int points, String submitTime) {
		super();
		this.name = name;
		this.points = points;
		this.submitTime = submitTime;
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

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}


}
