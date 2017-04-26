package com.dowloyalty.service.impl;

import java.util.List;

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
	public RetailerAccInfo findFurAccByRetailerId(int id) {
		return pointsLevelDao.findFurAccByRetailerId(id);
	}

	@Override
	public String findLvNameByRetailerId(int id, int pId) {
		return pointsLevelDao.findLvNameByRetailerId(id, pId);
	}

	@Override
	public PointsLevel findNexLvPByRetailerId(int id, int pId) {
		return pointsLevelDao.findNexLvPByRetailerId(id, pId);
	}

	@Override
	public void save(PointsLevel pointsLevel) {
		pointsLevelDao.save(pointsLevel);
	}

	@Override
	public void update(PointsLevel pointsLevel) {
		pointsLevelDao.update(pointsLevel);
	}

	@Override
	public PointsLevel findByProjectIdAndName(int projectId, String name) {
		return pointsLevelDao.findByProjectIdAndName(projectId, name);
	}

	@Override
	public List<PointsLevel> findByProjectId(int projectId) {
		return pointsLevelDao.findByProjectId(projectId);
	}

	@Override
	public RetailerAccInfo findAccountBasicInfoByRetailerId(int retailerId) {
		return pointsLevelDao.findAccountBasicInfoByRetailerId(retailerId);
	}

	@Override
	public String findPointslevelNameByProjectIdAndPoints(int projectId, int points) {
		return pointsLevelDao.findPointslevelNameByProjectIdAndPoints(projectId, points);
	}

	@Override
	public PointsLevel findNextPointslevelByProjectIdAndPoints(int projectId, int points) {
		// TODO Auto-generated method stub
		return pointsLevelDao.findNextPointslevelByProjectIdAndPoints(projectId, points);
	}

}
