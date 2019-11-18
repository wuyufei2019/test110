package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.AqscFyjsDao;
import com.cczu.model.mbgl.entity.AQSC_ExpenseCount;

/**
 *  安全生产-费用计算Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqscFyjsService")
public class AqscFyjsService {
	@Resource
	private AqscFyjsDao aqscFyjsDao;
	
	//获取行业类型list
	public List<Map<String, Object>> findLxList() {
		List<Map<String, Object>> lxList = aqscFyjsDao.findlxlist();
		return lxList;
	}
	
	//根据行业类型查询计算标准list并计算
	public Map<String, Object> count(String m2,float count) {
		List<AQSC_ExpenseCount> eclist= aqscFyjsDao.findByM1(m2);
		float sum=0;
		String standard="";
		for(AQSC_ExpenseCount ec:eclist){
			if(count>ec.getM3()){
				if(count>ec.getM4()){
					sum+=ec.getM6();
				}else{
					sum+=((count-ec.getM3())*ec.getM5());
				}
				standard+=ec.getM2()+"\n";
			}
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sum", sum);
		map.put("standard", standard);
		return map;
	}
	
}
