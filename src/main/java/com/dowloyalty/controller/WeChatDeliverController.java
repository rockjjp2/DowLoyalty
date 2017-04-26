package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.pojo.Deliver;
import com.dowloyalty.service.ExchangeRecordService;
import com.dowloyalty.service.ProjectService;

@Controller
@RequestMapping("/WeChat/promoter")
/**
 * 代发货列表，以及已发货列表的信息查看
 * @author MingXuan
 *
 */
public class WeChatDeliverController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
    private HttpSession session;
	@Resource
	ExchangeRecordService exchangeRecordService;
	@Resource
	ProjectService projectService ;
	
	@RequestMapping(value="/deliverInfo")
	public String deliverInfo(HttpServletResponse response){
		return "/WeChat/deliverInfo";

	}
	@RequestMapping(value="/deliverlist")
	public String deliverlist(HttpServletResponse response,Model model){
		return "/WeChat/deliverList";
	}
	@RequestMapping(value="/deliverAjax")
	@ResponseBody
	public void getDeliver(String status,HttpServletResponse response) {
		PrintWriter printWriter = null;
		try {
		int promoterId=((Promoter)session.getAttribute("USER")).getId();
		List<Project> projects = projectService.findProjectByAssistantId(promoterId);
		List<Deliver> deliverlist=new ArrayList<>();
		if (projects.size()!= 0) {
			for (Project project : projects) {
				List<Deliver> delivers = exchangeRecordService.findDeliverList(project.getId(), Integer.parseInt(status));
				deliverlist.addAll(delivers);
			}
		}
		String jsonString = JSON.toJSONString(deliverlist);
			printWriter=response.getWriter();
			printWriter.println(jsonString);
		} catch (IOException e) {
			logger.error("printWriter，io异常",e);
		}catch (NumberFormatException e) {
			logger.error("Status传入错误",e);
		}finally {
			printWriter.flush();
			printWriter.close();
		}
	}
	@RequestMapping(value="/doDeliverAjax")
	@ResponseBody
	public void doDeliver(String key,HttpServletResponse response) {
		try {
			exchangeRecordService.updateExchangerrecordToComplete(Integer.parseInt(key));
		} catch (NumberFormatException e) {
			logger.error("Status传入错误",e);
		}
	}
}
