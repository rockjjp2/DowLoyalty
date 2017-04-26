package com.dowloyalty.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 根据项目id和礼品id获取交换商店的礼品记录
	 * @param projectId	项目id
	 * @param goodsId	礼品id
	 * @return	交换商店的礼品记录
	 */
	public ExchangeShop findById(@Param("ProjectID")int projectId, @Param("GoodsID")int goodsId);
}
