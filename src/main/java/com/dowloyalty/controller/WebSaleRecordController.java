package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.dowloyalty.entity.Admin;
import com.dowloyalty.entity.Points;
import com.dowloyalty.entity.Product;
import com.dowloyalty.entity.ProductCategory;
import com.dowloyalty.entity.ProductFamily;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Province;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.Excel;
import com.dowloyalty.pojo.JsonSaleRecord;
import com.dowloyalty.pojo.webSale;
import com.dowloyalty.service.IImportExcelService;
import com.dowloyalty.service.IPointsService;
import com.dowloyalty.service.IProvinceService;
import com.dowloyalty.service.ISalesRecordService;
import com.dowloyalty.service.ISearchSaleRecordService;
import com.dowloyalty.utils.CompareNums;
import com.dowloyalty.utils.ReadExcel;
import com.dowloyalty.utils.UploadUtils;

@Controller
@RequestMapping({"/website","/web"})
public class WebSaleRecordController {

	@Resource
	ISalesRecordService iSalesRecordService;
	@Resource
	IImportExcelService iImportExcelService;
	@Resource
	private ISearchSaleRecordService iSearchSaleRecordService;
	@Resource
	private IPointsService iPointsService;
	@Resource
	private IProvinceService iProvinceService;

	private static final String IMPORTSALESRECORD = "PC/ImportSalesRecord";
	private static final String HISTORYSALESRECORD = "PC/HistorySalesRecord";
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 从项目进入销售记录页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/salerecord")
	public String enterByProject(Model model, HttpServletRequest request,HttpSession session) {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		List<Project> projects;
		int userid = 0;
		if (session.getAttribute("USER") instanceof Admin) {
			projects = iSearchSaleRecordService.findProject();
		}else {
			userid=((Promoter)session.getAttribute("USER") ).getId();
			projects = iSearchSaleRecordService.findProjectByPromoterId(userid);
		}
		
		for (Project project : projects) {
			project.setBackgroundPath(null);
			project.setPlacardPath(null);
		}
		model.addAttribute("projectId", projectId);
		model.addAttribute("projects", projects);
		return "PC/HistorySalesRecord";
	}

	/**
	 * 从主页面进入历史销售记录页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/showSaleRecord")
	public String showSalesRecord(Model model,HttpSession session) {
		List<Project> projects;
		if (session.getAttribute("USER") instanceof Admin) {
			//当前登录人是管理员，可以查看所有项目
			projects = iSearchSaleRecordService.findProject();
		}else {
			//当前登录人是推广员，可以查看参与的项目
			int userid=((Promoter)session.getAttribute("USER") ).getId();
			projects = iSearchSaleRecordService.findProjectByPromoterId(userid);
		}
		for (Project project : projects) {
			project.setPlacardPath(null);
			project.setBackgroundPath(null);
		}
		model.addAttribute("projects", projects);

		return HISTORYSALESRECORD;
	}

	/**
	 * 显示根据字段搜索到的销售记录，带分页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/querySaleRecord")
	@ResponseBody
	public void querySalesRecord(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		/* 获得前台传入的数据 */
		String projectId = request.getParameter("projectId");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String retailerName = request.getParameter("retailerName");
		List<Project> projects;
		int promoterId=0;
		if (session.getAttribute("USER") instanceof Admin) {
			//当前登录人是管理员，可以查看所有项目
			projects = iSearchSaleRecordService.findProject();
		}else {
			//当前登录人是推广员，可以查看参与的项目
			 promoterId =((Promoter)session.getAttribute("USER") ).getId();
			projects = iSearchSaleRecordService.findProjectByPromoterId(promoterId);
		}
		for (Project project : projects) {
			project.setPlacardPath(null);
			project.setBackgroundPath(null);
		}
		List<webSale> webSales;
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(pageNum);
		int totalPageNum;// 总页面数
		totalPageNum = iSearchSaleRecordService.getTotalPage(Integer.parseInt(projectId), startDate, endDate, promoterId,
				retailerName);
		num = CompareNums.compareNums(num, totalPageNum);
		webSales = iSearchSaleRecordService.findWebSaleRecordByQuery(Integer.parseInt(projectId), startDate, endDate,
				promoterId, retailerName, num);
System.out.println("查看销售记录页面"+webSales.size());
		String longJson = JSON.toJSONString(
				new JsonSaleRecord(projectId, startDate, endDate, retailerName, num, totalPageNum, projects, webSales));
		try {
			PrintWriter out = response.getWriter();
			out.println(longJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("Json数据传输异常");
		}

	}

