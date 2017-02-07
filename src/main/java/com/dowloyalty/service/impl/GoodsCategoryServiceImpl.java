package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.GoodsCategoryDao;
import com.dowloyalty.entity.GoodsCategory;
import com.dowloyalty.service.GoodsCategoryService;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

	@Resource
	GoodsCategoryDao goodsCategoryDao;
	
	@Override
	public List<GoodsCategory> findAll() {
		return goodsCategoryDao.findAll();
	}

}
