package com.cczu.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcObserveCountDao;


/**
 *  观察统计分析Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("YhpcObserveCountService")
public class YhpcObserveCountService {
	@Resource
	private YhpcObserveCountDao yhpcObserveCountDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=yhpcObserveCountDao.dataGrid(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 观察记录统计图数据
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> jlcount(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=yhpcObserveCountDao.dataGrid(mapData);
		Map<String, Object> map=new HashMap<>();
		List<String> listlx=new ArrayList<String>();
		List<String> listxw=new ArrayList<String>();
		List<String> listzt=new ArrayList<String>();
		for(Map<String, Object> m:list){
			String lx=m.get("m1").toString();
			String xw=m.get("baqxw").toString();
			String zt=m.get("baqzt").toString();
			listlx.add(lx);
			listxw.add(xw);
			listzt.add(zt);
		}
		map.put("lx", listlx);
		map.put("xw", listxw);
		map.put("zt", listzt);
		return map;
	}
}
