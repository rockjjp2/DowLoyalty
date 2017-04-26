package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IPointsDao;
import com.dowloyalty.entity.Points;
import com.dowloyalty.pojo.PointMapping;
import com.dowloyalty.service.IPointsService;
@Service
public class IPointsServiceImpl implements IPointsService {
@Resource
IPointsDao iPontsDao;
	@Override
	public Points findPointByPrjectIdAndProductId(int projectID, int productID) {
		// TODO Auto-generated method stub
		return iPontsDao.findPointByPrjectIdAndProductId(projectID, productID);
	}
	@Override
	public void save(Points points) {
		iPontsDao.save(points);
	}
	@Override
	public void update(Points points) {
		iPontsDao.update(points);
	}
	@Override
	public List<PointMapping> findPointMappingByProject(int projectId) {
		// TODO Auto-generated method stub
		return iPontsDao.findPointMappingByProject(projectId);
	}
	@Override
	public void patchAddProducts(List<String> ids, Points points) {
		iPontsDao.patchAddProducts(ids, points);
	}
	@Override
	public List<String> findAllPointsByProjectId(String projectId) {
		return iPontsDao.findAllPointsByProjectId(projectId);
	}

}
