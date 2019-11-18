package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqjgCfjlDao;
import com.cczu.model.entity.AQJG_DSFCfjlEntity;
import com.cczu.model.service.IAqjgCfjlService;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("AqjgCfjlService")
@Transactional(readOnly=true)
public class AqjgCfjlServiceImpl implements IAqjgCfjlService {

	@Resource
	private IAqjgCfjlDao AqjgCfjlDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=AqjgCfjlDao.dataGrid(mapData);
		int getTotalCount=AqjgCfjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void addInfo(AQJG_DSFCfjlEntity cfjl) {
		long m1 = AqjgCfjlDao.getDsfid(cfjl.getDname());
		cfjl.setM1(m1);
		cfjl.setS3(0);
		cfjl.setS2(cfjl.getS1());
		AqjgCfjlDao.addInfo(cfjl);
	}

	@Override
	public void deleteInfo(long id) {
		AqjgCfjlDao.deleteInfo(id);
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="第三方处罚记录表.xls";
		List<Map<String, Object>> list=AqjgCfjlDao.getExport(mapData);
		String[] title={"单位名称","处罚类型","处罚内容","备注","处罚时间"};  
		String[] keys={"m1","m2","m3","m5","s1"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public AQJG_DSFCfjlEntity findById(Long id) {
		return AqjgCfjlDao.findInfoById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateInfo(AQJG_DSFCfjlEntity cfjllist) {
		AQJG_DSFCfjlEntity cfjl = AqjgCfjlDao.findInfoById(cfjllist.getID());
		cfjl.setS1(cfjllist.getS1());
		cfjl.setS2(cfjllist.getS1());
	    cfjl.setM2(cfjllist.getM2());
	    cfjl.setM3(cfjllist.getM3());
	    cfjl.setM5(cfjllist.getM5());
		AqjgCfjlDao.updateInfo(cfjl);
	}
}
