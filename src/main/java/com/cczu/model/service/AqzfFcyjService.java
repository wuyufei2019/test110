package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqzfFcyjDao;
import com.cczu.model.dao.AqzfSetBasicInfoDao;
import com.cczu.model.dao.AqzfSetNumberDao;
import com.cczu.model.dao.AqzfZlzgDao;
import com.cczu.model.entity.AQZF_ReformEntity;
import com.cczu.model.entity.AQZF_ReviewEntity;
import com.cczu.model.entity.AQZF_SetBasicInfoEntity;

/**
 * 
 * @Description: 复查意见Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("AqzfFcyjService")
public class AqzfFcyjService {
	@Resource
	private AqzfFcyjDao aqzfFcyjDao;
	@Resource
	private AqzfSetNumberDao aqzfSetNumberDao;
	@Resource
	private AqzfJcfaService aqzfJcfaService;
	@Resource
	private AqzfJcjlService aqzfJcjlService;
	@Resource
	private AqzfZlzgDao aqzfZlzgDao;
	@Resource
	private AqzfSetBasicInfoDao setbasicdao;
	
	/**
	 * 查询复查意见信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=aqzfFcyjDao.dataGrid(mapData);
		int getTotalCount=aqzfFcyjDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(AQZF_ReviewEntity bis) {
		aqzfFcyjDao.save(bis);
		//修改责令整改状态
		AQZF_ReformEntity zlzg = aqzfZlzgDao.find(bis.getID1());
		zlzg.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		zlzg.setM9("1");
		aqzfZlzgDao.save(zlzg);
	}
	
	//根据id查询详细信息
	public AQZF_ReviewEntity findById(Long id) {
		return aqzfFcyjDao.find(id);
	}
	
	//根据id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return aqzfFcyjDao.findInforById(id);
	}
	
	//更新信息
	public void updateInfo(AQZF_ReviewEntity bis) {
		aqzfFcyjDao.save(bis);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		//根据查询复查意见信息
		AQZF_ReviewEntity fcyj=aqzfFcyjDao.find(id);
		//修改责令整改状态
		AQZF_ReformEntity zlzg = aqzfZlzgDao.find(fcyj.getID1());
		zlzg.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		zlzg.setM9("0");
		aqzfFcyjDao.deleteInfo(id);
		aqzfZlzgDao.save(zlzg);
	}

	//根据id1查找信息
	public AQZF_ReviewEntity findInfoById1(long id1) {
		return aqzfFcyjDao.findInfoById1(id1);
	}

	public Map<String, Object> findWordById(Long id) {
		// TODO Auto-generated method stub
		return aqzfFcyjDao.findWordById(id);
	}
	
	//根据id获得复查意见word表数据
	public Map<String, Object> getAjWord(long id){
		Map<String, Object> mapret = aqzfFcyjDao.findWordById(id);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("m0", mapret.get("m0")==null||mapret.get("m0").toString().equals("")?"":mapret.get("m0").toString());
		map.put("qyname", mapret.get("qyname")==null||mapret.get("qyname").toString().equals("")?"              ":mapret.get("qyname").toString());
		//检查时间解析
		if(mapret.get("m1")!=null&&!mapret.get("m1").toString().equals("")){
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
		//责令整改编号
		map.put("m3",mapret.get("m3")==null||mapret.get("m3").toString().equals("")?"（ ）安监____〔  〕（ ）号":mapret.get("m3").toString());
		map.put("m2",mapret.get("m2")==null||mapret.get("m2").toString().equals("")?"":mapret.get("m2").toString());//改正问题
		map.put("m4_1",mapret.get("m4_1")==null||mapret.get("m4_1").toString().equals("")?"       ":mapret.get("m4_1").toString());//证号1
		map.put("m4_2",mapret.get("m4_2")==null||mapret.get("m4_2").toString().equals("")?"       ":mapret.get("m4_2").toString());//证号2
		map.put("m6",mapret.get("m6")==null||mapret.get("m6").toString().equals("")?"                   ":mapret.get("m6").toString());//做出决定
		
		AQZF_SetBasicInfoEntity bie = setbasicdao.findInfor();
		map.put("ssqmc",bie.getSsqmc());
		return map;
	}
}
