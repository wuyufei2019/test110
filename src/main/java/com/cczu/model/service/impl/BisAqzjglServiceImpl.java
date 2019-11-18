package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.BIS_DirectorEntity;
import com.cczu.model.dao.IBisAqzjglDao;
import com.cczu.model.service.IBisAqzjglService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.dao.UserDao;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("IBisAqzjglService")
public class BisAqzjglServiceImpl implements IBisAqzjglService {
	@Resource
	private IBisAqzjglDao bisAqzjgl;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_DirectorEntity> list=bisAqzjgl.dataGrid(mapData);
		int getTotalCount=bisAqzjgl.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> dataGridAJ(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=bisAqzjgl.dataGridAJ(mapData);
		int getTotalCount=bisAqzjgl.getTotalCountAJ(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public BIS_DirectorEntity findInfoById(long id) {
		return bisAqzjgl.findInfoById(id);
	}

	@Override
	public Long addInfo(BIS_DirectorEntity bis) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		bis.setID1(sessionuser.getId());
		bis.setS1(t);
		bis.setS2(t);
		bis.setS3(0);
		bis.setID1(sessionuser.getQyid());
		bis.setID2(sessionuser.getXzqy());
		bis.setID3(sessionuser.getId());
		
		long l=bisAqzjgl.addInfore(bis);
		if(l>0){
			return l;
		}else{
			return (long) 0;
		}
	}
	
	@Override
	public void updateInfo(BIS_DirectorEntity bis) {
		Timestamp t=DateUtils.getSysTimestamp();
		bis.setS2(t);
		bis.setS3(0);
		bisAqzjgl.updateInfo(bis);
	}
	
	@Override
	public void spupdateInfo(BIS_DirectorEntity bis) {
		Timestamp t=DateUtils.getSysTimestamp();
		bis.setS2(t);
		bis.setM11(t);
		bisAqzjgl.updateInfo(bis);
	}

	@Override
	public void deleteInfo(long id) {
		bisAqzjgl.deleteInfo(id);
	}


	@Override
	public List<BIS_DirectorEntity> findAll() {
		return bisAqzjgl.findAlllist();
	}

	@Override
	public List<BIS_DirectorEntity> findAllByUserId(long userid) {
		return null;
	}

	@Override
	public String getQyJson(Map<String, Object> map) {
		List<Map<String, Object>> list=bisAqzjgl.dirHaveAllQyList(map);
		return JsonMapper.getInstance().toJson(list);
	}
	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="安全总监管理表.xls"; 
		List<Map<String, Object>> list=bisAqzjgl.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"姓名","性别","出生年月","学历","专业","职称","工作年限","聘用开始时间","聘用结束时间","附件","安监局审核意见","安监局审批时间","备注","审核状态"};
			String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m8_1","m9","m10","m11","m12","m13"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","姓名","性别","出生年月","学历","专业","职称","工作年限","聘用开始时间","聘用结束时间","附件","安监局审核意见","安监局审批时间","备注","审核状态"};
			String[] keys={"qynm","m1","m2","m3","m4","m5","m6","m7","m8","m8_1","m9","m10","m11","m12","m13"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				if(!mapData.get("colval").toString().equals("qynm")){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
			}
			new ExportExcel(fileName, title, keys, list, response, true);
		}
	}
}
