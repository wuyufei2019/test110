package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.AqjgZXGLSxjlDao;
import com.cczu.model.entity.AQJG_ZXGLSxjlEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("AqjgZXGLSxjlService")
public class AqjgZXGLSxjlService {
	@Resource
	AqjgZXGLSxjlDao sxjlDao;
	
	public List<AQJG_ZXGLSxjlEntity> findAll(long userid) {
		return sxjlDao.findAll(userid);
	}


	public void addInfo(AQJG_ZXGLSxjlEntity entity) {
		sxjlDao.save(entity);
		
	}


	public void updateInfo(AQJG_ZXGLSxjlEntity entity) {
		sxjlDao.save(entity);
	}


	public Page<AQJG_ZXGLSxjlEntity> search(Page<AQJG_ZXGLSxjlEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}


	public Map<String, Object> dataGridAQ(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=sxjlDao.dataGridAQ(mapData);
		int getTotalCount=sxjlDao.getTotalCountAQ(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<AQJG_ZXGLSxjlEntity> list=sxjlDao.dataGrid(mapData);
		int getTotalCount=sxjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public  List<Map<String, Object>> findSxnamelist(){
		return sxjlDao.findSxnmList();
	}

	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		sxjlDao.deleteInfo(id);
	}


	public AQJG_ZXGLSxjlEntity findById(long id) {
		
		return sxjlDao.findById(id);
	}

	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="失信行为信息表.xls";
		List<Map<String, Object>> list=sxjlDao.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"企业名称","失信行为","失信行为面熟","失信行为级别","失信时间起","备注"};  
			String[] keys={"qyname","m1","m2","m3","m5","m7"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业名称","失信行为","失信行为面熟","失信行为级别","失信时间起","备注"};  
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
}