	/**
	 * 导入销售记录的入口
	 * 
	 * @return
	 */
	@RequestMapping("/enterImportSaleRecord")
	public String first() {
		return IMPORTSALESRECORD;
	}

	/**
	 * 导入销售记录
	 * 
	 * @return
	 */
	@RequestMapping("/importSaleRecord")
	public String addSaleRecord(Model model, HttpServletRequest request,
			@RequestParam("uploadExcel") MultipartFile file,HttpSession session ) {
		
			//当前登录人是推广员，可以查看参与的项目
			int promoterId =((Promoter)session.getAttribute("USER") ).getId();
		int provinceId = iImportExcelService.findProvIdByPromId(promoterId);
		UploadUtils uploadUtils = new UploadUtils();
		uploadUtils.uploadFile(file, request);
		System.out.println("file===============================" + file);
		// 存放失败的数据字段
		List<String> excelData = new ArrayList<String>();
		ReadExcel readExcel = new ReadExcel();
		List<Excel> excels;
		try {
			// 读取导入Excel中的数据
			excels = readExcel.readExcel(file);
			excelData = getErrorMsg(excels,provinceId);
			
			// 查找导入excel前最大的销售记录id
			int maxId;
			try {
				maxId = iImportExcelService.findMaxSaleRecordID(promoterId);
			} catch (Exception e) {
				maxId=0;
			}
			// 如果excel中的数据全部正确，则导入数据库中，并查看导入数据
			if (excelData.isEmpty()) {

				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				for (Excel excel : excels) {

					double totalPrice = excel.getTotalPrice();
					// 导入未参加活动的销售记录
					if (excel.getProjectName() == null || excel.getProjectName() == "") {
						int productId = iImportExcelService.findProductIDByName(excel.getProductName(),
								excel.getProductFamilyName(), excel.getCategory()).getId();
						int retailerId = iImportExcelService.findRetailerByProvId(provinceId, excel.getRetailerName())
								.getId();
						iImportExcelService.addSaleRecordNoPoint(retailerId, productId, totalPrice, promoterId, sf.format(date),
								excel.getAmount());
					} else {
						// 导入参加活动的销售记录
						int projectId = iImportExcelService.findByProjectName(excel.getProjectName(), provinceId)
								.getId();
						int productId = iImportExcelService.findProductIDByNameAndProjId(excel.getProductName(),
								excel.getProductFamilyName(), excel.getCategory(), projectId).getId();
						int retailerId = iImportExcelService.findByRetailerName(excel.getRetailerName(), projectId)
								.getId();
						Points points = iPointsService.findPointByPrjectIdAndProductId(projectId, productId);
						if (points != null) {
							int point= ((int)totalPrice)/points.getSalesAmount()*points.getPoints();
						iImportExcelService.addSaleRecord(retailerId, productId, totalPrice, promoterId, sf.format(date),
								projectId, point, excel.getAmount());

					}
				}
				// 导入成功后，显示新导进的销售记录
				List<webSale> newSalesRecord = iSalesRecordService.findNewInsertSalesRecord(maxId, promoterId);
				System.out.println("===================导入成功");
				request.setAttribute("msg", 1);
				model.addAttribute("newSalesRecord", newSalesRecord);
				}
			} else {
				// excel中有输入错误的数据，将错误数据显示到页面
				request.setAttribute("msg", 0);
				excelData.add("请修改Excel文件后重新上传");
				model.addAttribute("errorData", excelData);
				System.out.println("===================导入失败");
			}
		} catch (InvalidFormatException e) {
			logger.warn("时间转换异常");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return IMPORTSALESRECORD;
	}

	//获取错误信息
	public List<String> getErrorMsg(List<Excel> excels,int provinceId){
		// 存放失败的数据字段
		List<String> excelData = new ArrayList<String>();
		if (excels.isEmpty()) {
			excelData.add("您导入的Excel是空的！");
		} else {
			// 按行遍历数据
			for (int i = 0; i < excels.size(); i++) {
				Excel excel = excels.get(i);
				System.out.println("导入输出：" + excel);
				// 对Excel表格中的数据进行验证
				if(excel.getRetailerName() == null || excel.getRetailerName() == "" || excel.getProductName() == null || excel.getProductName() == "" || excel.getProductFamilyName() == null || excel.getProductFamilyName() == ""
						|| excel.getCategory() == null || excel.getCategory() == "" || excel.getTotalPrice() == 0 || excel.getAmount() == 0){
					
					if (excel.getRetailerName() == null || excel.getRetailerName() == "") {
						excelData.add("第" + (i + 1) + "行，零售商：未输入或输入了非字符型数据");
					}
					if (excel.getProductName() == null || excel.getProductName() == "") {
						excelData.add("第" + (i + 1) + "行，产品：未输入或输入了非字符型数据");
					}
					if (excel.getProductFamilyName() == null || excel.getProductFamilyName() == "") {
						excelData.add("第" + (i + 1) + "行，产品家族：未输入或输入了非字符型数据");
					}
					if (excel.getCategory() == null || excel.getCategory() == "") {
						excelData.add("第" + (i + 1) + "行，产品类别：未输入或输入了非字符型数据");
					}
					if (excel.getTotalPrice() == 0) {
						excelData.add("第" + (i + 1) + "行，销售额：未输入或输入了非数字数据");
					}
					if (excel.getAmount() == 0) {
						excelData.add("第" + (i + 1) + "行，数量：未输入或输入了非数字数据");
				}
				} else {
					if (excel.getProjectName() == null || excel.getProjectName() == "") {
						Retailer retailer = iImportExcelService.findRetailerByProvId(provinceId,
								excel.getRetailerName());
						Product product = iImportExcelService.findByProductName(excel.getProductName());
						ProductFamily familyName = iImportExcelService
								.findByProductFamilyName(excel.getProductFamilyName());
						ProductCategory productCategory = iImportExcelService
								.findByProductCategoryName(excel.getCategory());
						Product products = iImportExcelService.findProductIDByName(excel.getProductName(),
								excel.getProductFamilyName(), excel.getCategory());
						if (retailer == null || product == null || familyName == null || productCategory == null) {

							if (retailer == null) {
								Province province = iProvinceService.findProvinceById(provinceId);
								excelData.add("第" + (i + 1) + "行，" + province.getName() + "省内零售商:"
										+ excel.getRetailerName() + " 不存在");
							}
							if (product == null) {
								excelData.add("第" + (i + 1) + "行，产品：" + excel.getProductName()+ " 不存在");
							}
							if (familyName == null) {
								excelData.add("第" + (i + 1) + "行，产品家族：" + excel.getProductFamilyName()+ " 不存在");
							}
							if (productCategory == null) {
								excelData.add("第" + (i + 1) + "行，产品种类：" + excel.getCategory()+ " 不存在");
							}
						} else if (products == null) {
							excelData.add("第" + (i + 1) + "行，产品种类：" + excel.getCategory() + "，产品家族："
									+ excel.getProductFamilyName() + "，产品：" + excel.getProductName() + "，导入的产品配置有误");
						}

					} else {// excel.getProjectName()不为空
						Project project = iImportExcelService.findByProjectName(excel.getProjectName(), provinceId);
						if (project == null) {
							excelData.add("第" + (i + 1) + "行，项目：" + excel.getProjectName()+ " 不存在");
						}else{						
						Retailer retailer = iImportExcelService.findByRetailerName(excel.getRetailerName(),
								project.getId());
						if (retailer == null) {
							excelData.add("第" + (i + 1) + "行，零售商：" + excel.getRetailerName() + " 未参加该项活动");
						}
						
						Product product = iImportExcelService.findByProductName(excel.getProductName());
						ProductFamily familyName = iImportExcelService
								.findByProductFamilyName(excel.getProductFamilyName());
						ProductCategory productCategory = iImportExcelService
								.findByProductCategoryName(excel.getCategory());
						Product products = iImportExcelService.findProductIDByNameAndProjId(excel.getProductName(),
								excel.getProductFamilyName(), excel.getCategory(), project.getId());

						 if (project == null || product == null || familyName == null
								|| productCategory == null) {
							
							if (product == null) {
								excelData.add("第" + (i + 1) + "行，产品：" + excel.getProductName()+ " 不存在");
							}
							if (familyName == null) {
								excelData.add("第" + (i + 1) + "行，产品家族：" + excel.getProductName()+ " 不存在");
							}
							if (productCategory == null) {
								excelData.add("第" + (i + 1) + "行，产品种类：" + excel.getCategory()+ " 不存在");
							}

						} else{
							if (products == null) {
						
							excelData.add("第" + (i + 1) + "行，产品种类：" + excel.getCategory() + "，产品家族："
									+ excel.getProductFamilyName() + "，产品：" + excel.getProductName() + "，导入的项目产品配置有误");
							}
							}
						}

					}
				}
			}

		} 
		
		return excelData;
	}
}
