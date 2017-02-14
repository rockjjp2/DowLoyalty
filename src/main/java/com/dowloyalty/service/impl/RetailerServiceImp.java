package com.dowloyalty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IRetailerDao;
import com.dowloyalty.entity.Retailer;
import com.dowloyalty.pojo.PointsDetails;
import com.dowloyalty.service.IRetailerService;

@Service
public class RetailerServiceImp implements IRetailerService {
	@Resource
	IRetailerDao iRetailerDao;

	@Override
	public List<Retailer> findRetailerByProvinceId(int ProvinceId) {
		return iRetailerDao.findRetailerByProvinceId(ProvinceId);
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
	public Retailer findRetailerByLoginCode(String LoginCode) {
		return iRetailerDao.findRetailerByLoginCode(LoginCode);
	}

	@Override
	public void updateOpenIdByLoginCode(String openId, String loginCode) {
		// TODO Auto-generated method stub
		iRetailerDao.updateOpenIdByLoginCode(openId, loginCode);
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
}
 