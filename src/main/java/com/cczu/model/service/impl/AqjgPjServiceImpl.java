package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqjgCfjlDao;
import com.cczu.model.dao.IAqjgPjDao;
import com.cczu.model.entity.AQJG_DSFPjEntity;
import com.cczu.model.service.IAqjgPjService;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("AqjgPjService")
@Transactional(readOnly=true)
public class AqjgPjServiceImpl implements IAqjgPjService {
	
	@Resource
	private IAqjgPjDao AqjgPjDao;
	
	@Resource
	private IAqjgCfjlDao AqjgCfjlDao;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=AqjgPjDao.dataGrid(mapData);
		int getTotalCount=AqjgPjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public List<Map<String, Object>> findNdList() {
		return AqjgPjDao.findNdList();
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		AqjgPjDao.deleteInfo(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="第三方评价表.xls";
		List<Map<String, Object>> list=AqjgPjDao.getExport(mapData);
		String[] title={"单位名称","评价","年度","评价人","备注"};  
		String[] keys={"m1","m2","m4","m5","m6"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}

	@Override
	public List<Map<String, Object>> getdname() {
		return AqjgPjDao.getdname();
	}

	@Override
	public List<Map<String, Object>> getdname2() {
		return AqjgPjDao.getdname2();
	}
	
	@Override
	public void addInfo(AQJG_DSFPjEntity pjlist) {
		Date d = new Date();
		pjlist.setM3(new Timestamp(d.getTime()));
		pjlist.setM4(d.getYear()+1900);
		AqjgPjDao.addInfo(pjlist);
	}

	@Override
	public AQJG_DSFPjEntity findById(Long id) {
		return AqjgPjDao.findById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void updateInfo(AQJG_DSFPjEntity pjlist) {
		AQJG_DSFPjEntity pj = AqjgPjDao.findById(pjlist.getID());
		pj.setM2(pjlist.getM2());
		pj.setM3(new Timestamp((new Date()).getTime()));
		pj.setM5(pjlist.getM5());
		pj.setM6(pjlist.getM6());
		AqjgPjDao.updateInfo(pj);
	}

	

}
