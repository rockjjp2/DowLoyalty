package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.PointsDetails;

public interface IRetailerDao {
	public List<Retailer> findRetailerByProvinceId(int provinceId) ;
	public Retailer findRetailerByOpenId(String openID);
	public Retailer findRetailerByLoginCode(String loginCode);
	public void updateOpenIdByLoginCode(@Param("openID")String openId,@Param("loginCode")String loginCode);
	
	/**
	 * * 通过零售商id获取相应的积分明细
	 * @param id	零售商id
	 * @param matter	积分事项
	 * @param month		筛选月份
	 * @return	零售商所有积分记录
	 */
	public List<PointsDetails> findByRetailerId(@Param("RetailerID")int id,@Param("matter") String matter, @Param("month") String month);
	
	/**
	 * 通过id查找零售商信息
	 * @param id 零售商id
	 * @return 零售商对象
	 */
	public Retailer findById(int id);
}
