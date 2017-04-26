package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.Admin;
import com.dowloyalty.pojo.AccountMapping;

public interface IAdminService {
	public void insertAccountMapping(AccountMapping accountMapping);
	public String findLastModifiedDate();
	public AccountMapping findAccountMappingById(String id);
	public void updateAccountMappingById(AccountMapping accountMapping);
	public List<AccountMapping> findUpdateAccount();
	public List<AccountMapping> findNewAccount();
	public void releaseAccount(String id);
	

	public Admin findAdminByUserId(String userId);
}
