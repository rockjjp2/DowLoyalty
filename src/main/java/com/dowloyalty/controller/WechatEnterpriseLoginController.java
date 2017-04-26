package com.dowloyalty.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dowloyalty.entity.Admin;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.service.IAdminService;
import com.dowloyalty.service.IPromoterService;
import com.dowloyalty.utils.JWTTokenUtils;
import com.dowloyalty.utils.SimpleHttpConnectUtil;
/**
 * Wechat Enterprise Account Login
 * @author MingXuan
 *
 */
@Controller
public class WechatEnterpriseLoginController {
/*	
	@Autowired  
	private  HttpServletRequest request; */
	@Autowired
    private HttpSession session;
	
	@Resource
	IPromoterService iPromoterService;
	@Resource
	IAdminService iAdminService;
	/**
	 * mobile登陆微信接口，转发至LoginDo处理信息
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChat/LoginEnterprise")
	public ModelAndView WeChatEnterpriseLogin(HttpServletResponse response){
		//已经登陆，直接进入
	if ("promoterWechat".equals(session.getAttribute("IDENTITY"))) {
		System.out.println("------------企业微信号直接登陆");
			return new ModelAndView("redirect:/WeChat/promoter/home");
		}
		//首次登陆
		System.out.println("------------企业微信号直接登陆");
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ TaskWeChatKeyConfiguration.CORPID
				+ "&redirect_uri="+ TaskWeChatKeyConfiguration.MAIN_URL+"%2FWeChat%2FLoginEnterpriseDo"
				+ "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		return new ModelAndView("redirect:"+url);

	}
	@RequestMapping(value="/WeChat/LoginEnterpriseDo")
	public String WeChatEnterpriseDo(String code,HttpServletResponse response){
		String url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="
				+ TaskWeChatKeyConfiguration.WECHATENTERPRISETOKEN+"&code="+code;
		String userInformation = SimpleHttpConnectUtil.getInstance().sendPost(url, null);
		System.out.println("------------------企业微信号首次登陆获得的用户信息:"+userInformation);
		JSONObject userInformationObject = JSON.parseObject(userInformation);
		String userId=userInformationObject.getString("UserId");
		Promoter promoter = iPromoterService.findPromoterByUserId(userId);
		if (userId != null&& promoter!=null) {
			//创建token并返回用户
			String token=JWTTokenUtils.getInstance().creatToken("promoterWechat", promoter.getId()+"");
			response.addCookie(new Cookie("LoyaltyToken",token));
			//系统保留相关信息
			session.setAttribute("USER", promoter);
			session.setAttribute("IDENTITY", "promoterWechat");
			session.setMaxInactiveInterval(30*60);//秒
		}else {
			session.setAttribute("IDENTITY", "guest");
			return "/WeChat/error";
		}
		return "redirect:/WeChat/promoter/home";
	}
	/**
	 * pc端登陆接口，转发至微信验证并获取相关信息
	 * @return
	 */
	@RequestMapping(value="/WeChatRedirect")
	public ModelAndView WeChatEnterpriseRedirect(){
		//已经登陆直接进入
		if ("admin".equals(session.getAttribute("IDENTITY"))) {
			System.out.println("----------管理员通过WeChatRedirect直接进入");
			return new ModelAndView("redirect:/website/index");
		}else if("promoter".equals(session.getAttribute("IDENTITY"))){
			System.out.println("----------promoter通过WeChatRedirect直接进入");
			return new ModelAndView("redirect:/web/index");
		}else {
			//首次登陆
			System.out.println("----------WeChatRedirect首次进入");
			return new ModelAndView("redirect:https://qy.weixin.qq.com/cgi-bin/loginpage?corp_id="
					+TaskWeChatKeyConfiguration.CORPID+ "&redirect_uri="+ TaskWeChatKeyConfiguration.MAIN_URL+ "%2FWeChatRedirectDo&state=123"
					+ "&usertype=member");
		}
	}
	
	/**
	 * 由服务器转发到微信，获得code后，以此获取用户信息
	 * @param auth_code
	 * @return
	 */
	@RequestMapping(value="/WeChatRedirectDo")
	public String WeChatRedirectDo(String auth_code,HttpServletResponse response){
		String url="https://qyapi.weixin.qq.com/cgi-bin/service/get_login_info?access_token="+ TaskWeChatKeyConfiguration.WECHATENTERPRISETOKEN;
		String postString="{\"auth_code\":\""+ auth_code+ "\"}";
		String AccountInformation = SimpleHttpConnectUtil.getInstance().sendPost(url,postString);
		System.out.println(AccountInformation);
		//String AccountInformation="{\"usertype\":5,\"user_info\":{\"userid\":\"ssss\",\"avatar\":\"http://shp.qpic.cn/bizmp/KXd4oAtQpdjhI8QCibDjVusKA0zeWBJGjodSNVZOgSjtUsuHoNJyaiag/\"},\"corp_info\":{\"corpid\":\"wx1a69765a07594350\"}}";
		JSONObject accountInformationObject = JSON.parseObject(AccountInformation);
		if (accountInformationObject.get("user_info")!=null) {
			JSONObject userInformationObject=JSON.parseObject(accountInformationObject.get("user_info").toString());
			Object userid = userInformationObject.get("userid");
			if (userid!=null&& iAdminService.findAdminByUserId(userid.toString().trim())!=null) {
				//管理员登陆成功
				Admin admin=iAdminService.findAdminByUserId(userid.toString().trim());
				//创建token并返回用户
				String token=JWTTokenUtils.getInstance().creatToken("admin", admin.getId()+"");
				//系统保留相关信息
				response.addCookie(new Cookie("LoyaltyToken",token));
				session.setAttribute("USER", admin);
				session.setAttribute("IDENTITY", "admin");
				session.setMaxInactiveInterval(30*60);//秒
				return "redirect:/website/index";
			}else if(userid!=null && iPromoterService.UserIsPromoter( userid.toString().trim() ) ){
				//登陆成功
				Promoter promoter=iPromoterService.findPromoterByUserId(userid.toString().trim());
				//创建token并返回用户
				String token=JWTTokenUtils.getInstance().creatToken("promoter", promoter.getId()+"");
				response.addCookie(new Cookie("LoyaltyToken",token));
				//系统保留相关信息
				session.setAttribute("USER", promoter);
				session.setAttribute("IDENTITY", "promoter");
				session.setMaxInactiveInterval(30*60);//秒
				return "redirect:/web/index";
			}
		}
		return "redirect:/Login";
	}
}
