package com.dowloyalty.pojo;

public class PromoterVo {
	private int id;
	private String chineseName;
	private String userID;
	private String provinceName;
	private int provinceId;
	private boolean isActive;

	public PromoterVo() {
		super();
	}

	public PromoterVo(int id, String chineseName, String userID, String provinceName, int provinceId,
			boolean isActive) {
		super();
		this.id = id;
		this.chineseName = chineseName;
		this.userID = userID;
		this.provinceName = provinceName;
		this.provinceId = provinceId;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
