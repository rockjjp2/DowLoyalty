package com.dowloyalty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IPromoterDao;
import com.dowloyalty.entity.Promoter;
import com.dowloyalty.entity.RPromoterProvince;
import com.dowloyalty.pojo.PromoterVo;
import com.dowloyalty.service.IPromoterService;
import com.dowloyalty.utils.Page;

@Service
public class PromoterServiceImp implements IPromoterService {
	@Resource
	IPromoterDao iPromoterDao;


	@Override
	public boolean UserIsPromoter(String userId) {
		return iPromoterDao.findPromoterByUserId(userId)!=null?true:false;
	}

	@Override
	public Promoter findPromoterByUserId(String userId) {
		return iPromoterDao.findPromoterByUserId(userId);
	}

	@Override
	public List<Promoter> searchPromoterByPromoterName(int provinceId, String promoterName,int startNum) {
		return iPromoterDao.searchPromoterByPromoterName(provinceId, promoterName,(startNum-1)*(Page.ShOWNUM.getNum()-5), (Page.ShOWNUM.getNum()-5));
	}

	@Override
	public int findCountByProvinceId(int provinceId, String promoterName) {
		int count = iPromoterDao.findCountByProvinceId(provinceId,promoterName);
		int totalPageNum = count % (Page.ShOWNUM.getNum()-5) == 0 ? count / (Page.ShOWNUM.getNum()-5) : count / (Page.ShOWNUM.getNum()-5) + 1;
		return totalPageNum;
	}

	@Override
	public List<Promoter> findPromoterByProvinceId(int provinceId) {
		return iPromoterDao.findPromoterByProvinceId(provinceId);
	}

	@Override
	public List<Promoter> findAllPromotersByProjectId(String projectId) {
		return iPromoterDao.findAllPromotersByProjectId(projectId);
	}

	@Override
	public void patchInsertRelationBetweenPromoterAndProject(List<String> ids, String projectId) {
		iPromoterDao.patchInsertRelationBetweenPromoterAndProject(ids, projectId);
	}

	@Override
	public void patchDeleteRelationBetweenPromoterAndProject(List<String> ids, String projectId) {
		iPromoterDao.patchDeleteRelationBetweenPromoterAndProject(ids, projectId);
	}

	@Override
	public List<String> findAllPromoterByProjectId(String projectId) {
		return iPromoterDao.findAllPromoterByProjectId(projectId);
	}

	@Override
	public List<Promoter> findAllActivePromoters() {
		return iPromoterDao.findAllActivePromoters();
	}

	@Override
	public void update(PromoterVo promoter) {
		iPromoterDao.update(promoter);
	}

	@Override
	public void batchSave(List<PromoterVo> promoters) {
		iPromoterDao.batchSave(promoters);
	}

	@Override
	public List<RPromoterProvince> findAllPromoterAndProvinceRelation() {
		return iPromoterDao.findAllPromoterAndProvinceRelation();
	}

	@Override
	public void updatePromoterAndProvinceRelation(int promoterId, int provinceId) {
		iPromoterDao.updatePromoterAndProvinceRelation(promoterId, provinceId);
	}

	@Override
	public void batchSavePromoterAndProvinceRelation(List<PromoterVo> promoters) {
		iPromoterDao.batchSavePromoterAndProvinceRelation(promoters);
	}
	
}
 