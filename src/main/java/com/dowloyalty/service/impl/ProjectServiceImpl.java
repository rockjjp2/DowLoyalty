package com.dowloyalty.service.impl;

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

}
