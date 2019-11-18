package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqzfJcbkDao;
import com.cczu.model.entity.AQZF_SafetyCheckItemEntity;
import com.cczu.model.service.IAqzfJcbkService;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("AqzfJcbkService")
@Transactional(readOnly=true)
public class AqzfJcbkServiceImpl implements IAqzfJcbkService {

	@Resource
	private IAqzfJcbkDao AqzfJcbkDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=AqzfJcbkDao.dataGrid(mapData);
		int getTotalCount=AqzfJcbkDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void addInfo(AQZF_SafetyCheckItemEntity jcbk) {
		jcbk.setS1(new Timestamp(System.currentTimeMillis()));
		jcbk.setS2(new Timestamp(System.currentTimeMillis()));
		jcbk.setS3(0);
		AqzfJcbkDao.addInfo(jcbk);
	}

	@Override
	public void deleteInfo(long id) {
		AqzfJcbkDao.deleteInfo(id);
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="检查表库记录表.xls";
		List<Map<String, Object>> list=AqzfJcbkDao.getExport(mapData);
		String[] title={"检查单元","检查内容","检查依据","备注"};  
		String[] keys={"m1","m2","m3","m4"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public AQZF_SafetyCheckItemEntity findById(Long id) {
		return AqzfJcbkDao.findInfoById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateInfo(AQZF_SafetyCheckItemEntity jcbk) {
		AQZF_SafetyCheckItemEntity a = findById(jcbk.getID());
		jcbk.setS1(a.getS1());
		jcbk.setS2(new Timestamp(System.currentTimeMillis()));
		jcbk.setM1(a.getM1());
	    AqzfJcbkDao.updateInfo(jcbk);
	}
}
