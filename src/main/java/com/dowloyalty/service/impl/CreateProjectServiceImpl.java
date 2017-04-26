package com.dowloyalty.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ICreateProjectDao;
import com.dowloyalty.entity.Project;
import com.dowloyalty.service.ICreateProjectService;

@Service
public class CreateProjectServiceImpl implements ICreateProjectService{

	@Resource
	ICreateProjectDao iCreateProjectDao;
	
	@Override
	public void insertProjectInfo(String name, int provinceID, String placardPath, Timestamp startDate,
			Timestamp endDate, int adminID, boolean isVisible, boolean isActive, String backgroundPath,
			String description) {
		// TODO Auto-generated method stub
		iCreateProjectDao.insertProjectInfo(name, provinceID, placardPath, startDate, endDate, adminID, isVisible, isActive, backgroundPath, description);
	}

	@Override
	public void insertRProjectRetailer(int projectID, int retailerID, boolean isActive) {
		// TODO Auto-generated method stub
		iCreateProjectDao.insertRProjectRetailer(projectID, retailerID, isActive);
	}

	@Override
	public void insertRProjectPromoter(int projectID, int promoterID, boolean isActive) {
		// TODO Auto-generated method stub
		iCreateProjectDao.insertRProjectPromoter(projectID, promoterID, isActive);
	}

	@Override
	public void updateDeliveryGoodsPromoter(int promoterId, int projectId) {
		// TODO Auto-generated method stub
		iCreateProjectDao.updateDeliveryGoodsPromoter(promoterId, projectId);
	}

	@Override
	public Project findProjectByInfo(String startDate, String endDate, int provinceId, String projectName) {
		// TODO Auto-generated method stub
		return iCreateProjectDao.findProjectByInfo(startDate, endDate, provinceId, projectName);
	}

	@Override
	public List<Integer> findRetailerIdByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return iCreateProjectDao.findRetailerIdByProjectId(projectId);
	}

	@Override
	public void updateRProjRetailer(int projectID, int retailerID, boolean isActive) {
		// TODO Auto-generated method stub
		iCreateProjectDao.updateRProjRetailer(projectID, retailerID, isActive);
	}

	@Override
	public List<Integer> findPromoterIdByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return iCreateProjectDao.findPromoterIdByProjectId(projectId);
	}

	@Override
	public void updateRProjPromoter(int projectID, int promoterId, boolean isActive) {
		// TODO Auto-generated method stub
		iCreateProjectDao.updateRProjPromoter(projectID, promoterId, isActive);
	}

	@Override
	public void updateProjectInfo(String name, int provinceID, String placardPath, String backgroundPath,
			String description,int projectId) {
		// TODO Auto-generated method stub
		iCreateProjectDao.updateProjectInfo(name, provinceID, placardPath, backgroundPath, description,projectId);
		
	}

}
