package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.ExchangeRecord;

public interface ExchangeRecordService {
	
	public void save(ExchangeRecord exchangeRecord);
	
	public void delete(int id);
	
	public void update(ExchangeRecord exchangeRecord);
	
	public List<ExchangeRecord> findAll();
	
	public ExchangeRecord findById(int id);
}
