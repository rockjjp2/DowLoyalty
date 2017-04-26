package com.dowloyalty.pojo;

import java.util.List;

import com.dowloyalty.entity.Retailer;

public class JsonRetailerPage {

	private List<Retailer> retailers;
	private String projectId;
	private String provinceId;
	private String retailerName;
	private int pageNum;//当前页码
	private int maxPageNum;//最大页数
	public JsonRetailerPage(List<Retailer> retailers, String provinceId, String retailerName, int pageNum,
			int maxPageNum) {
		super();
		this.retailers = retailers;
		this.provinceId = provinceId;
		this.retailerName = retailerName;
		this.pageNum = pageNum;
		this.maxPageNum = maxPageNum;
	}
	public JsonRetailerPage(List<Retailer> retailers, String projectId, String provinceId, String retailerName,
			int pageNum, int maxPageNum) {
		super();
		this.retailers = retailers;
		this.projectId = projectId;
		this.provinceId = provinceId;
		this.retailerName = retailerName;
		this.pageNum = pageNum;
		this.maxPageNum = maxPageNum;
	}
	public JsonRetailerPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Retailer> getRetailers() {
		return retailers;
	}
	public void setRetailers(List<Retailer> retailers) {
		this.retailers = retailers;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getMaxPageNum() {
		return maxPageNum;
	}
	public void setMaxPageNum(int maxPageNum) {
		this.maxPageNum = maxPageNum;
	}
	@Override
	public String toString() {
		return "JsonRetailerPage [retailers=" + retailers + ", projectId=" + projectId + ", provinceId=" + provinceId
				+ ", retailerName=" + retailerName + ", pageNum=" + pageNum + ", maxPageNum=" + maxPageNum + "]";
	}
	
	
}
