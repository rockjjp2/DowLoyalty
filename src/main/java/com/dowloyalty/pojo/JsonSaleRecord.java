package com.dowloyalty.pojo;

import java.util.List;

import com.dowloyalty.entity.Project;

public class JsonSaleRecord {

	private String projectId ;
	private String startDate ;
	private String endDate ;
	private String retailerName;
	private int pageNum;//当前页码
	private int maxPageNum;//最大页数
	private List<Project> projects;
	private List<webSale> webSales;
	public JsonSaleRecord(String projectId, String startDate, String endDate, String retailerName, int pageNum,
			int maxPageNum, List<Project> projects, List<webSale> webSales) {
		super();
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.retailerName = retailerName;
		this.pageNum = pageNum;
		this.maxPageNum = maxPageNum;
		this.projects = projects;
		this.webSales = webSales;
	}
	public JsonSaleRecord(String projectId, String startDate, String endDate, String retailerName, int pageNum,
			int maxPageNum,  List<webSale> webSales) {
		super();
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.retailerName = retailerName;
		this.pageNum = pageNum;
		this.maxPageNum = maxPageNum;
		this.webSales = webSales;
	}


	public JsonSaleRecord() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public List<webSale> getWebSales() {
		return webSales;
	}
	public void setWebSales(List<webSale> webSales) {
		this.webSales = webSales;
	}
	@Override
	public String toString() {
		return "JsonSaleRecord [projectId=" + projectId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", retailerName=" + retailerName + ", pageNum=" + pageNum + ", maxPageNum=" + maxPageNum
				+  ", projects=" + projects
				+ ", webSales=" + webSales + "]";
	}
	
	
}
