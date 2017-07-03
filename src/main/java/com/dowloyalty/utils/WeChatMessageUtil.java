package com.dowloyalty.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.dowloyalty.controller.TaskWeChatKeyConfiguration;
import com.dowloyalty.pojo.wechat.PointChangeMessage;

public class WeChatMessageUtil {

	// static final String TEMPLATE_ID =
	// "BWwoMNh9OWzE3lghFVCE10iYKvNSrEYobmxW24hdNZc";
	//测试
//	static final String TEMPLATE_ID = "GoERNR4MDoH4_jG6r-00n5tsxqWKAxBoZ7-Mqa5b6sc";
	//正式
	static final String TEMPLATE_ID = "rI1tSNorqCn5_fEDhS9QRwQs2DC0D9oyNPOATgP9-8M";
	static final String MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
			+ TaskWeChatKeyConfiguration.WECHATSUBSCRIPTIONTOKEN;

	public static String focusInfo(String openId, String projectName, String productName, String points) {
		// 发送积分变化的推送消息给retailer
		Map<String, Map<String, String>> firstFloor = new HashMap<>();
		Map<String, String> sercondFloor = null;
		// 存入开头提示数据
		sercondFloor = getMap("您有一笔积分到账，详情如下：");
		firstFloor.put("first", sercondFloor);
		// 存入积分数据
		sercondFloor = getMap(points);
		firstFloor.put("keyword1", sercondFloor);
		// 存入当前时间数据
		sercondFloor = getMap(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		firstFloor.put("keyword2", sercondFloor);
		// 存入当前时间数据
		sercondFloor = getMap("您在活动：" + projectName + "中购入:" + productName + ",获得相应积分:" + points);
		firstFloor.put("keyword3", sercondFloor);
		// 存入结尾提示数据
		sercondFloor = getMap("请及时兑换礼品!");
		firstFloor.put("remark", sercondFloor);
		String info = sendMessage(openId, firstFloor);
		return info;
	}

	private static String sendMessage(String openId, Map<String, Map<String, String>> data) {
		PointChangeMessage msg = new PointChangeMessage();
		msg.setTouser(openId);
		msg.setTemplate_id(TEMPLATE_ID);
		msg.setData(data);
		JSONObject json = (JSONObject) JSONObject.toJSON(msg);
		String postBody = json.toJSONString();
		System.out.println(postBody);
		String info = SimpleHttpConnectUtil.getInstance().sendPost(MESSAGE_URL, postBody);
		return info;
	}

	private static Map<String, String> getMap(String info) {
		Map<String, String> map = new HashMap<>();
		map.put("value", info);
		map.put("color", "#173177");
		return map;
	}

}
