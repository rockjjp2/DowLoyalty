package com.dowloyalty.dao;

import java.util.List;

import com.dowloyalty.entity.Province;

public interface IProvinceDao {
	/**
	 * 获取所有省份信息
	 * @return	省份对象集合
	 */
	public List<Province> getAllProvince();
	public Province findProvinceByPromoterId(int promoterID);
	public int findProvinceIdByEnName(String enName);
	
	public Province findProvinceById(int provinceId);
}
