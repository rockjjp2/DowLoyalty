package com.dowloyalty.pojo.wechat;

import java.util.Map;

public class PointChangeMessage {

	private String touser;
	private String template_id;
	private Map<String,Map<String,String>> data;

	public PointChangeMessage() {
		super();
	}

	public PointChangeMessage(String touser, String template_id, Map<String,Map<String,String>> data) {
		super();
		this.touser = touser;
		this.template_id = template_id;
		this.data = data;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public Map<String,Map<String,String>> getData() {
		return data;
	}

	public void setData(Map<String,Map<String,String>> data) {
		this.data = data;
	}

}
