package com.dowloyalty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IProvinceDao;
import com.dowloyalty.entity.Province;
import com.dowloyalty.service.IProvinceService;

@Service
public class ProvinceServiceImp implements IProvinceService {
	@Resource
	IProvinceDao iProvinceDao;

	@Override
	public List<Province> getAllProvince() {
		return iProvinceDao.getAllProvince();
	} 
	
}
 