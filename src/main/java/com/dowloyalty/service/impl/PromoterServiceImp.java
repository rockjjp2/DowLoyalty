package com.dowloyalty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IPromoterDao;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.service.IPromoterService;

@Service
public class PromoterServiceImp implements IPromoterService {
	@Resource
	IPromoterDao iPromoterDao;

	@Override
	public List<Promoter> findPromoterByProvinceId(int ProvinceId) {
		return iPromoterDao.findPromoterByProvinceId(ProvinceId);
	}
	
}
 