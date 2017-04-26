package com.dowloyalty.pojo.wechat;
/**
 * @Description: 链接消息
 * @author zhoum
 *
 */
public class LinkMessage extends BaseMessage {
	// 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}