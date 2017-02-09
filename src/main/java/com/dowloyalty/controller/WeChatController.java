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
import com.dowloyalty.pojo.GoodsVo;
import com.dowloyalty.service.ExchangeRecordService;
import com.dowloyalty.service.GoodsService;

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
	
}
