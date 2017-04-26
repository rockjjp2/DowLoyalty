package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.PointsLevel;
import com.dowloyalty.pojo.RetailerAccInfo;

public interface PointsLevelService {
	/**
	 * 根据项目id与总积分获取当前等级
	 * @param id 零售商id
	 * @param pId 项目id
	 * @return 积分等级名称
	 */
	public String findPointslevelNameByProjectIdAndPoints(int projectId,int points);
	public PointsLevel findNextPointslevelByProjectIdAndPoints(int projectId,int points);
	/**
	 * 根据id获取零售商当前积分等级名称
	 * @param id 零售商id
	 * @param pId 项目id
	 * @return 积分等级名称
	 */
	public String findLvNameByRetailerId(int id,int pId);
	
	/**
	 * 根据id获取零售商当前积分等级的下一个等级
	 * @param id	零售商id
	 * @param pId 项目id
	 * @return	积分等级对象
	 */
	public PointsLevel findNexLvPByRetailerId(int id,int pId);
	
	/**
	 * 通过零售商id获取相应的账号信息
	 * @param id 零售商id
	 * @return	零售商账号信息对象
	 */
	public RetailerAccInfo findAccountBasicInfoByRetailerId(int retailerId);
	/**
	 * 通过零售商id获取相应的账号信息
	 * @param id 零售商id
	 * @return	零售商账号信息对象
	 */
	public RetailerAccInfo findAccInfoByRetailerId(int id);
	
	/**
	 * 通过零售商id获取相应的账号信息
	 * @param id 零售商id
	 */
	public RetailerAccInfo findFurAccByRetailerId(int id);
	
	/**
	 *增加项目下的一条积分等级记录
	 * @param pointsLevel	积分等级记录
	 */
	public void save(PointsLevel pointsLevel);
	
	/**
	 * 修改某条记分等级记录
	 * @param pointsLevel	积分等级记录	
	 */
	public void update(PointsLevel pointsLevel);
	
	/**
	 * 根据项目id和积分等级名称获取积分等级记录
	 * @param projectId	项目id
	 * @param name	积分等级名称
	 * @return	积分等级记录
	 */
	public PointsLevel findByProjectIdAndName(int projectId, String name);
	
	/**
	 * 根据项目id获取积分等级记录
	 * @param projectId	项目id
	 * @return	积分等级记录集合
	 */
	public List<PointsLevel> findByProjectId(int projectId);
}
