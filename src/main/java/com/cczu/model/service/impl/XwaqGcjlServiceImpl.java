package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IXwaqGcjlDao;
import com.cczu.model.entity.XWAQ_ObservationsEntity;
import com.cczu.model.service.IXwaqGcjlService;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("XwaqGcjlService")
public class XwaqGcjlServiceImpl implements IXwaqGcjlService {
	
	@Resource
	private IXwaqGcjlDao xwaqGcjlDao;

	@Override
	public void addInfo(XWAQ_ObservationsEntity xwaq) {
		xwaqGcjlDao.addInfo(xwaq);
		
	}

	@Override
	public void updateInfo(XWAQ_ObservationsEntity xwaq) {
		xwaqGcjlDao.updateInfo(xwaq);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=xwaqGcjlDao.dataGrid(mapData);
		int getTotalCount=xwaqGcjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		xwaqGcjlDao.deleteInfo(id);
	}

	@Override
	public XWAQ_ObservationsEntity findById(Long id) {
		return xwaqGcjlDao.findById(id);
	}

	
	@Override
	public Map<String,Object> findByIdForView(Long id) {
		return xwaqGcjlDao.findByIdForView(id);
	}
	
	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"不安全行为类型","行为描述","有无不安全行为","伤害","数量","观察时间","备注","部门","员工"};  
		String[] keys={"xwlx","xwms","m1","m2","m3","m4","m5","bmnm","ygnm"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="观察记录.xls";
		List<Map<String, Object>> list=xwaqGcjlDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
}
