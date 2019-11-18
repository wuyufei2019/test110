package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.AqjgDSFTzzyryDao;
import com.cczu.model.entity.AQJG_DSFTzzyryEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;

@Service("AqjgDSFTzzyryService")
public class AqjgDSFTzzyryService {
	
	@Resource
	private AqjgDSFTzzyryDao tzzyDao;


	public List<AQJG_DSFTzzyryEntity> findAll(long userid) {
		return tzzyDao.findAll(userid);
	}

	public void addInfo(AQJG_DSFTzzyryEntity entity) {
		tzzyDao.save(entity);

	}


	public void updateInfo(AQJG_DSFTzzyryEntity entity) {
//		tzzyDao.updateInfo(entity);
		tzzyDao.save(entity);
	}


	public Page<AQJG_DSFTzzyryEntity> search(Page<AQJG_DSFTzzyryEntity> page, List<PropertyFilter> filters) {
		return null;
	}


	public Map<String, Object> ajdataGrid(Map<String, Object> mapData) {

		List<AQJG_DSFTzzyryEntity> list = tzzyDao.ajdataGrid(mapData);
		int getTotalCount = tzzyDao.ajgetTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<AQJG_DSFTzzyryEntity> list = tzzyDao.dataGrid(mapData);
		int getTotalCount = tzzyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		tzzyDao.deleteInfo(id);
	}


	public AQJG_DSFTzzyryEntity findById(Long id) {
		return tzzyDao.findById(id);
	}

	public AQJG_DSFTzzyryEntity findByDwID(long id1) {
		// TODO Auto-generated method stub
		
		return tzzyDao.findByDwID(id1);
	}


//	public AQJG_DSFTzzyryEntity findByM(String M1) {
//		// TODO Auto-generated method stub
//		return tzzyDao.findByM(M1);
//	}


//	public String findByQyID(Long id1) {
		// TODO Auto-generated method stub
//		List<AQJG_DSFTzzyryEntity> list = tzzyDao.findByQyID(id1);
//		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
//		for (AQJG_DSFTzzyryEntity cj : list) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("id", cj.getID());
//			map.put("text", cj.getM1());
//			arrylist.add(map);
//		}
//		return JsonMapper.getInstance().toJson(arrylist);

//	}


//	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
//		String fileName = "车间信息表.xls";
//		List<Map<String, Object>> list = tzzyDao.getExport(mapData);
//		if ("1".equals(mapData.get("usertype").toString())) {
//			String[] title = { "车间名称", "车间编号", "占地面积", "火灾危险等级", "建筑结构", "层数", "备注", "耐火等级" };
//			String[] keys = { "m1", "m2", "m3", "m4", "m5", "m6", "m7", "m8" };
//			if (StringUtils.isNotEmpty(mapData.get("colval").toString())) {
//				title = mapData.get("coltext").toString().split(",");
//				keys = mapData.get("colval").toString().split(",");
//			}
//			new ExportExcel(fileName, title, keys, list, response);
//		} else {
//			String[] title = { "企业", "车间名称", "车间编号", "占地面积", "火灾危险等级", "建筑结构", "层数", "备注", "耐火等级" };
//			String[] keys = { "qynm", "m1", "m2", "m3", "m4", "m5", "m6", "m7", "m8" };
//			if (StringUtils.isNotEmpty(mapData.get("colval").toString())) {
//				if (!mapData.get("colval").toString().equals("qynm")) {
//					title = mapData.get("coltext").toString().split(",");
//					keys = mapData.get("colval").toString().split(",");
//				}
//			}
//			new ExportExcel(fileName, title, keys, list, response, true);
//		}
//	}

}
