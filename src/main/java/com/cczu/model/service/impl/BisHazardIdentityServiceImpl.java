package com.cczu.model.service.impl;

import com.cczu.model.dao.IBisHazardIdentityDao;
import com.cczu.model.entity.BIS_HazardIdentityEntity;
import com.cczu.model.service.IBisHazardIdentityService;
import com.cczu.sys.comm.utils.ExportExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly=true)
@Service("BisHazardIdentityService")
public class BisHazardIdentityServiceImpl implements IBisHazardIdentityService {
	
	@Resource
	private IBisHazardIdentityDao bishazaredao;

	@Override
	public String content(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return bishazaredao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<BIS_HazardIdentityEntity> list = bishazaredao.dataGrid(mapData);
		int getTotalCount = bishazaredao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public BIS_HazardIdentityEntity findAll(Long wxid) {
		// TODO Auto-generated method stub
		return bishazaredao.findAll(wxid);
	}

	@Override
	public void addInfo(BIS_HazardIdentityEntity bis) {
		// TODO Auto-generated method stub
		bishazaredao.addInfo(bis);
	}

	@Override
	public BIS_HazardIdentityEntity findById(Long id) {
		// TODO Auto-generated method stub
		return bishazaredao.findById(id);
	}

	@Override
	public void updateInfo(BIS_HazardIdentityEntity bis) {
		// TODO Auto-generated method stub
		bishazaredao.updateInfo(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		bishazaredao.deleteInfo(id);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return bishazaredao.count();
	}

	@Override
	public List<Map<String, Object>> Qzhi() {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = bishazaredao.Qzhi();
		
		return list;
	}

	@Override
	public Map<String, Object> datafy(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = bishazaredao.datafy(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", list.size());
		return map;
	}

	@Override
	public List<BIS_HazardIdentityEntity> findListHdid(Long qyid) {
		// TODO Auto-generated method stub
		return bishazaredao.findListHdid(qyid);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		// TODO Auto-generated method stub
			String[] title={"类别","辨识物质","最大储量","临界储量","计算值 (M3M4)"};   
			String[] keys={"m1","m2","m3","m4","m5"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="重大危险源信息信息表.xls"; 
			
			List<BIS_HazardIdentityEntity> list=bishazaredao.getExport(mapData);
			List<Map<String, Object>> listContent=new ArrayList<>();
			for(BIS_HazardIdentityEntity entity:list){
				Map<String, Object> map =new HashMap<String, Object>();
				map.put("id", entity.getID());
				map.put("m1", entity.getM1());
				map.put("m2", entity.getM2());
				map.put("m3", entity.getM3());
				map.put("m4", entity.getM4());
				map.put("m5", entity.getM5());
				
				listContent.add(map);
			}
			new ExportExcel(fileName, title, keys, listContent, response);
		}
	
	@Override
	public Map<String, Object> dataGridApp(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = bishazaredao.dataGridApp(mapData);
		int getTotalCount = bishazaredao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
