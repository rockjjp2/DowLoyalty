package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ExchangeShopDao;
import com.dowloyalty.entity.ExchangeShop;
import com.dowloyalty.service.ExchangeShopService;

@Service
public class ExchangeShopServiceImpl implements ExchangeShopService {

	@Resource
	ExchangeShopDao exchangeShopDao;
	
	@Override
	public void save(ExchangeShop exchangeShop) {
		exchangeShopDao.save(exchangeShop);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ExchangeShop exchangeShop) {
		exchangeShopDao.update(exchangeShop);
	}

	@Override
	public List<ExchangeShop> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExchangeShop findById(int projectId, int goodsId) {
		return exchangeShopDao.findById(projectId, goodsId);
	}

}
