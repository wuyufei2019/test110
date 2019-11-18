package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxpgHazopDao;
import com.cczu.model.dao.FxgkFxpgHazopStepDao;
import com.cczu.model.entity.FXGK_HazopRiskAssessment;
import com.cczu.model.entity.FXGK_HazopRiskStep;
import com.cczu.sys.comm.utils.ExinExcel;

@Transactional(readOnly=true)
@Service("FxgkFxpgHazopService")
public class FxgkFxpgHazopService {
	
	@Resource
	private FxgkFxpgHazopDao fxgkFxpgHazopDao;
	@Resource
	private FxgkFxpgHazopStepDao fxgkFxpgHazopStepDao;
	
	public void addInfo(FXGK_HazopRiskAssessment entity) {
		// TODO Auto-generated method stub
		fxgkFxpgHazopDao.save(entity);
	}
	public Long addInfoReID(FXGK_HazopRiskAssessment entity) {
		// TODO Auto-generated method stub
		fxgkFxpgHazopDao.save(entity);
		return entity.getID();
	}

	
	public void updateInfo(FXGK_HazopRiskAssessment entity) {
		fxgkFxpgHazopDao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		fxgkFxpgHazopDao.deleteInfo(id);
		fxgkFxpgHazopStepDao.deleteInfoById1(id);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=fxgkFxpgHazopDao.dataGrid(mapData);
		int getTotalCount=fxgkFxpgHazopDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public FXGK_HazopRiskAssessment findById(Long id) {
		// TODO Auto-generated method stub
		return fxgkFxpgHazopDao.find(id);
	}
	
	//根据id获得scl风险分析表数据
	public Map<String, Object> getExportWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapjha = fxgkFxpgHazopDao.export(id);
		
		map.put("question", mapjha.get("question")==null||mapjha.get("question").toString().equals("")?"":mapjha.get("question").toString());
		map.put("drawingnumber", mapjha.get("drawingnumber")==null||mapjha.get("drawingnumber").toString().equals("")?"":mapjha.get("drawingnumber").toString());
		map.put("revision", mapjha.get("revision")==null||mapjha.get("revision").toString().equals("")?"":mapjha.get("revision").toString());
		/*map.put("analysistime", mapjha.get("analysistime")==null||mapjha.get("analysistime").toString().equals("")?"":mapjha.get("analysistime").toString());*/
		if (mapjha.get("analysistime")!=null||!(mapjha.get("analysistime").toString().equals(""))) {
			//将日期" 年-月-日     时：分：秒  "转换为 " 年-月-日    "格式
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Timestamp analysistime = (Timestamp)mapjha.get("analysistime");
			String sub_analysistime = format.format(analysistime);
			//将转换后的日期放入map中
			map.put("analysistime", sub_analysistime);
		}
		/*map.put("meetingtime", mapjha.get("meetingtime")==null||mapjha.get("meetingtime").toString().equals("")?"":mapjha.get("meetingtime").toString());*/
		if (mapjha.get("meetingtime")!=null||!(mapjha.get("meetingtime").toString().equals(""))) {
			//将日期" 年-月-日     时：分：秒  "转换为 " 年-月-日    "格式
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Timestamp meetingtime = (Timestamp)mapjha.get("meetingtime");
			String sub_meetingtime = format.format(meetingtime);
			//将转换后的日期放入map中
			map.put("meetingtime", sub_meetingtime);
		}
		map.put("member", mapjha.get("member")==null||mapjha.get("member").toString().equals("")?"":mapjha.get("member").toString());
		map.put("analysispart", mapjha.get("analysispart")==null||mapjha.get("analysispart").toString().equals("")?"":mapjha.get("analysispart").toString());
		map.put("material", mapjha.get("material")==null||mapjha.get("material").toString().equals("")?"":mapjha.get("material").toString());
		map.put("function", mapjha.get("function")==null||mapjha.get("function").toString().equals("")?"":mapjha.get("function").toString());
		map.put("source", mapjha.get("source")==null||mapjha.get("source").toString().equals("")?"":mapjha.get("source").toString());
		map.put("purpose", mapjha.get("purpose")==null||mapjha.get("purpose").toString().equals("")?"":mapjha.get("purpose").toString());
		
		List<FXGK_HazopRiskStep> list=fxgkFxpgHazopStepDao.findBy("ID1", id);
		map.put("fxhd", list);
		return map;
	}
	
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		long id = (long)map.get("id1");
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> aclist : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				try{
					FXGK_HazopRiskStep  step = new FXGK_HazopRiskStep();
					step.setID1(id);
					step.setGuidanceword(aclist.get(0).toString());
					step.setFactor(aclist.get(1).toString());
					step.setDeviation(aclist.get(2).toString());
					step.setPossiblereason(aclist.get(3).toString());
					step.setResult(aclist.get(4).toString());
					step.setSafetymeasure(aclist.get(5).toString());
					step.setNote(aclist.get(6).toString());
					step.setSuggestion(aclist.get(7).toString());
					step.setExecutor(aclist.get(8).toString());
					fxgkFxpgHazopStepDao.save(step);
					result++;
				}catch(Exception e){
					e.printStackTrace();
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


