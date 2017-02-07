package com.dowloyalty.entity;

import java.io.Serializable;

public class ExchangeShop implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8306914937602103819L;
	private int id;
	private int projectID;
	private int goodsID;
	private int exchangePoints;
	private boolean isActive;

	public ExchangeShop() {
		super();
	}

	public ExchangeShop(int id, int provinceID, int goodsID, int exchangePoints, boolean isActive) {
		super();
		this.id = id;
		this.projectID = provinceID;
		this.goodsID = goodsID;
		this.exchangePoints = exchangePoints;
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

	public int getGoodsID() {
		return goodsID;
	}

	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}

	public int getExchangePoints() {
		return exchangePoints;
	}

	public void setExchangePoints(int exchangePoints) {
		this.exchangePoints = exchangePoints;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "ExchangeShop [id=" + id + ", projectID=" + projectID + ", goodsID=" + goodsID + ", exchangePoints="
				+ exchangePoints + ", isActive=" + isActive + "]";
	}

	
	
}
