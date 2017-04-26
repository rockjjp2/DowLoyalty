package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dowloyalty.entity.Retailer;
import com.dowloyalty.service.IMessagesService;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.service.ProjectService;
import com.dowloyalty.service.WeChatDispatcher;
import com.dowloyalty.utils.MessageUtil;
import com.dowloyalty.utils.WeChatSignUtil;
@Controller
public class WeChatHand {
	@Autowired  
	private  HttpServletRequest request; 
	@Resource
	private IRetailerService iRetailerService;
	@Resource
	IMessagesService iMessagesService;
	@Resource
	ProjectService projectService;
	/**
	 * 微信消息接受
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/WeChat/Hand")
	public void weChatDispatcher(HttpServletResponse response){
		WeChatDispatcher wechatDispatcher=new WeChatDispatcher();
		// 随机字符串  
        String echostr = request.getParameter("echostr");
        PrintWriter printWriter= null;
        try {
        	printWriter = response.getWriter();
	        //鉴别随机字符串，如果有则进行首次握手
			if (echostr!=null) {
				// 微信加密签名  
		        String signature = request.getParameter("signature");  
		        // 时间戳  
		        String timestamp = request.getParameter("timestamp");  
		        // 随机数  
		        String nonce = request.getParameter("nonce");  
		        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
		        if (WeChatSignUtil.checkSignature(signature, timestamp, nonce)) {  
		        	printWriter.print(echostr);  
		        }  
			}else {
				response.setContentType("text/xml;charset=utf-8");
				String outMsg="";
					//接受处理器信息并进行处理
					Map<String, String> map=MessageUtil.parseXml(request);
					System.out.println("---------wechat"+map);
					String msgtype=map.get("MsgType");
					if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
						//进入事件处理,返回谢谢关注
						outMsg=wechatDispatcher.EventDispatcher(map); 
					}else{
						String msg="您还不在活动中，请在活动详情中加入";//默认非系统内用户的回复
						if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){//判断用户消息类型是否为文本
							//检查用户是否存在
							Retailer retailer = iRetailerService.findRetailerByOpenId(map.get("FromUserName"));
							if (retailer!=null) {
								msg="您还没有已参加的活动，感谢您的支持";//默认无参加活动的用户回复
								//检查用户是否有活动
								if (projectService.findProjectByRetailerId(retailer.getId())!=null) {
									msg="您的留言已被收集，感谢您的参加";
									iMessagesService.insertMessagesIncludeRetailerIdAndMessage(retailer.getId(), map.get("Content"));
								}
							}
						}
						outMsg = wechatDispatcher.MessageDispatcher(map,msg); //进入消息处理
					}
					printWriter.print(outMsg); 
			}
        } catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (printWriter!=null) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}
}
