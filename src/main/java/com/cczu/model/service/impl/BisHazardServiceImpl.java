package com.cczu.model.service.impl;

import com.cczu.model.dao.IBisHazardDao;
import com.cczu.model.entity.BIS_HazardEntity;
import com.cczu.model.service.IBisHazardService;
import com.cczu.sys.comm.utils.ExportExcel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly=true)
@Service("BisHazardService")
public class BisHazardServiceImpl implements IBisHazardService {
	
	@Resource
	private IBisHazardDao bishazardao;

	@Override
	public BIS_HazardEntity findqyid(Long qyid) {
		// TODO Auto-generated method stub
		return bishazardao.findQyId(qyid);
	}

	@Override
	public BIS_HazardEntity findById(Long id) {
		// TODO Auto-generated method stub
		return bishazardao.findById(id);
	}
	
	@Override
	public Map<String,Object> findMapByQyId(Long qyid) {
		// TODO Auto-generated method stub
		return bishazardao.findMapQyId(qyid);
	}

	@Override
	public void addInfo(BIS_HazardEntity bis) {
		// TODO Auto-generated method stub
		bishazardao.addInfo(bis);
	}

	@Override
	public void updateInfo(BIS_HazardEntity bis) {
		// TODO Auto-generated method stub
		bishazardao.updateInfo(bis);
	}

	@Override
	public BIS_HazardEntity findByid(Long id) {
		// TODO Auto-generated method stub
		return bishazardao.findById(id);
	}
	
	public Map<String, Object> dataGrid(Map<String,Object> mapData){
		List<Map<String,Object>> list = bishazardao.dataGrid(mapData);
		int getTotalCount = bishazardao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String fileName="高危工艺信息表.xls"; 
		List<Map<String, Object>> list=bishazardao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bishazardao.deleteInfo(id);
	}

	@Override
	public List<Integer> statistics(Map<String,Object> map) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<>();
		for(int i=1;i<5;i++){
			map.put("wxydj", i+"");
			list.add(bishazardao.getTotalCount(map));
			map.remove("wxydj");
		}
		return list;
	}
	
	/**
	 * 查询最新流水号
	 */
	@Override
	public String FindWaterCode(Long qyid) {
		return bishazardao.FindWaterCode(qyid);
	}
	
	/**
	 * 重大危险源编码Json
	 */
	@Override
	public List<Map<String,Object>> findhazardCode(Long qyid) {
		return bishazardao.findhazardCode(qyid);
	}

}
