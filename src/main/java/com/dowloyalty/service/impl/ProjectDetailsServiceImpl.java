package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IProjectDetailsDao;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.ExchangeshopGoods;
import com.dowloyalty.pojo.ProjectProvince;
import com.dowloyalty.service.IProjectDetailsService;
@Service
public class ProjectDetailsServiceImpl implements IProjectDetailsService{

	@Resource
	private IProjectDetailsDao iProjectDetailsDao;
	
	@Override
	public ProjectProvince findProjectById(int projectId) {
		// TODO Auto-generated method stub
		return iProjectDetailsDao.findProjectById(projectId);
	}

	@Override
	public List<Retailer> findRetaileByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return iProjectDetailsDao.findRetaileByProjectId(projectId);
	}

	@Override
	public List<Promoter> findPromoterByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return iProjectDetailsDao.findPromoterByProjectId(projectId);
	}

	@Override
	public List<ExchangeshopGoods> findGoodsByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return iProjectDetailsDao.findGoodsByProjectId(projectId);
	}

	@Override
	public Promoter findDelivPromoterByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return iProjectDetailsDao.findDelivPromoterByProjectId(projectId);
	}

}
