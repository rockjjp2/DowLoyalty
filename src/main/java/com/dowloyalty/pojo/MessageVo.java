package com.dowloyalty.pojo;

import java.io.Serializable;
import java.util.List;

import com.dowloyalty.entity.Project;

/**
 * Messages混合类，包含一下信息
 * Messageid，retailerid，retailerName，Message，提交时间，状态，projectlist信息
 * @author MingXuan
 *
 */
public class MessageVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3028260148327420366L;
	private int id;
	private int retailerId;
	private String retailerName;
	private String message;
	private String submitTime;
	private Boolean status;
	private int initPointsID;
	List<Project> projects;
	public MessageVo() {
		super();
	}
	public MessageVo(int id, int retailerId, String retailerName, String message, String submitTime, Boolean status,
			int initPointsID, List<Project> projects) {
		super();
		this.id = id;
		this.retailerId = retailerId;
		this.retailerName = retailerName;
		this.message = message;
		this.submitTime = submitTime;
		this.status = status;
		this.initPointsID = initPointsID;
		this.projects = projects;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getInitPointsID() {
		return initPointsID;
	}
	public void setInitPointsID(int initPointsID) {
		this.initPointsID = initPointsID;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	@Override
	public String toString() {
		return "MessageVo [id=" + id + ", retailerId=" + retailerId + ", retailerName=" + retailerName + ", message="
				+ message + ", submitTime=" + submitTime + ", status=" + status + ", initPointsID=" + initPointsID
				+ ", projects=" + projects + "]";
	}
	
}
