package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.Points;
import com.dowloyalty.pojo.PointMapping;

public interface IPointsDao {
	public Points findPointByPrjectIdAndProductId(@Param("projectID")int projectID,@Param("productID")int productID); 
	
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
	
	public List<PointMapping> findPointMappingByProject(int projectId);
	
	/**
	 * 批量插入产品积分记录
	 * @param ids 产品id集合
	 * @param points 积分记录对象
	 */
	public void patchAddProducts(@Param("ids")List<String> ids, @Param("Points")Points points);
	
	/**
	 * 根据项目id获取所有产品积分中的产品id
	 * @param projectId 项目id
	 * @return 产品id集合
	 */
	public List<String> findAllPointsByProjectId(String projectId);
	
}
