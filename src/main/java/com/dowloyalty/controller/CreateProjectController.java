package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Province;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.JsonDataBind;
import com.dowloyalty.service.IPromoterService;
import com.dowloyalty.service.IProvinceService;
import com.dowloyalty.service.IRetailerService;

/**
 * a example demonstrate how to use controller
 * @author zhoum
 *
 */
@Controller
@RequestMapping("/createproject")
public class CreateProjectController {
	@Resource
	IProvinceService iProvinceService;
	@Resource
	IPromoterService iPromoterService;
	@Resource
	IRetailerService iRetailerService;
	@RequestMapping("")
	public String one(Model model){
		List<Province> provinces = iProvinceService.getAllProvince();
		model.addAttribute("provinces", provinces);
		return "gift_Config";
	}
	/**
	 * ������У�ʡѡ��ajax��̨
	 * @param provinceID ʡID
	 * @param response
	 */
	@RequestMapping("/provincesDetail")
	@ResponseBody
	public void getMsgByProvinceId(String provinceID,HttpServletResponse response) {
		PrintWriter printWriter = null;
		JsonDataBind jsonDataBind = null;
		try {
			int provinceId=Integer.valueOf(provinceID);
			List<Retailer> retailers = iRetailerService.findRetailerByProvinceId(provinceId);
			List<Promoter> promoters = iPromoterService.findPromoterByProvinceId(provinceId);
			jsonDataBind=new JsonDataBind(retailers,promoters);
			String jsonString = JSON.toJSONString(jsonDataBind);
			printWriter=response.getWriter();
			printWriter.println(jsonString);
		} catch (NumberFormatException | IOException e) {
			e.getMessage();
		} finally {
			printWriter.flush();
			printWriter.close();
		}
	}
}
