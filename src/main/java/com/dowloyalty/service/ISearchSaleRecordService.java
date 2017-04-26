package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Project;
import com.dowloyalty.pojo.webSale;

public interface ISearchSaleRecordService {

	public List<webSale> findWebSaleRecordByProject(int projectId,int promoterId);
	
	public List<webSale> findWebSaleRecordByQuery(int projectId, String startDate, String endDate, int promoterId,
			String retailerName, int pageNum);	//pageNum当前页面
	public int getTotalPage(int projectId, String startDate, String endDate, int promoterId, String retailerName);

	
	public List<webSale> findWeChatSaleRecordByQuery(String startDate, String endDate,String promoterId,String retailerName);

	/**
	 * 查询并导出销售记录
	 * @param projectId	项目id
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param promoterId	推广员id
	 * @param retailerName	零售商id
	 * @return	销售记录对象
	 */
	public List<webSale> findWebSaleRecords(int projectId,String startDate, String endDate,int promoterId,String retailerName);
	

	public List<Project> findProjectByPromoterId(int promoterId);
	public List<Project> findProject();
}
