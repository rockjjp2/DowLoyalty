package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.pojo.webSale;
import com.dowloyalty.entity.SaleRecord;
import com.dowloyalty.pojo.PojoSaleRecord;
import com.dowloyalty.pojo.SendSaleRecord;


public interface ISalesRecordService {

	//public List<SaleRecord> selectSalesRecord(int startIndex,int pageSize);
	//public List<webSale> selectSalesRecord(int promoterId,int startIndex,int pageSize);
	public List<webSale> findNewInsertSalesRecord(int id,int promoterId);
	
	public List<PojoSaleRecord> selectWeChatSaleRecords(int promoterId);
	
	/**
	 * 获取所有需要发送到saleforce的销售记录
	 * @return	销售记录集合
	 */
	public List<SendSaleRecord> findAllSaleRecord();
	
	/**
	 * 修改销售记录发送sfdc状态并记录oppId
	 * @param saleRecord	销售记录对象
	 */
	public void update(SaleRecord saleRecord);
	
	/**
	 * 根据销售记录id获取销售记录信息
	 * @param id	销售记录id
	 * @return	销售记录对象
	 */
	public SaleRecord findById(int id);
//	/**
//	 * 获取所有需要发送到saleforce的指定时间以后的销售记录总数
//	 * @param time	指定时间
//	 * @return	销售记录总数
//	 */
//	public int getMaxPageNum(String time);
	
//	/**
//	 * 根据零售商id或者姓名获取所有销售记录
//	 * @param id	零售商id
//	 * @param name	零售商姓名
//	 * @return	所有销售记录
//	 */
//	public List<webSale> findAllrecords(String id, String name);
	
//	/**
//	 * 根据零售商id或者姓名获取所有销售记录并分页显示
//	 * @param id	零售商id
//	 * @param name	零售商姓名
//	 * @param PageNum	页码数
//	 * @return	所有销售记录
//	 */
//	public List<SearchSaleRecords> findByConditions(String id, String name,@Param("PageNum")int PageNum);
//	
//	/**
//	 * 根据零售商id或者姓名获取销售记录最大页数
//	 * @param id	零售商id
//	 * @param name	零售商姓名
//	 * @return	销售记录最大页数
//	 */
//	public int getMaxPageNum(String id, String name);
}
