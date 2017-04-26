package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.Goods;
import com.dowloyalty.pojo.GoodsVo;


/**
 *	礼品-数据库操作层
 */
public interface GoodsDao {

	/**
	 * 增加礼品
	 * @param 礼品对象
	 */
	public void save(Goods goods);

	/**
	 * 根据礼品ID删除礼品
	 * @param id 礼品ID
	 */
	public void delete(int id);

	public void update(Goods goods);

	/**
	 * 获取所有礼品
	 * @return 礼品集合
	 */
	public List<Goods> findAll();
	
	/**
	 * 根据零售商ID获取零售商所在省份的礼品商城的所有礼品信息
	 * 包括ID，名称，图片地址和礼品的积分
	 * @param retailerId 零售商ID
	 * @return 礼品集合
	 */
	public List<GoodsVo> findByRetailerId(@Param("RetailerID")int retailerId);
	
	/**
	 * 根据礼品ID获取礼品信息
	 * 包括ID，名称，图片地址和礼品的积分
	 * @param retailerId	零售商ID
	 * @param goodsId	礼品ID
	 * @return	礼品对象
	 */
	public GoodsVo findByGoodsId(@Param("RetailerID")int retailerId,@Param("GoodsID")int goodsId);
	
	/**
	 * 根据省份ID获取该省礼品商城所有礼品信息
	 * @param provinceId	省份ID
	 * @return	礼品集合
	 */
	public List<GoodsVo> findByProvinceId(int provinceId);
	
	/**
	 * 获取所有礼品的数量
	 * @return 礼品数量
	 */
	public int getGoodsAmount();
	
	public Goods findById(int id);
	
	/**
	 * 批量插入礼品信息
	 * @param goodsList 礼品集合
	 */
	public void batchGoodsInfo(@Param("goodsList")List<Goods> goodsList);
}
