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

import com.cczu.model.dao.IErmBncsDao;
import com.cczu.model.entity.ERM_EmergencyResPlaceEntity;
import com.cczu.model.service.IErmBncsService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("ErmBncsService")
public class ErmBncsServiceImpl implements IErmBncsService {
	
	@Resource
	private IErmBncsDao ermBncsDao;

	@Override
	public List<ERM_EmergencyResPlaceEntity> findAll() {
		return ermBncsDao.findAllInfo();
	}

	@Override
	public void addInfo(ERM_EmergencyResPlaceEntity erm) {
		ermBncsDao.addInfo(erm);
		
	}

	@Override
	public void updateInfo(ERM_EmergencyResPlaceEntity erm) {
		ermBncsDao.updateInfo(erm);
	}	
	
	@Override
	public String content(Map<String, Object> mapData) {
		return ermBncsDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<ERM_EmergencyResPlaceEntity> list=ermBncsDao.dataGrid(mapData);
		int getTotalCount=ermBncsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		ermBncsDao.deleteInfo(id);
	}

	@Override
	public ERM_EmergencyResPlaceEntity findById(Long id) {
		return ermBncsDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"场所名称","场所类型","详细地址","可容纳人数","联系人","联系人电话","负责人","负责人电话","功能描述","备注"};
		String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m11"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="避难场所.xls";
		List<Map<String, Object>> list=ermBncsDao.getExcel(mapData);
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
					ERM_EmergencyResPlaceEntity bncs = new ERM_EmergencyResPlaceEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					bncs.setS1(t);
					bncs.setS2(t);
					bncs.setS3(0);
					bncs.setM1(bis.get(0).toString());
					bncs.setM2(bis.get(1).toString());
					bncs.setM3(bis.get(2).toString());
					bncs.setM4(Integer.parseInt(bis.get(3).toString()));
					bncs.setM5(bis.get(4).toString());
					bncs.setM6(bis.get(5).toString());
					bncs.setM7(bis.get(6).toString());
					bncs.setM8(bis.get(7).toString());
					bncs.setM9(bis.get(8).toString());
					bncs.setM11(bis.get(9).toString());
					ermBncsDao.addInfo(bncs);
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
		return ermBncsDao.findMapList(mapdata);
	}
}
