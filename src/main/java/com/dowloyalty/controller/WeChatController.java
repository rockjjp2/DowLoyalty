package com.dowloyalty.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dowloyalty.entity.ExchangeRecord;
import com.dowloyalty.entity.PointsLevel;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.GoodsVo;
import com.dowloyalty.pojo.PointsDetails;
import com.dowloyalty.pojo.RetailerAccInfo;
import com.dowloyalty.service.ExchangeRecordService;
import com.dowloyalty.service.GoodsService;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.service.PointsLevelService;
import com.dowloyalty.service.ProjectService;

/**
 * 微信端控制器
 * @author wangyuanjie
 *
 */
@Controller
@RequestMapping("/v1/WeChat")
public class WeChatController {
	@Resource
	GoodsService goodsService;
	@Resource
	ExchangeRecordService exchangeRecordService;
	@Resource
	IRetailerService retailerService;
	@Resource
	PointsLevelService pointsLevelService;
	@Resource
	ProjectService projectService;
	
	/**
	 * 零售商操作
	 * 初始化礼品商城页面
	 * @param model	模型(传数据用)
	 * @return	礼品商城页面与所有初始化必须的信息，包括省份信息、礼品信息
	 */
	@RequestMapping("/retailer/exchangeshop")
	public ModelAndView initShop(HttpServletRequest request,HttpSession session)
	{
		//获取零售商所在省份礼品商城的所有礼品与分类信息
		//String retailerId = request.getParameter("retailerId");
		//测试数据
		String retailerId = "1";
		
		List<GoodsVo> allGoods = goodsService.findByRetailerId(Integer.parseInt(retailerId));
		
		//获取所有礼品目录
		//List<GoodsCategory> allCategory = goodsCategoryService.findAll();
		//model.addAttribute("allCategory", allCategory);
		session.setAttribute("retailerId", retailerId);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("allGoods", allGoods);
		mv.setViewName("WeChat/giftStore");
		return mv;
	}
	
	/**
	 * 零售商操作
	 * 获取对应id的礼品
	 * @param request	客户端请求
	 * @param model	模型(传数据用)
	 * @return	礼品具体信息显示页面
	 */
	@RequestMapping("/retailer/goodsInfo")
	public String getOneGoodsInfo(HttpServletRequest request,Model model)
	{
		//根据礼品id获取礼品具体信息并显示到页面
		String goodsId = request.getParameter("goodsId");
		GoodsVo goods = goodsService.findByGoodsId(Integer.parseInt(goodsId));
		model.addAttribute("goods", goods);
		return "WeChat/giftInfo";
	}
	
	/**
	 * 零售商操作
	 * 兑换礼品
	 * @param request 服务器请求
	 * @return 兑换结果(成功或失败)页面
	 */
	@RequestMapping("/retailer/goods/exchange")
	public String exchangeGoods(HttpServletRequest request)
	{
		String retailerId = request.getParameter("retailerId");
		String goodsId = request.getParameter("goodsId");
		String amount = request.getParameter("amount");
		String exchangePoints = request.getParameter("exchangePoints");
		int totalPoints = - Integer.parseInt(exchangePoints) * Integer.parseInt(amount);
		ExchangeRecord exchangeRecord = new ExchangeRecord();
		exchangeRecord.setRetailerID(Integer.parseInt(retailerId));
		exchangeRecord.setGoodsID(Integer.parseInt(goodsId));
		exchangeRecord.setAmount(Integer.parseInt(amount));
		exchangeRecord.setSubmitTime(Timestamp.valueOf(LocalDateTime.now()));
		exchangeRecord.setStatus(0);
		exchangeRecord.setExchangePoints(totalPoints);
		exchangeRecordService.save(exchangeRecord); 
		return "redirect:/v1/WeChat/retailer/exchangeshop";
	}
	
	/**
	 * 获取零售商积分明细
	 * @param request 服务器请求
	 * @param model 模型（传参用）
	 * @return	积分明细页面
	 */
	@RequestMapping("/retailer/pointsDetails")
	public ModelAndView getPointsDetails(HttpServletRequest request,Model model)
	{
		//获取零售商id、筛选条件（月份、事项）并查找数据
		String month = request.getParameter("month");
		String matter = request.getParameter("matter");
		//String retailerId = request.getParameter("retailerId");
		//测试用
		String retailerId = "1";
		List<PointsDetails> pointsDetails = retailerService.findByRetailerId(Integer.parseInt(retailerId), matter, month);
		
		//返回数据与视图
		ModelAndView mv = new ModelAndView();
		model.addAttribute("month", month);
		model.addAttribute("matter", matter);
		mv.addObject("pointsDetails", pointsDetails);
		mv.setViewName("WeChat/scoreDetails");
		return mv;
	}
	
	@RequestMapping("/retailer/accountInfo")
	public String getAccountInfo(HttpServletRequest request,Model model)
	{
		//String retailerId = request.getParameter("retailerId");
		String retailerId = "1";
		
		String projectId = projectService.findActiveByRid(Integer.parseInt(retailerId));
		if(null != projectId)
		{
			int pId = Integer.parseInt(projectId);
			
			PointsLevel pointsLevel = pointsLevelService.findNexLvPByRetailerId(Integer.parseInt(retailerId), pId);
			String lvName = pointsLevelService.findLvNameByRetailerId(Integer.parseInt(retailerId), pId);
			
			RetailerAccInfo reAccFur = pointsLevelService.findFurAccByRetailerId(Integer.parseInt(retailerId), pId);
			RetailerAccInfo reAcc = pointsLevelService.findAccInfoByRetailerId(Integer.parseInt(retailerId));
			
			Retailer retailer = retailerService.findById(Integer.parseInt(retailerId));
			String rankPercent = pointsLevelService.getRankPercent(Integer.parseInt(retailerId), retailer.getProvinceID());
			reAcc.setCurRank(reAccFur.getCurRank());//当前排名
			reAcc.setNextRank(reAccFur.getNextRank());//前一名名次
			reAcc.setToUpPersonRemainPoints(reAccFur.getToUpPersonRemainPoints());//与前一名所差积分数
			reAcc.setLvName(lvName);//当前积分等级名称
			reAcc.setToNextLvRemainPoints(pointsLevel.getPoints()-reAcc.getTotalPoints());//与下一积分等级所差积分数
			reAcc.setNextLv(pointsLevel.getName());//下一积分等级名称
			reAcc.setRankPercent(rankPercent);//排名百分比
			model.addAttribute("accountInfo", reAcc);
			return "WeChat/accountInfo";
		}
		return "";
	}
}
