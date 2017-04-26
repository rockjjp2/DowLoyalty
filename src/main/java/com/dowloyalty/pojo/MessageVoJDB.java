package com.dowloyalty.pojo;

import java.util.List;

public class MessageVoJDB {
	private List<MessageVo> messageVos;
	private int totalPage;
	private int nowPage;
	public MessageVoJDB(List<MessageVo> messageVos, int totalPage, int nowPage) {
		super();
		this.messageVos = messageVos;
		this.totalPage = totalPage;
		this.nowPage = nowPage;
	}
	public MessageVoJDB() {
		super();
	}
	public List<MessageVo> getMessageVos() {
		return messageVos;
	}
	public void setMessageVos(List<MessageVo> messageVos) {
		this.messageVos = messageVos;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	@Override
	public String toString() {
		return "MessageVoJDB [messageVos=" + messageVos + ", totalPage=" + totalPage + ", nowPage=" + nowPage + "]";
	}
	
}
