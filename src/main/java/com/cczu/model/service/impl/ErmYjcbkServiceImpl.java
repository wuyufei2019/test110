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

import com.cczu.model.dao.IErmYjcbkDao;
import com.cczu.model.entity.ERM_EmergencyRepositoryEntity;
import com.cczu.model.service.IErmYjcbkService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("ErmYjcbkService")
public class ErmYjcbkServiceImpl implements IErmYjcbkService {
	
	@Resource
	private IErmYjcbkDao ermYjcbkDao;

	@Override
	public List<ERM_EmergencyRepositoryEntity> findAll() {
		return ermYjcbkDao.findAllInfo();
	}

	@Override
	public void addInfo(ERM_EmergencyRepositoryEntity erm) {
		ermYjcbkDao.addInfo(erm);
		
	}

	@Override
	public void updateInfo(ERM_EmergencyRepositoryEntity erm) {
		ermYjcbkDao.updateInfo(erm);
	}
	
	@Override
	public String content(Map<String, Object> mapData) {
		return ermYjcbkDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<ERM_EmergencyRepositoryEntity> list=ermYjcbkDao.dataGrid(mapData);
		int getTotalCount=ermYjcbkDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		ermYjcbkDao.deleteInfo(id);
	}

	@Override
	public ERM_EmergencyRepositoryEntity findById(Long id) {
		return ermYjcbkDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"储备库名称","储备库地址","负责人","负责人电话","存放物品","库容","所属单位","功能描述","应对事故类型","备注"};
		String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="应急储备库.xls";
		List<Map<String, Object>> list=ermYjcbkDao.getExcel(mapData);
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
					ERM_EmergencyRepositoryEntity yjcb = new ERM_EmergencyRepositoryEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yjcb.setS1(t);
					yjcb.setS2(t);
					yjcb.setS3(0);
					yjcb.setM1(bis.get(0).toString());
					yjcb.setM7(bis.get(1).toString());
					yjcb.setM2(bis.get(2).toString());
					yjcb.setM3(bis.get(3).toString());
					yjcb.setM4(bis.get(4).toString());
					yjcb.setM6(bis.get(5).toString());
					yjcb.setM5(bis.get(6).toString());
					yjcb.setM8(bis.get(7).toString());
					yjcb.setM10(bis.get(8).toString());
					ermYjcbkDao.addInfo(yjcb);
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
		return ermYjcbkDao.findMapList(mapdata);
	}
}
