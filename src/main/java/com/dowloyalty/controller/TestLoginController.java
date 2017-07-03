package com.dowloyalty.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dowloyalty.entity.Admin;
import com.dowloyalty.entity.Farmer;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.service.FarmerService;
import com.dowloyalty.service.IAdminService;
import com.dowloyalty.service.IPromoterService;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.utils.JWTTokenUtils;

@Controller
public class TestLoginController {
	@Autowired
    private HttpSession session;
	@Resource
	IRetailerService iRetailerService;
	@Resource
	IPromoterService iPromoterService;
	@Resource
	IAdminService iAdminService;
	@Resource
	FarmerService farmerService;
	/**
	 * PC端登陆
	 * @param auth_code
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChatRedirectDoTest")
	public String WeChatRedirectDotest(String auth_code, HttpServletResponse response) {
		// 测试身份是管理员
		String userid = "aaa";
		// 管理员登陆成功
		Admin admin = iAdminService.findAdminByUserId(userid.toString().trim());
		// 创建token并返回用户
		String token = JWTTokenUtils.getInstance().creatToken("admin", admin.getId() + "");
		// 系统保留相关信息
		response.addCookie(new Cookie("LoyaltyToken", token));
		session.setAttribute("USER", admin);
		session.setAttribute("IDENTITY", "admin");
		session.setMaxInactiveInterval(30 * 60);// 秒
		return "redirect:/website/index";
	}
	
	/**
	 * PC端登陆
	 * @param auth_code
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChatRedirectPromoterDotest")
	public String WeChatPromoterRedirectDotest(String auth_code, HttpServletResponse response) {
		// 修改测试身份是推广员
		String userid = "bingqian";
		// 登陆成功
		Promoter promoter = iPromoterService.findPromoterByUserId(userid.toString().trim());
		// 创建token并返回用户
		String token = JWTTokenUtils.getInstance().creatToken("promoter", promoter.getId() + "");
		response.addCookie(new Cookie("LoyaltyToken", token));
		// 系统保留相关信息
		session.setAttribute("USER", promoter);
		session.setAttribute("IDENTITY", "promoter");
		session.setMaxInactiveInterval(30 * 60);// 秒
		return "redirect:/web/index";
	}
	/**
	 * 微信订阅号retailer登陆
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChat/LoginDoTest")
	public String identifyUser(HttpServletResponse response){
		System.out.println("---------微信订阅号Retailer测试号登陆");
		//输入id就可测试输入id就可测试输入id就可测试输入id就可测试输入id就可测试输入id就可测试
		int id=90005;
		//该用户为Retailer
		Retailer retailer=iRetailerService.findById(id);
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
	}
	@RequestMapping(value="/WeChat/LoginDoSignUp")
	public String identifyUser1(HttpServletResponse response){
		return "/WeChat/SignUp";
	}
	/**
	 * 微信企业号推广员登陆
	 * @param code
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChat/LoginEnterpriseDoTest")
	public String WeChatEnterpriseDo(String code,HttpServletResponse response){
		String userId="wangyuanjiet@163.com";
		Promoter promoter = iPromoterService.findPromoterByUserId(userId);
		//创建token并返回用户
		String token=JWTTokenUtils.getInstance().creatToken("promoterWechat", promoter.getId()+"");
		response.addCookie(new Cookie("LoyaltyToken",token));
		//系统保留相关信息
		session.setAttribute("USER", promoter);
		session.setAttribute("IDENTITY", "promoterWechat");
		session.setMaxInactiveInterval(30*60);//秒
		return "redirect:/WeChat/promoter/home";
	}
	
	/**
	 * 微信订阅号farmer登陆
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChat/FarmerLoginTest")
	public String identifyFarmer(HttpServletResponse response){
		System.out.println("---------微信订阅号Farmer测试号登陆");
		//输入id就可测试输入id就可测试输入id就可测试输入id就可测试输入id就可测试输入id就可测试
		int id=16;
		//该用户为Farmer
		Farmer farmer=farmerService.findById(id);
		//创建token并返回用户
		String token=JWTTokenUtils.getInstance().creatToken("farmer", farmer.getId()+"");
		Cookie cookie=new Cookie("LoyaltyToken",token );
		cookie.setMaxAge(30*60);
		response.addCookie(cookie);
		//系统保留相关信息
		session.setAttribute("USER", farmer);
		session.setAttribute("IDENTITY", "farmer");
		session.setMaxInactiveInterval(30*60);//秒
		//进入主页面
		return "redirect:/WeChat/farmer/home";
	}
}
