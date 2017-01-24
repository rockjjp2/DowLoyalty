package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Promoter;

public interface IPromoterService {
	public List<Promoter> findPromoterByProvinceId(int ProvinceId) ;
}
