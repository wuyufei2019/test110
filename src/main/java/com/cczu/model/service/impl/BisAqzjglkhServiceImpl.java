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

import com.cczu.model.entity.BIS_DirectorAssessEntity;
import com.cczu.model.dao.IBisAqzjglkhDao;
import com.cczu.model.service.IBisAqzjglkhService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.dao.UserDao;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("IBisAqzjglkhService")
public class BisAqzjglkhServiceImpl implements IBisAqzjglkhService {
	@Resource
	private IBisAqzjglkhDao bisAqzjglkh;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_DirectorAssessEntity> list=bisAqzjglkh.dataGrid(mapData);
		int getTotalCount=bisAqzjglkh.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> dataGridAJ(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=bisAqzjglkh.dataGridAJ(mapData);
		int getTotalCount=bisAqzjglkh.getTotalCountAJ(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public BIS_DirectorAssessEntity findInfoById(long id) {
		return bisAqzjglkh.findInfoById(id);
	}

	@Override
	public Long addInfo(BIS_DirectorAssessEntity bis) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		bis.setS1(t);
		bis.setS2(t);
		bis.setS3(0);
		bis.setID1(sessionuser.getQyid());
		bis.setID2(sessionuser.getXzqy());
		bis.setID3(sessionuser.getId());
		
		long l=bisAqzjglkh.addInfore(bis);
		if(l>0){
			return l;
		}else{
			return (long) 0;
		}
	}
	
	@Override
	public void updateInfo(BIS_DirectorAssessEntity bis) {
		Timestamp t=DateUtils.getSysTimestamp();
		bis.setS2(t);
		bis.setS3(0);
		bisAqzjglkh.updateInfo(bis);
	}

	@Override
	public void spupdateInfo(BIS_DirectorAssessEntity bis) {
		Timestamp t=DateUtils.getSysTimestamp();
		bis.setS2(t);
		bis.setM5(t);
		bisAqzjglkh.updateInfo(bis);
	}

	@Override
	public void deleteInfo(long id) {
		bisAqzjglkh.deleteInfo(id);
	}


	@Override
	public List<BIS_DirectorAssessEntity> findAll() {
		return bisAqzjglkh.findAlllist();
	}

	@Override
	public List<BIS_DirectorAssessEntity> findAllByUserId(long userid) {
		return bisAqzjglkh.findAllByUserId(userid);
	}

	@Override
	public String getQyJson(Map<String, Object> map) {
		List<Map<String, Object>> list=bisAqzjglkh.dirHaveAllQyList(map);
		return JsonMapper.getInstance().toJson(list);
	}
	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="安全总监年度考核表.xls"; 
		List<Map<String, Object>> list=bisAqzjglkh.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"年度","工作报告","附件","安监局审核意见","安监局审核时间","备注","审核状态"};
			String[] keys={"m1","m2","m3","m4","m5","m6","m7"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","年度","工作报告","附件","安监局审核意见","安监局审核时间","备注","审核状态"};
			String[] keys={"qynm","m1","m2","m3","m4","m5","m6","m7"};
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
