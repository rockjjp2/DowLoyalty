package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dowloyalty.entity.Points;
import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.JsonDataBindSalesRecord;
import com.dowloyalty.pojo.webSale;
import com.dowloyalty.service.IImportExcelService;
import com.dowloyalty.service.IPointsService;
import com.dowloyalty.service.IProductService;
import com.dowloyalty.service.IProvinceService;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.service.ISalesRecordService;
import com.dowloyalty.service.ISearchSaleRecordService;
import com.dowloyalty.service.ProjectService;

@Controller
@RequestMapping("/WeChat/promoter")
public class WeChatSaleRecordController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	@Autowired
    private HttpSession session;
	
	@Resource
	ISalesRecordService iSalesRecordService;
	@Resource
	IImportExcelService importExcelService;
	@Resource
	IProvinceService iProvinceService;
	@Resource
	IRetailerService iRetailerService;
	@Resource
	ProjectService projectService;
	@Resource
	IProductService iProductService;
	@Resource
	private ISearchSaleRecordService iSearchSaleRecordService;
	@Resource
	private IPointsService iPointsService;
	
	private static final String ADDSALERECORD="WeChat/addSaleRecord";
	private static final String QUERYSALERECORD="WeChat/querySaleRecord";
	
	//添加销售记录
	@RequestMapping("/addSalesrecord")
	public String selectProject(Model model,String msg){
		int promoterId=((Promoter)session.getAttribute("USER")).getId();
		List<Project> projects = projectService.findProjectByPromoterId(promoterId);
		for(Project project : projects)
		{
			project.setPlacardPath(null);
			project.setBackgroundPath(null);
		}
		int provinceId = 1;
		try {
			provinceId = iProvinceService.findProvinceByPromoterId(promoterId).getId();
		} catch (Exception e) {
			logger.error("查询推广员省信息失败，设置成固定值1", e);
		}
		List<Retailer> retailers = iRetailerService.findRetailerByProvinceId(provinceId);
		List<ProductCategory> productcategors=iProductService.findAllProductCategory();
		model.addAttribute("projects", projects);
		model.addAttribute("retailers", retailers);
		model.addAttribute("productcategors", productcategors);
		if (msg!=null) {
			model.addAttribute("MSG", "添加记录成功");			
		}
		return ADDSALERECORD;
	}
	/**
	 * 项目改变，ajax更改相关信息
	 * @param projectId
	 * @param response
	 */
	@RequestMapping("/ProjectChangeAjax")
	@ResponseBody
	public void changeProject(String projectId,HttpServletResponse response){
		int promoterId=((Promoter)session.getAttribute("USER")).getId();
		int projectid=Integer.parseInt(projectId);
		int provinceId = 1;
		PrintWriter printWriter = null;
		List<Retailer> retailers ;
		List<ProductCategory> productcategors;
		String message = null;
		
		Project project = projectService.findProjectByProjectId(projectid);
		if(project != null)
		{
			boolean time = project.getStartDate().after(new Date());
			if(time)
			{
				message = "-1";
			}
		}
		if (projectid==0) 
		{
			try 
			{
				provinceId = iProvinceService.findProvinceByPromoterId(promoterId).getId();
			} 
			catch (Exception e) 
			{
				logger.error("查询推广员省信息失败，设置成固定值1", e);
			}
			retailers = iRetailerService.findRetailerByProvinceId(provinceId);
			productcategors=iProductService.findAllProductCategory();
		}
		else 
		{
			productcategors= iProductService.findProductCategoryByProjectId(projectid);
			retailers = iRetailerService.findRetailerByProjectID(projectid);
		}
		
		String salesJson = JSON.toJSONString(new JsonDataBindSalesRecord(message,null,null,productcategors,retailers));
		System.out.println(salesJson);
		try 
		{
			printWriter=response.getWriter();
			printWriter.println(salesJson);
		} 
		catch (IOException e) 
		{
			logger.error("printWriter，io异常",e);
		}
		finally 
		{
			printWriter.flush();
			printWriter.close();
		}
	}
	/**
	 * Ajax搜索用户信息
	 * @param projectId
	 * @param response
	 */
	@RequestMapping("/searchReatilerAjax")
	@ResponseBody
	public void searchRetailer(String projectId,String name,HttpServletResponse response){
		int projectid=Integer.parseInt(projectId);
		PrintWriter printWriter = null;
		List<Retailer> retailers ;
		if (projectid==0) {
			int promoterId=((Promoter)session.getAttribute("USER")).getId();
			int provinceId = iProvinceService.findProvinceByPromoterId(promoterId).getId();
			retailers= iRetailerService.searchRetailerByRetailerNameAndProvinceId(provinceId, name);
		}else {
			retailers = iRetailerService.findRetailerByProjectIdAndRetailerName(projectid, name);
		}
		
		String retailersJson = JSON.toJSONString(retailers);
		System.out.println(retailersJson);
		try {
			printWriter=response.getWriter();
			printWriter.println(retailersJson);
		} catch (IOException e) {
			logger.error("printWriter，io异常",e);
		}finally {
			printWriter.flush();
			printWriter.close();
		}
	}
	/**
	 * 产品种类修改后，页面动态修改
	 * @param categoryId
	 * @param projectId
	 * @param response
	 */
	@RequestMapping("/ProductCatChangeAjax")
	@ResponseBody
	public void changeProductCategors(String categoryId,String projectId,HttpServletResponse response){
		PrintWriter printWriter = null;
		try {
			int categoryid=Integer.parseInt(categoryId);
			int projectid=Integer.parseInt(projectId);
			List<ProductFamily> productFamilys;
			//前台种类为空，则不可选择其他家族和产品种类
			if (categoryid==0) {
				productFamilys=null;
			}else if(projectid==0){
			//前台project未选，则只根据种类分类
				productFamilys= iProductService.findProductFamilyByCategroyID(categoryid);
			}else {
				productFamilys=iProductService.findProductFamilyByCategroyIdAndProjectId(categoryid, projectid);
			}
			String salesJson = JSON.toJSONString(new JsonDataBindSalesRecord(null,productFamilys,null,null));
			System.out.println(salesJson);
				printWriter=response.getWriter();
				printWriter.println(salesJson);
		}catch (IOException e) {
			logger.error("printWriter，io异常",e);
		}catch (NumberFormatException e) {
			logger.error("前台至后台数据，转换异常",e);
		}finally {
			printWriter.flush();
			printWriter.close();
		}
	}
	
	//初始化产品家族信息
	@RequestMapping("/ProductFamChangeAjax")
	@ResponseBody
	public void changeProductFamily(String categoryId,String projectId,String familyId,HttpServletResponse response){
		PrintWriter printWriter = null;
		try {
		int familyid=Integer.parseInt(familyId);
		int projectid=Integer.parseInt(projectId);
		int promoterid=( (Promoter)session.getAttribute("USER") ).getId();
		List<Product> products;
		if (familyid==0) {
			int categoryid=Integer.parseInt(categoryId);
			//家族未选择，产品不可选
			products = iProductService.findProductByCategroyIdOrProjectIdAndPromoterId(categoryid, projectid, promoterid);//project id 可为0，即为空
		}else if(projectid==0){
			//project未定，根据家族选择产品
			products=  iProductService.findProductByFamilyId(familyid);
		}else {
			products=iProductService.findProductByFamilyIdAndProjectId(projectid, familyid);
		}
		String salesJson = JSON.toJSONString(products);
			printWriter=response.getWriter();
			printWriter.println(salesJson);
		} catch (IOException e) {
			logger.error("PrintWriter IO异常",e);
		}catch (NumberFormatException e) {
			logger.error("数字转换异常",e);
		}finally {
			printWriter.flush();
			printWriter.close();
		}
	}
	
	/**
	 * 单条插入销售记录
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/AddSaleRecordDo")
	@ResponseBody
	public void addSaleRecord(String projectId,String retailerId,
			String familyId,String productId,String nums,String sales){
		Date date = new Date();
		SimpleDateFormat sf =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		int promoterid=( (Promoter)session.getAttribute("USER") ).getId();
		//不选择项目则为无积分项目
		if ("0".equals(projectId)) {
			importExcelService.addSaleRecordNoPoint(Integer.parseInt(retailerId), Integer.parseInt(productId), Integer.parseInt(sales), promoterid,
					sf.format(date),Integer.parseInt(nums));
		}else {
			Points points = iPointsService.findPointByPrjectIdAndProductId(Integer.parseInt(projectId), Integer.parseInt(productId));
			//如果存在项目积分表，才可进行积分计算并写入数据库
			if (points != null) {
			int point=Integer.parseInt(sales)/points.getSalesAmount()*points.getPoints();
			importExcelService.addSaleRecord(Integer.parseInt(retailerId), Integer.parseInt(productId), Integer.parseInt(sales), promoterid,
					sf.format(date), Integer.parseInt(projectId), point,Integer.parseInt(nums));
			}else{
				importExcelService.addSaleRecord(Integer.parseInt(retailerId), Integer.parseInt(productId), Integer.parseInt(sales), promoterid,
						sf.format(date), Integer.parseInt(projectId), 0,Integer.parseInt(nums));
			}
		}
	}
	
	/**
	 * 查看销售记录
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/querySaleRecord")
	public String querySalesRecord(Model model,HttpServletRequest request){
		
		return QUERYSALERECORD;
	}
	
	//显示销售记录查询结果
	@RequestMapping("/showSaleRecord")
	public String showSalesRecord(Model model,HttpServletRequest request){
		String startDate = request.getParameter("startdate");
		String endDate = request.getParameter("enddate");
		String retailerName = request.getParameter("retailername");
		int promoterid=( (Promoter)session.getAttribute("USER") ).getId();
		List<webSale> saleRecords = iSearchSaleRecordService.findWeChatSaleRecordByQuery(startDate, endDate, Integer.toString(promoterid), retailerName);
		model.addAttribute("salesRecord", saleRecords);
		model.addAttribute("msgs", -1);
		return QUERYSALERECORD;
	}
}
