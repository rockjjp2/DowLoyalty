package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ProjectDao;
import com.dowloyalty.entity.Project;
import com.dowloyalty.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Resource
	ProjectDao projectDao;
	
	@Override
	public Project findActiveByRid(int rId) {
		return projectDao.findActiveByRid(rId);
	}

	@Override
	public List<Project> findProjectByPromoterId(int promoterID) {
		return projectDao.findProjectByPromoterId(promoterID);
	}

	@Override
	public Project findProjectByProjectId(int projectId) {
		return projectDao.findProjectByProjectId(projectId);
	}

	@Override
	public Project findActiveProjectByPromoterId(int promoterID) {
		return projectDao.findActiveProjectByPromoterId(promoterID);
	}

	@Override
	public void update(Project project) {
		projectDao.update(project);
	}

	@Override
	public List<Project> findProjectByAssistantId(int assistantId) {
		return projectDao.findProjectByAssistantId(assistantId);
	}

	@Override
	public List<Project> findProjectByRetailerId(int retailerId) {
		return projectDao.findProjectByRetailerId(retailerId);
	}

	@Override
	public Project findProjectByProvinceIdAndName(String provinceId, String name) {
		return projectDao.findProjectByProvinceIdAndName(provinceId, name);
	}

	@Override
	public void save(Project project) {
		projectDao.save(project);
	}

	@Override
	public List<Project> findAllActiveProjects() {
		return projectDao.findAllActiveProjects();
	}

}
