package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IXwaqBaqxwglDao;
import com.cczu.model.entity.XWAQ_UnsafebehaviorEntity;
import com.cczu.model.service.IXwaqBaqxwglService;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("XwaqBaqxwglService")
public class XwaqBaqxwglServiceImpl implements IXwaqBaqxwglService {
	
	@Resource
	private IXwaqBaqxwglDao xwaqBaqxwglDao;

	@Override
	public void addInfo(XWAQ_UnsafebehaviorEntity xwaq) {
		xwaqBaqxwglDao.addInfo(xwaq);
		
	}

	@Override
	public void updateInfo(XWAQ_UnsafebehaviorEntity xwaq) {
		xwaqBaqxwglDao.updateInfo(xwaq);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=xwaqBaqxwglDao.dataGrid(mapData);
		int getTotalCount=xwaqBaqxwglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		xwaqBaqxwglDao.deleteInfo(id);
	}

	@Override
	public XWAQ_UnsafebehaviorEntity findById(Long id) {
		return xwaqBaqxwglDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"不安全行为类型","行为描述","备注"};  
		String[] keys={"m1","m2","m3"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="不安全行为管理.xls";
		List<Map<String, Object>> list=xwaqBaqxwglDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
}
