package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ISaleRecordDao;
import com.dowloyalty.entity.SaleRecord;
import com.dowloyalty.pojo.webSale;
import com.dowloyalty.pojo.PojoSaleRecord;
import com.dowloyalty.pojo.SendSaleRecord;
import com.dowloyalty.service.ISalesRecordService;

@Service
public class SalesRecordServiceImpl implements ISalesRecordService{

	@Resource
	private ISaleRecordDao iSaleRecordDao;
	/*@Override
	public List<webSale> selectSalesRecord(int promoterId,int startIndex,int pageSize) {
		// TODO Auto-generated method stub
		return saleRecordDao.selectSalesRecord(promoterId, startIndex, pageSize);
	}*/
	@Override
	public List<webSale> findNewInsertSalesRecord(int id,int promoterId) {
		// TODO Auto-generated method stub
		return iSaleRecordDao.findNewInsertSalesRecord(id,promoterId);
	}
	@Override
	public List<PojoSaleRecord> selectWeChatSaleRecords(int promoterId) {
		// TODO Auto-generated method stub
		return iSaleRecordDao.selectWeChatSaleRecords(promoterId);
	}
	@Override
	public List<SendSaleRecord> findAllSaleRecord() {
		return iSaleRecordDao.findAllSaleRecord();
	}
//	@Override
//	public int getMaxPageNum(String time) {
//		int count = iSaleRecordDao.getCount(time);
//		int maxPageNum = count % Page.ShOWNUM.getNum() == 0 ? count / Page.ShOWNUM.getNum() : count / Page.ShOWNUM.getNum() + 1;
//		return maxPageNum;
//	}
	@Override
	public void update(SaleRecord saleRecord) {
		iSaleRecordDao.update(saleRecord);
	}
	@Override
	public SaleRecord findById(int id) {
		return iSaleRecordDao.findById(id);
	}
//	@Override
//	public List<SearchSaleRecords> findByConditions(String id, String name, int PageNum) {
//		return iSaleRecordDao.findByConditions(id, name, (PageNum-1) * Page.ShOWNUM.getNum(), Page.ShOWNUM.getNum());
//	}
//	@Override
//	public int getMaxPageNum(String id, String name) {
//		int count = iSaleRecordDao.getCount(id, name);
//		int maxPageNum = count % Page.ShOWNUM.getNum() == 0 ? count / Page.ShOWNUM.getNum() : count / Page.ShOWNUM.getNum() + 1;
//		return maxPageNum;
//	}

}
