package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.SbglSbyfxbyglDao;
import com.cczu.model.entity.Sbgl_SbyfxbyglEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

@Service("SbglSbyfxbyglService")
public class SbglSbyfxbyglService {

	@Resource
	SbglSbyfxbyglDao sbglSbyfxbyglDao;
	

	public void addInfo(Sbgl_SbyfxbyglEntity entity) {
		Timestamp t = DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		entity.setQyid(UserUtil.getCurrentShiroUser().getQyid());
		sbglSbyfxbyglDao.save(entity);
	}

	public void updateInfo(Sbgl_SbyfxbyglEntity entity) {
		sbglSbyfxbyglDao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sbglSbyfxbyglDao.dataGrid(mapData);
		int getTotalCount=sbglSbyfxbyglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		//删除项目信息
		sbglSbyfxbyglDao.deleteInfo(id);
	}

	public Sbgl_SbyfxbyglEntity findById(long id) {
		return sbglSbyfxbyglDao.findById(id);
	}
	
	//导出
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="预防性保养管理表.xls";
		List<Map<String, Object>> list=sbglSbyfxbyglDao.getExport(mapData);
		String[] title={"设备名称","维护项目","维护要求","维护方法","维护周期","操作者","维修者"};  
		String[] keys={"sbname","m1","m2","m3","m4","m5","m6"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response);
	}
}
