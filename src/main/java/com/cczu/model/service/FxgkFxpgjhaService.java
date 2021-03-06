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

import com.cczu.model.dao.FxgkFxpgJhaActionDao;
import com.cczu.model.dao.FxgkFxpgMajorRiskDao;
import com.cczu.model.dao.FxgkFxpgjhaDao;
import com.cczu.model.entity.FXGK_RiskAction;
import com.cczu.model.entity.FXGK_RiskAssessment;
import com.cczu.sys.comm.utils.ExinExcel;

@Transactional(readOnly=true)
@Service("FxgkFxpgjhaService")
public class FxgkFxpgjhaService {
	
	@Resource
	private FxgkFxpgjhaDao fxgkFxpgDao;
	@Resource
	private FxgkFxpgJhaActionDao fxgkfxpgjhaactiondao;
	@Resource
	private FxgkFxpgMajorRiskDao fxgkfxpgmajorriskdao;
	
	public void addInfo(FXGK_RiskAssessment entity) {
		// TODO Auto-generated method stub
		fxgkFxpgDao.save(entity);
	}
	public Long addInfoReID(FXGK_RiskAssessment entity) {
		// TODO Auto-generated method stub
		fxgkFxpgDao.save(entity);
		return entity.getID();
	}

	
	public void updateInfo(FXGK_RiskAssessment entity) {
		fxgkFxpgDao.save(entity);
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		List<FXGK_RiskAction> list=fxgkfxpgjhaactiondao.findBy("ID1", id);
		for(FXGK_RiskAction action: list){
			if("极度危险".equals(action.getRisklevel())){
				//删除重大风险
				fxgkfxpgmajorriskdao.deleteInfoById1(action.getID());
			}
		}
		fxgkfxpgjhaactiondao.deleteInfoById1(id);//删除风险活动
		fxgkFxpgDao.deleteInfo(id);//删除风险评估
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=fxgkFxpgDao.dataGrid(mapData);
		int getTotalCount=fxgkFxpgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public FXGK_RiskAssessment findById(Long id) {
		// TODO Auto-generated method stub
		return fxgkFxpgDao.find(id);
	}
	
	public Map<String,Object> export(long id) {
		return fxgkFxpgDao.export(id);
	}

	//根据id获得jha风险分析表数据
	public Map<String, Object> getExportWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapjha = fxgkFxpgDao.export(id);
		
		map.put("deptname", mapjha.get("deptname"));
		map.put("m1", mapjha.get("m1"));
		map.put("jobname", mapjha.get("jobname"));
		map.put("work", mapjha.get("work"));
		map.put("analysisper", mapjha.get("analysisper"));
		//将日期" 年-月-日     时：分：秒  "转换为 " 年-月-日    "格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp analysistime = (Timestamp)mapjha.get("analysistime");
		String sub_analysistime = format.format(analysistime);
		//将转换后的日期放入map中
		map.put("analysistime", sub_analysistime);
		map.put("reviewer", mapjha.get("reviewer"));
		map.put("auditor", mapjha.get("auditor"));
		
		List<FXGK_RiskAction> list=fxgkfxpgjhaactiondao.findBy("ID1", id);
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
					FXGK_RiskAction  ac = new FXGK_RiskAction();
					ac.setID1(id);
					ac.setWorkstep(aclist.get(0).toString());
					ac.setPotentialhazard(aclist.get(1).toString());
					ac.setMainresult(aclist.get(2).toString());
					ac.setSafetymeasure(aclist.get(3).toString());
					ac.setPossibility((int)Float.parseFloat(aclist.get(4).toString()));
					ac.setSeverity((int)Float.parseFloat(aclist.get(5).toString()));
					ac.setRiskvalue((int)Float.parseFloat(aclist.get(6).toString()));
					ac.setRisklevel(aclist.get(7).toString());
					ac.setImprovemeasure(aclist.get(8).toString());
					fxgkfxpgjhaactiondao.save(ac);
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


