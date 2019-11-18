package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IXwaqGctjfxDao;
import com.cczu.model.service.IXwaqGctjfxService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.dao.DepartmentDao;
import com.cczu.sys.system.entity.Department;


@Transactional(readOnly=true)
@Service("XwaqGctjfxService")
public class XwaqGctjfxServiceImpl implements IXwaqGctjfxService {
	@Resource
	private IXwaqGctjfxDao xwaqGctjfxDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=xwaqGctjfxDao.dataGrid(mapData);
		return list;
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		if("2".equals(mapData.get("con_type").toString())){
			String[] title={"部门","员工","不安全行为数量","不安全状态数量","不安全行为/小时","不安全状态/小时"};  
			String[] keys={"bmnm","ygnm","totalsl","totalzt","hourcnt1","hourcnt2"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="观察统计分析.xls";
			List<Map<String, Object>> list=xwaqGctjfxDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"观察类别","轻伤 ","重伤","死亡","其他事故","不安全行为数量","不安全状态数量","不安全行为/小时","不安全状态/小时"};  
			String[] keys={"xwlx","hasqs","haszs","hassw","hasqt","totalsl","totalzt","hourcnt1","hourcnt2"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="观察统计分析.xls";
			List<Map<String, Object>> list=xwaqGctjfxDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}
	}
	
	@Override
	public String findQybmList(Long qyid) {
		List<Department> list=departmentDao.findInfoByQyId(qyid);
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for(Department dp:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", dp.getId());
			map.put("text", dp.getM1());
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}
}
