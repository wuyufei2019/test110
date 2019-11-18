package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqzfJcjhDao;
import com.cczu.model.entity.AQZF_SafetyCheckPlanEntity;
import com.cczu.model.service.IAqzfJcjhService;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("AqzfJcjhService")
@Transactional(readOnly=true)
public class AqzfJcjhServiceImpl implements IAqzfJcjhService {

	@Resource
	private IAqzfJcjhDao AqzfJcjhDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=AqzfJcjhDao.dataGrid(mapData);
		int getTotalCount=AqzfJcjhDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	@Override
	public void deleteInfo(long id) {
		AqzfJcjhDao.deleteInfo(id);
	}
	
	@Override
	public void addInfo(AQZF_SafetyCheckPlanEntity jcjh) {
		String[] ym = jcjh.getM2().split("-");
		jcjh.setM1(ym[0]);
		jcjh.setM2(ym[1]+"月");
		jcjh.setS1(new Timestamp(System.currentTimeMillis()));
		jcjh.setS2(new Timestamp(System.currentTimeMillis()));
		jcjh.setS3(0);
		AqzfJcjhDao.addInfo(jcjh);
	}

	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="检查计划记录表.xls";
		List<Map<String, Object>> list=AqzfJcjhDao.getExport(mapData);
		String[] title={"计划时间","企业名称","属地","行业类型","检查处室"};  
		String[] keys={"m1","qyname","m3","m4","m5"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}


	@Override
	public long addjcjh(AQZF_SafetyCheckPlanEntity jcjh) {
		String[] ym = jcjh.getM2().split("-");
		jcjh.setM1(ym[0]);
		jcjh.setM2(ym[1]+"月");
		jcjh.setS1(new Timestamp(System.currentTimeMillis()));
		jcjh.setS2(new Timestamp(System.currentTimeMillis()));
		jcjh.setS3(0);
		return AqzfJcjhDao.addjcjh(jcjh);
	}

	@Override
	public List<Map<String, Object>> findById(Long id) {
		return AqzfJcjhDao.findInfoById(id);
	}


	@Override
	public AQZF_SafetyCheckPlanEntity findById2(long id) {
		// TODO Auto-generated method stub
		return AqzfJcjhDao.findById(id);
	}


	@Override
	public void deletefa(long id1) {
		AqzfJcjhDao.deletefa(id1);
		
	}

	@Override
	public void deletejl(String faids) {
		AqzfJcjhDao.deletejl(faids);
		
	}
	
	@Override
	public List<Map<String, Object>> findJcfa(long id1) {
		// TODO Auto-generated method stub
		return AqzfJcjhDao.findJcfaById(id1);
	}


	@Override
	public int getfasl(long id1) {
		// TODO Auto-generated method stub
		return AqzfJcjhDao.getfasl(id1);
	}


	@Override
	public Map<String, Object> findHy(String id) {
		// TODO Auto-generated method stub
		return AqzfJcjhDao.findHy(id);
	}


}
