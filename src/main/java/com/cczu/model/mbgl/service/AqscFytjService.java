package com.cczu.model.mbgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.AqscFytjDao;

/**
 *  安全生产-费用统计Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqscFytjService")
public class AqscFytjService {
	@Resource
	private AqscFytjDao aqscFytjDao;
	
	/**
	 * 按类别统计预算支出
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findlxlist(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqscFytjDao.findlxlist(mapData);
		Map<String, Object> map=new HashMap<>();
		List<String> listys=new ArrayList<String>();
		List<String> listzc=new ArrayList<String>();
		List<String> listlx=new ArrayList<String>();
		for(Map<String, Object> m:list){
			String ys=m.get("ys").toString();
			String zc=m.get("zc").toString();
			String lx=m.get("lx").toString();
			listys.add(ys);
			listzc.add(zc);
			listlx.add(lx);
		}
		map.put("ys", listys);
		map.put("zc", listzc);
		map.put("lx", listlx);
		return map;
	}
	
	/**
	 * 按部门统计支出
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findDepartCount(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqscFytjDao.getDepartCount(mapData);
		Map<String, Object> map=new HashMap<>();
		List<String> listdepart=new ArrayList<String>();
		List<String> listzc=new ArrayList<String>();
		for(Map<String, Object> m:list){
			String zc=m.get("zc").toString();
			String depart=m.get("m1").toString();
			listzc.add(zc);
			listdepart.add(depart);
		}
		map.put("depart", listdepart);
		map.put("zc", listzc);
		return map;
	}
}
