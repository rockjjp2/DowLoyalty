package com.dowloyalty.utils;

/**
 * 分页应用枚举类
 * 
 * @author wangyuanjie
 *
 */
public enum Page {
	ShOWNUM(10), SENDSHOWNUM(2000), CODE("code"),
	ISSUER("capgemini"),SUBJECT("salerecord");

	private int num;
	private String info;

	Page(int num) {
		this.num = num;
	}

	Page(String info) {
		this.info = info;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
