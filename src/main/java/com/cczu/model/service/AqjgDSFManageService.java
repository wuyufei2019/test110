/**
 * @ClassName: AdminDSFManageService
 * @Description: 第三方技术服务管理——管理服务
 * @author iDoctor
 * @date 2017年4月18日
 */
package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.AqjgDSFManageDao;
import com.cczu.model.entity.AQJG_DSFManageEntity;
import com.cczu.sys.comm.utils.ExportExcel;

@Service("AqjgDSFManageService")
public class AqjgDSFManageService {

	@Resource
	private AqjgDSFManageDao manageDao;

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<AQJG_DSFManageEntity> list = manageDao.dataGrid(mapData);
		int totalCount = manageDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", totalCount);
		return map;
	}

	public AQJG_DSFManageEntity findById(long id) {
		return manageDao.findById(id);
	}

	public void addInfo(AQJG_DSFManageEntity entity) {
		manageDao.save(entity);
	}

	public void updateInfo(AQJG_DSFManageEntity entity) {

		manageDao.save(entity);
	}

	public void deleteInfo(long id) {
		manageDao.deleteInfo(id);
	}

	public List<Map<String, Object>> findDwnmList() {
		List<Map<String, Object>> qynmList = manageDao.findDwnmList();
		return qynmList;
	}
	
	public String findDwname(long id) {
		List<Map<String, Object>> qynmList = manageDao.findDwname(id);
		return qynmList.get(0).get("text").toString();
	}

	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName = "单位信息案表.xls";
		List<Map<String, Object>> list = manageDao.getExport(mapData);
		// if("1".equals(mapData.get("usertype").toString())){
		String[] title = { "单位名称", "单位类型", "法人代表", "联系方式", "注册地址", "注册资金", "项目负责人", "联系方式", "备注" };
		String[] keys = { "m1", "m2", "m3", "m4", "m5", "m6", "m7", "m8", "m9" };
		if (StringUtils.isNotEmpty(mapData.get("colval").toString())) {
			title = mapData.get("coltext").toString().split(",");
			keys = mapData.get("colval").toString().split(",");
		}
		new ExportExcel(fileName, title, keys, list, response);
	}

	public List<Map<String, Object>> findXmnmList() {
		List<Map<String, Object>> qynmList = manageDao.findXmnmList();
		return qynmList;
	}
}
