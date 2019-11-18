package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ISekbWhpaqxxDao;
import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;
import com.cczu.model.service.ISekbWhpaqxxService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("SekbWhpaqxxService")
public class SekbWhpaqxxServiceImpl implements ISekbWhpaqxxService {
	
	@Resource
	private ISekbWhpaqxxDao sekbWhpaqxxDao;

	@Override
	public Page<TMESK_ChemicalsdirectoryEntity> search(Page<TMESK_ChemicalsdirectoryEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}

	
	
	@Override
	public String content(Map<String, Object> mapData) {
		return sekbWhpaqxxDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<TMESK_ChemicalsdirectoryEntity> list=sekbWhpaqxxDao.dataGrid(mapData);
		int getTotalCount=sekbWhpaqxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public TMESK_ChemicalsdirectoryEntity findById(Long id) {
		return sekbWhpaqxxDao.findById(id);
	}



	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"品名","别名","英文名","CAS","危险性类别","是否为重点监管危险化学品","是否为剧毒化学品","是否为易制毒化学品","备注"}; 
		String[] keys={"m2","m3","m4","m5","m6","m8","m9","m10","m7"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="化学品危险性类别.xls";
		List<Map<String, Object>> list=sekbWhpaqxxDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
}
