package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.dowloyalty.pojo.SendSaleRecord;
import com.dowloyalty.service.ISalesRecordService;

@Controller
@RequestMapping("/v1/saleforce")
public class SendToSaleForceController {
	
	@Resource
	ISalesRecordService salesRecordService;
	
	@RequestMapping("/salerecord/get")
	public void sendSaleRecordJson(HttpServletRequest request, HttpServletResponse response)
	{
		String time = request.getParameter("time");
		List<SendSaleRecord> records = salesRecordService.findAllSaleRecord(time);
		JSONArray json = (JSONArray)JSONArray.toJSON(records);
		try {
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
		}
	}
}
