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

import com.cczu.model.dao.IErmYjyyDao;
import com.cczu.model.entity.ERM_EmergencyHospitalEntity;
import com.cczu.model.service.IErmYjyyService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("ErmYjyyService")
public class ErmYjyyServiceImpl implements IErmYjyyService {
	
	@Resource
	private IErmYjyyDao ermYjyyDao;

	@Override
	public List<ERM_EmergencyHospitalEntity> findAll() {
		return ermYjyyDao.findAllInfo();
	}

	@Override
	public void addInfo(ERM_EmergencyHospitalEntity erm) {
		ermYjyyDao.addInfo(erm);
		
	}

	@Override
	public void updateInfo(ERM_EmergencyHospitalEntity erm) {
		ermYjyyDao.updateInfo(erm);
	}
	
	@Override
	public String content(Map<String, Object> mapData) {
		return ermYjyyDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<ERM_EmergencyHospitalEntity> list=ermYjyyDao.dataGrid(mapData);
		int getTotalCount=ermYjyyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		ermYjyyDao.deleteInfo(id);
	}

	@Override
	public ERM_EmergencyHospitalEntity findById(Long id) {
		return ermYjyyDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"医院名称","等级","地址","主要负责人","主要负责人电话","联系人","联系人电话","医院专长","病床数量","备注"};
		String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="应急医院.xls";
		List<Map<String, Object>> list=ermYjyyDao.getExcel(mapData);
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
					ERM_EmergencyHospitalEntity yjyy = new ERM_EmergencyHospitalEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yjyy.setS1(t);
					yjyy.setS2(t);
					yjyy.setS3(0);
					yjyy.setM1(bis.get(0).toString());
					yjyy.setM2(bis.get(1).toString());
					yjyy.setM3(bis.get(2).toString());
					yjyy.setM4(bis.get(3).toString());
					yjyy.setM5(bis.get(4).toString());
					yjyy.setM6(bis.get(5).toString());
					yjyy.setM7(bis.get(6).toString());
					yjyy.setM8(bis.get(7).toString());
					yjyy.setM9(Long.parseLong(bis.get(8).toString()));
					yjyy.setM10(bis.get(9).toString());
					ermYjyyDao.addInfo(yjyy);
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
		return ermYjyyDao.findMapList(mapdata);
	}
}
