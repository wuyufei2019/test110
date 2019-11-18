package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.AqjgZXGLHmdDao;
import com.cczu.model.entity.AQJG_ZXGLHmdEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("AqjgZXGLHmdService")
public class AqjgZXGLHmdService {

	@Resource
	AqjgZXGLHmdDao hmdDao;
	
	public List<AQJG_ZXGLHmdEntity> findAll(long userid) {
		return hmdDao.findAll(userid);
	}


	public void addInfo(AQJG_ZXGLHmdEntity entity) {
		hmdDao.save(entity);
		
	}


	public void updateInfo(AQJG_ZXGLHmdEntity entity) {
		hmdDao.save(entity);
	}


	public Page<AQJG_ZXGLHmdEntity> search(Page<AQJG_ZXGLHmdEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}


	public Map<String, Object> dataGridAQ(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=hmdDao.dataGridAQ(mapData);
		int getTotalCount=hmdDao.getTotalCountAQ(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<AQJG_ZXGLHmdEntity> list=hmdDao.dataGrid(mapData);
		int getTotalCount=hmdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		hmdDao.deleteInfo(id);
	}


	public AQJG_ZXGLHmdEntity findById(long id) {
		
		return hmdDao.findById(id);
	}
	
	public Map<String, Object> findById2(long id) {
		
		return hmdDao.findById2(id);
	}

	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="黑名单信息表.xls";
		List<Map<String, Object>> list=hmdDao.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"企业名称","黑名单行为","黑名单行为描述","黑名单行为级别","开始时间","备注"};  
			String[] keys={"qyname","m1","m2","m3","m5","m7"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业名称","黑名单行为","黑名单行为描述","黑名单行为级别","开始时间","备注"};  
			String[] keys={"qyname","m1","m2","m3","m5","m7"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				if(!mapData.get("colval").toString().equals("qyname")){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
			}
			new ExportExcel(fileName, title, keys, list, response, true);
		}
	}


	public  List<Map<String, Object>> findHmnamelist(){
		return hmdDao.findHmnmList();
	}
}
