package com.dowloyalty.dao;

import java.util.List;

import com.dowloyalty.entity.Promoter;

public interface IPromoterDao {
	public List<Promoter> findPromoterByProvinceId(int ProvinceId) ;
}
