package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.PointsDetails;
import com.dowloyalty.pojo.RetailerAccInfo;

public interface IRetailerDao {
	public void insertRetailer(Retailer retailer);
	public int findCountByProvinceId(@Param("value")int provinceId,@Param("retailerName")String retailerName);
	public List<Retailer> findRetailerByProvinceId(int provinceId) ;
	public List<Retailer> searchRetailerByRetailerName(@Param("value")int provinceId,@Param("retailerName")String retailerName,@Param("startNum")int startNum,@Param("pageNum")int pageNum);
	public List<Retailer> searchRetailerByRetailerNameAndProvinceId(@Param("provinceId")int provinceId,@Param("retailerName")String retailerName);
	public Retailer findRetailerByOpenId(String openID);
	public List<Retailer> findRetailerByProjectID(int projectID);
	public List<Retailer> findRetailerBySFDCCode(String SFDCCode);
	public List<Retailer> findRetailerByProjectIdAndRetailerName(@Param("projectId")int projectId,@Param("retailername")String retailername);
	
	/**
	 * 根据手机号码查询零售商
	 * @param mobile 手机号码
	 * @return	零售商对象
	 */
	public Retailer findRetailerByMobile(String mobile);
	
	public List<Retailer> findRetailerByPromoterId(int promoterId);
	public List<Retailer> findRetailerByPromoterIdAndRetailerName(@Param("promoterId")int promoterId,@Param("retailername")String retailername);
	public void updateOpenIdByLoginCode(@Param("openID")String openId,@Param("mobile")String mobile);
	public void updateRetailerByRetailer(Retailer retailer);
	public void updateRetailerByAccount();
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
	
	/**
	 * 通过零售商id和省份id获取零售商省内排名百分比
	 * @param id	零售商id
	 * @param pId	项目id
	 * @return	零售商省内排名百分比
	 */
	public String getRankPercent(@Param("RetailerID")int id,@Param("ProjectID")int pId);
	
	/**
	 * 根据会员名或者会员ID获取会员信息并分页显示
	 * @param id	会员ID
	 * @param name	会员名
	 * @param PageNum	页码数
	 * @param ShowNum	每页显示兑换记录条数
	 * @return	会员信息对象集合
	 */
	public List<RetailerAccInfo> findByConditions(@Param("RetailerID")String id,@Param("RetailerName")String name,@Param("PageNum")int PageNum,@Param("ShowNum")int ShowNum);

	/**
	 * 根据会员名或者会员ID获取会员信息总数
	 * @param id	会员ID
	 * @param name	会员名
	 * @return	会员信息总数
	 */
	public int getCount(@Param("RetailerID")String id,@Param("RetailerName")String name);
	
	/**
	 * 根据项目id获取所有激活零售商
	 * @param projectId	项目id
	 * @return 零售商对象集合
	 */
	public List<Retailer> findAllRetailersByProjectId(@Param("ProjectID")String projectId);
	
	/**
	 * 批量添加
	 * @param ids 零售商id集合
	 * @param projectId 项目id
	 */
	public void patchInsertRelationBetweenRetailerAndProject(@Param("ids")List<String> ids,@Param("projectId")String projectId);
	
	/**
	 * 批量删除
	 * @param ids 零售商id集合
	 * @param projectId 项目id
	 */
	public void patchDeleteRelationBetweenRetailerAndProject(@Param("ids")List<String> ids,@Param("projectId")String projectId);
	
	/**
	 * 获取项目下的所有零售商id
	 * @param projectId   项目id
	 * @return    推广员id集合
	 */
	public List<String> findAllRetailerByProjectId(String projectId);
	
}
