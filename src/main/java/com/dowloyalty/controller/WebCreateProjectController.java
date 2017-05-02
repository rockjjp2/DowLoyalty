package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dowloyalty.entity.Admin;
import com.dowloyalty.entity.PointsLevel;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Province;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.ExchangeshopGoods;
import com.dowloyalty.pojo.ProductInfos;
import com.dowloyalty.pojo.ProjectProvince;
import com.dowloyalty.service.ICreateProjectService;
import com.dowloyalty.service.IProductService;
import com.dowloyalty.service.IProjectDetailsService;
import com.dowloyalty.service.IPromoterService;
import com.dowloyalty.service.IProvinceService;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.service.PointsLevelService;
import com.dowloyalty.service.ProjectService;
import com.dowloyalty.utils.Sort;

/**
 * a example demonstrate how to use controller
 * @author zhoum
 *
 */
@Controller
public class WebCreateProjectController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
    private HttpSession session;
	@Resource
	ProjectService projectService;
	@Resource
	IProvinceService iProvinceService;
	@Resource
	IPromoterService iPromoterService;
	@Resource
	IRetailerService iRetailerService;
	@Resource
	IProjectDetailsService iProjectDetailsService;
	@Resource
	IProductService iProductService;
	@Resource
	PointsLevelService pointsLevelService;
	@Resource
	ICreateProjectService iCreateProjectService;
	
	/**
	 * 进入创建项目路径
	 * @param model
	 * @return
	 */
	@RequestMapping("/website/createproject")
	public String one(Model model){
		List<Province> provinces = iProvinceService.getAllProvince();
		List<String> provinceNames = sortProvinces(provinces);
		model.addAttribute("provinces", provinceNames);
		return "/PC/Addproject";
	}
	
	/**
	 * 创建项目基本信息的保存
	 * @param request
	 * @param model
	 * @param file  海报图
	 * @param file1  背景图
	 * @return
	 * @throws IOException
	 */
	
	@RequestMapping("/website/subProjectActive")
	public void subProjectActive(HttpServletRequest request, Model model,MultipartHttpServletRequest Mrequest,HttpServletResponse response){
		String edit_id = request.getParameter("edit_id");// 从积分项目进入获取的项目id
		int adminId = ((Admin) session.getAttribute("USER")).getId();// 管理员权限
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		String provinceId = request.getParameter("Province");
		String start_time = request.getParameter("Start_time");
		String end_time = request.getParameter("End_time");
		String title = request.getParameter("Title");
		String introduce = request.getParameter("Introduce");
		System.out.println("introduce="+introduce);
		MultipartFile postImg = null;
		postImg = Mrequest.getFile("file");
		MultipartFile backImg = null;
		backImg = Mrequest.getFile("file1");
		System.out.println("postImg="+postImg);
		System.out.println("backImg="+backImg);
		try {
			System.out.println("postImg="+request.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Project project = null;
		
			//根据项目id判断行为：创建 or 修改
			//创建
			if(edit_id == null || "".equals(edit_id.trim()))
			{
				//根据省份id和项目名判断项目名是否已存在
				project = projectService.findProjectByProvinceIdAndName(provinceId, title);
				if(project != null)
				{
					try 
					{
						PrintWriter out = response.getWriter();
						out.println("exist");
						out.flush();
						out.close();
					} 
					catch (IOException e) 
					{
						logger.warn("传输数据异常");
					}
					
				}
				else
				{
					project = new Project();
					project.setName(title);
					project.setProvinceID(Integer.parseInt(provinceId));
					project.setVisible(true);
					project.setActive(true);
					project.setAdminID(adminId);
					try 
					{
						project.setBackgroundPath(backImg.getBytes());
						project.setBackgroundBase64(Base64.getEncoder().encodeToString(backImg.getBytes()));
						project.setDescription(introduce);
						project.setPlacardPath(postImg.getBytes());
						project.setPlacardBase64(Base64.getEncoder().encodeToString(postImg.getBytes()));
						project.setStartDate(new Timestamp(sf.parse(start_time).getTime()));
						project.setEndDate(new Timestamp(sf.parse(end_time).getTime()));
						projectService.save(project);
						project = projectService.findProjectByProvinceIdAndName(provinceId, title);
						try 
						{
							PrintWriter out = response.getWriter();
							out.println(project.getId());
							out.flush();
							out.close();
						} 
						catch (IOException e) 
						{
							logger.warn("传输数据异常");
						}
					}
					catch (IOException e) 
					{
						logger.warn("创建项目上传文件获取异常");
					} 
					catch (ParseException e) 
					{
						logger.warn("创建项目时间解析异常");
					}
				}
			}
			//修改
			else
			{
				project = projectService.findProjectByProjectId(Integer.parseInt(edit_id));
				project.setName(title);
				project.setProvinceID(Integer.parseInt(provinceId));
				project.setVisible(true);
				project.setActive(true);
				project.setAdminID(adminId);
				try 
				{
					if(postImg != null)
					{
						project.setPlacardPath(postImg.getBytes());
					}
					if(backImg != null)
					{
						project.setBackgroundPath(backImg.getBytes());
					}
					project.setDescription(introduce);
					project.setStartDate(new Timestamp(sf.parse(start_time).getTime()));
					project.setEndDate(new Timestamp(sf.parse(end_time).getTime()));
					projectService.update(project);
					try 
					{
						PrintWriter out = response.getWriter();
						out.println(project.getId());
						out.flush();
						out.close();
					} 
					catch (IOException e) 
					{
						logger.warn("传输数据异常");
					}
				} 
				catch (IOException e)
				{
					logger.warn("创建项目上传文件获取异常");
				} 
				catch (ParseException e) 
				{
					logger.warn("创建项目时间解析异常");
				}
			}
	}

	

//	/**
//	 * "选择零售商"部分
//	 * @param model
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping("/website/searchRetailerByRetailerName")
//	public void searchRetailerByRetailerName(Model model, HttpServletRequest request, HttpServletResponse response) {
//		String provinceID = request.getParameter("provinceId");
//		int provinceId = Integer.valueOf(provinceID);
//		List<Retailer> retailers = iRetailerService.findRetailerByProvinceId(provinceId);
//		System.out.println("搜索到的零售商："+retailers);
//		JSONArray longJson = (JSONArray) JSONArray.toJSON(retailers);
//		try {
//			PrintWriter out = response.getWriter();
//			out.println(longJson);
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			logger.warn("创建项目中搜索零售商信息Json数据传输异常");
//		}
//
//	}
//
//	/**
//	 * "选择推广员"部分
//	 * @param model
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping("/website/searchPromoterByPromoterName")
//	public void searchPromoterByPromoterName(Model model, HttpServletRequest request, HttpServletResponse response) {
//		String provinceID = request.getParameter("provinceId");
//		int provinceId = Integer.valueOf(provinceID);
//		List<Promoter> promoters = iPromoterService.findPromoterByProvinceId(provinceId);
//		System.out.println("搜索到的零售商："+promoters);
//		JSONArray longJson =(JSONArray) JSONArray.toJSON(promoters);
//		try {
//			PrintWriter out = response.getWriter();
//			out.println(longJson);
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			logger.warn("创建项目中搜索推广员信息Json数据传输异常");
//		}
//
//	}
//
//	/**
//	 * 保存项目所选择的零售商信息
//	 * @param request
//	 */
//	@RequestMapping("/website/subProjectRetailer")
//	public void subProjectRetailer(HttpServletRequest request) {
//		String projectId = request.getParameter("projectId");
//		String retailerNames = request.getParameter("retailerNames");
//		String retailer_name[] = retailerNames.split(";");// 页面中新选择的零售商数组
//
//		List<Integer> retailerIds = iCreateProjectService.findRetailerIdByProjectId(Integer.parseInt(projectId));// 项目中已经选择的零售商
//		if (retailerIds.isEmpty()) {
//			for (int i = 0; i < retailer_name.length; i++) {
//				
//				iCreateProjectService.insertRProjectRetailer(Integer.parseInt(projectId),
//						Integer.parseInt(retailer_name[i]), true);
//			}
//
//		} else {
//			for (int y = 0; y < retailer_name.length; y++) {
//				if (!retailerIds.toString().contains(retailer_name[y])) {
//					iCreateProjectService.insertRProjectRetailer(Integer.parseInt(projectId),
//							Integer.parseInt(retailer_name[y]), true);
//				}
//			}
//			for (int x = 0; x < retailerIds.size(); x++) {
//				if (!retailerNames.contains(retailerIds.get(x).toString())) {
//					iCreateProjectService.updateRProjRetailer(Integer.parseInt(projectId), retailerIds.get(x), false);
//				}
//			}
//		}
//	}
//
//	/**
//	 * 保存项目所选择的推广员的信息
//	 * @param request
//	 */
//	@RequestMapping("/website/subProjectPromoter")
//	public void subProjectPromoter(HttpServletRequest request) {
//		String projectId = request.getParameter("projectId");
//		String promoterNames = request.getParameter("promoterNames");
//		String promoter_name[] = promoterNames.split(";");// 选择的推广员数组
//
//		List<Integer> promoterIds = iCreateProjectService.findPromoterIdByProjectId(Integer.parseInt(projectId));// 项目中已经选择的推广员
//		if (promoterIds.isEmpty()) {
//			for (int i = 0; i < promoter_name.length; i++) {
//				iCreateProjectService.insertRProjectPromoter(Integer.parseInt(projectId), Integer.parseInt(promoter_name[i]), true);
//			}
//
//		} else {
//			for (int y = 0; y < promoter_name.length; y++) {
//				if (!promoterIds.toString().contains(promoter_name[y])) {
//					iCreateProjectService.insertRProjectPromoter(Integer.parseInt(projectId),
//							Integer.parseInt(promoter_name[y]), true);
//				}
//			}
//			for (int x = 0; x < promoterIds.size(); x++) {
//				if (!promoterNames.contains(promoterIds.get(x).toString())) {
//					iCreateProjectService.updateRProjPromoter(Integer.parseInt(projectId), promoterIds.get(x), false);
//				}
//			}
//		}
//	}
//	/**
//	 * 更新项目的发货推广员
//	 * @param request
//	 */
//	@RequestMapping("/website/updateProjects_delivPromoter")
//	public void updateProject(HttpServletRequest request){
//		String projectId = request.getParameter("projectId");
//		String delivPromoter = request.getParameter("delivPromoter");
//		iCreateProjectService.updateDeliveryGoodsPromoter(Integer.parseInt(delivPromoter), Integer.parseInt(projectId));
//	}
	/**
	 * 初始化项目view或edit页面项目信息
	 * @param request	客户端请求
	 * @return	数据和显示页面
	 */
	@RequestMapping({"/website/project","/web/project"})
	public ModelAndView initProjectInfos(HttpServletRequest request,Model model,HttpSession session)
	{
		//获取项目id和开始时间
		String projectId = request.getParameter("projectId");
		Project project = projectService.findProjectByProjectId(Integer.parseInt(projectId));
		String startTime = project.getStartDate().toString();
		boolean start = false;
		try {
			//比较项目开始时间和当前时间并获取比较结果的返回值
		start = (new Date().getTime() - new SimpleDateFormat("yyyy-MM-dd").parse(startTime).getTime()) > 0 ? true : false;
		} catch (ParseException e) {
			logger.warn("初始化项目view或edit页面时,跳转发生异常");
		}
		
		//设置需获取的数据的变量
		
		List<ExchangeshopGoods> exchangeShopGoods = null;
		List<ProductInfos> productInfos = null;
		List<PointsLevel> pointsLevels = null;
		ProjectProvince projectProvince = null;
		List<Retailer> retailers = null;
		List<Promoter> promoters = null;
		Promoter promoter = null;
		
		//将变量和相关数据绑定（需自己加入所需数据对应的变量）
		projectProvince = iProjectDetailsService.findProjectById(Integer.parseInt(projectId));
		projectProvince.setPlacardBase64(Base64.getEncoder().encodeToString(projectProvince.getPlacardPath()));
		projectProvince.setBackgroundBase64(Base64.getEncoder().encodeToString(projectProvince.getBackgroundPath()));
		projectProvince.setPlacardPath(null);
		projectProvince.setBackgroundPath(null);
		retailers = iProjectDetailsService.findRetaileByProjectId(Integer.parseInt(projectId));
		promoters = iProjectDetailsService.findPromoterByProjectId(Integer.parseInt(projectId));
		promoter = iProjectDetailsService.findDelivPromoterByProjectId(Integer.parseInt(projectId));
		exchangeShopGoods =  iProjectDetailsService.findGoodsByProjectId(Integer.parseInt(projectId));
		productInfos = iProductService.findProductInfosByProjectId(Integer.parseInt(projectId));
		pointsLevels = pointsLevelService.findByProjectId(Integer.parseInt(projectId));
		
		List<Province> provinces = iProvinceService.getAllProvince();
		List<String> provinceNames = sortProvinces(provinces);
		 
		//建立数据与视图传递媒介
		ModelAndView mv = new ModelAndView();
		
		//全部转为json数据
		JSONObject projectProvinceJson = (JSONObject)JSONObject.toJSON(projectProvince);
		JSONArray retailersJson = (JSONArray)JSONArray.toJSON(retailers);
		JSONArray promotersJson = (JSONArray)JSONArray.toJSON(promoters);
		JSONObject promoterJson = (JSONObject)JSONObject.toJSON(promoter);
		JSONArray exchangeShopGoodsJson = (JSONArray)JSONArray.toJSON(exchangeShopGoods);
		JSONArray productInfosJson = (JSONArray)JSONArray.toJSON(productInfos);
		JSONArray pointsLevelsJson = (JSONArray)JSONArray.toJSON(pointsLevels);
		
		
		//创建相关数据变量以供前台使用
		mv.addObject("projectId", projectId);
		mv.addObject("projectProvince", projectProvinceJson);
		mv.addObject("retailers", retailersJson);
		mv.addObject("promoters", promotersJson);
		mv.addObject("promoter", promoterJson);
		mv.addObject("exchangeShopGoods", exchangeShopGoodsJson);
		mv.addObject("productInfos", productInfosJson);
		mv.addObject("pointsLevels", pointsLevelsJson);
		mv.addObject("msg", 1);
		
		mv.addObject("provinces", provinceNames);
		
		if (session.getAttribute("USER") instanceof Admin) {//管理员可以查看也可以编辑项目
			
			//通过start值判断进入view界面或edit界面
			if(start)
			{
				//进入view界面
				mv.setViewName("PC/ProjectDetail");
			}
			else
			{
				//进入edit界面
				mv.setViewName("PC/Addproject");
			}
		}else {//推广员只可以查看不能编辑项目
			//进入view界面
			mv.setViewName("PC/ProjectDetail");
			
		}
		
		return mv;
	}
	
	
//	/**
//	 * 比较获取的页码和最大页码
//	 * 
//	 * @param num
//	 *            当前页码
//	 * @param maxPageNum
//	 *            最大页码
//	 * @return 实际页码
//	 */
//	public static int compareNums(int num, int maxPageNum) {
//		if (num < 1) {
//			num = 1;
//		} else {
//			if (num > maxPageNum) {
//				if (maxPageNum == 0) {
//					num = 1;
//				} else {
//					num = maxPageNum;
//				}
//			}
//		}
//		return num;
//	}

	/**
	 * 根据给定省份集合将省份名按照首字母排序
	 * @param provinces 省份集合
	 * @return 排序后的省份名集合
	 */
	public List<String> sortProvinces(List<Province> provinces)
	{
		List<String> provinceNames = new ArrayList<>();
		for(Province province : provinces)
		{
			provinceNames.add(province.getName() + "-" + province.getId());
		}
		Sort sort = new Sort();
		Map<String, ArrayList<String>> map = sort.sort(provinceNames);
		for (int i = 0; i < map.get("Z").size(); i++) {
			if("重庆".equals(map.get("Z").get(i).substring(0, 2)))
			{
				map.get("C").add(map.get("Z").get(i));
				map.get("Z").remove(i);
			}
		}
		provinceNames.clear();
		for (int i = 65; i < 91; i++)
		{
			String str = (char)i +"";
			if(map.get(str) != null && !map.get(str).isEmpty())
			{
				for(String name : map.get(str))
				{
					provinceNames.add(name);
				}
			}
		}
		
		return provinceNames;
	}
	
}