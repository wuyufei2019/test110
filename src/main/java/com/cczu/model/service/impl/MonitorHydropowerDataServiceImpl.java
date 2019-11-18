package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IMonitorHydropowerDataDao;
import com.cczu.model.entity.Main_SignalStorageEntity;
import com.cczu.model.service.IMonitorHydropowerDataService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.util.dao.BaseDao;

@Service("MonitorHydropowerService")
@Transactional(readOnly=true)
public class MonitorHydropowerDataServiceImpl extends BaseDao<Main_SignalStorageEntity, Long> implements IMonitorHydropowerDataService {

	@Resource
	private IMonitorHydropowerDataDao monitorHydropowerDataDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorHydropowerDataDao.dataGrid(mapData);
		int getTotalCount=monitorHydropowerDataDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="水电气用量记录表.xls";
		List<Map<String, Object>> list=monitorHydropowerDataDao.getExport(mapData);
		String[] title={"编号","企业名称","水表（m³）","电表（kWh）","蒸气（m³）"}; 
		String[] keys={"rownum","qyname","sb","db","zq"};
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public String qyListJson(Map<String, Object> mapData) {
		return JsonMapper.toJsonString(monitorHydropowerDataDao.qyList(mapData));
	}

}
