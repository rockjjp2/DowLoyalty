package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.RPromoterProvince;
import com.dowloyalty.pojo.PromoterVo;

public interface IPromoterDao {
	//根据省份和推广员获得零售商总数
	public int findCountByProvinceId(@Param("value")int provinceId,@Param("promoterName")String promoterName);
	public List<Promoter> findPromoterByProvinceId(int provinceId) ;
	//根据省份和推广员获得零售商list
	public List<Promoter> searchPromoterByPromoterName(@Param("value")int provinceId,@Param("promoterName")String promoterName,@Param("startNum")int startNum,@Param("pageNum")int pageNum);
	public List<Promoter> findPromoterByProjectId(int promoterId) ;
	public Promoter findPromoterByUserId(String userId);
	
	/**
	 * 根据项目id获取所有激活推广员
	 * @param projectId	项目id
	 * @return 推广员对象集合
	 */
	public List<Promoter> findAllPromotersByProjectId(@Param("ProjectID")String projectId);
	
	/**
	 * 批量添加
	 * @param ids 推广员id集合
	 * @param projectId 项目id
	 */
	public void patchInsertRelationBetweenPromoterAndProject(@Param("ids")List<String> ids,@Param("projectId")String projectId);
	
	/**
	 * 批量删除
	 * @param ids 推广员id集合
	 * @param projectId 项目id
	 */
	public void patchDeleteRelationBetweenPromoterAndProject(@Param("ids")List<String> ids,@Param("projectId")String projectId);
	
	/**
	 * 获取项目下的所有推广员id
	 * @param projectId   项目id
	 * @return    推广员id集合
	 */
	public List<String> findAllPromoterByProjectId(String projectId);
	
	/**
	 * 获取所有激活的推广员信息
	 * @return 推广员集合
	 */
	public List<Promoter> findAllActivePromoters();
	
	/**
	 * 修改推广员信息
	 * @param promoter 推广员
	 */
	public void update(PromoterVo promoter);
	
	/**
	 * 批量添加推广员
	 * @param promoter 推广员集合
	 */
	public void batchSave(@Param("promoters")List<PromoterVo> promoters);
	
	/**
	 * 获取所有推广员与省份的关系记录
	 * @return 关系记录集合
	 */
	public List<RPromoterProvince> findAllPromoterAndProvinceRelation();
	
	/**
	 * 修改推广员与省份的关系记录
	 * @param promoterId 推广员id
	 * @param provinceId 省份id
	 */
	public void updatePromoterAndProvinceRelation(@Param("promoterID")int promoterId, @Param("provinceID")int provinceId);
	
	/**
	 * 批量增加推广员与省份的关系记录
	 * @param promoters 推广员关系信息集合
	 */
	public void batchSavePromoterAndProvinceRelation(@Param("promoters")List<PromoterVo> promoters);
}
