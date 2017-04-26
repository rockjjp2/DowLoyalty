package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.ExchangeRecord;
import com.dowloyalty.pojo.Deliver;
import com.dowloyalty.pojo.ExchangeRecordWeb;

public interface ExchangeRecordService {
	
	public void save(ExchangeRecord exchangeRecord);
	
	public void delete(int id);
	
	public void update(ExchangeRecord exchangeRecord);
	public void updateExchangerrecordToComplete(int exchangeRecordId);
	public List<ExchangeRecord> findAll();
	/**
	 * 根据项目以及retailer获得兑换所用积分之和。返回值类似于：-20 此类
	 * @param projectId
	 * @param retailerId
	 * @return
	 */
	public int getSumExchangPointsByProjectIdAndRetailerId(int projectId,int retailerId);
	/**
	 * 找出省内所有已发货或未发货retailer的兑换记录，其中status 0为 ，1为
	 * @param provinceId
	 * @return
	 */
	public List<ExchangeRecord> findExchangeRecordByProvinceIdAndStatus(int provinceId,int status);
	public ExchangeRecord findById(int id);
	
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
	public List<ExchangeRecordWeb> findByConditions(String id,String name, String projectId, String startDate, String endDate, int PageNum);
	
	/**
	 * 根据指定条件获取兑换记录最大页数
	 * @param id	会员ID
	 * @param name	会员名
	 * @param projectId 项目id
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return	兑换记录最大页数
	 */
	public int getMaxPageNum(String id,String name, String projectId, String startDate, String endDate);
	
	public List<Deliver> findDeliverList(int projectId,int status);
}
