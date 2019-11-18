package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqpxXxjlDao;
import com.cczu.model.entity.AQPX_StudyhistoryEntity;
import com.cczu.model.service.IAqpxXxjlService;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("AqpxXxjlService")
public class AqpxXxjlServiceImpl implements IAqpxXxjlService {

	@Resource
	private IAqpxXxjlDao aqpxxxdao;
	
	@Override
	public void xueshi(AQPX_StudyhistoryEntity as) {
		// TODO Auto-generated method stub
		aqpxxxdao.xueshi(as);
	}

	@Override
	public void addInfo(AQPX_StudyhistoryEntity as) {
		// TODO Auto-generated method stub
		aqpxxxdao.addInfo(as);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		aqpxxxdao.deleteInfo(id);
	}

	@Override
	public AQPX_StudyhistoryEntity findAll(Long qyid) {
		// TODO Auto-generated method stub
		return aqpxxxdao.findAll(qyid);
	}

	@Override
	public List<AQPX_StudyhistoryEntity> getlist(Long xyid, Long kcid) {
		// TODO Auto-generated method stub
		return aqpxxxdao.getlist(xyid, kcid);
	}

	@Override
	public void addshic(AQPX_StudyhistoryEntity as) {
		// TODO Auto-generated method stub
		aqpxxxdao.addshic(as);
	}

	@Override
	public void updateshic(AQPX_StudyhistoryEntity as) {
		// TODO Auto-generated method stub
		aqpxxxdao.updateshic(as);
	}

	@Override
	public AQPX_StudyhistoryEntity findsc(Long xyid, Long kcid) {
		// TODO Auto-generated method stub
		return aqpxxxdao.findsc(xyid, kcid);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqpxxxdao.dataGrid(mapData);
		int getTotalCount=aqpxxxdao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> dataGridCompanyAdmin(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqpxxxdao.dataGridCompanyAdmin(mapData);
		int getTotalCount=aqpxxxdao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="学习记录.xls";
		List<Map<String, Object>> list=aqpxxxdao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
}
