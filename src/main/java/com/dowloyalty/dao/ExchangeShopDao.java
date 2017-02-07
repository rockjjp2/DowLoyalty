package com.dowloyalty.dao;

import java.util.List;

import com.dowloyalty.entity.ExchangeShop;

public interface ExchangeShopDao {

	/**
	 * 增加一条礼品商城的记录
	 * @param exchangeShop 礼品商城记录对象
	 */
	public void save(ExchangeShop exchangeShop);
	
	public void delete(int id);
	
	public void update(ExchangeShop exchangeShop);
	
	public List<ExchangeShop> findAll();
	
	public ExchangeShop findById(int id);
}
