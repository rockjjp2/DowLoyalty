package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dowloyalty.entity.ExchangeRecord;
import com.dowloyalty.entity.PointsLevel;
import com.dowloyalty.entity.Project;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.GoodsVo;
import com.dowloyalty.pojo.PointMapping;
import com.dowloyalty.pojo.PointsDetails;
import com.dowloyalty.pojo.RetailerAccInfo;
import com.dowloyalty.service.ExchangeRecordService;
import com.dowloyalty.service.GoodsService;
import com.dowloyalty.service.IPointsService;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.service.PointsLevelService;
import com.dowloyalty.service.ProjectService;

/**
 * 微信端控制器
 * @author wangyuanjie
 *
 */
@Controller
@RequestMapping("/WeChat")
public class WeChatController {
	@Resource
	GoodsService goodsService;
	@Resource
	ExchangeRecordService exchangeRecordService;
	@Resource
	IRetailerService iRetailerService;
	@Resource
	PointsLevelService pointsLevelService;
	@Resource
	IPointsService iPointsService;
	@Resource
	ProjectService projectService;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * 零售商操作
	 * 初始化礼品商城页面
	 * @param model	模型(传数据用)
	 * @return	礼品商城页面与所有初始化必须的信息，包括省份信息、礼品信息
	 */
	@RequestMapping("retailer/exchangeshop")
	public ModelAndView initShop(HttpServletRequest request,HttpSession session)
	{
		//获取零售商所在省份礼品商城的所有礼品与分类信息
		Retailer user = (Retailer)session.getAttribute("USER");
		int retailerId = user.getId();
		
		List<GoodsVo> allGoods = goodsService.findByRetailerId(retailerId);
		
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
	@RequestMapping("retailer/goodsInfo")
	public String getOneGoodsInfo(HttpServletRequest request,Model model,HttpSession session)
	{
		//根据礼品id获取礼品具体信息并显示到页面
		String goodsId = request.getParameter("goodsId");
		Retailer user = (Retailer)session.getAttribute("USER");
		int retailerId = user.getId();
		GoodsVo goods = goodsService.findByGoodsId(retailerId,Integer.parseInt(goodsId));
		model.addAttribute("goods", goods);
		return "WeChat/giftInfo";
	}
	
	
	/**
	 * 获取零售商积分明细
	 * @param request 客户端请求
	 * @param model 模型（传参用）
	 * @return	积分明细页面
	 */
	@RequestMapping("/retailer/pointsDetails")
	public ModelAndView getPointsDetails(HttpServletRequest request,Model model,HttpSession session)
	{
		//获取零售商id、筛选条件（月份、事项）并查找数据
		String month = request.getParameter("month");
		String matter = request.getParameter("matter");
		Retailer user = (Retailer)session.getAttribute("USER");
		int retailerId = user.getId();
		List<PointsDetails> pointsDetails = iRetailerService.findByRetailerId(retailerId, matter, month);
		
		//返回数据与视图
		ModelAndView mv = new ModelAndView();
		model.addAttribute("month", month);
		model.addAttribute("matter", matter);
		mv.addObject("pointsDetails", pointsDetails);
		mv.setViewName("WeChat/scoreDetails");
		return mv;
	}
	
	/**
	 * 获取零售商账户信息
	 * @param request 客户端请求
	 * @param model 模型（传参用）
	 * @return	零售商账户信息显示页面
	 */
	@RequestMapping("/retailer/accountInfo")
	public String getAccountInfo(HttpServletRequest request,Model model,HttpSession session)
	{
		Retailer user = (Retailer)session.getAttribute("USER");
		int retailerId = user.getId();
		
		Project project = projectService.findActiveByRid(retailerId);
		boolean isVisible = false;
		
		RetailerAccInfo retailerAccInfo=pointsLevelService.findAccountBasicInfoByRetailerId(retailerId);
		if(null != project)
		{
			isVisible = project.isVisible();
			//临时对象
			PointMapping tempPointMapping=null;
			if (isVisible) {
				int projectId=project.getId();
				List<PointMapping> pointMappings = iPointsService.findPointMappingByProject(projectId);
				for (PointMapping pointMapping : pointMappings) {
					if (pointMapping.getRetailerID()==retailerId) {
						//
						int points=pointMapping.getTotalPoint();
						//根据项目和总积分获得等级名称
						String lvName=pointsLevelService.findPointslevelNameByProjectIdAndPoints(projectId,points);
						retailerAccInfo.setLvName(lvName);
						//设置累计积分
						retailerAccInfo.setTotalPoints(points);
						//设置剩余积分
						int costPoints=exchangeRecordService.getSumExchangPointsByProjectIdAndRetailerId(projectId, retailerId);
						retailerAccInfo.setRemainPoints(points+costPoints);
						//行号即为排名
						retailerAccInfo.setCurRank(pointMapping.getRownum());
						if (pointMapping.getRownum()!=1) {
							
						}
						//排名百分比等于排名除以全部数量
						int RankPercent=100-pointMapping.getRownum()*100/pointMappings.size();
						retailerAccInfo.setRankPercent(RankPercent+"");
						if (tempPointMapping!=null) {
							retailerAccInfo.setToUpPersonRemainPoints(tempPointMapping.getTotalPoint()-pointMapping.getTotalPoint());//与前一名所差积分数
							retailerAccInfo.setNextRank(tempPointMapping.getRownum());
						}
						PointsLevel pointsLevel = pointsLevelService.findNextPointslevelByProjectIdAndPoints(projectId, points);
						if (pointsLevel != null) {
							retailerAccInfo.setNextLv(pointsLevel.getName());
							retailerAccInfo.setToNextLvRemainPoints(pointsLevel.getPoints()-points);
						}
					}
					//临时mapping对象代表的是排名上一位
					tempPointMapping=pointMapping;
				}
			}
		}
			model.addAttribute("accountInfo", retailerAccInfo);
			model.addAttribute("isVisible", isVisible);
			return "WeChat/accountInfo";
	}
	
	/**
	 * 获取对应id的零售商剩余积分以判断是否满足兑换条件
	 * @param request	客户端请求
	 * @param response	服务器响应
	 */
	@RequestMapping("/retailer/goodsexchange")
	@ResponseBody
	public void exchangeGoods(HttpServletRequest request,HttpServletResponse response)
	{
		String retailerId = request.getParameter("retailerId");
		String exchangePoints = request.getParameter("exchangePoints");
		String amount = request.getParameter("amount");
		String goodsId = request.getParameter("goodsId");
		String msg = "";
		int retailerId_INT=Integer.parseInt(retailerId);
		int exchangePoints_INT = Integer.parseInt(exchangePoints);
		int amount_INT=Integer.parseInt(amount);
		Project project = projectService.findActiveByRid(retailerId_INT);
		int remainPoints=0;
		if (project!=null) {
			int projectId_INT=project.getId();
			List<PointMapping> pointMappings = iPointsService.findPointMappingByProject(projectId_INT);
			for (PointMapping pointMapping : pointMappings) {
				if (pointMapping.getRetailerID()==retailerId_INT) {
					//累计兑换的积分,注意此处返回的是负值
					int costPoints=exchangeRecordService.getSumExchangPointsByProjectIdAndRetailerId(projectId_INT, retailerId_INT);
					remainPoints=pointMapping.getTotalPoint()+costPoints;
				}
			}
			
			//判断剩余积分是否足够兑换商品
			//---------------------
			if((amount_INT * exchangePoints_INT) > remainPoints)
			{
				msg = "积分不足";
			}
			else
			{
				int totalPoints = - amount_INT * exchangePoints_INT;
				ExchangeRecord exchangeRecord = new ExchangeRecord();
				exchangeRecord.setRetailerID(Integer.parseInt(retailerId));
				exchangeRecord.setGoodsID(Integer.parseInt(goodsId));
				exchangeRecord.setAmount(Integer.parseInt(amount));
				exchangeRecord.setSubmitTime(Timestamp.valueOf(LocalDateTime.now()));
				exchangeRecord.setStatus(0);
				exchangeRecord.setExchangePoints(totalPoints);
				exchangeRecord.setProjectID(project.getId());
				exchangeRecordService.save(exchangeRecord); 
				msg = "兑换成功";
			}
			
			logger.debug("用户"+retailerId+"在项目(id-->"+project.getId()+")的兑换商店中兑换了礼品,id-->"+goodsId+
					",exchangePoints-->" + (amount_INT * exchangePoints_INT));
			//---------------------
		}
		else
		{
			msg = "积分不足";
		}
		
		System.out.println("-------------------"+remainPoints);
		
		try {
			PrintWriter out = response.getWriter();
			out.println(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("对应id的零售商剩余积分数据传输异常");
		}
	}
}
