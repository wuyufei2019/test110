package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITsDeviceChanelDao;
import com.cczu.model.entity.TS_DeviceChanel;
import com.cczu.util.dao.BaseDao;

@Repository("TsDeviceChanelDao")
public class TsDeviceChanelDaoImpl  extends BaseDao<TS_DeviceChanel, Long> implements ITsDeviceChanelDao {

	@Override
	public TS_DeviceChanel findDeviceChanelByContent(String deviceNo, String channelNo) {
		String sql ="SELECT a.* from ts_devicechannel a left join ts_device b on a.ID1=b.ID  where a.channelno like '"+channelNo+"' and b.deviceno like '"+deviceNo+"'";
		List<TS_DeviceChanel> list=findBySql(sql, null,TS_DeviceChanel.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Object> findDeviceByID(long id) {
		String sql ="SELECT b.deviceno m1,a.channelno m2 from ts_devicechannel a left join ts_device b on a.ID1=b.ID  where a.ID="+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
