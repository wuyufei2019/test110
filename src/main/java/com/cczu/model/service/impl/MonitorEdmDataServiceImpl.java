package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IMonitorEdmDataDao;
import com.cczu.model.entity.Main_SignalStorageEntity;
import com.cczu.model.service.IMonitorEdmDataService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.util.dao.BaseDao;


@Service("MonitorEdmDataService")
@Transactional(readOnly=true)
public class MonitorEdmDataServiceImpl extends BaseDao<Main_SignalStorageEntity, Long> implements IMonitorEdmDataService {

	@Resource
	private IMonitorEdmDataDao monitorEdmDataDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=monitorEdmDataDao.dataGrid(mapData);
		int getTotalCount=monitorEdmDataDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String fileName="二道门人员数据记录表.xls";
		List<Map<String, Object>> list=monitorEdmDataDao.getExport(mapData);
		String[] title={"编号","企业名称","部门","实时人数"}; 
		String[] keys={"rownum","qyname","m1","m6"};
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public String qyListJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return JsonMapper.toJsonString(monitorEdmDataDao.qyList(mapData));
	}

	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> maplist= monitorEdmDataDao.findMapList(mapData);
		return maplist;
	}

	@Override
	public String getqylist(Map<String, Object> tmap) {
		return JsonMapper.toJsonString(monitorEdmDataDao.getqylistapp(tmap));
	}

	@Override
	public List<Map<String, Object>> findInfoById(long qyid) {
		// TODO Auto-generated method stub
		return monitorEdmDataDao.findInfoById(qyid);
	}
}
