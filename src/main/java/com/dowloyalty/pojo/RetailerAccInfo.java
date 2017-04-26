package com.dowloyalty.pojo;

import java.io.Serializable;

public class RetailerAccInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -308853617656718128L;
	private String lvName;
	private String reName;
	private int reId;
	private String provinceName;
	private int totalPoints;
	private int remainPoints;
	private int curRank;
	private int nextRank;
	private int toUpPersonRemainPoints;
	private String rankPercent;
	private String nextLv;
	private int toNextLvRemainPoints;
	private int totalPrice;

	public RetailerAccInfo() {
		super();
	}

	public RetailerAccInfo(String lvName, String reName, int reId, String provinceName, int totalPoints,
			int remainPoints, int curRank, int nextRank, int toUpPersonRemainPoints, String rankPercent, String nextLv,
			int toNextLvRemainPoints, int totalPrice) {
		super();
		this.lvName = lvName;
		this.reName = reName;
		this.reId = reId;
		this.provinceName = provinceName;
		this.totalPoints = totalPoints;
		this.remainPoints = remainPoints;
		this.curRank = curRank;
		this.nextRank = nextRank;
		this.toUpPersonRemainPoints = toUpPersonRemainPoints;
		this.rankPercent = rankPercent;
		this.nextLv = nextLv;
		this.toNextLvRemainPoints = toNextLvRemainPoints;
		this.totalPrice = totalPrice;
	}

	public String getLvName() {
		return lvName;
	}

	public void setLvName(String lvName) {
		this.lvName = lvName;
	}

	public String getReName() {
		return reName;
	}

	public void setReName(String reName) {
		this.reName = reName;
	}

	public int getReId() {
		return reId;
	}

	public void setReId(int reId) {
		this.reId = reId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public int getRemainPoints() {
		return remainPoints;
	}

	public void setRemainPoints(int remainPoints) {
		this.remainPoints = remainPoints;
	}

	public int getCurRank() {
		return curRank;
	}

	public void setCurRank(int curRank) {
		this.curRank = curRank;
	}

	public int getNextRank() {
		return nextRank;
	}

	public void setNextRank(int nextRank) {
		this.nextRank = nextRank;
	}

	public int getToUpPersonRemainPoints() {
		return toUpPersonRemainPoints;
	}

	public void setToUpPersonRemainPoints(int toUpPersonRemainPoints) {
		this.toUpPersonRemainPoints = toUpPersonRemainPoints;
	}

	public String getRankPercent() {
		return rankPercent;
	}

	public void setRankPercent(String rankPercent) {
		this.rankPercent = rankPercent;
	}

	public String getNextLv() {
		return nextLv;
	}

	public void setNextLv(String nextLv) {
		this.nextLv = nextLv;
	}

	public int getToNextLvRemainPoints() {
		return toNextLvRemainPoints;
	}

	public void setToNextLvRemainPoints(int toNextLvRemainPoints) {
		this.toNextLvRemainPoints = toNextLvRemainPoints;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
