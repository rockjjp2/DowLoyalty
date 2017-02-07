package com.dowloyalty.dao;

import java.util.List;

import com.dowloyalty.entity.GoodsCategory;

/**
 * 礼品目录数据库访问层
 * @author wangyuanjie
 *
 */
public interface GoodsCategoryDao {
	
	/**
	 * 获取所有礼品目录
	 * @return	礼品目录对象集合
	 */
	public List<GoodsCategory> findAll();
}
