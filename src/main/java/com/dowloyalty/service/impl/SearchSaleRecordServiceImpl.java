package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.ISearchSaleRecordDao;
import com.dowloyalty.entity.Project;
import com.dowloyalty.pojo.webSale;
import com.dowloyalty.service.ISearchSaleRecordService;
import com.dowloyalty.utils.Page;
@Service
public class SearchSaleRecordServiceImpl implements ISearchSaleRecordService{

	@Resource
	private ISearchSaleRecordDao iSearchSaleRecordDao;
	
	@Override
	public List<webSale> findWebSaleRecordByProject(int projectId, int promoterId) {
		// TODO Auto-generated method stub
		return iSearchSaleRecordDao.findWebSaleRecordByProject(projectId, promoterId);
	}
	
	@Override
	public List<webSale> findWebSaleRecordByQuery(int projectId, String startDate, String endDate, int promoterId,
			String retailerName, int pageNum) {
		// TODO Auto-generated method stub
		return iSearchSaleRecordDao.findWebSaleRecordByQuery(projectId, startDate, endDate, promoterId, retailerName, (pageNum-1)*Page.ShOWNUM.getNum(), Page.ShOWNUM.getNum());
	}
	

	@Override
	public List<webSale> findWeChatSaleRecordByQuery(String startDate, String endDate, String promoterId,
			String retailerName) {
		// TODO Auto-generated method stub
		return iSearchSaleRecordDao.findWeChatSaleRecordByQuery(startDate, endDate, promoterId, retailerName);
	}

	@Override
	public List<Project> findProjectByPromoterId(int promoterId) {
		// TODO Auto-generated method stub
		return iSearchSaleRecordDao.findProjectByPromoterId(promoterId);
	}

	@Override
    public int getTotalPage(int projectId, String startDate, String endDate, int promoterId, String retailerName){
    	int count = iSearchSaleRecordDao.getSaleRecordCount(projectId, startDate, endDate, promoterId, retailerName);
		int totalPageNum = count % Page.ShOWNUM.getNum() == 0 ? count / Page.ShOWNUM.getNum() : count / Page.ShOWNUM.getNum() + 1;
		return totalPageNum;
    }

	@Override
	public List<Project> findProject() {
		// TODO Auto-generated method stub
		return iSearchSaleRecordDao.findProject();
	}

	@Override
	public List<webSale> findWebSaleRecords(int projectId, String startDate, String endDate, int promoterId,
			String retailerName) {
		return iSearchSaleRecordDao.findWebSaleRecords(projectId, startDate, endDate, promoterId, retailerName);
	}


}
