package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Project;

public interface ProjectService {
	
	/**
	 * 通过零售商id获取激活的积分活动
	 * @param rId 零售商id
	 * @return 积分活动对象
	 */
	public Project findActiveByRid(int rId);
	public Project findActiveProjectByPromoterId(int promoterID);
	public List<Project> findProjectByRetailerId(int retailerId);
	public List<Project> findProjectByPromoterId(int promoterID);
	public List<Project> findProjectByAssistantId(int assistantId);
	public Project findProjectByProjectId(int projectId);
	
	/**
	 * 修改项目下的发货推广员id
	 * @param project 项目对象
	 */
	public void update(Project project);
	
	/**
	 * 根据项目名和省份id来查询项目
	 * @param provinceId 省份id
	 * @param name 项目名
	 * @return 项目对象
	 */
	public Project findProjectByProvinceIdAndName(String provinceId, String name);
	
	/**
	 * 创建项目
	 * @param project 项目对象
	 */
	public void save(Project project);
	
	/**
	 * 查询所有激活的项目
	 * @return 项目集合
	 */
	public List<Project> findAllActiveProjects();
}
