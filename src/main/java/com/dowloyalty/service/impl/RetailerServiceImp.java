package com.dowloyalty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IRetailerDao;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.PointsDetails;
import com.dowloyalty.pojo.RetailerAccInfo;
import com.dowloyalty.service.IRetailerService;
import com.dowloyalty.utils.Page;

@Service
public class RetailerServiceImp implements IRetailerService {
	@Resource
	IRetailerDao iRetailerDao;

	@Override
	public int findCountByProvinceId(int ProvinceId, String retailerName) {
		int count = iRetailerDao.findCountByProvinceId(ProvinceId,retailerName);
		int totalPageNum = count % (Page.ShOWNUM.getNum()-5) == 0 ? count / (Page.ShOWNUM.getNum()-5) : count / (Page.ShOWNUM.getNum()-5) + 1;
		return totalPageNum;
	}

	@Override
	public Boolean UserIsRetailer(String openID) {
		return iRetailerDao.findRetailerByOpenId(openID)!=null?true:false;
	}

	
	@Override
	public Retailer findRetailerByOpenId(String openID) {
		// TODO Auto-generated method stub
		return iRetailerDao.findRetailerByOpenId(openID);
	}
	
	@Override
	public Retailer findRetailerByMobile(String mobile) {
		return iRetailerDao.findRetailerByMobile(mobile);
	}

	@Override
	public void updateOpenIdByLoginCode(String openId, String mobile) {
		// TODO Auto-generated method stub
		iRetailerDao.updateOpenIdByLoginCode(openId, mobile);
	}
	
	@Override
	public List<PointsDetails> findByRetailerId(int id, String matter, String month) {
		return iRetailerDao.findByRetailerId(id, matter, month);
	}

	@Override
	public Retailer findById(int id) {
		return iRetailerDao.findById(id);
	}
	
	@Override
	public String getRankPercent(int id, int pId) {
		return iRetailerDao.getRankPercent(id, pId);
	}

	@Override
	public List<Retailer> findRetailerByPromoterId(int promoterId) {
		// TODO Auto-generated method stub
		return iRetailerDao.findRetailerByPromoterId(promoterId);
	}

	@Override
	public List<Retailer> findRetailerByProjectID(int projectID) {
		// TODO Auto-generated method stub
		return iRetailerDao.findRetailerByProjectID(projectID);
	}

	@Override
	public List<RetailerAccInfo> findByConditions(String id, String name, int PageNum) {
		return iRetailerDao.findByConditions(id, name, (PageNum-1) * Page.ShOWNUM.getNum(), Page.ShOWNUM.getNum());
	}

	@Override
	public int getMaxPageNum(String id, String name) {
		int count = iRetailerDao.getCount(id, name);
		int maxPageNum = count % Page.ShOWNUM.getNum() == 0 ? count / Page.ShOWNUM.getNum() : count / Page.ShOWNUM.getNum() + 1;
		return maxPageNum;
	}

	@Override
	public List<Retailer> searchRetailerByRetailerName(int provinceId, String retailerName,int startNum) {
		// TODO Auto-generated method stub
		
		return iRetailerDao.searchRetailerByRetailerName(provinceId, retailerName,(startNum-1)*(Page.ShOWNUM.getNum()-5), (Page.ShOWNUM.getNum()-5));
	}

	@Override
	public List<Retailer> findRetailerByPromoterIdAndRetailerName(int promoterId, String retailername) {
		// TODO Auto-generated method stub
		return iRetailerDao.findRetailerByPromoterIdAndRetailerName(promoterId, retailername);
	}

	@Override
	public List<Retailer> findRetailerByProjectIdAndRetailerName(int projectId, String retailername) {
		// TODO Auto-generated method stub
		return iRetailerDao.findRetailerByProjectIdAndRetailerName(projectId, retailername);
	}

	@Override
	public List<Retailer> findRetailerBySFDCCode(String SFDCCode) {
		// TODO Auto-generated method stub
		return iRetailerDao.findRetailerBySFDCCode(SFDCCode);
	}

	@Override
	public void updateRetailerByAccount() {
		// TODO Auto-generated method stub
		iRetailerDao.updateRetailerByAccount();
	}

	@Override
	public void updateRetailerByRetailer(Retailer retailer) {
		// TODO Auto-generated method stub
		iRetailerDao.updateRetailerByRetailer(retailer);
	}

	@Override
	public void insertRetailer(Retailer retailer) {
		// TODO Auto-generated method stub
		iRetailerDao.insertRetailer(retailer);
	}

	@Override
	public List<Retailer> findRetailerByProvinceId(int provinceId) {
		// TODO Auto-generated method stub
		return iRetailerDao.findRetailerByProvinceId(provinceId);
	}

	@Override
	public List<Retailer> findAllRetailersByProjectId(String projectId) {
		return iRetailerDao.findAllRetailersByProjectId(projectId);
	}

	@Override
	public void patchInsertRelationBetweenRetailerAndProject(List<String> ids, String projectId) {
		iRetailerDao.patchInsertRelationBetweenRetailerAndProject(ids, projectId);
	}

	@Override
	public void patchDeleteRelationBetweenRetailerAndProject(List<String> ids, String projectId) {
		iRetailerDao.patchDeleteRelationBetweenRetailerAndProject(ids, projectId);
	}
		
	@Override
	public List<Retailer> searchRetailerByRetailerNameAndProvinceId(int provinceId, String retailerName) {
		// TODO Auto-generated method stub
		return iRetailerDao.searchRetailerByRetailerNameAndProvinceId(provinceId, retailerName);
	}

	@Override
	public List<String> findAllRetailerByProjectId(String projectId) {
		return iRetailerDao.findAllRetailerByProjectId(projectId);
	}




	/*@Override
	public List<Retailer> findRetailerByProvinceId(int ProvinceId) {
		// TODO Auto-generated method stub
		return null;
	}*/

	
}
 