package com.dowloyalty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dowloyalty.dao.IMessagesDao;
import com.dowloyalty.entity.InitPoints;
import com.dowloyalty.pojo.MessageVo;
import com.dowloyalty.service.IMessagesService;
@Service
public class IMessagesServiceImpl implements IMessagesService {
	@Resource
	IMessagesDao iMessagesDao;
	
	@Override
	public List<MessageVo> findMessagesInfoByStatus(Boolean status,int startPage,int pageSize) {
		if (startPage>1) {
			startPage=(startPage-1)*pageSize;
		}else {
			startPage=0;
		}
		return iMessagesDao.findMessagesInfoByStatus(status, startPage, pageSize);
	}

	@Override
	public int getMessagesCountByStatus(Boolean status) {
		return iMessagesDao.getMessagesCountByStatus(status);
	}

	@Override
	public void updateStatusById(int id,int executorId) {
		iMessagesDao.updateStatusById(id,executorId);
	}

	@Override
	public int insertInitpoints(InitPoints initPoints) {
		return iMessagesDao.insertInitpoints(initPoints);
	}

	@Override
	public void updateStatusAndInitPointsIdById(int initPointsId,int executorId, int id) {
		iMessagesDao.updateStatusAndInitPointsIdById(initPointsId,executorId, id);
	}

	@Override
	public int insertMessagesIncludeRetailerIdAndMessage(int retailerId, String message) {
		return iMessagesDao.insertMessagesIncludeRetailerIdAndMessage(retailerId, message);
	}
}
