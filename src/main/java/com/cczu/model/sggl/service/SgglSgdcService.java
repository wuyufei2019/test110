package com.cczu.model.sggl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.sggl.dao.SgglSgdcDao;
import com.cczu.model.sggl.entity.SGGL_AccidentSurveyEntity;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("SgglSgdcService")
public class SgglSgdcService {
	
	@Resource
	private SgglSgdcDao sgglSgdcDao;

	
	public List<SGGL_AccidentSurveyEntity> findAll() {
		return sgglSgdcDao.findAllInfo();
	}

	
	public void addInfo(SGGL_AccidentSurveyEntity erm) {
		sgglSgdcDao.addInfo(erm);
		
	}

	
	public void updateInfo(SGGL_AccidentSurveyEntity erm) {
		sgglSgdcDao.updateInfo(erm);
	}
	
	
	public String content(Map<String, Object> mapData) {
		return sgglSgdcDao.content(mapData);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=sgglSgdcDao.dataGrid(mapData);
		int getTotalCount=sgglSgdcDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		sgglSgdcDao.deleteInfo(id);
	}

	
	public SGGL_AccidentSurveyEntity findById(Long id) {
		return sgglSgdcDao.findById(id);
	}

	public Map<String, Object> swrydataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=sgglSgdcDao.swrydataGrid(mapData);
		int getTotalCount=sgglSgdcDao.getswryTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void deleteSwry(long id) {
		sgglSgdcDao.deleteSwry(id);
	}
	
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"队伍名称","队伍类型","队伍地址","主要负责人","应急电话","主管部门","总人数","应急功能","成立时间","应对事故类型","备注"};
			String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="演练计划.xls";
			List<Map<String, Object>> list=sgglSgdcDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","队伍名称","队伍类型","队伍地址","主要负责人","应急电话","主管部门","总人数","应急功能","成立时间","应对事故类型","备注"};
			String[] keys={"qynm","m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="演练计划.xls";
			List<Map<String, Object>> list=sgglSgdcDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}
	}
	
}
