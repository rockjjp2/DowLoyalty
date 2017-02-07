package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.dowloyalty.entity.Goods;
import com.dowloyalty.entity.Province;
import com.dowloyalty.pojo.GoodsVo;
import com.dowloyalty.service.ExchangeShopService;
import com.dowloyalty.service.GoodsService;
import com.dowloyalty.service.IProvinceService;

/**
 * Web端控制器
 * @author wangyuanjie
 *
 */
@Controller
@RequestMapping("/v1/Web")
public class WebController {
	
	@Resource
	GoodsService goodsService;
	@Resource
	ExchangeShopService exchangeShopService;
	@Resource
	IProvinceService iProvinceService;
	
	/**
	 * 管理员操作
	 * 初始化礼品商城页面
	 * @param model	模型(传数据用)
	 * @return	礼品商城页面与所有初始化必须的信息，包括省份信息、礼品信息
	 */
	@RequestMapping("/admin/exchangeshop")
	public ModelAndView initExchangeShop(Model model)
	{
		//获取所有省份并传到页面
		List<Province> provinces = iProvinceService.getAllProvince();
		model.addAttribute("provinces", provinces);
		
		//获取所有礼品并传到页面
		List<Goods> allGoods = goodsService.findAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("allGoods", allGoods);
		mv.setViewName("");
		return mv;
	}
	
	/**
	 * 管理员操作
	 * 增加礼品
	 * @param request	客户端请求
	 * @param response	服务器响应
	 * @return	增加礼品成功页面
	 */
	@RequestMapping("/admin/goods/post")
	public void addGoods(HttpServletRequest request, HttpServletResponse response)
	{
		//增加礼品记录
//		String goodsName = request.getParameter("goodsName");
//		String description = request.getParameter("description");
//		String imagePath = request.getParameter("imagePath");
//		String goodsCategory = request.getParameter("goodsCategory");
//		String provinceId = request.getParameter("provinceId");
//		String exchangePoints = request.getParameter("exchangePoints");
//		Goods goods = new Goods();
//		goods.setName(goodsName);
//		goods.setDescription(description);
//		//goods.setImagePath(imagePath);
//		goods.setIsActive(true);
//		goods.setGoodsCategoryID(Integer.parseInt(goodsCategory));
//		goodsService.save(goods);
//		
//		//增加礼品商城礼品记录
//		int goodsId = goodsService.getGoodsAmount();
//		ExchangeShop exchangeShop = new ExchangeShop();
//		exchangeShop.setGoodsID(goodsId);
//		exchangeShop.setIsActive(true);
//		exchangeShop.setProvinceID(Integer.parseInt(provinceId));
//		exchangeShop.setExchangePoints(Integer.parseInt(exchangePoints));
//		exchangeShopService.save(exchangeShop);
		
		//将所有礼品以json形式发送到前台页面以供显示
		//List<GoodsVo> allGoods = goodsService.findByProvinceId(Integer.parseInt(provinceId));
		//测试json用
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
	 * 管理员操作
	 * 获取对应省份的兑换商城的所有礼品
	 * @param request	客户端请求
	 * @param response	服务器响应
	 * @return	对应省份的兑换商城的礼品显示页面
	 */
	@RequestMapping("/admin/exchangeshop/goodsInfo/get")
	public void getExchangeshopGoodsByProvinceId(HttpServletRequest request, HttpServletResponse response)
	{
		//获取对应省份的兑换商城的所有礼品并以json形式发送到前台页面以供显示
		String provinceId = request.getParameter("provinceId");
		List<GoodsVo> allGoods = goodsService.findByProvinceId(Integer.parseInt(provinceId));
		//测试json用
		//List<Goods> allGoods = goodsService.findAll();
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
	 * 管理员操作
	 * 获取对应id的礼品
	 * @param request	客户端请求
	 * @return	对应id的礼品的模型与视图
	 */
	@RequestMapping("/goodsInfo/get")
	public ModelAndView getOneGoodsInfo(HttpServletRequest request)
	{
		//获取对应id的礼品信息并传至显示页面
		String goodId = request.getParameter("goodId");
		GoodsVo goods = goodsService.findByGoodsId(Integer.parseInt(goodId));
		//测试用
		//GoodsVo goods = goodsService.findByGoodsId(1);
		ModelAndView mv = new ModelAndView();
		mv.addObject("goodsInfo", goods);
		mv.setViewName("goodsInfo");
		return mv;
	}
	
	@RequestMapping("/test")
	public String dsf()
	{
		return "scoreDetails";
	}
}
