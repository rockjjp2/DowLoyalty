package com.dowloyalty.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.utils.JWTTokenUtils;
import com.dowloyalty.utils.SimpleHttpConnectUtil;

@Controller
public class WeChatSubscriptionLoginController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	@Autowired  
	private  HttpServletRequest request; 
	@Autowired
    private HttpSession session;
	
	private static String APPID=TaskWeChatKeyConfiguration.APPID;
	private static String APPSECRET=TaskWeChatKeyConfiguration.APPSECRET;
	@Resource
	IRetailerService iRetailerService;
	/**
	 * 微信服务号进入链接
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChat/Login")
	public ModelAndView WeChatEnterpriseLogin(HttpServletResponse response){
		//已经登陆，直接进入
		if ("retailer".equals(session.getAttribute("IDENTITY"))) {
			System.out.println("------------微信订阅号直接登陆");
			return new ModelAndView("redirect:/WeChat/retailer/home");
		}
		//首次登陆
		System.out.println("------------微信订阅号首次登陆");
		logger.info("微信首次登陆，订阅号跳转进行验证。");
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ APPID+ "&redirect_uri="+ TaskWeChatKeyConfiguration.MAIN_URL
				+ "%2fWeChat%2fLoginDo&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		return new ModelAndView("redirect:"+url);

	}
	/**
	 * 通过微信授权并回调至该网址
	 * 回调时微信返回code参数，通过code，appid，secret获得用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChat/LoginDo")
	public String identifyUser(HttpServletResponse response){
		System.out.println("---------微信订阅号首次登陆WeChatLoginDo");
		String wechatCode=request.getParameter("code");
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ APPID+"&secret="+APPSECRET+"&code="
				+ wechatCode+"&grant_type=authorization_code";
		
		//处理信息并获得openid
		String IdentificationMessageString = SimpleHttpConnectUtil.getInstance().sendPost(url, null);
		JSONObject IdentificationMessage=JSON.parseObject(IdentificationMessageString);
		String openID=IdentificationMessage.getString("openid");
		if (openID!=null&&iRetailerService.UserIsRetailer(openID)) {
			logger.info("微信订阅号首次登陆,openid:"+openID);
			//该用户为Retailer
			Retailer retailer=iRetailerService.findRetailerByOpenId(openID);
			//创建token并返回用户
			String token=JWTTokenUtils.getInstance().creatToken("retailer", retailer.getId()+"");
			Cookie cookie=new Cookie("LoyaltyToken",token );
			cookie.setMaxAge(30*60);
			response.addCookie(cookie);
			//系统保留相关信息
			session.setAttribute("USER", retailer);
			session.setAttribute("IDENTITY", "retailer");
			session.setMaxInactiveInterval(30*60);//秒
			
			//进入主页面
			return "redirect:/WeChat/retailer/home";
		}else{
			//该用户微信号并未与Retailer用户匹配，转到匹配页面
			response.addCookie(new Cookie("openid", openID));
			return "redirect:/WeChat/SignUp";
		}
	}
	/**
	 * 微信注册页面
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChat/SignUp")
	public String SignUp(){
		return "/WeChat/SignUp";
	}
	/**
	 * 微信注册后台
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChat/SignUpDo")
	public String SignUpDo(String mobile,Model model,HttpServletResponse response){
		//前台输入code不为空，执行操作
		if (mobile!=null) {
			Retailer retailer = iRetailerService.findRetailerByMobile(mobile);
			if (retailer != null&& retailer.getOpenID()!=null&& retailer.getOpenID().length()>0) {
				//retailer已经绑定微信
				model.addAttribute("msg", "已经绑定微信");
			}else if (retailer != null) {
				//retailer可绑定微信，并绑定
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("openid")) {
						iRetailerService.updateOpenIdByLoginCode(cookie.getValue(), mobile);
					}
				}
				//创建token并返回用户
				String token=JWTTokenUtils.getInstance().creatToken("retailer", retailer.getId()+"");
				response.addCookie(new Cookie("LoyaltyToken",token ));
				//系统保留相关信息
				session.setAttribute("USER", retailer);
				session.setAttribute("IDENTITY", "retailer");
				session.setMaxInactiveInterval(30*60);//秒
				//进入主页
				return "redirect:/WeChat/retailer/home";
			}else {
				//该用户code无效
				model.addAttribute("msg", "您输入的手机号码不存在");
			}
		}
		return "/WeChat/SignUp";
	}
}
