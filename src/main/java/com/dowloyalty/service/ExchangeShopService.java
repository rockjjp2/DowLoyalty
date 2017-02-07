package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.ExchangeShop;

public interface ExchangeShopService {

	/**
	 * Add one of goods infomation in exchange shop
	 * @param exchangeShop the object of exchangeShop
	 */
	public void save(ExchangeShop exchangeShop);
	
	public void delete(int id);
	
	public void update(ExchangeShop exchangeShop);
	
	public List<ExchangeShop> findAll();
	
	public ExchangeShop findById(int id);
}
