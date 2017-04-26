package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IAccountDao;
import com.dowloyalty.dao.IAdminDao;
import com.dowloyalty.entity.Admin;
import com.dowloyalty.pojo.AccountMapping;
import com.dowloyalty.service.IAdminService;
@Service
public class IAdminServiceImpl implements IAdminService {
	@Resource
	IAccountDao iAccountDao;
	@Resource
	IAdminDao iAdminDao;
	@Override
	public void insertAccountMapping(AccountMapping accountMapping) {
		iAccountDao.insertAccountMapping(accountMapping);
	}

	@Override
	public String findLastModifiedDate() {
		return iAccountDao.findLastModifiedDate();
	}

	@Override
	public AccountMapping findAccountMappingById(String id) {
		return iAccountDao.findAccountMappingById(id);
	}

	@Override
	public void updateAccountMappingById(AccountMapping accountMapping) {
		iAccountDao.updateAccountMappingById(accountMapping);
	}

	@Override
	public List<AccountMapping> findUpdateAccount() {
		return iAccountDao.findUpdateAccount();
	}

	@Override
	public List<AccountMapping> findNewAccount() {
		return iAccountDao.findNewAccount();
	}

	@Override
	public void releaseAccount(String id) {
		iAccountDao.releaseAccount(id);
	}

	@Override
	public Admin findAdminByUserId(String userId) {
		return iAdminDao.findAdminByUserId(userId);
	}

}
