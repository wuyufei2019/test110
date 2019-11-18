package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqjdXzxkDao;
import com.cczu.model.entity.AQJD_AdministrativeEntity;
import com.cczu.model.service.IAqjdXzxkService;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("aqjdXzxkService")
public class AqjdXzxkServiceImpl implements IAqjdXzxkService {
	@Resource
	private IAqjdXzxkDao aqjdXzxkDao;

	@Override
	public List<AQJD_AdministrativeEntity> findAllByQyId(long id) {
		return aqjdXzxkDao.findAllByQyId(id);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<AQJD_AdministrativeEntity> list=aqjdXzxkDao.dataGrid(mapData);
		int getTotalCount=aqjdXzxkDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return aqjdXzxkDao.getTotalCount(mapData);
	}

	@Override
	public AQJD_AdministrativeEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return aqjdXzxkDao.findInfoById(id);
	}

	@Override
	public void saveInfo(AQJD_AdministrativeEntity aqjd) {
		// TODO Auto-generated method stub
		aqjdXzxkDao.saveInfo(aqjd);
	}

	@Override
	public Long returnBySqlID(AQJD_AdministrativeEntity aqjd) {
		// TODO Auto-generated method stub
		return aqjdXzxkDao.returnBySqlID(aqjd);
	}

	@Override
	public void updateInfo(AQJD_AdministrativeEntity aqjd) {
		// TODO Auto-generated method stub
		aqjdXzxkDao.updateInfo(aqjd);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqjdXzxkDao.deleteInfo(id);
	}

	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqjdXzxkDao.dataGrid2(mapData);
		int getTotalCount=aqjdXzxkDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="行政许可表.xls"; 
		List<Map<String, Object>> list=aqjdXzxkDao.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"许可名称","批准时间","到期时间","许可部门","许可内容  ","备注"};  
			String[] keys={"m1","m2","m3","m4","m5","m6"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","许可名称","批准时间","到期时间","许可部门","许可内容  ","备注"};
			String[] keys={"qynm","m1","m2","m3","m4","m5","m6"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				if(!mapData.get("colval").toString().equals("qynm")){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
			}
			new ExportExcel(fileName, title, keys, list, response, true);
		}
	}

	@Override
	public List<AQJD_AdministrativeEntity> findAllaj() {
		// TODO Auto-generated method stub
		return aqjdXzxkDao.findAllaj();
	}
}
