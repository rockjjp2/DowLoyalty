package com.dowloyalty.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dowloyalty.pojo.AccountMapping;
@Component
public interface IAccountDao {


	public void insertAccountMapping(AccountMapping accountMapping);
	public String findLastModifiedDate();
	public AccountMapping findAccountMappingById(String id);
	public void updateAccountMappingById(AccountMapping accountMapping);
	public List<AccountMapping> findUpdateAccount();
	public List<AccountMapping> findNewAccount();
	public void releaseAccount(String id);
}
