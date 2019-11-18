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

import com.cczu.model.dao.FxgkFxpgLecActionDao;
import com.cczu.model.dao.FxgkFxpgLecDao;
import com.cczu.model.dao.FxgkFxpgMajorRiskDao;
import com.cczu.model.entity.FXGK_LecRiskAction;
import com.cczu.model.entity.FXGK_LecRiskAssessment;
import com.cczu.sys.comm.utils.ExinExcel;

@Transactional(readOnly=true)
@Service("FxgkFxpgLecService")
public class FxgkFxpgLecService {
	
	@Resource
	private FxgkFxpgLecDao fxgkfxpglecdao;
	@Resource
	private FxgkFxpgLecActionDao fxgkfxpglecactiondao;
	@Resource
	private FxgkFxpgMajorRiskDao fxgkfxpgmajorriskdao;
	
	public void addInfo(FXGK_LecRiskAssessment entity) {
		// TODO Auto-generated method stub
		fxgkfxpglecdao.save(entity);
	}
	public Long addInfoReID(FXGK_LecRiskAssessment entity) {
		// TODO Auto-generated method stub
		fxgkfxpglecdao.save(entity);
		return entity.getID();
	}

	
	public void updateInfo(FXGK_LecRiskAssessment entity) {
		fxgkfxpglecdao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		List<FXGK_LecRiskAction> list=fxgkfxpglecactiondao.findBy("ID1", id);
		for(FXGK_LecRiskAction action: list){
			if(action.getRiskvalue()>320){
				//删除重大风险
				fxgkfxpgmajorriskdao.deleteInfoById1(action.getID());
			}
		}
		fxgkfxpglecactiondao.deleteInfoById1(id);//删除风险活动
		fxgkfxpglecdao.deleteInfo(id);//删除风险评估
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=fxgkfxpglecdao.dataGrid(mapData);
		int getTotalCount=fxgkfxpglecdao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public FXGK_LecRiskAssessment findById(Long id) {
		// TODO Auto-generated method stub
		return fxgkfxpglecdao.find(id);
	}

	//根据id获得scl风险分析表数据
	public Map<String, Object> getExportWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapjha = fxgkfxpglecdao.export(id);
		
		map.put("deptname", mapjha.get("deptname")==null||mapjha.get("deptname").toString().equals("")?"":mapjha.get("deptname").toString());
		map.put("m1", mapjha.get("m1")==null||mapjha.get("m1").toString().equals("")?"":mapjha.get("m1").toString());
		map.put("jobname", mapjha.get("jobname")==null||mapjha.get("jobname").toString().equals("")?"":mapjha.get("jobname").toString());
		map.put("work", mapjha.get("work")==null||mapjha.get("work").toString().equals("")?"":mapjha.get("work").toString());
		map.put("analysisper", mapjha.get("analysisper")==null||mapjha.get("analysisper").toString().equals("")?"":mapjha.get("analysisper").toString());
		/*map.put("analysistime", mapjha.get("analysistime")==null||mapjha.get("analysistime").toString().equals("")?"":mapjha.get("analysistime").toString());*/
		if (mapjha.get("analysistime")!=null||!(mapjha.get("analysistime").toString().equals(""))) {
			//将日期" 年-月-日     时：分：秒  "转换为 " 年-月-日    "格式
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Timestamp analysistime = (Timestamp)mapjha.get("analysistime");
			String sub_analysistime = format.format(analysistime);
			//将转换后的日期放入map中
			map.put("analysistime", sub_analysistime);
		}
		map.put("reviewer", mapjha.get("reviewer")==null||mapjha.get("reviewer").toString().equals("")?"":mapjha.get("reviewer").toString());
		map.put("auditor", mapjha.get("auditor")==null||mapjha.get("auditor").toString().equals("")?"":mapjha.get("auditor").toString());
		
		List<FXGK_LecRiskAction> list=fxgkfxpglecactiondao.findBy("ID1", id);
		map.put("fxhd", list);
		return map;
	}
	
	//导入数据
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
					FXGK_LecRiskAction  ac = new FXGK_LecRiskAction();
					ac.setID1(id);
					ac.setWorkaction(aclist.get(0).toString());
					ac.setPotentialhazard(aclist.get(1).toString());
					ac.setMainresult(aclist.get(2).toString());
					ac.setSafetymeasure(aclist.get(3).toString());
					ac.setPossibility(aclist.get(4).toString());
					ac.setExposefrequency(aclist.get(5).toString());
					ac.setSeverity(aclist.get(6).toString());
					ac.setRiskvalue(Float.parseFloat(aclist.get(7).toString()));
					ac.setRisklevel(aclist.get(8).toString());
					ac.setImprovemeasure(aclist.get(9).toString());
					fxgkfxpglecactiondao.save(ac);
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


