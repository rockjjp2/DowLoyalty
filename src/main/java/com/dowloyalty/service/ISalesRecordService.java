package com.dowloyalty.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.SaleRecord;
import com.dowloyalty.pojo.SendSaleRecord;

public interface ISalesRecordService {

	//public List<SaleRecord> selectSalesRecord(int startIndex,int pageSize);
	public List<SaleRecord> selectSalesRecord();
	public List<SaleRecord> findNewInsertSalesRecord(int id);
	
	public List<SaleRecord> selectWeChatSaleRecords();
	
	/**
	 * 获取所有需要发送到saleforce的销售记录
	 * @return	销售记录对象集合
	 */
	public List<SendSaleRecord> findAllSaleRecord();
}
