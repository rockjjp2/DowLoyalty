package com.dowloyalty.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.PointsLevelDao;
import com.dowloyalty.entity.PointsLevel;
import com.dowloyalty.pojo.RetailerAccInfo;
import com.dowloyalty.service.PointsLevelService;

@Service
public class PointsLevelServiceImpl implements PointsLevelService {

	@Resource
	PointsLevelDao pointsLevelDao;
	

	@Override
	public RetailerAccInfo findAccInfoByRetailerId(int id) {
		return pointsLevelDao.findAccInfoByRetailerId(id);
	}

	@Override
	public RetailerAccInfo findFurAccByRetailerId(int id, int pId) {
		return pointsLevelDao.findFurAccByRetailerId(id, pId);
	}

	@Override
	public String findLvNameByRetailerId(int id, int pId) {
		return pointsLevelDao.findLvNameByRetailerId(id, pId);
	}

	@Override
	public PointsLevel findNexLvPByRetailerId(int id, int pId) {
		return pointsLevelDao.findNexLvPByRetailerId(id, pId);
	}

}
