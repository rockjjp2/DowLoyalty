package com.dowloyalty.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dowloyalty.utils.SimpleHttpConnectUtil;

/**
 * 微信公众号与百度BOS基础配置信息
 * @author MingXuan
 *
 */
@Service
public class TaskWeChatKeyConfiguration {
	protected final Log logger = LogFactory.getLog(this.getClass());
	//系统发布网址
	private static String mainURL="http://daschinaloyalty.bceapp.com";
	//测试系统网址
//	private static String mainURL="http://dasdowloyalty.bceapp.com";
	//微信服务号
	public static String APPID="wx3c840097a9200b83";
	public static String APPSECRET="decfb2251d9f5531341c9637fb98104a";
	public static String WechatSubscriptionToken="zhouToken";
	//微信测试服务号
//	public static String APPID="wx0554723b81caf5f6";
//	public static String APPSECRET="387c1897790b3b2f74f320f89d287b5e";
//	public static String WechatSubscriptionToken="yuanjie_token";
	
	//微信企业号
	public static String CORPID="wxf4e637680eda2902";
//	public static String CORPSECRECT="UOgiMyIjqSXMA9lZOmZQjkA8a_9c3zioB8Q_2DXnVQD12JeOgTO1FIwRlfglPLLA";
	public static String CORPSECRECT="eqHM9mY9WlxgQbGnp0uOBroDpA44R8RxYpvT2TRRzk8";
	
	
	//测试企业号 
//	public static String CORPID="wx1a69765a07594350";
//	public static String CORPSECRECT="65XaFbUwTrE8FZFYOxKuWJ8Vvq8bl8mQGGzuQNZ9zeLToKCzYwpKK8D_KVqHmJyB";
	//百度BOS
	public static String BaiduBOS_ACCESS_KEY_ID="1f8b057512ce44308ff93a4bff64e28e";//百度bos keyid
	public static String BaiduBOS_SECRET_ACCESS_KEY="e9cc9afaa4cb43238b98506b8b093bcc";//百度bos keyid
	
	public static String MAIN_URL;
	//微信企业号、服务号token
	public static String WECHATENTERPRISETOKEN;
	public static String WECHATSUBSCRIPTIONTOKEN;
	private String wechatEnterpriseTokenURL="https://qyapi.weixin.qq.com/cgi-bin/gettoken?"
			+ "corpid="+ CORPID+ "&corpsecret="+ CORPSECRECT;
	private String wechatSubscriptionTokenURL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ APPID+ "&secret="+ APPSECRET;
	
	static {
		try {
			MAIN_URL=URLEncoder.encode(mainURL,"UTF-8");
			postMenu();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/*定时获取token*/
	@Scheduled(fixedRate=7000*1000)//*1000=1s
	public void weChatTakken() {
		GetWechatTaken(0);
		postMenu();
	}
	/**
	 * 获得企业号token
	 * @param n
	 */
	private void GetWechatTaken(int n) {
		String EnterpriseJson = SimpleHttpConnectUtil.getInstance().sendPost(wechatEnterpriseTokenURL, null);
		String SubscriptionJson = SimpleHttpConnectUtil.getInstance().sendPost(wechatSubscriptionTokenURL, null);
		logger.debug("获取企业号token信息:"+EnterpriseJson);
		logger.debug("获取订阅号token信息:"+SubscriptionJson);
		JSONObject wechatEnterpriseToken = JSON.parseObject(EnterpriseJson);
		JSONObject wechatSubscriptionToken = JSON.parseObject(SubscriptionJson);
		if (wechatEnterpriseToken!=null&&wechatEnterpriseToken.getString("access_token") != null
				&&wechatSubscriptionToken!=null&&wechatSubscriptionToken.getString("access_token")!=null) {
			WECHATENTERPRISETOKEN=wechatEnterpriseToken.getString("access_token");
			WECHATSUBSCRIPTIONTOKEN=wechatSubscriptionToken.getString("access_token");
		}else {
			//没有获取到token，重复执行3次
			GetWechatTaken(n+1);
			if (n>3) {
				logger.error("获取企业号Token失败，第"+n+"次。");
				return ;
			}
		}
	}
	/**
	 * 设置微信服务菜单
	 * @return
	 */
	public static String postMenu() {
		String menuUrl=" https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ WECHATSUBSCRIPTIONTOKEN;
		String menu= "{\"button\":[{\"type\":\"view\",\"name\":\"积分畅想\",\"url\":\""+mainURL+"/WeChat/Login\"},"
					+ "{\"type\":\"view\",\"name\":\"农户入口\",\"url\":\""+mainURL+"/WeChat/FarmerLogin\"}]}";
		String re=SimpleHttpConnectUtil.getInstance().sendPost(menuUrl, menu);
		System.out.println("微信订阅号菜单状态设置");
		return re;
	}
	
}
