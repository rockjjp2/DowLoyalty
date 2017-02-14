package com.dowloyalty.service;

public interface ProjectService {
	
	/**
	 * 通过零售商id获取激活的积分活动
	 * @param rId 零售商id
	 * @return 积分活动id
	 */
	public String findActiveByRid(int rId);
}
