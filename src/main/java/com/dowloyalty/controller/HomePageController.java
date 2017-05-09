package com.dowloyalty.controller;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dowloyalty.entity.Admin;
import com.dowloyalty.entity.Farmer;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.service.ProjectService;

@Controller
public class HomePageController {
	@Autowired
    private HttpSession session;
	@Resource
	ProjectService projectService;
	
	/**
	 * 管理员页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/website/index")
	public String WeChatEnterpriseAdmin(Model model){
		System.out.println("session中获得的管理员"+(Admin)session.getAttribute("USER"));
		String userName=((Admin)session.getAttribute("USER")).getChineseName();
		model.addAttribute("USERNAME", userName);
		return "/PC/Main";
	}
	/**
	 * 推广员页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/web/index")
	public String WeChatEnterprisePro(Model model){
		String userName=((Promoter)session.getAttribute("USER")).getChineseName();
		model.addAttribute("USERNAME", userName);
		return "/PC/Main2";
	}
	
	/*PC主页*/
	@RequestMapping(value={"","/Login"})
	public String PCLogin(HttpServletRequest request, HttpServletResponse response){
		return "/PC/index";
	}
	
	/*农户注册页面*/
	@RequestMapping("/WeChat/Farmer_Register")
	public String farmerLogin(Model model)
	{
		List<Project> list = projectService.findAllHaveFarmerProjects();
		model.addAttribute("projects", list);
		return "/WeChat/farmer_register";
	}
	
	/*微信端区分加载零售商菜单和推广员菜单*/
	@RequestMapping(value={"/WeChat/retailer/home","/WeChat/promoter/home","/WeChat/farmer/home"})
	public String WeChatSubscription(Model model){
		Map<String, String> map=new LinkedHashMap<>();
		System.out.println("主页面"+session.getAttribute("IDENTITY"));
		if ("promoterWechat".equals(session.getAttribute("IDENTITY"))) {
			map.put("addSalesrecord", "增加销售记录");
			map.put("querySaleRecord", "查询销售记录");
			//是否有代发货列表
			int promoterId=((Promoter)session.getAttribute("USER")).getId();
			List<Project> projects = projectService.findProjectByAssistantId(promoterId);
			if (projects.size()!= 0) {
				map.put("deliverlist", "待发货列表");
			}else{
				//只是一个标记，随便写的。前台有这个在判断是否相等的时候用。。判断几个菜单
				model.addAttribute("menunum",3);
			}
		}
		else if("farmer".equals(session.getAttribute("IDENTITY")))
		{
			map.put("farmerinfo", "账户信息");
			map.put("farmersalesrecord", "销售记录");
		}
		else {
			//根据权限确定相应菜单，零售商页面
			map.put("accountInfo", "账户信息");
			map.put("exchangeshop", "礼品商城");
			map.put("pointsDetails", "积分明细");
		}
		model.addAttribute("map",map);
		return "/WeChat/Main";
	}
	
	/*微信端农户界面*/
	@RequestMapping(value="/WeChat/farmer/homePage")
	public String WeChatSubscriptionH0(HttpServletRequest request,Model model){
		Project project = projectService.findByFarmerId(((Farmer)session.getAttribute("USER")).getId() );
		if (project!=null) {
			model.addAttribute("imageURL", Base64.getEncoder().encodeToString(project.getPlacardPath()));
		}
		return "/WeChat/homePage";
	}
	
	/*微信端零售商界面*/
	@RequestMapping(value="/WeChat/retailer/homePage")
	public String WeChatSubscriptionH(HttpServletRequest request,Model model){
		Project project = projectService.findActiveByRid( ((Retailer)session.getAttribute("USER")).getId() );
		if (project!=null) {
			model.addAttribute("imageURL", Base64.getEncoder().encodeToString(project.getPlacardPath()));
			
		}
		return "/WeChat/homePage";
	}
	
	/*微信端推广员界面*/
	@RequestMapping(value="/WeChat/promoter/homePage")
	public String WeChatSubscriptionH2(HttpServletRequest request,Model model){
		//int id=((Promoter)session.getAttribute("USER")).getId();
		//int id=325;
		Project project = projectService.findActiveProjectByPromoterId(((Promoter)session.getAttribute("USER")).getId());
		System.out.println(project);
		if (project!=null) {
			model.addAttribute("imageURL", Base64.getEncoder().encodeToString(project.getPlacardPath()));
			
		}
		return "/WeChat/homePage";
	}
	
	/*登录错误页面*/
	@RequestMapping("/WeChat/Logintips")
	public String tips(Model model,String a){
		if(a!=null){
			model.addAttribute("mx", "a");
		}
		return "ReLogin";
	}
	
	/*登出系统*/
	@RequestMapping({"/website/loginout","/web/loginout"})
	public String loginout(){
		session.removeAttribute("USER");
		session.removeAttribute("IDENTITY");
		return "redirect:/Login";
	}
}
