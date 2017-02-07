package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ExchangeRecordDao;
import com.dowloyalty.entity.ExchangeRecord;
import com.dowloyalty.service.ExchangeRecordService;

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

}
