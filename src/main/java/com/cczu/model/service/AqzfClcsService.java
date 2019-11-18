package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqzfClcsDao;
import com.cczu.model.dao.AqzfSetBasicInfoDao;
import com.cczu.model.dao.AqzfSetNumberDao;
import com.cczu.model.dao.AqzfWfxwDao;
import com.cczu.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_TreatmentEntity;
import com.cczu.model.entity.AQZF_WfxwEntity;

/**
 * 
 * @Description: 处理措施Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("AqzfClcsService")
public class AqzfClcsService {
	@Resource
	private AqzfClcsDao aqzfClcsDao;
	@Resource
	private AqzfSetNumberDao aqzfSetNumberDao;
	@Resource
	private AqzfWfxwDao aqzfWfxwDao;
	@Resource
	private AqzfJcjlService aqzfJcjlService;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	
	/**
	 * 查询处理措施信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=aqzfClcsDao.dataGrid(mapData);
		int getTotalCount=aqzfClcsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(AQZF_TreatmentEntity bis) {
		aqzfClcsDao.save(bis);
		//查询检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(bis.getID3());
        jcjl.setM12("1");
        aqzfJcjlService.updateInfo(jcjl);
	}
	
	//根据id查询详细信息
	public AQZF_TreatmentEntity findById(Long id) {
		return aqzfClcsDao.find(id);
	}
	
	//根据id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return aqzfClcsDao.findInforById(id);
	}
	
	//更新信息
	public void updateInfo(AQZF_TreatmentEntity bis) {
		aqzfClcsDao.save(bis);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		AQZF_TreatmentEntity clcs = aqzfClcsDao.find(id);
		//查询检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(clcs.getID3());
	    jcjl.setM12("0");
	    aqzfClcsDao.deleteInfo(id);
	    aqzfJcjlService.updateInfo(jcjl);
	}
	
	//根据id获得现场处理措施word表数据
	public Map<String, Object> getAjWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapret = aqzfClcsDao.findInforById(id);
		map.put("m0", mapret.get("m0")==null||mapret.get("m0").toString().equals("")?"":mapret.get("m0").toString());
		map.put("qyname", mapret.get("qyname")==null||mapret.get("qyname").toString().equals("")?"":mapret.get("qyname").toString());
		//检查时间解析
		if(mapret.get("m1")!=null&&!mapret.get("m1").equals("")){
			String a = mapret.get("m1").toString();
			String[] as = a.substring(0,10).split("-");
			map.put("year", as[0]);
			map.put("month", as[1]);
			map.put("day", as[2]);
		}else{
			map.put("year", "    ");
			map.put("month", "  ");
			map.put("day", "  ");
		}
		
		//事故
		String sgyh = "";
		if(mapret.get("m2")!=null&&!StringUtils.isEmpty(mapret.get("m2").toString())){
			String[] wfxwids = mapret.get("m2").toString().split(",");
			for(int i=0;i<wfxwids.length;i++){
				AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
				 try {
			    	wfxw = aqzfWfxwDao.findInfoById(Long.parseLong(wfxwids[i]));
			    	sgyh += (i+1)+". "+wfxw.getM1()+" ";
				} catch (Exception e) {
					sgyh += (i+1)+". "+wfxwids[i]+" ";
				}
			}
		}
		
		map.put("m2",sgyh);
		map.put("m3",mapret.get("m3")==null||mapret.get("m3").toString().equals("")?"                                                          ":mapret.get("m3").toString());
		map.put("m4",mapret.get("m4")==null||mapret.get("m4").toString().equals("")?"":mapret.get("m4").toString());
		map.put("m5",mapret.get("m5")==null||mapret.get("m5").toString().equals("")?"                                     ":mapret.get("m5").toString());
		map.put("m6",mapret.get("m6")==null||mapret.get("m6").toString().equals("")?"                                     ":mapret.get("m6").toString());
		map.put("m8_1",mapret.get("m8_1")==null||mapret.get("m8_1").toString().equals("")?"      ":mapret.get("m8_1").toString());
		map.put("m8_2",mapret.get("m8_2")==null||mapret.get("m8_2").toString().equals("")?"      ":mapret.get("m8_2").toString());
		
		AQZF_SetBasicInfoEntity bie = setbasicdao.findInfor();
		map.put("sjzf",bie.getHighgov());
		map.put("ssqmc",bie.getSsqmc());
		
		return map;
	}
}
