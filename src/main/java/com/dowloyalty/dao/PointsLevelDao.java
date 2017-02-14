package com.dowloyalty.dao;

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
	 * @param pId 省份id
	 */
	public RetailerAccInfo findFurAccByRetailerId(@Param("RetailerID")int id,@Param("ProvinceID")int pId);
	
}
