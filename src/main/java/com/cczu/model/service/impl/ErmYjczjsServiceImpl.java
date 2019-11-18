package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ISekbMsdsDao;
import com.cczu.model.entity.TMESK_MsdsEntity;
import com.cczu.model.service.IErmYjczjsService;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("ErmYjczjsService")
public class ErmYjczjsServiceImpl implements IErmYjczjsService {
	
	@Resource
	private ISekbMsdsDao sekbMsdsDao;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<TMESK_MsdsEntity> list=sekbMsdsDao.dataGrid(mapData);
		int getTotalCount=sekbMsdsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	@Override
	public Map<String,Object> findById(Long id) {
		TMESK_MsdsEntity tc = sekbMsdsDao.findById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("m1", tc.getM1());
		map.put("m2", "健康危害:"+ tc.getM10() + ",环境危害:"+ tc.getM11() + ",燃爆危害:" + tc.getM12());
		map.put("m3", tc.getM20());
		return map;
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"化学品名称","主要危险性","事故应急处置技术"};  
		String[] keys={"m1","m2","m3"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="应急处置技术.xls";
		List<Map<String, Object>> list=sekbMsdsDao.getExcelDataWhp(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
	
}
