package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqzfSetBasicInfoDao;
import com.cczu.model.dao.AqzfSetNumberDao;
import com.cczu.model.dao.AqzfWfxwDao;
import com.cczu.model.dao.AqzfZlzgDao;
import com.cczu.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.model.entity.AQZF_ReformEntity;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;
import com.cczu.model.entity.AQZF_WfxwEntity;

/**
 * 
 * @Description: 责令整改Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("AqzfZlzgService")
public class AqzfZlzgService {
	@Resource
	private AqzfZlzgDao aqzfZlzgDao;
	@Resource
	private AqzfSetNumberDao aqzfSetNumberDao;
	@Resource
	private AqzfJcfaService aqzfJcfaService;
	@Resource
	private AqzfJcjlService aqzfJcjlService;
	@Resource
	private AqzfFcyjService aqzfFcyjService;
	@Resource
	private AqzfWfxwDao aqzfWfxwDao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	
	/**
	 * 查询责令整改信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=aqzfZlzgDao.dataGrid(mapData);
		int getTotalCount=aqzfZlzgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(AQZF_ReformEntity bis) {
		bis.setM9("0");
		aqzfZlzgDao.save(bis);
		//查询检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(bis.getID1());
        jcjl.setM13("1");
        aqzfJcjlService.updateInfo(jcjl);
	}
	
	//根据id查询详细信息
	public AQZF_ReformEntity findById(Long id) {
		return aqzfZlzgDao.find(id);
	}
	
	//根据id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return aqzfZlzgDao.findInforById(id);
	}
	
	//更新信息
	public void updateInfo(AQZF_ReformEntity bis) {
		bis.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		aqzfZlzgDao.save(bis);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		AQZF_ReformEntity zlzg=aqzfZlzgDao.find(id);
		//查询检查记录
		AQZF_SafetyCheckRecordEntity jcjl=aqzfJcjlService.findjl(zlzg.getID1());
		jcjl.setM13("0");
		//删除责令整改信息
		aqzfZlzgDao.deleteInfo(id);
		aqzfJcjlService.updateInfo(jcjl);
	}
	
	//根据id1查找信息
	public AQZF_ReformEntity findInfoById1(long id1) {
		return aqzfZlzgDao.findInfoById1(id1);
	}
	
	//根据id获得责令整改word表数据
    public Map<String, Object> getAjWord(long id){
    	Map<String, Object> mapret = aqzfZlzgDao.findInforById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m0", mapret.get("m0")==null||mapret.get("m0").toString().equals("")?"":mapret.get("m0").toString());
		map.put("qyname", mapret.get("qyname")==null||mapret.get("qyname").toString().equals("")?"              ":mapret.get("qyname").toString());
		//检查时间解析
		if(mapret.get("m3")!=null&&!mapret.get("m3").toString().equals("")){
			String a = mapret.get("m3").toString();
			String[] as = a.substring(0,10).split("-");
			map.put("year", as[0]);
			map.put("month", as[1]);
			map.put("day", as[2]);
		}else{
			map.put("year", "    ");
			map.put("month", "  ");
			map.put("day", "  ");
		}
		
		//存在问题
		String czwt = "";
		if(mapret.get("m2")!=null&&!StringUtils.isEmpty(mapret.get("m2").toString())){
			String[] wfxwids = mapret.get("m2").toString().split(",");
			String sm = "";
			for(int i=0;i<wfxwids.length;i++){
				AQZF_WfxwEntity wfxw = new AQZF_WfxwEntity();
				 try {
			    	wfxw = aqzfWfxwDao.findInfoById(Long.parseLong(wfxwids[i]));
			    	czwt += (i+1)+". "+wfxw.getM1()+" ";
				} catch (Exception e) {
					czwt += (i+1)+". "+wfxwids[i]+" ";
				}
				if(i==0){
					sm += (i+1);
				}else{
					sm += ","+(i+1);
				}
			}
			map.put("sm",sm);
		}else{
			map.put("sm","    ");
		}
		map.put("m2",czwt.equals("")?"                                                                            ":czwt);
		
		map.put("m4",mapret.get("m4")==null||mapret.get("m4").toString().equals("")?"     ":mapret.get("m4").toString());
		map.put("m5",mapret.get("m5")==null||mapret.get("m5").toString().equals("")?"        ":mapret.get("m5").toString());
		map.put("m7_1",mapret.get("m7_1")==null||mapret.get("m7_1").toString().equals("")?"      ":mapret.get("m7_1").toString());
		map.put("m7_2",mapret.get("m7_2")==null||mapret.get("m7_2").toString().equals("")?"      ":mapret.get("m7_2").toString());
		
		AQZF_SetBasicInfoEntity bie = setbasicdao.findInfor();
		map.put("sjzf",bie.getHighgov());
		map.put("ssqmc",bie.getSsqmc());
		
		return map;
    }
}
