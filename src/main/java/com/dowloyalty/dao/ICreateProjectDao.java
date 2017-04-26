package com.dowloyalty.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.Project;

public interface ICreateProjectDao {

	public void insertProjectInfo(@Param("name")String name,@Param("provinceID")int provinceID,@Param("placardPath")String placardPath,@Param("startDate")Timestamp startDate,@Param("endDate")Timestamp endDate,@Param("adminID")int adminID,@Param("isVisible")boolean isVisible,@Param("isActive")boolean isActive,@Param("backgroundPath")String backgroundPath,@Param("description")String description);
	public void updateProjectInfo(@Param("name")String name,@Param("provinceID")int provinceID,@Param("placardPath")String placardPath,@Param("backgroundPath")String backgroundPath,@Param("description")String description,@Param("value")int projectId);

	public void insertRProjectRetailer(@Param("projectID")int projectID, @Param("retailerID")int retailerID, @Param("isActive")boolean isActive);

	public void insertRProjectPromoter(@Param("projectID")int projectID, @Param("promoterID")int promoterID, @Param("isActive")boolean isActive);
	
	public void updateRProjRetailer(@Param("projectId")int projectId,@Param("retailerId")int retailerId,@Param("isActive")boolean isActive);
	
	public void updateRProjPromoter(@Param("projectId")int projectID, @Param("promoterId")int promoterId, @Param("isActive")boolean isActive);
	
	public void updateDeliveryGoodsPromoter(@Param("promoterId")int promoterId,@Param("projectId")int projectId);

	public Project findProjectByInfo(@Param("sDate")String startDate,@Param("eDate")String endDate,@Param("provinceId")int provinceId,@Param("projectName")String projectName);

    public List<Integer> findRetailerIdByProjectId(int projectId);
    
    public List<Integer> findPromoterIdByProjectId(int projectId);
    
    
}
