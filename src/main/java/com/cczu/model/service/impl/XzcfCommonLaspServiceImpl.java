package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.impl.XzcfCommonLaspDaoImpl;
import com.cczu.model.entity.XZCF_YbcfLaspEntity;
import com.cczu.model.service.IXzcfCommonLaspService;

/**
 * 行政处罚-一般处罚-立案审批实现类
 * 
 * @author jason
 * 
 */

@Service("PunishCommonLaspService")
public class XzcfCommonLaspServiceImpl implements IXzcfCommonLaspService {
	@Resource
	private XzcfCommonLaspDaoImpl punishcommonlaspdao;

	@Override
	public Long addInfoReturnID(XZCF_YbcfLaspEntity yle) {
		// TODO Auto-generated method stub
		return punishcommonlaspdao.addInfoReturnID(yle);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list = punishcommonlaspdao.dataGrid(mapData);
		int count = punishcommonlaspdao.getTotalCount(mapData);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
//	@Override
//	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
//		// TODO Auto-generated method stub
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Map<String,Object>> list = punishcommonlaspdao.dataGrid2(mapData);
//		int count = punishcommonlaspdao.getTotalCount2(mapData);
//		map.put("rows", list);
//		map.put("total", count);
//		return map;
//	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		punishcommonlaspdao.deleteInfo(id);
	}

	@Override
	public XZCF_YbcfLaspEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return punishcommonlaspdao.findInfoById(id);

	}

	@Override
	public void updateInfo( XZCF_YbcfLaspEntity yle) {
		// TODO Auto-generated method stub
		punishcommonlaspdao.updateInfo(yle);
	}
//	
//	@Override
//	public Object[] getMaxYearAndMinYear(){
//		return punishcommonlaspdao.getMaxYearAndMinYear();
//	}
//
//	@Override
//	public List<Map<String, Object>> findGzCaseNameList(String xzqy) {
//		// TODO Auto-generated method stub
//		List<Map<String, Object>> list=punishcommonlaspdao.findGzCaseNameList(xzqy);
//		return list;
//	}
//
//	@Override
//	public List<Map<String,Object>>getYearDate() {
//		// TODO Auto-generated method stub
//		return punishcommonlaspdao.getYearDate();
//	}
//	@Override
//	public List<Object[]>getYearDate2(String year) {
//		// TODO Auto-generated method stub
//		return punishcommonlaspdao.getYearDate2(year);
//	}

	@Override
	public int getTempCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return  punishcommonlaspdao.getTempCount(mapData);
	}

	@Override
	public XZCF_YbcfLaspEntity findTempInfo(String xzqy) {
		// TODO Auto-generated method stub
		return punishcommonlaspdao.findTempInfo(xzqy);
	}

	@Override
	public List<Map<String, Object>> getNumberlist(long id,String xzqy) {
		// TODO Auto-generated method stub
		Object[] obj =(Object[]) punishcommonlaspdao.getNumberlist(id,xzqy).get(0);
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		for(int i=0;i<obj.length;i++){
			if(obj[i]!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("text", obj[i]);
				list.add(map);
			}
		}
		return list;
	}

}
