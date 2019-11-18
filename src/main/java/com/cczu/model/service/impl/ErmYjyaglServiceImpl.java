package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IErmYjyaglDao;
import com.cczu.model.entity.AQJG_SafetyRecord;
import com.cczu.model.service.IErmYjyaglService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("ErmYjyaglService")
public class ErmYjyaglServiceImpl implements IErmYjyaglService {
	
	@Resource
	private IErmYjyaglDao ermYjyaglDao;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=ermYjyaglDao.dataGrid(mapData);
		int getTotalCount=ermYjyaglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public AQJG_SafetyRecord findById(Long id) {
		return ermYjyaglDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Map<String, Object> mapData) {
		// TODO Auto-generated method stub		
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"备案编号","备案日期","备案经手人","备案意见","备注"};  
			String[] keys={"m1","m2","m7","m5","m8"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="应急预案管理表.xls";
			List<Map<String, Object>> list=ermYjyaglDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			if("1".equals(mapData.get("cxtype").toString())){
				String[] title={"备案编号","备案日期","备案经手人","备案意见","备注"};  
				String[] keys={"m1","m2","m7","m5","m8"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
				String fileName="应急预案管理表.xls";
				List<Map<String, Object>> list=ermYjyaglDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response);
			}else{
				String[] title={"企业","备案编号","备案日期","备案经手人","备案意见","备注"};  
				String[] keys={"qynm","m1","m2","m7","m5","m8"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					if(!mapData.get("colval").toString().equals("qynm")){
						title =("企业名称,"+mapData.get("coltext").toString()).split(",") ;
						keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
					}
				}
				String fileName="应急预案管理表.xls";
				List<Map<String, Object>> list=ermYjyaglDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response, true);
			}
		}
	}
	
	@Override
	public String getqylistapp(Map<String, Object> tmap) {
		return JsonMapper.toJsonString(ermYjyaglDao.getqylistapp(tmap));
	}
}
