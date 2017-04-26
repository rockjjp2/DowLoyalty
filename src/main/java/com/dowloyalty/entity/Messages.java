package com.dowloyalty.entity;

import java.io.Serializable;

public class Messages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7142510621366290467L;
	private int id;
	private int retailerId;
	private String message;
	private String submitTime;
	private Boolean status;
	private int initPointsID;
	private String executorId;
	
	
	
	public Messages() {
		super();
	}
	public Messages(int id, int retailerId, String message, String submitTime, Boolean status, int initPointsID,
			String executorId) {
		super();
		this.id = id;
		this.retailerId = retailerId;
		this.message = message;
		this.submitTime = submitTime;
		this.status = status;
		this.initPointsID = initPointsID;
		this.executorId = executorId;
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
	public String getExecutorId() {
		return executorId;
	}
	public void setExecutorId(String executorId) {
		this.executorId = executorId;
	}
	@Override
	public String toString() {
		return "Messages [id=" + id + ", retailerId=" + retailerId + ", message=" + message + ", submitTime="
				+ submitTime + ", status=" + status + ", initPointsID=" + initPointsID + ", executorId=" + executorId
				+ "]";
	}
	
}
