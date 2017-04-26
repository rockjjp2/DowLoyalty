package com.dowloyalty.pojo.wechat;

public class BaseMessage {
	// 开发者微信号
    private String ToUserName;
    // 发送方帐号（一个 OpenID）
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/image/location/link/video/shortvideo）
    private String MsgType;
    
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	@Override
	public String toString() {
		return "BaseMessage [DevelopID=" + ToUserName + ", OpenID=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType +  "]";
	}


}
