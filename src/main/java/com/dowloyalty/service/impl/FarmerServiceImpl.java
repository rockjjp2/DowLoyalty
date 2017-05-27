package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.FarmerDao;
import com.dowloyalty.entity.Farmer;
import com.dowloyalty.entity.FarmerSalesRecord;
import com.dowloyalty.entity.RProjectFarmer;
import com.dowloyalty.pojo.FarmerExcel;
import com.dowloyalty.pojo.FarmerVo;
import com.dowloyalty.service.FarmerService;

@Service
public class FarmerServiceImpl implements FarmerService {
	
	@Resource
	FarmerDao farmerDao;
	
	@Override
	public void save(Farmer farmer) {
		farmerDao.save(farmer);
	}

	@Override
	public Farmer findFarmerByOpenId(String openId) {
		return farmerDao.findFarmerByOpenId(openId);
	}

	@Override
	public boolean UserIsFarmer(String openId) {
		return farmerDao.findFarmerByOpenId(openId) != null ? true : false;
	}

	@Override
	public void saveRelationWithProject(List<RProjectFarmer> relations) {
		farmerDao.saveRelationWithProject(relations);
	}

	@Override
	public int getFarmerCountByName(String name) {
		return farmerDao.getFarmerCountByName(name);
	}

	@Override
	public int getFarmerCountByMobile(String mobile) {
		return farmerDao.getFarmerCountByMobile(mobile);
	}

	@Override
	public Farmer findByMobile(String mobile) {
		return farmerDao.findByMobile(mobile);
	}

	@Override
	public List<FarmerVo> findAccountInfoByFarmerId(int farmerId) {
		return farmerDao.findAccountInfoByFarmerId(farmerId);
	}

	@Override
	public List<FarmerSalesRecord> findByCondition(String month, int farmerId) {
		return farmerDao.findByCondition(month, farmerId);
	}

	@Override
	public Farmer findById(int farmerId) {
		return farmerDao.findById(farmerId);
	}

	@Override
	public List<Farmer> findByProvinceId(int provinceId) {
		return farmerDao.findByProvinceId(provinceId);
	}

	@Override
	public List<FarmerExcel> findNewInsertFarmerSalesRecord(int promoterId, int maxId) {
		return farmerDao.findNewInsertFarmerSalesRecord(promoterId, maxId);
	}

	@Override
	public void saveFarmerSalesRecord(List<FarmerSalesRecord> list) {
		farmerDao.saveFarmerSalesRecord(list);
	}

}
