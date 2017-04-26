package com.dowloyalty.service;

import java.util.List;

import com.dowloyalty.entity.InitPoints;
import com.dowloyalty.pojo.MessageVo;

public interface IMessagesService {
	/**
	 * 得特定状态的分页信息
	 * @param status
	 * @param startPage
	 * @param pageSize
	 * @return retailerName,messages.*
	 */
	public List<MessageVo> findMessagesInfoByStatus(Boolean status,int startPage,int pageSize);
	public int  getMessagesCountByStatus(Boolean status);
	/**
	 * 更新项目状态并记录执行adminid
	 * @param id
	 * @param executorId
	 */
	public void  updateStatusById(int id,int executorId);
	public int  insertInitpoints(InitPoints initPoints);
	public int  insertMessagesIncludeRetailerIdAndMessage(int retailerId,String message);
	/**
	 * 更新状态与初始积分表id，并记录执行人
	 * @param initPointsId
	 * @param executorId
	 * @param id
	 */
	public void  updateStatusAndInitPointsIdById(int initPointsId,int executorId,int id);
}
