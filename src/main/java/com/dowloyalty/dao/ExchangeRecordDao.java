package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.ExchangeRecord;
import com.dowloyalty.pojo.Deliver;
import com.dowloyalty.pojo.ExchangeRecordWeb;

public interface ExchangeRecordDao {
	
	public void save(ExchangeRecord exchangeRecord);
	
	public int getSumExchangPointsByProjectIdAndRetailerId(@Param("projectId")int projectId,@Param("retailerId")int retailerId);
	
	public void updateExchangerrecordToComplete(@Param("exchangeRecordId")int exchangeRecordId);
	
	public List<ExchangeRecord> findAll();
	/**
	 * 找出省内所有已发货或未发货retailer的兑换记录，其中status 0为 ，1为
	 * @param provinceId
	 * @return
	 */
	public List<ExchangeRecord> findExchangeRecordByProvinceIdAndStatus(@Param("provinceId")int provinceId,@Param("status")int status);
	
	/**
	 * 根据指定条件获取兑换记录并分页显示
	 * @param id	会员ID
	 * @param name	会员名
	 * @param projectId 项目id
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 兑换记录总数
	 * @return	兑换记录对象集合
	 */
	public List<ExchangeRecordWeb> findByConditions(@Param("RetailerID")String id,@Param("RetailerName")String name,@Param("ProjectID")String projectId,@Param("StartDate")String startDate,@Param("EndDate")String endDate,@Param("PageNum")int PageNum,@Param("ShowNum")int ShowNum);
	
	/**
	 * 根据指定条件获取兑换记录总数
	 * @param id	会员ID
	 * @param name	会员名
	 * @param projectId 项目id
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 兑换记录总数
	 */
	public int getCount(@Param("RetailerID")String id,@Param("RetailerName")String name,@Param("ProjectID")String projectId,@Param("StartDate")String startDate,@Param("EndDate")String endDate);
	
	public List<Deliver> findDeliverList(@Param("projectId")int projectId,@Param("status")int status);
}
