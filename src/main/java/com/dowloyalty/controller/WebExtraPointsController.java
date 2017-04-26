package com.dowloyalty.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dowloyalty.entity.Admin;
import com.dowloyalty.entity.InitPoints;
import com.dowloyalty.entity.Project;
import com.dowloyalty.pojo.MessageVo;
import com.dowloyalty.pojo.MessageVoJDB;
import com.dowloyalty.service.IMessagesService;
import com.dowloyalty.service.ProjectService;

@Controller
public class WebExtraPointsController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	@Resource
	IMessagesService iMessagesService;
	@Resource
	ProjectService projectService;
	@RequestMapping({"/website/extrapoints"})
	public String extrapoints() {
		return "/PC/MemberMessage";
	}
	@RequestMapping({"/website/expointsajax"})
	public String expointsajax(HttpServletResponse response,int pageNow) {
		PrintWriter printWriter = null;
		//定义最终传出量
		List<MessageVo> messageVoList=new ArrayList<>();
		//定义每页数量为15
		int pageSize=15;
		try {
			//获取信息总数量
			int i = iMessagesService.getMessagesCountByStatus(true);
			//总页数等于总数量除以页面条数+1
			int totalPage=i/pageSize;
			if (i%pageSize!=0) {
				totalPage+=1;
			}
			//页码大于最后一页，跳回到最后页
			if (totalPage<=pageNow) {
				pageNow=totalPage;
			}
			List<MessageVo> messageVos = iMessagesService.findMessagesInfoByStatus(true,pageNow,pageSize);
			for (MessageVo messageVo : messageVos) {
				//补充project信息
				List<Project> projects = projectService.findProjectByRetailerId(messageVo.getRetailerId());
				if ( projects.size()!=0) {
					for(Project project : projects)
					{
						project.setBackgroundPath(null);
						project.setPlacardPath(null);
					}
					messageVo.setProjects(projects);
				};
				messageVo.setSubmitTime(messageVo.getSubmitTime().substring(0, 19));
				messageVoList.add(messageVo);
			}
			MessageVoJDB messageVoJDB=new MessageVoJDB(messageVoList, totalPage>0?totalPage:1, pageNow);
			String jsonString = JSON.toJSONString(messageVoJDB);
			printWriter=response.getWriter();
			printWriter.println(jsonString);
		} catch (IOException e) {
			logger.error("printWriter，io异常",e);
		}catch (IndexOutOfBoundsException  e) {
			logger.error("留言时间没有记录",e);
		}finally {
			printWriter.flush();
			printWriter.close();
			
		}
		return "/PC/MemberMessage";
	}
	@RequestMapping({"/website/addpoints"})
	public @ResponseBody void addpointAjax(HttpServletResponse response,int retailerId,
			int id,int projectId,int point,HttpSession session) {
		Admin admin=(Admin)session.getAttribute("USER");
		String reason="留言积分奖励";
		if (point==0) {
			iMessagesService.updateStatusById(id,admin.getId());
		}else {
			InitPoints initPoints=new InitPoints(projectId,reason,retailerId,point);
			iMessagesService.insertInitpoints(initPoints);
			iMessagesService.updateStatusAndInitPointsIdById(initPoints.getId(),admin.getId(), id);
			
		}
	}
}
