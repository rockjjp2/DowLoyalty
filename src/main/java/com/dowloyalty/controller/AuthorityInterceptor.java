package com.dowloyalty.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
public class AuthorityInterceptor implements HandlerInterceptor{
	protected final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
    private HttpSession session;
	
	public void before() {
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@SuppressWarnings("unused")
	private Cookie getCookieToken(HttpServletRequest request,String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		session.setAttribute("IDENTITY", "guest");
		return null;
	}

	/**
	 * 用户登陆后cookie保留taken，服务器保留用户信息以及身份。加密后与token比对，通过则放行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("进入系统验证,IDENTITY:"+session.getAttribute("IDENTITY")+"URL:"+request.getRequestURI());
		String requestURL=request.getServletPath();
		// 正则表达式规则
		String regEx =null;
		String goPage;
		Matcher matcher;
		//微信retailer
		if ("retailer".equals(session.getAttribute("IDENTITY"))) {
			// 正则表达式规则
			regEx = "/WeChat/retailer//*";
			// 编译正则表达式
			matcher = Pattern.compile(regEx).matcher(requestURL);
			goPage="/WeChat/retailer/home";
		//WeChatPromoter
		}else if ("promoterWechat".equals(session.getAttribute("IDENTITY"))) {
			regEx = "/WeChat/promoter//*";
			matcher = Pattern.compile(regEx).matcher(requestURL);
			goPage="/WeChat/promoter/home";
		//管理员
		}else if ("admin".equals(session.getAttribute("IDENTITY"))) {
			regEx = "/website//*";
			matcher = Pattern.compile(regEx).matcher(requestURL);
			goPage="/website/index";
		//PC端promoter
		}else if ("promoter".equals(session.getAttribute("IDENTITY"))) {
			regEx = "/web//*";
			matcher = Pattern.compile(regEx).matcher(requestURL);
			goPage="/web/index";
		//没有身份，不匹配
		}else {
			if(isWeb(requestURL)){
				response.sendRedirect(request.getContextPath()+"/WeChat/Logintips?a=a");
			}else {
				response.sendRedirect(request.getContextPath()+"/WeChat/Logintips");
			}
			return false;
		}
		//根据权限进入身份页面，访问错误则返回主页
		if (!matcher.find()) {
			response.sendRedirect(request.getContextPath()+goPage);
			return false;
		}else{
			//无错误直接放行
			return true;
		}
	}
	private boolean isWeb(String requestURL){
		String regEx = "/web+";
		Matcher matcher = Pattern.compile(regEx).matcher(requestURL);
		return matcher.find();
	}
}
