package com.dowloyalty.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ProjectDao;
import com.dowloyalty.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Resource
	ProjectDao projectDao;
	
	@Override
	public String findActiveByRid(int rId) {
		return projectDao.findActiveByRid(rId);
	}

}
