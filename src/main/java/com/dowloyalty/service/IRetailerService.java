package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Retailer;

public interface IRetailerService {
	public List<Retailer> findRetailerByProvinceId(int ProvinceId) ;
}
