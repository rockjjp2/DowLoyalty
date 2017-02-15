package com.dowloyalty.dao;

import com.dowloyalty.entity.Project;

public interface ProjectDao {
	
	/**
	 * 通过零售商id获取激活的积分活动
	 * @param rId 零售商id
	 * @return 积分活动对象
	 */
	public Project findActiveByRid(int rId);
}
