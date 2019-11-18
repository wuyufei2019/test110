package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ISekbSgalDao;
import com.cczu.model.entity.TMESK_AccidentEntity;
import com.cczu.model.service.ISekbSgalService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("SekbSgalService")
public class SekbSgalServiceImpl implements ISekbSgalService {
	
	@Resource
	private ISekbSgalDao sekbSgalDao;

	@Override
	public TMESK_AccidentEntity findAll() {
		return sekbSgalDao.findAllInfo();
	}

	@Override
	public void addInfo(TMESK_AccidentEntity sekb) {
		sekbSgalDao.addInfo(sekb);
		
	}

	@Override
	public void updateInfo(TMESK_AccidentEntity sekb) {
		sekbSgalDao.updateInfo(sekb);
	}

	@Override
	public Page<TMESK_AccidentEntity> search(Page<TMESK_AccidentEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}

	
	
	@Override
	public String content(Map<String, Object> mapData) {
		return sekbSgalDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<TMESK_AccidentEntity> list=sekbSgalDao.dataGrid(mapData);
		int getTotalCount=sekbSgalDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		sekbSgalDao.deleteInfo(id);
	}

	@Override
	public TMESK_AccidentEntity findById(Long id) {
		return sekbSgalDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"类别","标题","正文","备注"};
		String[] keys={"m1","m2","m3","m4"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="事故案例.xls";
		List<Map<String, Object>> list=sekbSgalDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
}
