package com.cczu.model.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ITsDeviceChanelDao;
import com.cczu.model.entity.TS_DeviceChanel;
import com.cczu.model.service.ITsDeviceChanelService;

@Transactional(readOnly=true)
@Service("TsDeviceChanelService")
public class TsDeviceChanelServiceImpl implements ITsDeviceChanelService {
	@Resource
	private  ITsDeviceChanelDao tsDeviceChanelDao; 

	@Override
	public TS_DeviceChanel findDeviceChanelByContent(String deviceNo, String channelNo) {
		
		return tsDeviceChanelDao.findDeviceChanelByContent(deviceNo, channelNo);
	}

	@Override
	public Map<String, Object> findDeviceByID(long id) {
		// TODO Auto-generated method stub
		return tsDeviceChanelDao.findDeviceByID(id);
	}

}
