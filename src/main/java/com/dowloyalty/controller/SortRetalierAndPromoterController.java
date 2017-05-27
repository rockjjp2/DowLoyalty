package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.service.IPromoterService;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.service.ProjectService;
import com.dowloyalty.utils.PinYinUtil;

@Controller
public class SortRetalierAndPromoterController {
	
	@Resource
	IRetailerService iRetailerService;
	@Resource
	IPromoterService iPromoterService;
	@Resource
	ProjectService projectService;

	/*初始化零售商信息*/
	@RequestMapping("website/initRetailers")
	public void SortRetailersAndPut(HttpServletRequest request, HttpServletResponse response)
	{
		String projectId = request.getParameter("projectId");
		List<Retailer> retailers = iRetailerService.findAllRetailersByProjectId(projectId);
		List<String> nameList = new ArrayList<String>();
		for(Retailer retailer : retailers)
		{
			nameList.add(retailer.getChineseName()+"$@#"+retailer.getId());
		}
		Map<String, List<String>> nameMap = PinYinUtil.getOrderByFirstWord(nameList);
		
		JSON Json = (JSON) JSON.toJSON(nameMap);
		try {
			PrintWriter out = response.getWriter();
			out.println(Json);
			out.flush();
			out.close();
		} catch (IOException e) {
		}
	}
	
	/*关联零售商与项目*/
	@ResponseBody
	@RequestMapping("website/insertRetailers")
	public void insertRetailers(HttpServletRequest request)
	{
		String array = request.getParameter("array");
		array = "["+ array +"]";
		System.out.println("array  =  "+array);
		List<String> retailerIds = new ArrayList<String>();
		JSONArray jsonArray = (JSONArray)JSONArray.parse(array);
		String projectId = ((JSONObject)(jsonArray.get(0))).getString("projectId");
		List<String> ids = iRetailerService.findAllRetailerByProjectId(projectId);
		for(Object json : jsonArray)
		{
			if(ids != null && !ids.contains(((JSONObject)json).getString("retailerId")))
			{
				retailerIds.add(((JSONObject)json).getString("retailerId"));
			}
		}
		System.out.println("projectId  =  "+projectId);
		System.out.println("retailerIds  =  "+retailerIds);
		
		if(projectId != "" && !"".equals(projectId.trim()) && !retailerIds.isEmpty() && !"".equals(retailerIds.get(0).trim()))
		{
			iRetailerService.patchInsertRelationBetweenRetailerAndProject(retailerIds, projectId);
		}
	}
	
	/*取消零售商与项目的关联*/
	@ResponseBody
	@RequestMapping("website/deleteRetailers")
	public void deleteRetailers(HttpServletRequest request)
	{
		String array = request.getParameter("array");
		array = "["+ array +"]";
		System.out.println("array  =  "+array);
		List<String> retailerIds = new ArrayList<String>();
			JSONArray jsonArray = (JSONArray)JSONArray.parse(array);
			String projectId = ((JSONObject)(jsonArray.get(0))).getString("projectId");
			
			for(Object json : jsonArray)
			{
				retailerIds.add(((JSONObject)json).getString("retailerId"));
			}
		System.out.println("projectId  =  "+projectId);
		System.out.println("retailerIds  =  "+retailerIds);
		
		if(projectId != "" && !retailerIds.isEmpty() && !"".equals(retailerIds.get(0).trim()))
		{
			iRetailerService.patchDeleteRelationBetweenRetailerAndProject(retailerIds, projectId);
		}
	}
	
	/*初始化推广员信息*/
	@RequestMapping("website/initPromoters")
	public void SortPromotersAndPut(HttpServletRequest request, HttpServletResponse response)
	{
		
		String projectId = request.getParameter("projectId");
		List<Promoter> promoters = iPromoterService.findAllPromotersByProjectId(projectId);
		List<String> nameList = new ArrayList<String>();
		for(Promoter promoter : promoters)
		{
			nameList.add(promoter.getChineseName()+"$@#"+promoter.getId());
		}
		Map<String, List<String>> nameMap = PinYinUtil.getOrderByFirstWord(nameList);
		JSON Json = (JSON) JSON.toJSON(nameMap);
		try {
			PrintWriter out = response.getWriter();
			out.println(Json);
			out.flush();
			out.close();
		} catch (IOException e) {
		}
	}
	
	
	/*关联推广员与项目*/
	@ResponseBody
	@RequestMapping("website/insertPromoters")
	public void insertPromoters(HttpServletRequest request)
	{
		String array = request.getParameter("array");
		array = "["+ array +"]";
		System.out.println("array  =  "+array);
		List<String> promoterIds = new ArrayList<String>();
		JSONArray jsonArray = (JSONArray)JSONArray.parse(array);
		String projectId = ((JSONObject)(jsonArray.get(0))).getString("projectId");
		List<String> ids = iPromoterService.findAllPromoterByProjectId(projectId);
		for(Object json : jsonArray)
		{
			if(ids != null && !ids.contains(((JSONObject)json).getString("promoterId")))
			{
				promoterIds.add(((JSONObject)json).getString("promoterId"));
			}
		}
		System.out.println("projectId  =  "+projectId);
		System.out.println("promoterIds  =  "+promoterIds);
		
		if(projectId != "" && !"".equals(projectId.trim()) && !promoterIds.isEmpty() && !"".equals(promoterIds.get(0).trim()))
		{
			iPromoterService.patchInsertRelationBetweenPromoterAndProject(promoterIds, projectId);
		}
	}
	
	
	/*取消推广员与项目的关联*/
	@ResponseBody
	@RequestMapping("website/deletePromoters")
	public void deletePromoters(HttpServletRequest request)
	{
		String array = request.getParameter("array");
		array = "["+ array +"]";
		System.out.println("array  =  "+array);
		List<String> promoterIds = new ArrayList<String>();
			JSONArray jsonArray = (JSONArray)JSONArray.parse(array);
			String projectId = ((JSONObject)(jsonArray.get(0))).getString("projectId");
			for(Object json : jsonArray)
			{
				promoterIds.add(((JSONObject)json).getString("promoterId"));
			}
		System.out.println("projectId  =  "+projectId);
		System.out.println("promoterIds  =  "+promoterIds);
		
		if(projectId != "" && promoterIds != null &&!promoterIds.isEmpty() && !"".equals(promoterIds.get(0).trim()))
		{
			iPromoterService.patchDeleteRelationBetweenPromoterAndProject(promoterIds, projectId);
		}
	}
	
	
	/*修改项目下的发货员信息*/
	@ResponseBody
	@RequestMapping("website/updateProjectAssistant")
	public void updateProjectAssistant(HttpServletRequest request)
	{
		String projectId = request.getParameter("projectId");
		String promoterId = request.getParameter("promoterId");
		Project project = projectService.findProjectByProjectId(Integer.parseInt(projectId));
		System.out.println("project="+project);
		if(promoterId != null && !"".equals(promoterId))
		{
			project.setAssistantID(Integer.parseInt(promoterId));
		}
		else
		{
			project.setAssistantID(0);
		}
		System.out.println("projectId="+projectId);
		System.out.println("promoterId="+promoterId);
		projectService.update(project);
	}
}
