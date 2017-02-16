package com.dowloyalty.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.SaleRecord;

import com.dowloyalty.pojo.webSale;

import com.dowloyalty.pojo.SendSaleRecord;


public interface ISalesRecordService {

	//public List<SaleRecord> selectSalesRecord(int startIndex,int pageSize);
	public List<webSale> selectSalesRecord();
	public List<webSale> findNewInsertSalesRecord(int id);
	
	public List<SaleRecord> selectWeChatSaleRecords();
	
	/**
	 * 获取所有需要发送到saleforce的指定时间以后的销售记录
	 * @param	时间
	 * @return	销售记录对象集合
	 */
	public List<SendSaleRecord> findAllSaleRecord(String time);
}
