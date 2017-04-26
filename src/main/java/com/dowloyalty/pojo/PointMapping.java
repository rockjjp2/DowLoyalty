package com.dowloyalty.pojo;

import java.io.Serializable;
/**
 * 
 * Rownum：排名；RetailerID，ProjectID
 * SalesPoint：销售总积分，Initpoint：初始总积分
 * TotalPoint=SalesPoint+Initpoint
 * @author MingXuan
 *
 */
public class PointMapping implements Serializable{
	private static final long serialVersionUID = 7969647755355320008L;
	private int Rownum;
	private int RetailerID;
	private int ProjectID;
	private int SalesPoint;
	private int Initpoint;
	private int TotalPoint;
	public PointMapping() {
		super();
	}
	public PointMapping(int rownum, int retailerID, int projectID, int salesPoint, int initpoint, int totalPoint) {
		super();
		Rownum = rownum;
		RetailerID = retailerID;
		ProjectID = projectID;
		SalesPoint = salesPoint;
		Initpoint = initpoint;
		TotalPoint = totalPoint;
	}
	
	public int getRownum() {
		return Rownum;
	}
	public void setRownum(int rownum) {
		Rownum = rownum;
	}
	public int getRetailerID() {
		return RetailerID;
	}
	public void setRetailerID(int retailerID) {
		RetailerID = retailerID;
	}
	public int getProjectID() {
		return ProjectID;
	}
	public void setProjectID(int projectID) {
		ProjectID = projectID;
	}
	public int getSalesPoint() {
		return SalesPoint;
	}
	public void setSalesPoint(int salesPoint) {
		SalesPoint = salesPoint;
	}
	public int getInitpoint() {
		return Initpoint;
	}
	public void setInitpoint(int initpoint) {
		Initpoint = initpoint;
	}
	public int getTotalPoint() {
		return TotalPoint;
	}
	public void setTotalPoint(int totalPoint) {
		TotalPoint = totalPoint;
	}
	
	@Override
	public String toString() {
		return "PointMapping [Rownum=" + Rownum + ", RetailerID=" + RetailerID + ", ProjectID=" + ProjectID
				+ ", SalesPoint=" + SalesPoint + ", Initpoint=" + Initpoint + ", TotalPoint=" + TotalPoint + "]";
	}
	
}
