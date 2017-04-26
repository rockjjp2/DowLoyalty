package com.dowloyalty.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dowloyalty.pojo.wechat.TextMessage;
import com.dowloyalty.utils.MessageUtil;

public class WeChatDispatcher {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	/**
	 * 用户消息处理器，根据传入信息，制定返回文字信息
	 * @param map 用户消息数据的Map数据集合
	 * @param outMsg 用户自定义返回消息
	 * @return
	 */
	public String MessageDispatcher(Map<String, String> map,String outMsg) {
		String openid=map.get("FromUserName"); //用户 openid
		String mpid=map.get("ToUserName");   //公众号原始 ID
		logger.info("用户openid为："+openid);
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
        	TextMessage txtmsg=new TextMessage();
        	txtmsg.setToUserName(openid);
        	txtmsg.setFromUserName(mpid);
        	txtmsg.setCreateTime(new Date().getTime());
        	txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        	txtmsg.setContent(outMsg);
        	logger.info("用户留言为："+map.get("Content"));
        	
            return MessageUtil.textMessageToXml(txtmsg);
        }else {
        	TextMessage txtmsg=new TextMessage();
        	txtmsg.setToUserName(openid);
        	txtmsg.setFromUserName(mpid);
        	txtmsg.setCreateTime(new Date().getTime());
        	txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        	txtmsg.setContent("很抱歉，暂时只能接受文本留言，感谢您的支持");
        	logger.info("用户留言为非文本事件。");
        	return MessageUtil.textMessageToXml(txtmsg);
		}
    }
	/**
	 * 事件处理器，对关注事件进行感谢处理
	 * @param map用户消息数据的Map数据集合
	 * @return
	 */
	public String EventDispatcher(Map<String, String> map) {
		String openid=map.get("FromUserName"); //用户 openid
		String mpid=map.get("ToUserName");   //公众号原始 ID
        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
        	TextMessage txtmsg=new TextMessage();
        	txtmsg.setToUserName(openid);
        	txtmsg.setFromUserName(mpid);
        	txtmsg.setCreateTime(new Date().getTime());
        	txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        	txtmsg.setContent("感谢您关注本账号");
        	System.out.println(MessageUtil.textMessageToXml(txtmsg));
        	return MessageUtil.textMessageToXml(txtmsg);
        }

        return null;
    }
}
