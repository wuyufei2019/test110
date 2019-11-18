package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IErmYjdwDao;
import com.cczu.model.entity.ERM_EmergencyResTeamEntity;
import com.cczu.model.service.IErmYjdwService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("ErmYjdwService")
public class ErmYjdwServiceImpl implements IErmYjdwService {
	
	@Resource
	private IErmYjdwDao ermYjdwDao;

	@Override
	public List<ERM_EmergencyResTeamEntity> findAll() {
		return ermYjdwDao.findAllInfo();
	}

	@Override
	public void addInfo(ERM_EmergencyResTeamEntity erm) {
		ermYjdwDao.addInfo(erm);
		
	}

	@Override
	public void updateInfo(ERM_EmergencyResTeamEntity erm) {
		ermYjdwDao.updateInfo(erm);
	}
	
	@Override
	public String content(Map<String, Object> mapData) {
		return ermYjdwDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<ERM_EmergencyResTeamEntity> list=ermYjdwDao.dataGrid(mapData);
		int getTotalCount=ermYjdwDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		ermYjdwDao.deleteInfo(id);
	}

	@Override
	public ERM_EmergencyResTeamEntity findById(Long id) {
		return ermYjdwDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"队伍名称","队伍类型","队伍地址","主要负责人","应急电话","主管部门","总人数","应急功能","成立时间","应对事故类型","备注"};
		String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="应急队伍.xls";
		List<Map<String, Object>> list=ermYjdwDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	@Override
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				try{
					ERM_EmergencyResTeamEntity yjdw = new ERM_EmergencyResTeamEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yjdw.setS1(t);
					yjdw.setS2(t);
					yjdw.setS3(0);
					yjdw.setM1(bis.get(0).toString());
					yjdw.setM2(bis.get(1).toString());
					yjdw.setM3(bis.get(2).toString());
					yjdw.setM4(bis.get(3).toString());
					yjdw.setM5(bis.get(4).toString());
					yjdw.setM6(bis.get(5).toString());
					yjdw.setM7(Integer.parseInt(bis.get(6).toString()));
					yjdw.setM8(bis.get(8).toString());
					yjdw.setM9(DateUtils.getTimestampFromStr(bis.get(7).toString()));
					yjdw.setM11(bis.get(9).toString());
					ermYjdwDao.addInfo(yjdw);
					result++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",result);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}

	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata) {
		// TODO Auto-generated method stub
		return ermYjdwDao.findMapList(mapdata);
	}
}
