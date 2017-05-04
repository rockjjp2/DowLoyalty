package com.dowloyalty.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dowloyalty.entity.Admin;
import com.dowloyalty.entity.ExchangeShop;
import com.dowloyalty.entity.Goods;
import com.dowloyalty.entity.Points;
import com.dowloyalty.entity.PointsLevel;
import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Province;
import com.dowloyalty.entity.RPromoterProvince;
import com.dowloyalty.pojo.ExchangeRecordWeb;
import com.dowloyalty.pojo.PointMapping;
import com.dowloyalty.pojo.PromoterVo;
import com.dowloyalty.pojo.RetailerAccInfo;
import com.dowloyalty.pojo.webSale;
import com.dowloyalty.service.ExchangeRecordService;
import com.dowloyalty.service.ExchangeShopService;
import com.dowloyalty.service.GoodsService;
import com.dowloyalty.service.IPointsService;
import com.dowloyalty.service.IProductService;
import com.dowloyalty.service.IPromoterService;
import com.dowloyalty.service.IProvinceService;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.service.ISalesRecordService;
import com.dowloyalty.service.ISearchSaleRecordService;
import com.dowloyalty.service.PointsLevelService;
import com.dowloyalty.service.ProjectService;
import com.dowloyalty.utils.CompareNums;
import com.dowloyalty.utils.ImportExcelUtil;
import com.dowloyalty.utils.WeChatMessageUtil;

/**
 * Web端控制器
 * @author wangyuanjie
 *
 */
@Controller
public class WebController {
	
