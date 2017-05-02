package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dowloyalty.entity.Admin;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.service.IProjectDetailsService;
import com.dowloyalty.service.ISearchSaleRecordService;
import com.dowloyalty.service.ProjectService;

/**
 * PC端主页面调用
 */

@Controller
public class WebMainController {

	@Resource
	private ISearchSaleRecordService iSearchSaleRecordService;
	@Resource
	private IProjectDetailsService iProjectDetailsService;
	@Resource
	private ProjectService projectService;
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	
	//PC主页面
	@RequestMapping(value={"/website/integralitem","/web/integralitem"})
	public String integralItem(Model model,HttpSession session) {
		return "PC/IntegralItem";
	}
	
	//加载所有项目
	@RequestMapping({"/website/getProjectDetails","/web/getProjectDetails"})
	public void getProjectDetails(HttpServletResponse response,HttpServletRequest request,HttpSession session)
	{
		List<Project> projects;
		int userid = 0;
		if (session.getAttribute("USER") instanceof Admin) {
			//当前登录人是管理员，可以查看所有项目
		projects = iSearchSaleRecordService.findProject();
		}else {
			//当前登录人是推广员，可以查看参与的项目
			userid=((Promoter)session.getAttribute("USER") ).getId();
			projects = iSearchSaleRecordService.findProjectByPromoterId(userid);
		}
		for (Project project : projects) {
			project.setBackgroundBase64(Base64.getEncoder().encodeToString(project.getBackgroundPath()));
			project.setPlacardPath(null);
			project.setBackgroundPath(null);
		}
		JSONArray json = (JSONArray)JSON.toJSON(projects);
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("Json数据传输异常");
		}
	}

	//兑换记录查询页面，加载下拉框中活动名
	@RequestMapping({"/website/gift_ExChange","/web/gift_ExChange"})
	public String gift_ExChange(Model model) {
		List<Project> list = projectService.findAllActiveProjects();
		for(Project project : list)
		{
			project.setPlacardPath(null);
			project.setBackgroundPath(null);
		}
		model.addAttribute("projects", list);

		return "PC/gift_ExChange";
	}

	//创建项目页面
	@RequestMapping("/website/Addproject")
	public String addproject(Model model) {

		return "PC/Addproject";
	}

	//会员信息页面
	@RequestMapping({"/website/cardinfo","/web/cardinfo"})
	public String cardInfo(Model model) {

		return "PC/CardInfo";
	}

	
}
