package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.PointsLevel;
import com.dowloyalty.pojo.RetailerAccInfo;

public interface PointsLevelDao {
	
	/**
	 * 根据零售商id和项目id获取零售商当前积分等级名称
	 * @param id 零售商id
	 * @param pId 项目id
	 * @return 积分等级名称
	 */
	public String findLvNameByRetailerId(@Param("RetailerID")int id,@Param("ProjectID")int pId);
	/**
	 * 根据项目id与总积分获取当前等级
	 * @param id 零售商id
	 * @param pId 项目id
	 * @return 积分等级名称
	 */
	public String findPointslevelNameByProjectIdAndPoints(@Param("projectId")int projectId,@Param("points")int points);
	public PointsLevel findNextPointslevelByProjectIdAndPoints(@Param("projectId")int projectId,@Param("points")int points);
	
	/**
	 * 根据零售商id和项目id获取零售商当前积分等级的下一个等级
	 * @param id	零售商id
	 * @param pId 项目id
	 * @return	积分等级对象
	 */
	public PointsLevel findNexLvPByRetailerId(@Param("RetailerID")int id,@Param("ProjectID")int pId);
	
	/**
	 * 通过零售商id获取相应的账号信息
	 * @param id 零售商id
	 * @return	零售商账号信息对象
	 */
	public RetailerAccInfo findAccInfoByRetailerId(@Param("RetailerID")int id);
	/**
	 * 通过零售商id获取相应的账号信息
	 * @param id 零售商id
	 * @return	零售商账号信息对象
	 */
	public RetailerAccInfo findAccountBasicInfoByRetailerId(@Param("retailerId")int retailerId);
	
	/**
	 * 通过零售商id获取相应的账号信息
	 * @param id 零售商id
	 */
	public RetailerAccInfo findFurAccByRetailerId(@Param("RetailerID")int id);
	
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
	public PointsLevel findByProjectIdAndName(@Param("ProjectID")int projectId, @Param("Name")String name);
	
	/**
	 * 根据项目id获取积分等级记录
	 * @param projectId	项目id
	 * @return	积分等级记录集合
	 */
	public List<PointsLevel> findByProjectId(int projectId);
}
