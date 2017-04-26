package com.dowloyalty.dao;

import java.util.List;

import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.ExchangeshopGoods;
import com.dowloyalty.pojo.ProjectProvince;

public interface IProjectDetailsDao {

	public ProjectProvince findProjectById(int projectId);
	
	public List<Retailer> findRetaileByProjectId(int projectId);
	
	public List<Promoter> findPromoterByProjectId(int projectId);
	
	public List<ExchangeshopGoods> findGoodsByProjectId(int projectId);

    public Promoter findDelivPromoterByProjectId(int projectId);
}
