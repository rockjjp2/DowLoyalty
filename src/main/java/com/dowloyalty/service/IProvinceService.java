package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Province;

public interface IProvinceService {
	public List<Province> getAllProvince();
	public int findProvinceIdByEnName(String enName);
	public Province findProvinceByPromoterId(int promoterID);
	
	public Province findProvinceById(int provinceId);
}
