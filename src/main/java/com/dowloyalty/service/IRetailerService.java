package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.PointsDetails;

public interface IRetailerService {
	public List<Retailer> findRetailerByProvinceId(int ProvinceId) ;
	public Boolean UserIsRetailer(String openID);
	public Retailer findRetailerByLoginCode(String LoginCode);
	public void updateOpenIdByLoginCode(String openId,String loginCode);
	
	/**
	 * * 通过零售商id获取相应的积分明细
	 * @param id	零售商id
	 * @param matter	积分事项
	 * @param month		筛选月份
	 * @return	零售商所有积分记录
	 */
	public List<PointsDetails> findByRetailerId(int id, String matter, String month);
	
	/**
	 * 通过id查找零售商信息
	 * @param id 零售商id
	 * @return 零售商对象
	 */
	public Retailer findById(int id);
}
