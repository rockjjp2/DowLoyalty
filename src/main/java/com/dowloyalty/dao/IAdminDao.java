package com.dowloyalty.dao;

import org.springframework.stereotype.Component;

import com.dowloyalty.entity.Admin;
@Component
public interface IAdminDao {
	public Admin findAdminByUserId(String userId);
}
