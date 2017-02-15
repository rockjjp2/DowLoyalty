package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ISaleRecordDao;
import com.dowloyalty.entity.SaleRecord;
import com.dowloyalty.pojo.SendSaleRecord;
import com.dowloyalty.service.ISalesRecordService;

@Service
public class SalesRecordServiceImpl implements ISalesRecordService{

	@Resource
	private ISaleRecordDao saleRecordDao;
	@Override
	public List<SaleRecord> selectSalesRecord() {
		// TODO Auto-generated method stub
		return saleRecordDao.selectSalesRecord();
	}
	@Override
	public List<SaleRecord> findNewInsertSalesRecord(int id) {
		// TODO Auto-generated method stub
		return saleRecordDao.findNewInsertSalesRecord(id);
	}
	@Override
	public List<SaleRecord> selectWeChatSaleRecords() {
		// TODO Auto-generated method stub
		return saleRecordDao.selectWeChatSaleRecords();
	}
	@Override
	public List<SendSaleRecord> findAllSaleRecord() {
		return saleRecordDao.findAllSaleRecord();
	}

}
