package com.dowloyalty.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dowloyalty.entity.InitPoints;
import com.dowloyalty.pojo.MessageVo;

public interface IMessagesDao {
	
	public List<MessageVo> findMessagesInfoByStatus(@Param("status")Boolean status,
			@Param("startPage")int startPage,@Param("pageSize")int pageSize);
	public int  getMessagesCountByStatus(Boolean status);
	public void  updateStatusById(@Param("id")int id,@Param("executorId")int executorId);
	public int  insertInitpoints(InitPoints initPoints);
	public int  insertMessagesIncludeRetailerIdAndMessage(@Param("retailerId")int retailerId,@Param("message")String message);
	public void  updateStatusAndInitPointsIdById(@Param("initPointsId")int initPointsId,@Param("executorId")int executorId,@Param("id")int id);
}
