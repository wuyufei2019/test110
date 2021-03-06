package com.cczu.model.service;

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

import com.cczu.model.dao.ErmYljhDao;
import com.cczu.model.dao.ErmYljlDao;
import com.cczu.model.entity.ERM_ExercisePlanEntity;
import com.cczu.model.entity.ERM_ExerciseRecordEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("ErmYljlService")
public class ErmYljlService {
	
	@Resource
	private ErmYljlDao ermYljlDao;
	@Resource
	private ErmYljhDao ermYljhDao;

	
	public List<ERM_ExerciseRecordEntity> findAll() {
		return ermYljlDao.findAllInfo();
	}

	
	public void addInfo(ERM_ExerciseRecordEntity erm) {
		ermYljlDao.addInfo(erm);
		
	}

	
	public void updateInfo(ERM_ExerciseRecordEntity erm) {
		ermYljlDao.updateInfo(erm);
	}
	
	
	public String content(Map<String, Object> mapData) {
		return ermYljlDao.content(mapData);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=ermYljlDao.dataGrid(mapData);
		int getTotalCount=ermYljlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		//修改演练计划状态
		ERM_ExerciseRecordEntity yljl=ermYljlDao.findById(id);
		ERM_ExercisePlanEntity yljh=ermYljhDao.findById(yljl.getID1());
		yljh.setID1(0l);
		ermYljhDao.save(yljh);
		//删除演练记录
		ermYljlDao.deleteInfo(id);
	}

	
	public ERM_ExerciseRecordEntity findById(Long id) {
		return ermYljlDao.findById(id);
	}

	
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
			String[] title=mapData.get("coltext").toString().split(",");
			String[] keys=mapData.get("colval").toString().split(",");
			String fileName="演练计划.xls";
			List<Map<String, Object>> list=ermYljlDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
	}
	
	
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
					ERM_ExerciseRecordEntity yjdw = new ERM_ExerciseRecordEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yjdw.setS1(t);
					yjdw.setS2(t);
					yjdw.setS3(0);
					yjdw.setM2(bis.get(1).toString());
					yjdw.setM3(bis.get(2).toString());
					yjdw.setM4(bis.get(3).toString());
					yjdw.setM5(bis.get(4).toString());
					yjdw.setM6(bis.get(5).toString());
					yjdw.setM8(bis.get(8).toString());
					ermYljlDao.addInfo(yjdw);
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
}
