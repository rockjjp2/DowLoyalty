package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.GoodsCategory;

public interface GoodsCategoryService {
	
	/**
	 * 获取所有礼品目录
	 * @return	礼品目录对象集合
	 */
	public List<GoodsCategory> findAll();
}
