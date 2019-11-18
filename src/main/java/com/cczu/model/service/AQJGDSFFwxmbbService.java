package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.AQJGDSFFwxmbbDao;
import com.cczu.model.entity.AQJG_DSFFwxmbbEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("AQJGDSFFwxmbbService")
public class AQJGDSFFwxmbbService {
	@Resource
	private AQJGDSFFwxmbbDao bbDao;
	
	public List<AQJG_DSFFwxmbbEntity> findAll(long userid) {
		return bbDao.findAll(userid);
	}


	public void addInfo(AQJG_DSFFwxmbbEntity entity) {
		bbDao.save(entity);
		
	}


	public void updateInfo(AQJG_DSFFwxmbbEntity entity) {
		bbDao.save(entity);
	}


	public Page<AQJG_DSFFwxmbbEntity> search(Page<AQJG_DSFFwxmbbEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}


	public Map<String, Object> dataGridAQ(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=bbDao.dataGridAQ(mapData);
		int getTotalCount=bbDao.getTotalCountAQ(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=bbDao.dataGrid(mapData);
		int getTotalCount=bbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bbDao.deleteInfo(id);
	}


	public AQJG_DSFFwxmbbEntity findById(long id) {
		
		return bbDao.findById(id);
	}

	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="第三方服务项目报备信息表.xls";
		List<Map<String, Object>> list=bbDao.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"第三方服务单位名称","项目类型","项目名称","服务项目内容","项目负责人","项目合同资金","开始时间","结束时间"};  
			String[] keys={"dwname","m1","m2","m3","m4","m5","m6","m7"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业名称","第三方服务单位名称","项目类型","项目名称","服务项目内容","项目负责人","项目合同资金","开始时间","结束时间"};  
			String[] keys={"qyname","dwname","m1","m2","m3","m4","m5","m6","m7"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				if(!mapData.get("colval").toString().equals("qyname")){
					title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
					keys = ("qyname,"+mapData.get("colval").toString()).split(",") ;
				}
			}
			new ExportExcel(fileName, title, keys, list, response, true);
		}
	}
}
