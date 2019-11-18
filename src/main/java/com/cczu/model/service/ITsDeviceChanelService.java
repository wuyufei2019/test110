package com.cczu.model.service;

import java.util.Map;

import com.cczu.model.entity.TS_DeviceChanel;

public interface ITsDeviceChanelService {
	
	/**
	 * 根据设备编号和信道号码查询 TS_DeviceChanel
	 * @param deviceNo 设备编号
	 * @param channelNo 信道号码
	 * @return
	 */
	public TS_DeviceChanel findDeviceChanelByContent(String deviceNo,String channelNo);
	
	/**
	 * 根据TS_DeviceChanel ID 查询设备编号和信道号码
	 * @param id
	 * @return
	 */
	public Map<String, Object> findDeviceByID(long id);
	
}
