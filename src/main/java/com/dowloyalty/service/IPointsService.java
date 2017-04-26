package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Points;
import com.dowloyalty.pojo.PointMapping;

public interface IPointsService {
	public Points findPointByPrjectIdAndProductId(int projectID,int productID); 
	
	/**
	 * 增加产品积分记录
	 * @param points	产品积分对象
	 */
	public void save(Points points);
	
	/**
	 * 根据id修改产品积分记录
	 * @param points	产品积分对象
	 */
	public void update(Points points);
	/**
	 * 根据项目id找到项目下的积分综合表
	 * 根据视图查出
	 * @param projectId
	 * @return
	 */
	public List<PointMapping> findPointMappingByProject(int projectId);
	
	/**
	 * 批量插入产品积分记录
	 * @param ids 产品id集合
	 * @param points 积分记录对象
	 */
	public void patchAddProducts(List<String> ids, Points points);
	
	/**
	 * 根据项目id获取所有产品积分中的产品id
	 * @param projectId 项目id
	 * @return 产品id集合
	 */
	public List<String> findAllPointsByProjectId(String projectId);
}
