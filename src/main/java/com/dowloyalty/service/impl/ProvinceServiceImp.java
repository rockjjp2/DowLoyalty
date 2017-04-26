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

	@Override
	public Province findProvinceByPromoterId(int promoterID) {
		// TODO Auto-generated method stub
		return iProvinceDao.findProvinceByPromoterId(promoterID);
	}

	@Override
	public int findProvinceIdByEnName(String enName) {
		int result = 0;
		try {
			result = iProvinceDao.findProvinceIdByEnName(enName);
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public Province findProvinceById(int provinceId) {
		// TODO Auto-generated method stub
		return iProvinceDao.findProvinceById(provinceId);
	} 
	
}
 