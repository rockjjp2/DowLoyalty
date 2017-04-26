package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ExchangeRecordDao;
import com.dowloyalty.entity.ExchangeRecord;
import com.dowloyalty.pojo.Deliver;
import com.dowloyalty.pojo.ExchangeRecordWeb;
import com.dowloyalty.service.ExchangeRecordService;
import com.dowloyalty.utils.Page;

@Service
public class ExchangeRecordServiceImpl implements ExchangeRecordService {
	
	@Resource
	ExchangeRecordDao exchangeRecordDao;
	
	@Override
	public void save(ExchangeRecord exchangeRecord) {
		exchangeRecordDao.save(exchangeRecord);
	}

	@Override
	public void delete(int id) {
		
	}

	@Override
	public void update(ExchangeRecord exchangeRecord) {
	}

	@Override
	public List<ExchangeRecord> findAll() {
		return null;
	}

	@Override
	public ExchangeRecord findById(int id) {
		return null;
	}

	@Override
	public List<ExchangeRecordWeb> findByConditions(String id,String name, String projectId, String startDate, String endDate, int PageNum) {
		return exchangeRecordDao.findByConditions(id, name, projectId, startDate, endDate, (PageNum-1) * Page.ShOWNUM.getNum(), Page.ShOWNUM.getNum());
	}

	@Override
	public int getMaxPageNum(String id,String name, String projectId, String startDate, String endDate) {
		int count = exchangeRecordDao.getCount(id, name, projectId, startDate, endDate);
		int maxPageNum = count % Page.ShOWNUM.getNum() == 0 ? count / Page.ShOWNUM.getNum() : count / Page.ShOWNUM.getNum() + 1;
		return maxPageNum;
	}

	@Override
	public List<ExchangeRecord> findExchangeRecordByProvinceIdAndStatus(int provinceId, int status) {
		// TODO Auto-generated method stub
		return exchangeRecordDao.findExchangeRecordByProvinceIdAndStatus(provinceId, status);
	}

	@Override
	public List<Deliver> findDeliverList(int projectId, int status) {
		// TODO Auto-generated method stub
		return exchangeRecordDao.findDeliverList(projectId, status);
	}

	@Override
	public void updateExchangerrecordToComplete(int exchangeRecordId) {
		exchangeRecordDao.updateExchangerrecordToComplete(exchangeRecordId);
	}

	@Override
	public int getSumExchangPointsByProjectIdAndRetailerId(int projectId, int retailerId) {
		// TODO Auto-generated method stub
		return exchangeRecordDao.getSumExchangPointsByProjectIdAndRetailerId(projectId, retailerId);
	}

}
