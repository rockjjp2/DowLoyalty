package com.dowloyalty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IRetailerDao;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.service.IRetailerService;

@Service
public class RetailerServiceImp implements IRetailerService {
	@Resource
	IRetailerDao iRetailerDao;

	@Override
	public List<Retailer> findRetailerByProvinceId(int ProvinceId) {
		return iRetailerDao.findRetailerByProvinceId(ProvinceId);
	}
}
 