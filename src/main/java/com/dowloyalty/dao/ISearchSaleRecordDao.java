package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.Project;
import com.dowloyalty.pojo.webSale;

public interface ISearchSaleRecordDao {
	public List<webSale> findWebSaleRecordByProject(@Param("projectId")int projectId,@Param("promoterId")int promoterId);
	
	public List<webSale> findWebSaleRecordByQuery(@Param("projectId")int projectId,@Param("startDate")String startDate, @Param("endDate")String endDate,@Param("promoterId")int promoterId,@Param("retailerName")String retailerName,@Param("startindex")int startIndex,@Param("pagesize")int pageSize);
	
	public int getSaleRecordCount(@Param("projectId")int projectId,@Param("startDate")String startDate, @Param("endDate")String endDate,@Param("promoterId")int promoterId,@Param("retailerName")String retailerName);

	/**
	 * 查询并导出销售记录
	 * @param projectId	项目id
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param promoterId	推广员id
	 * @param retailerName	零售商id
	 * @return	销售记录对象
	 */
	public List<webSale> findWebSaleRecords(@Param("projectId")int projectId,@Param("startDate")String startDate, @Param("endDate")String endDate,@Param("promoterId")int promoterId,@Param("retailerName")String retailerName);
	
	public List<webSale> findWeChatSaleRecordByQuery(@Param("startDate")String startDate, @Param("endDate")String endDate,@Param("promoterId")String promoterId,@Param("retailerName")String retailerName);
	
	
	public List<Project> findProjectByPromoterId(int promoterId);
	public List<Project> findProject();
}