	@Resource
	GoodsService goodsService;
	@Resource
	ExchangeShopService exchangeShopService;
	@Resource
	IProvinceService iProvinceService;
	@Resource
	ISalesRecordService iSalesRecordService;
	@Resource
	ExchangeRecordService exchangeRecordService;
	@Resource
	IRetailerService iRetailerService;
	@Resource
	IProductService iProductService;
	@Resource
	IPointsService iPointsService;
	@Resource
	PointsLevelService pointsLevelService;
	@Resource
	ProjectService projectService;
	@Resource
	ISearchSaleRecordService iSearchSaleRecordService;
	@Resource
	IPromoterService iPromoterService;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	
	/**
	 * 管理员操作
	 * 获取所有礼品
	 * @param response	服务器响应
	 */
	@RequestMapping("/website/GoodsInfo")
	public void getExchangeshopGoods(HttpServletResponse response)
	{
		List<Goods> allGoods = goodsService.findAll();
		JSONArray json = (JSONArray) JSONArray.toJSON(allGoods);
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
		}
	}
	
	
	/**
	 * 根据项目id和礼品id增加礼品商城礼品配置
	 * @param request	客户端请求
	 */
	@RequestMapping("/website/addExchangeShopGoods")
	public void addExchangeShopGoods(HttpServletRequest request,HttpSession session,HttpServletResponse response)
	{
		int userId = ((Admin)session.getAttribute("USER")).getId();
		String projectId = request.getParameter("projectId");
		String goodsId = request.getParameter("goodsId");
		String points = request.getParameter("points");
		ExchangeShop existShop = exchangeShopService.findById(Integer.parseInt(projectId), Integer.parseInt(goodsId));
		if(existShop != null)
		{
			existShop.setExchangePoints(Integer.parseInt(points));
			existShop.setIsActive(true);
			exchangeShopService.update(existShop);
		}else
		{
			ExchangeShop shop = new ExchangeShop();
			shop.setProjectID(Integer.parseInt(projectId));
			shop.setGoodsID(Integer.parseInt(goodsId));
			shop.setExchangePoints(Integer.parseInt(points));
			shop.setIsActive(true);
			exchangeShopService.save(shop);
		}
		String msg = "添加成功";
		try {
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送添加礼品配置信息异常");
		}
		logger.debug("管理员"+userId+"在项目(id-->"+projectId+")中添加了礼品,id-->"+goodsId+",points-->"+points);
		
	}
	
	
	/**
	 * 根据项目id和礼品id获取礼品商城记录并注销该记录
	 * @param request	客户端请求
	 */
	@ResponseBody
	@RequestMapping("/website/deleteExchangeShopGoods")
	public void deleteExchangeShopGoods(HttpServletRequest request,HttpSession session,HttpServletResponse response)
	{
		int userId = ((Admin)session.getAttribute("USER")).getId();
		String projectId = request.getParameter("projectId");
		String goodsId = request.getParameter("goodsId");
		ExchangeShop shop = exchangeShopService.findById(Integer.parseInt(projectId), Integer.parseInt(goodsId));
		shop.setIsActive(false);
		exchangeShopService.update(shop);
		String msg = "删除成功";
		try {
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送删除礼品配置信息异常");
		}
		logger.debug("管理员"+userId+"在项目(id-->"+projectId+")中删除了礼品,id-->"+goodsId);
	}
	
	
	/**
	 * 根据项目id和积分等级名称增加积分等级记录
	 * @param request	客户端请求
	 */
	@RequestMapping("/website/addPointsLevel")
	public void addPointsLevel(HttpServletRequest request,HttpSession session,HttpServletResponse response)
	{
		int userId = ((Admin)session.getAttribute("USER")).getId();
		String projectId = request.getParameter("projectId");
		String name = request.getParameter("name");
		String points = request.getParameter("points");
		PointsLevel exisPointsLevel = pointsLevelService.findByProjectIdAndName(Integer.parseInt(projectId), name);
		if(null != exisPointsLevel)
		{
			exisPointsLevel.setProjectID(Integer.parseInt(projectId));
			exisPointsLevel.setName(name);
			exisPointsLevel.setPoints(Integer.parseInt(points));
			exisPointsLevel.setActive(true);
			pointsLevelService.update(exisPointsLevel);
		}else
		{
			PointsLevel pointsLevel = new PointsLevel();
			pointsLevel.setProjectID(Integer.parseInt(projectId));
			pointsLevel.setName(name);
			pointsLevel.setPoints(Integer.parseInt(points));
			pointsLevel.setActive(true);
			pointsLevelService.save(pointsLevel);
		}
		String msg = "添加成功";
		try {
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送添加等级记录信息异常");
		}
		logger.debug("管理员"+userId+"在项目(id-->"+projectId+")中添加了积分等级,name-->"+name+",points-->"+points);
	}
	
	/**
	 * 根据项目id和积分等级名称注销积分等级记录
	 * @param request	客户端请求
	 */
	@RequestMapping("/website/deletePointsLevel")
	public void deletePointsLevel(HttpServletRequest request,HttpSession session,HttpServletResponse response)
	{
		int userId = ((Admin)session.getAttribute("USER")).getId();
		String projectId = request.getParameter("projectId");
		String name = request.getParameter("name");
		PointsLevel pointsLevel = pointsLevelService.findByProjectIdAndName(Integer.parseInt(projectId), name);
		pointsLevel.setActive(false);
		pointsLevelService.update(pointsLevel);
		String msg = "删除成功";
		try {
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送删除等级记录信息异常");
		}
		logger.debug("管理员"+userId+"在项目(id-->"+projectId+")中删除了积分等级,name-->"+name);
	}
	
	/**
	 * 管理员操作
	 * 获取所有产品目录
	 * @param response	服务器响应
	 */
	@RequestMapping("/website/ProductCategory")
	public void getAllProductCategory(HttpServletResponse response)
	{
		List<ProductCategory> allCategory = iProductService.findAllProductCategory();
		JSONArray json = (JSONArray) JSONArray.toJSON(allCategory);
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("产品目录json数据传输异常");
		}
	}
	
	/**
	 * 管理员操作
	 * 获取所有产品家族
	 * @param request	客户端请求
	 * @param response	服务器响应
	 */
	@RequestMapping("/website/ProductFamily")
	public void getProductFamily(HttpServletRequest request, HttpServletResponse response)
	{
		String categoryId = request.getParameter("categoryId");
		List<ProductFamily> allProductFamily = iProductService.findProductFamilyByCategroyID(Integer.parseInt(categoryId));
		JSONArray json = (JSONArray) JSONArray.toJSON(allProductFamily);
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("产品家族json数据传输异常");
		}
	}
	
	/**
	 * 管理员操作
	 * 获取所有产品
	 * @param request	客户端请求
	 * @param response	服务器响应
	 */
	@RequestMapping("/website/Product")
	public void getProduct(HttpServletRequest request, HttpServletResponse response)
	{
		String familyId = request.getParameter("familyId");
		List<Product> allProduct = iProductService.findProductByFamilyId(Integer.parseInt(familyId));
		JSONArray json = (JSONArray) JSONArray.toJSON(allProduct);
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("产品json数据传输异常");
		}
	}
	
	
	/**
	 * 根据项目id和产品id增加产品积分记录
	 * @param request	客户端请求
	 */
	@RequestMapping("/website/addProductPoints")
	public void addProductPoints(HttpServletRequest request, HttpSession session,HttpServletResponse response)
	{
		int userId = ((Admin)session.getAttribute("USER")).getId();
		String projectId = request.getParameter("projectId");
		String productId = request.getParameter("productId");
		String salesAmount = request.getParameter("salesAmount");
		List<String> addIds = null;
		List<String> updateIds = null;
		if("all".equals(productId))
		{
			String addArray = request.getParameter("addArray");
			List<String> allPointsIds = iPointsService.findAllPointsByProjectId(projectId);
			if(addArray.contains(","))
			{
				String[] array = addArray.split(",");
				addIds = new ArrayList<String>();
				updateIds = new ArrayList<String>();
				for(String id : array)
				{
					if(allPointsIds.contains(id))
					{
						updateIds.add(id);
					}else
					{
						addIds.add(id);
					}
				}
			}
			
			if(null != addIds && !addIds.isEmpty())
			{
				Points points = new Points();
				points.setProjectID(Integer.parseInt(projectId));
				points.setPoints(1);
				points.setSalesAmount(Integer.parseInt(salesAmount));
				points.setActive(true);
				iPointsService.patchAddProducts(addIds, points);
			}
			
			if(null != updateIds && !updateIds.isEmpty())
			{
				for(String id : updateIds)
				{
					Points existPoints = iPointsService.findPointByPrjectIdAndProductId(Integer.parseInt(projectId), Integer.parseInt(id));
					existPoints.setSalesAmount(Integer.parseInt(salesAmount));
					existPoints.setActive(true);
					iPointsService.update(existPoints);
				}
			}
		}
		else
		{
			Points existPoints = iPointsService.findPointByPrjectIdAndProductId(Integer.parseInt(projectId), Integer.parseInt(productId));
			if(null != existPoints)
			{
				existPoints.setSalesAmount(Integer.parseInt(salesAmount));
				existPoints.setActive(true);
				iPointsService.update(existPoints);
			}
			else
			{
				Points points = new Points();
				points.setProjectID(Integer.parseInt(projectId));
				points.setProductID(Integer.parseInt(productId));
				points.setPoints(1);
				points.setSalesAmount(Integer.parseInt(salesAmount));
				points.setActive(true);
				iPointsService.save(points);
			}
		}
		String msg = "添加成功";
		try {
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送添加产品积分记录信息异常");
		}
		logger.debug("管理员"+userId+"在项目(id-->"+projectId+")中添加了产品积分,productId-->"+productId+",salesAmount-->"+salesAmount);
	}
	
	/**
	 * 根据项目id和礼品id获取礼品商城记录并注销该记录
	 * @param request	客户端请求
	 */
	@RequestMapping("/website/deletePorductPoints")
	public void deletePorductPoints(HttpServletRequest request, HttpSession session,HttpServletResponse response)
	{
		int userId = ((Admin)session.getAttribute("USER")).getId();
		String projectId = request.getParameter("projectId");
		String productId = request.getParameter("productId");
		Points points = iPointsService.findPointByPrjectIdAndProductId(Integer.parseInt(projectId), Integer.parseInt(productId));
		points.setActive(false);
		iPointsService.update(points);
		String msg = "删除成功";
		try {
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送删除产品积分记录信息异常");
		}
		logger.debug("管理员"+userId+"在项目(id-->"+projectId+")中删除了产品积分,productId-->"+productId);
	}
	
	
	/**
	 * 根据会员id或者会员名获取兑换记录且分页显示
	 * @param request	客户端请求
	 * @param response	服务器响应
	 */
	@RequestMapping({"/website/exchangerecord"})
	public void getExchangeRecords(HttpServletRequest request,HttpServletResponse response)
	{
		//获取搜索条件和页码
		String idOrName = request.getParameter("idOrName");
		String projectId = request.getParameter("projectId");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String pageNum = request.getParameter("pageNum");
		List<ExchangeRecordWeb> exchangeRecords = null;
		int num = Integer.parseInt(pageNum);
		int maxPageNum;
		if("-1".equals(projectId))
		{
			projectId = null;
		}
		
		//若搜索条件为空，默认搜索所有记录
		if(null == idOrName)
		{
			maxPageNum = exchangeRecordService.getMaxPageNum(null, null, projectId, startDate, endDate);
			num = CompareNums.compareNums(num,maxPageNum);
			exchangeRecords = exchangeRecordService.findByConditions(null, null, projectId, startDate, endDate, num);
		}
		else
		{
			//通过Integer.parseInt()测试搜索条件是否为数字来确定sql语句
			try
			{
				Integer.parseInt(idOrName);
				maxPageNum = exchangeRecordService.getMaxPageNum(idOrName, null, projectId, startDate, endDate);
				num = CompareNums.compareNums(num,maxPageNum);
				exchangeRecords = exchangeRecordService.findByConditions(idOrName, null, projectId, startDate, endDate, num);
			}catch(Exception e)
			{
				maxPageNum = exchangeRecordService.getMaxPageNum(null, idOrName, projectId, startDate, endDate);
				num = CompareNums.compareNums(num,maxPageNum);
				exchangeRecords = exchangeRecordService.findByConditions(null, idOrName, projectId, startDate, endDate, num);
			}
		}
		
		//将搜索条件，实际显示页码，最大页码以及获取的兑换记录集合拼接成json字符串传到前台页面
		String page = "[{page:"+num;
		String maxPage = ",maxPage:"+maxPageNum+"}]";
		String ejson = page + maxPage;
		ejson = JSONObject.parse(ejson).toString();
		JSONArray json = (JSONArray)JSONArray.toJSON(exchangeRecords);
		String longJson = ejson + "@#$" + json.toString();
		try {
			PrintWriter out = response.getWriter();
			out.println(longJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("根据条件查找所得兑换记录集合json数据传输异常");
		}
	}
	
	/**
	 * 根据会员id或者会员名获取会员信息且分页显示
	 * @param request	客户端请求
	 * @param response	服务器响应
	 * @param model	模型(传数据用)
	 */
	@RequestMapping({"/website/infos","/web/infos"})
	public void getRetailerInfos(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		String idOrName = request.getParameter("idOrName");
		String pageNum = request.getParameter("pageNum");
		List<RetailerAccInfo> retailerInfos = null;
		int num = Integer.parseInt(pageNum);
		int maxPageNum;
		
		//若搜索条件为空，默认搜索所有记录
		if(null == idOrName)
		{
			maxPageNum = iRetailerService.getMaxPageNum(null, null);
			num = CompareNums.compareNums(num,maxPageNum);
			retailerInfos = iRetailerService.findByConditions(null, null, num);
		}
		else
		{
			//通过Integer.parseInt()测试搜索条件是否为数字来确定sql语句
			try
			{
				Integer.parseInt(idOrName);
				maxPageNum = iRetailerService.getMaxPageNum(idOrName, null);
				num = CompareNums.compareNums(num,maxPageNum);
				retailerInfos = iRetailerService.findByConditions(idOrName, null, num);
			}catch(Exception e)
			{
				maxPageNum = iRetailerService.getMaxPageNum(null, idOrName);
				num = CompareNums.compareNums(num,maxPageNum);
				retailerInfos = iRetailerService.findByConditions(null, idOrName, num);
			}
		}
		
		for (int i = 0; i < retailerInfos.size(); i++) {
			RetailerAccInfo reAcc = retailerInfos.get(i);
			Project project = projectService.findActiveByRid(reAcc.getReId());
			if (null != project) {
				int projectId = project.getId();
				List<PointMapping> pointMappings = iPointsService.findPointMappingByProject(projectId);
				for (PointMapping pointMapping : pointMappings) {
					if (pointMapping.getRetailerID() == reAcc.getReId()) {
						// 获取剩余积分和累计积分
						int costPoints = exchangeRecordService.getSumExchangPointsByProjectIdAndRetailerId(projectId,
								reAcc.getReId());
						int points = pointMapping.getTotalPoint();
						if (project.isVisible()) {
							// 行号即为排名
							reAcc.setCurRank(pointMapping.getRownum());
						}
						// 设置累计积分
						reAcc.setTotalPoints(points);
						// 设置剩余积分
						reAcc.setRemainPoints(points + costPoints);
					}
				}
			}
		}
		
		//将搜索条件，实际显示页码，最大页码以及获取的兑换记录集合拼接成json字符串传到前台页面
		idOrName = "[{idOrName:"+("'"+idOrName+"'");
		String page = ",page:"+num;
		String maxPage = ",maxPage:"+maxPageNum+"}]";
		String ejson = idOrName + page + maxPage;
		ejson = JSONObject.parse(ejson).toString();
		JSONArray json = (JSONArray)JSONArray.toJSON(retailerInfos);
		String longJson = ejson + "@#$" + json.toString();
		try {
			PrintWriter out = response.getWriter();
			out.println(longJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("根据条件查找所得会员信息集合json数据传输异常");
		}
	}
	
	/*
	 * 导出销售记录报表
	 * */
	@RequestMapping({"/website/exportExcel","/web/exportExcel"})
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,HttpSession session)
	{
		int promoterId = 0;
		if (!(session.getAttribute("USER") instanceof Admin)) {
			promoterId = ((Promoter)session.getAttribute("USER")).getId();
		}
		
		String projectId = request.getParameter("projectId");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String retailerName = request.getParameter("retailerName");
		List<webSale> salerecords = null;
		
		
		String[] titleArray = new String[]{"项目名","时间","零售商名","产品类别","产品家族","产品","数量","销售额"};
		
		if(null != projectId)
		{
			salerecords = iSearchSaleRecordService.findWebSaleRecords(Integer.parseInt(projectId), startDate, endDate, promoterId, retailerName);
		}
		else
		{
			salerecords = iSearchSaleRecordService.findWebSaleRecords(0, startDate, endDate, promoterId, retailerName);
		}
		
		//创建excel
		Workbook excelBook = new HSSFWorkbook();
		Sheet excelSheet = excelBook.createSheet();
		CellStyle cellStyle = excelBook.createCellStyle();
		
		//创建第一行标题
		Row title = excelSheet.createRow(0);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		title.setRowStyle(cellStyle);
		//第一行开头
		for(int i = 0; i < titleArray.length;i++)
		{
			Cell titleCell = title.createCell(i,Cell.CELL_TYPE_STRING);
			titleCell.setCellStyle(cellStyle);
			titleCell.setCellValue(titleArray[i]);
		}
		
		
		
		//从第二行开始创建内容
		if (null != salerecords && !salerecords.isEmpty()) {
			for (int i = 0; i < salerecords.size(); i++) {
				// 创建行
				Row contentRow = excelSheet.createRow(i + 1);
				// 设置行样式
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				contentRow.setRowStyle(cellStyle);


				for (int k = 0; k < titleArray.length; k++) {
					Cell cell_00 = contentRow.createCell(0);
					cell_00.setCellStyle(cellStyle);
					cell_00.setCellValue(salerecords.get(i).getProjectName());
					Cell cell_01 = contentRow.createCell(1);
					cell_01.setCellStyle(cellStyle);
					cell_01.setCellValue(salerecords.get(i).getSubmitDate());
					Cell cell_02 = contentRow.createCell(2);
					cell_02.setCellStyle(cellStyle);
					cell_02.setCellValue(salerecords.get(i).getRetaileName());
					Cell cell_03 = contentRow.createCell(3);
					cell_03.setCellStyle(cellStyle);
					cell_03.setCellValue(salerecords.get(i).getCategoryName());
					Cell cell_04 = contentRow.createCell(4);
					cell_04.setCellStyle(cellStyle);
					cell_04.setCellValue(salerecords.get(i).getFamilyName());
					Cell cell_05 = contentRow.createCell(5);
					cell_05.setCellStyle(cellStyle);
					cell_05.setCellValue(salerecords.get(i).getProductName());
					Cell cell_06 = contentRow.createCell(6);
					cell_06.setCellStyle(cellStyle);
					cell_06.setCellValue(salerecords.get(i).getAmount());
					Cell cell_07 = contentRow.createCell(7);
					cell_07.setCellStyle(cellStyle);
					cell_07.setCellValue(salerecords.get(i).getTotalPrice());
				}
			}
		}
			try {
					//配置文件输出
					response.setContentType("multipart/form-data");
					response.setHeader("Content-Disposition", "attachment;fileName=salesRecord.xls");
					ServletOutputStream out = response.getOutputStream();
					excelBook.write(out);
					out.flush();
					out.close();
				} catch (Exception e) {
				}
	}
	
	
	/**
	 * 将礼品信息批量导入至数据库
	 * @param file 礼品信息表格文件
	 * @param response 服务器响应
	 */
	@RequestMapping({"/website/importExcel"})
	public void importExcel(MultipartFile file, HttpServletResponse response)
	{
		List<Goods> goodsList = null;
		try {
			goodsList = ImportExcelUtil.importGoodsInfo(file.getInputStream());
		} catch (Exception e) {
			logger.warn("解析表格文件异常");
		}
		List<Goods> allGoods = goodsService.findAll();
		for (int i = 0; i < allGoods.size(); i++) 
		{
			for (int j = 0; j < goodsList.size(); j++) 
			{
				if(allGoods.get(i).getName().equals(goodsList.get(j).getName()))
				{
					goodsList.remove(j);
				}
			}
		}
		if(goodsList != null && !goodsList.isEmpty())
		{
			goodsService.batchGoodsInfo(goodsList);
		}
		logger.info("从表格文件批量导入了礼品信息");
		try {
		String msg = "导入成功";
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送导入礼品消息异常");
		}
	}
	
	/*
	 * 导入信息页面入口
	 * */
	@RequestMapping({"/website/importExcelEnter"})
	public String importExcelEnter(Model model)
	{
		List<Goods> goodsList = goodsService.findAll();
		model.addAttribute("goodsList",goodsList);
		return "PC/importGoodsTest";
	}
	
	/**
	 * 导入礼品图片存入项目并修改相应礼品的路径
	 * @param file 上传的图片文件
	 * @param request 客户端请求
	 * @param response 服务器响应
	 */
	@RequestMapping({"/website/importImg"})
	public void importImg(MultipartFile file, HttpServletRequest request, HttpServletResponse response)
	{
		String goodsId = request.getParameter("goodsId");
		String goodsName = request.getParameter("goodsName");
		BufferedInputStream reader = null;
		FileOutputStream writer = null;
		try {
			reader = new BufferedInputStream(file.getInputStream());
		} catch (IOException e) {
			logger.warn("上传图片读取异常");
		}
		
		String savePath = "D:\\Software\\softwareProgram\\eclipse\\workspace\\DowLoyalty3\\src\\main\\webapp\\Resources\\html\\images\\goods\\" + goodsId + "-" + goodsName + ".png";
		System.out.println("Path=" + savePath);
		String imgPath = "/Resources/html/images/goods/" + goodsId + "-" + goodsName + ".png";
		File img = new File(savePath);
		if(img.exists())
		{
			img.delete();
		}
		try
		{
			img.createNewFile();
			writer = new FileOutputStream(img);
		}
		catch (IOException e)
		{
			logger.warn("存储礼品图片时，无法找到图片(创建文件异常)");
		}
		byte[] buffer = new byte[1024];
		
		try 
		{
			while(reader.read(buffer,0,buffer.length) != -1)
			{
				writer.write(buffer,0,buffer.length);
			}
			reader.close();
			writer.flush();
			writer.close();
		} 
		catch (IOException e) 
		{
			logger.warn("文件读取或写入异常");
		}
		Goods goods = goodsService.findById(Integer.parseInt(goodsId));
		System.out.println("goods exist="+(goods == null));
		goods.setImagePath(imgPath);
		goodsService.update(goods);
		logger.info("修改了"+ goodsName + "图片的路径");
		String msg = "导入图片成功";
		try {
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送导入图片信息异常");
		}
	}
	
	/**
	 * 导入推广员信息
	 * @param file 上传文件
	 * @param response 服务器响应
	 */
	@RequestMapping("/website/importPromoters")
	public void importPromoters(MultipartFile file, HttpServletResponse response)
	{
		List<PromoterVo> promoters = null;
		try 
		{
			promoters = ImportExcelUtil.importPromotersInfo(file.getInputStream());
		} catch (Exception e) 
		{
			logger.warn("获取上传推广员信息异常");
		}
		
		//将已存在的推广员与新的推广员分开
		List<Promoter> allPromoters = iPromoterService.findAllActivePromoters();
		List<PromoterVo> updatePromoters = new ArrayList<>();
		Set<PromoterVo> saveSets = new HashSet<>();
		for (int i = 0; i < allPromoters.size(); i++) {
			for (int j = 0; j < promoters.size(); j++) {
				if(allPromoters.get(i).getChineseName().equals(promoters.get(j).getChineseName()))
				{
					promoters.get(j).setId(allPromoters.get(i).getId());
					updatePromoters.add(promoters.get(j));
				}
				else
				{
					saveSets.add(promoters.get(j));
				}
			}
		}
		List<PromoterVo> savePromoters = new ArrayList<>();
		Iterator<PromoterVo> iterators = saveSets.iterator();
		while(iterators.hasNext())
		{
			savePromoters.add(iterators.next());
		}
		
		for (int i = 0; i < allPromoters.size(); i++) {
			for (int j = 0; j < savePromoters.size(); j++) {
				if(savePromoters.get(j).getChineseName().equals(allPromoters.get(i).getChineseName()))
				{
					savePromoters.remove(j);
					break;
				}
			}
		}
		
		//修改推广员信息
		for (PromoterVo promoterVo : updatePromoters) 
		{
			iPromoterService.update(promoterVo);
		}
		logger.info("从导入的excel表格数据修改了推广员信息");
		
		//添加新的推广员
		if(!promoters.isEmpty())
		{
//			iPromoterService.batchSave(savePromoters);
		}
		logger.info("从导入的excel表格数据添加了推广员信息");
		
		//将省份id与推广员绑定
		List<Province> provinces = iProvinceService.getAllProvince();
		for (Province province : provinces) {
			for (PromoterVo promoter : promoters) {
				if(province.getName().equals(promoter.getProvinceName()))
				{
					promoter.setProvinceId(province.getId());
				}
			}
		}
		
		//将已存在的推广员与省份的关系与新的分开
		List<RPromoterProvince> relations = iPromoterService.findAllPromoterAndProvinceRelation();
		List<PromoterVo> updateRelations = new ArrayList<>();
		Set<PromoterVo> saveSet = new HashSet<>();
		for (int i = 0; i < relations.size(); i++) {
			for (int j = 0; j < promoters.size(); j++) {
				if(relations.get(i).getPromoterID() == promoters.get(j).getId())
				{
					updateRelations.add(promoters.get(j));
				}
				else
				{
					saveSet.add(promoters.get(j));
				}
			}
		}
		List<PromoterVo> saveRelations = new ArrayList<>();
		Iterator<PromoterVo> iterator = saveSet.iterator();
		while(iterator.hasNext())
		{
			saveRelations.add(iterator.next());
		}
		
		for (int i = 0; i < relations.size(); i++) {
			for (int j = 0; j < saveRelations.size(); j++) {
				if(relations.get(i).getPromoterID() == saveRelations.get(j).getId())
				{
					saveRelations.remove(j);
					break;
				}
			}
		}
		
		//修改推广员与省份的关系
		for (PromoterVo promoter : updateRelations) {
			iPromoterService.updatePromoterAndProvinceRelation(promoter.getId(), promoter.getProvinceId());
		}
		logger.info("从导入的excel表格数据修改了推广员与省份的关系");
		
		//添加推广员与省份的关系
		if(!saveRelations.isEmpty())
		{
//			iPromoterService.batchSavePromoterAndProvinceRelation(saveRelations);
		}
		logger.info("从导入的excel表格数据添加了推广员与省份的关系");
		
		String msg = "导入成功";
		try {
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送导入推广员成功信息异常");
		}
	}
	
	
//	@RequestMapping("/website/json")
//	public void getInfo(HttpServletResponse response)
//	{
//		String openId = "oV21o0pUET6_EfyD4GkMZ9W0cjkM";
//		String info = WeChatMessageUtil.focusInfo(openId, "测试柑橘","绿大生",String.valueOf(15));
//		try {
//			PrintWriter out = response.getWriter();
//			out.println(info);
//			out.flush();
//			out.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
