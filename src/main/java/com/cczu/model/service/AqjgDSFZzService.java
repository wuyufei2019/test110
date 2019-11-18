/**
 * @ClassName: AqjgDSFZzService
 * @Description: 第三方技术服务管理——资质服务
 * @author iDoctor
 * @date 2017年4月19日
 */
package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.cczu.model.dao.AqjgDSFZzDao;
import com.cczu.model.entity.AQJG_DSFZzEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;

@Service("AqjgDSFZzService")
public class AqjgDSFZzService {

	@Resource
	private AqjgDSFZzDao zzDao;


	public List<AQJG_DSFZzEntity> findAll(long userid) {
		return zzDao.findAll(userid);
	}

	public void addInfo(AQJG_DSFZzEntity entity) {
//		zzDao.addInfo(entity);
		zzDao.save(entity);

	}


	public void updateInfo(AQJG_DSFZzEntity entity) {
//		zzDao.updateInfo(entity);
		zzDao.save(entity);
	}


	public Page<AQJG_DSFZzEntity> search(Page<AQJG_DSFZzEntity> page, List<PropertyFilter> filters) {
		return null;
	}


	public Map<String, Object> ajdataGrid(Map<String, Object> mapData) {

		List<AQJG_DSFZzEntity> list = zzDao.ajdataGrid(mapData);
		int getTotalCount = zzDao.ajgetTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<AQJG_DSFZzEntity> list = zzDao.dataGrid(mapData);
		int getTotalCount = zzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		zzDao.deleteInfo(id);
	}


	public AQJG_DSFZzEntity findById(Long id) {
		return zzDao.findById(id);
	}

	public AQJG_DSFZzEntity findByDwID(long id1) {
		// TODO Auto-generated method stub
		
		return zzDao.findByDwID(id1);
	}


//	public AQJG_DSFZzEntity findByM(String M1) {
//		// TODO Auto-generated method stub
//		return zzDao.findByM(M1);
//	}


//	public String findByQyID(Long id1) {
		// TODO Auto-generated method stub
//		List<AQJG_DSFZzEntity> list = zzDao.findByQyID(id1);
//		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
//		for (AQJG_DSFZzEntity cj : list) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("id", cj.getID());
//			map.put("text", cj.getM1());
//			arrylist.add(map);
//		}
//		return JsonMapper.getInstance().toJson(arrylist);

//	}


//	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
//		String fileName = "车间信息表.xls";
//		List<Map<String, Object>> list = zzDao.getExport(mapData);
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
