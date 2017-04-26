package com.dowloyalty.pojo;

import java.util.List;

import com.dowloyalty.entity.Promoter;

public class JsonPromoterPage {

	private List<Promoter> promoters;
	private String projectId;
	private String provinceId;
	private String promoterName;
	private int pageNum;//当前页码
	private int maxPageNum;//最大页数
	public JsonPromoterPage(List<Promoter> promoters, String provinceId, String promoterName, int pageNum,
			int maxPageNum) {
		super();
		this.promoters = promoters;
		this.provinceId = provinceId;
		this.promoterName = promoterName;
		this.pageNum = pageNum;
		this.maxPageNum = maxPageNum;
	}
	public JsonPromoterPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JsonPromoterPage(List<Promoter> promoters, String projectId, String provinceId, String promoterName,
			int pageNum, int maxPageNum) {
		super();
		this.promoters = promoters;
		this.projectId = projectId;
		this.provinceId = provinceId;
		this.promoterName = promoterName;
		this.pageNum = pageNum;
		this.maxPageNum = maxPageNum;
	}
	public List<Promoter> getPromoters() {
		return promoters;
	}
	public void setPromoters(List<Promoter> promoters) {
		this.promoters = promoters;
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
	public String getPromoterName() {
		return promoterName;
	}
	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
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
		return "JsonPromoterPage [promoters=" + promoters + ", projectId=" + projectId + ", provinceId=" + provinceId
				+ ", promoterName=" + promoterName + ", pageNum=" + pageNum + ", maxPageNum=" + maxPageNum + "]";
	}
	
	
}
