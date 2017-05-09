package com.dowloyalty.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dowloyalty.entity.Farmer;
import com.dowloyalty.entity.FarmerSalesRecord;
import com.dowloyalty.entity.Project;
import com.dowloyalty.pojo.FarmerVo;
import com.dowloyalty.service.FarmerService;
import com.dowloyalty.service.ProjectService;

@Controller
public class FarmerController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	public static String validateCode = null;
	
	@Resource
	FarmerService farmerService;
	@Resource
	ProjectService projectService;
	
	/**
	 * 验证农户名是否重复
	 * @param request
	 * @param response
	 */
	@RequestMapping("/WeChat/Farmer_validateNameUnique")
	public void validateNameUnique(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("name");
		int count = farmerService.getFarmerCountByName(name);
		int data = 0;
		if(count == 0)
		{
			data = 1;
		}
		
		try {
			PrintWriter out = response.getWriter();
			out.println(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送验证农户名称重复的结果出现异常");
		}
	}
	
	/**
	 * 验证手机号是否重复
	 * @param request
	 * @param response
	 */
	@RequestMapping("/WeChat/Farmer_validateMobileUnique")
	public void validateMobileUnique(HttpServletRequest request, HttpServletResponse response)
	{
		String mobile = request.getParameter("mobile");
		int count = farmerService.getFarmerCountByMobile(mobile);
		int data = 0;
		if(count == 0)
		{
			data = 1;
		}
		
		try {
			PrintWriter out = response.getWriter();
			out.println(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("发送验证农户手机号重复的结果出现异常");
		}
	}
	
	@RequestMapping("/WeChat/farmer/farmerinfo")
	public String getFarmerInfo(HttpSession session, Model model)
	{
		int farmerId = ((Farmer)session.getAttribute("USER")).getId();
		Project project = projectService.findByFarmerId(farmerId);
		List<FarmerVo> farmers = farmerService.findAccountInfoByFarmerId(farmerId);
		FarmerVo farmerVo = null;
		if(project != null)
		{
			if(farmers != null && !farmers.isEmpty())
			{
				for (FarmerVo farmer : farmers) 
				{
					if(project.isVisible())
					{
						int percent = 100 - farmer.getRank() * 100 / farmers.size();
						farmer.setPercent(percent);
						if(farmer.getRank() != 1)
						{
							farmer.setNextRank(farmer.getRank() - 1);
							farmer.setToNextRankRemainPrice(farmers.get(farmer.getRank()-2).getTotalPrice()-farmer.getTotalPrice());
						}
					}
					if(farmer.getId() == farmerId)
					{
						farmerVo = farmer;
					}
				}
			}
			model.addAttribute("farmer", farmerVo);
			model.addAttribute("isVisible", project.isVisible());
		}
		return "WeChat/farmer_info";
	}
	
	@RequestMapping("/WeChat/farmer/farmersalesrecord")
	public String getSalesRecord(HttpServletRequest request, Model model, HttpSession session)
	{
		String month = request.getParameter("month");
		int farmerId = ((Farmer)session.getAttribute("USER")).getId();
		List<FarmerSalesRecord> list = farmerService.findByCondition(month, farmerId);
		model.addAttribute("records", list);
		model.addAttribute("month", month);
		return "WeChat/farmer_salesDetails";
	}
	
	@RequestMapping("/WeChat/Farmer_validateCodeImg")
	public void getValidateCodeImg(HttpServletResponse response)
	{
		//意思一下  整一个词库
		String lib = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		//生成验证码
		//随机生成4个字符
		//定义一个字符串保存生成的4个随机字符
		StringBuffer code = new StringBuffer();
		Random ran = new Random();
		for(int i = 0; i < 4; i++){
			int index = ran.nextInt(lib.length());
			char c = lib.charAt(index);
			code.append(c);
		}
		
		//将生成的验证码保存起来
		validateCode = code.toString();
//		session.removeAttribute("validateCode");
//		session.setAttribute("validateCode",code);
//		session.setMaxInactiveInterval(60*5);
		
		//将验证码以图片的形式发送至客户端
		//设置响应的方式
		response.setContentType("image/gif");
		
		//创建图片对象
		BufferedImage image = new BufferedImage(70,24,BufferedImage.TYPE_INT_RGB);
		
		//获得与该图片对应的一支“笔”
		Graphics gp = image.getGraphics();
		
		//使用RGB生成随机的颜色
		int red = ran.nextInt(255);
		int green = ran.nextInt(255);
		int blue = ran.nextInt(255);
		
		//为“笔“注入颜色
		//gp.setColor(Color.yellow);
		gp.setColor(new Color(red,green,blue));
		
		//为图片填充颜色
		gp.fillRect(0,0,70,24);
		
		//为“笔”换个颜色
		gp.setColor(Color.BLACK);
		
		//将字符写到图片上
		gp.drawString(code.toString(),18,15);
		
		//gp.drawLine(2,10,68,21);
		for(int i = 0; i < 3; i++){
			int startX = ran.nextInt(70);
			int startY = ran.nextInt(24);
			int endX = ran.nextInt(70);
			int endY = ran.nextInt(24);
			gp.drawLine(startX,startY,endX,endY);
		}
		try {
		//将图片响应（写）到客户端   -->  输出流对象
		OutputStream os = response.getOutputStream();
		
		//要对图片进行压缩处理
		/* JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		encoder.encode(image); */
		
			ImageIO.write(image,"gif",os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/WeChat/Farmer_validateCode")
	public void getValidateCode(HttpServletResponse response)
	{
		try {
			PrintWriter out = response.getWriter();
			out.println(validateCode);
			out.flush();
			out.close();
		} catch (IOException e) {
			
		}
	}
}
