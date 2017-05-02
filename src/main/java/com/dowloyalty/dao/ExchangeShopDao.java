package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.ExchangeShop;

public interface ExchangeShopDao {

	/**
	 * 增加一条礼品商城的记录
	 * @param exchangeShop 礼品商城记录对象
	 */
	public void save(ExchangeShop exchangeShop);
	
	/**
	 * 根据id修改礼品商城记录
	 * @param exchangeShop
	 */
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
