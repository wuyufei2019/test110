package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IXfssJcDao;
import com.cczu.model.entity.XFSS_CheckEntity;
import com.cczu.model.service.IXfssJcService;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("XfssJcService")
public class XfssJcServiceImpl implements IXfssJcService {
	
	@Resource
	private IXfssJcDao xfssJcDao;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=xfssJcDao.dataGrid(mapData);
		int getTotalCount=xfssJcDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		xfssJcDao.deleteInfo(id);
	}

	@Override
	public XFSS_CheckEntity findById(Long id) {
		return xfssJcDao.findById(id);
	}
	
	@Override
	public Map<String,Object> findByIdForView(Long id) {
		return xfssJcDao.findByIdForView(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"消防设施名称","检查日期","检查内容","检查结论","检查人员","备注"};  
		String[] keys={"xfssnm","m1","m2","m3","jcnm","m4"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="消防设施检查信息.xls";
		List<Map<String, Object>> list=xfssJcDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
}
