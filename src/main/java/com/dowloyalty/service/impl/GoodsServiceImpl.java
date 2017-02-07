package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.GoodsDao;
import com.dowloyalty.entity.Goods;
import com.dowloyalty.pojo.GoodsVo;
import com.dowloyalty.service.GoodsService;


@Service
public class GoodsServiceImpl implements GoodsService {

	@Resource
	GoodsDao goodsDao;
	
	@Override
	public void save(Goods goods) {
		goodsDao.save(goods);
	}

	@Override
	public void delete(int id) {
		
	}

	@Override
	public void update(Goods goods) {

	}

	@Override
	public List<Goods> findAll() {
		List<Goods> list = goodsDao.findAll();
		return list;
	}

	@Override
	public Goods findById(int id) {
		return null;
	}

	@Override
	public int getGoodsAmount() {
		return goodsDao.getGoodsAmount();
	}

	@Override
	public List<GoodsVo> findByRetailerId(int retailerId) {
		return goodsDao.findByRetailerId(retailerId);
	}

	@Override
	public GoodsVo findByGoodsId(int goodsId) {
		return goodsDao.findByGoodsId(goodsId);
	}

	@Override
	public List<GoodsVo> findByProvinceId(int provinceId) {
		return goodsDao.findByProvinceId(provinceId);
	}

}
