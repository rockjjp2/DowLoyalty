package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Farmer;
import com.dowloyalty.entity.FarmerSalesRecord;
import com.dowloyalty.entity.RProjectFarmer;
import com.dowloyalty.pojo.FarmerVo;

public interface FarmerService {
	
	/**
	 * 添加农户
	 * @param farmer  农户对象
	 */
	public void save(Farmer farmer);
	
	/**
	 * 根据openId查询农户对象
	 * @param openId  微信openid
	 * @return	农户对象
	 */
	public Farmer findFarmerByOpenId(String openId);
	
	/**
	 * 根据openId判断用户是否为农户
	 * @param openId  微信openId
	 * @return  true即为农户，false则不是
	 */
	public boolean UserIsFarmer(String openId);
	
	/**
	 * 将项目和农户建立关系
	 * @param relation  关系对象
	 */
	public void saveRelationWithProject(RProjectFarmer relation);
	
	/**
	 * 根据姓名查询是否存在农户
	 * @param name 姓名
	 * @return 农户
	 */
	public int getFarmerCountByName(String name);
	
	/**
	 * 根据手机号查询是否存在农户
	 * @param mobile 手机号
	 * @return 农户
	 */
	public int getFarmerCountByMobile(String mobile);
	
	/**
	 * 根据手机号查询农户
	 * @param mobile  手机号
	 * @return  农户
	 */
	public Farmer findByMobile(String mobile);
	
	/**
	 * 根据id获取农户账号信息
	 * @param farmerId  农户id
	 * @return  农户账号信息对象集合
	 */
	public List<FarmerVo> findAccountInfoByFarmerId(int farmerId);
	
	/**
	 * 根据月份和id获取农户销售记录
	 * @param month  月份
	 * @param farmerId  农户id
	 * @return  农户销售记录集合
	 */
	public List<FarmerSalesRecord> findByCondition(String month,int farmerId);
	
	/**
	 * 根据id获取农户对象
	 * @param farmerId  农户id
	 * @return  农户对象
	 */
	public Farmer findById(int farmerId);
}
