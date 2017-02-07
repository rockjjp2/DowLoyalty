package com.dowloyalty.dao;

import java.util.List;

import com.dowloyalty.entity.ExchangeRecord;

public interface ExchangeRecordDao {
	
	public void save(ExchangeRecord exchangeRecord);
	
	public void delete(int id);
	
	public void update(ExchangeRecord exchangeRecord);
	
	public List<ExchangeRecord> findAll();
	
	public ExchangeRecord findById(int id);
}
