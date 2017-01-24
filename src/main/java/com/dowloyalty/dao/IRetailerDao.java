package com.dowloyalty.dao;

import java.util.List;

import com.dowloyalty.entity.Retailer;

public interface IRetailerDao {
	public List<Retailer> findRetailerByProvinceId(int ProvinceId) ;
}
