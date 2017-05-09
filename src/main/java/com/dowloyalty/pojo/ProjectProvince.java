package com.dowloyalty.pojo;

public class ProjectProvince {

	private int provinceId;
	private String provinceName;
	private String projectTitle;
	private String startDate;
	private String endDate;
	private String description;
	private byte[] placardPath;
	private byte[] backgroundPath;
	private String placardBase64;
	private String backgroundBase64;
	private boolean haveFarmer;

	public ProjectProvince() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectProvince(int provinceId, String provinceName, String projectTitle, String startDate, String endDate,
			String description, byte[] placardPath, byte[] backgroundPath, String placardBase64,
			String backgroundBase64, boolean haveFarmer) {
		super();
		this.provinceId = provinceId;
		this.provinceName = provinceName;
		this.projectTitle = projectTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.placardPath = placardPath;
		this.backgroundPath = backgroundPath;
		this.placardBase64 = placardBase64;
		this.backgroundBase64 = backgroundBase64;
		this.haveFarmer = haveFarmer;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "ProjectProvince [provinceId=" + provinceId + ", provinceName=" + provinceName + ", projectTitle="
				+ projectTitle + ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description
				+ ", placardPath=" + placardPath + ", backgroundPath=" + backgroundPath + "]";
	}

	public byte[] getPlacardPath() {
		return placardPath;
	}

	public void setPlacardPath(byte[] placardPath) {
		this.placardPath = placardPath;
	}

	public byte[] getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(byte[] backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public String getPlacardBase64() {
		return placardBase64;
	}

	public void setPlacardBase64(String placardBase64) {
		this.placardBase64 = placardBase64;
	}

	public String getBackgroundBase64() {
		return backgroundBase64;
	}

	public void setBackgroundBase64(String backgroundBase64) {
		this.backgroundBase64 = backgroundBase64;
	}

	public boolean isHaveFarmer() {
		return haveFarmer;
	}

	public void setHaveFarmer(boolean haveFarmer) {
		this.haveFarmer = haveFarmer;
	}

}
