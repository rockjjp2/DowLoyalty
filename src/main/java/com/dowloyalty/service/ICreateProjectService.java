package com.dowloyalty.service;



import java.sql.Timestamp;
import java.util.List;

import com.dowloyalty.entity.Project;

public interface ICreateProjectService {

    public void insertProjectInfo(String name,int provinceID,String placardPath,Timestamp startDate,Timestamp endDate,int adminID,boolean isVisible,boolean isActive,String backgroundPath,String description);
    public void updateProjectInfo(String name,int provinceID,String placardPath,String backgroundPath,String description,int projectId);

    
	public void insertRProjectRetailer(int projectID, int retailerID,boolean isActive);

	public void insertRProjectPromoter(int projectID, int promoterID,boolean isActive);
	
	public void updateDeliveryGoodsPromoter(int promoterId,int projectId);
	
	public Project findProjectByInfo(String startDate,String endDate,int provinceId,String projectName);
	
	public List<Integer> findRetailerIdByProjectId(int projectId);
	
	 public List<Integer> findPromoterIdByProjectId(int projectId);
	 
	public void updateRProjRetailer(int projectID, int retailerID, boolean isActive);
	
	public void updateRProjPromoter(int projectID, int promoterId, boolean isActive);
}
