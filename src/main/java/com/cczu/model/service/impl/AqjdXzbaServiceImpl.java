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

import com.cczu.model.entity.AQJD_BeiAnEntity;
import com.cczu.model.dao.IAqjdXzbaDao;
import com.cczu.model.service.IAqjdXzbaService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("AqjdXzbaService")
public class AqjdXzbaServiceImpl implements IAqjdXzbaService {
	@Resource
	private IAqjdXzbaDao aqjdXzba;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<AQJD_BeiAnEntity> list=aqjdXzba.dataGrid(mapData);
		int getTotalCount=aqjdXzba.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public AQJD_BeiAnEntity findInfoById(long id) {
		return aqjdXzba.findInfoById(id);
	}

	@Override
	public void addInfo(AQJD_BeiAnEntity aqjd) {
		Timestamp t=DateUtils.getSysTimestamp();
		aqjd.setS1(t);
		aqjd.setS2(t);
		aqjd.setS3(0);
		aqjdXzba.saveInfo(aqjd);
	}

	@Override
	public Long addInforeturnID(AQJD_BeiAnEntity aqjd) {
		return aqjdXzba.returnBySqlID(aqjd);
	}
	
	@Override
	public void updateInfo(AQJD_BeiAnEntity aqjd) {
		Timestamp t=DateUtils.getSysTimestamp();
		aqjd.setS2(t);
		aqjdXzba.updateInfo(aqjd);
	}

	@Override
	public void deleteInfo(long id) {
		AQJD_BeiAnEntity aqjd=aqjdXzba.findInfoById(id);
		aqjd.setS3(1);
		aqjdXzba.saveInfo(aqjd);
	}

	@Override
	public List<AQJD_BeiAnEntity> findListInfoByQyId(long qyid) {
		return aqjdXzba.findAllByQyId(qyid);
	}

	@Override
	public List<AQJD_BeiAnEntity> findAll() {
		return aqjdXzba.findAlllist();
	}

	@Override
	public List<AQJD_BeiAnEntity> findAllByUserId(long userid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqjdXzba.dataGrid2(mapData);
		int getTotalCount=aqjdXzba.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="行政备案表.xls"; 
		List<Map<String, Object>> list=aqjdXzba.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"备案名称","备案时间","有效期至","备案部门","备注"};  
			String[] keys={"m1","m2","m3","m4","m5"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","备案名称","备案时间","有效期至","备案部门","备注"};
			String[] keys={"qynm","m1","m2","m3","m4","m5"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				if(!mapData.get("colval").toString().equals("qynm")){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
			}
			new ExportExcel(fileName, title, keys, list, response, true);
		}
	}

	@Override
	public List<AQJD_BeiAnEntity> findAllaj() {
		// TODO Auto-generated method stub
		return aqjdXzba.findAllaj();
	}

	@Override
	public List<AQJD_BeiAnEntity> findAllqy(Long qyid) {
		// TODO Auto-generated method stub
		return aqjdXzba.findAllqy(qyid);
	}
}
