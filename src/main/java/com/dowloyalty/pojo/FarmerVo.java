package com.dowloyalty.pojo;

import java.io.Serializable;

public class FarmerVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6677948157510005735L;
	private int id;
	private String name;
	private String projectName;
	private float totalPrice;
	private int rank;
	private int nextRank;
	private float toNextRankRemainPrice;
	private int percent;

	public FarmerVo() {
		super();
	}

	public FarmerVo(int id, String name, String projectName, float totalPrice, int rank, int nextRank,
			float toNextRankRemainPrice, int percent) {
		super();
		this.id = id;
		this.name = name;
		this.projectName = projectName;
		this.totalPrice = totalPrice;
		this.rank = rank;
		this.nextRank = nextRank;
		this.toNextRankRemainPrice = toNextRankRemainPrice;
		this.percent = percent;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getNextRank() {
		return nextRank;
	}

	public void setNextRank(int nextRank) {
		this.nextRank = nextRank;
	}

	public float getToNextRankRemainPrice() {
		return toNextRankRemainPrice;
	}

	public void setToNextRankRemainPrice(float toNextRankRemainPrice) {
		this.toNextRankRemainPrice = toNextRankRemainPrice;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

}
