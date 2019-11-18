package com.cczu.model.dxj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.cczu.model.dxj.dao.DxjSbxjdDao;
import com.cczu.model.dxj.entity.DXJ_SbXjdEntity;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *Service
 */
@Service("DxjSbxjdService")
public class DxjSbxjdService {
	
	@Resource
	private DxjSbxjdDao dxjSbxjdDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=dxjSbxjdDao.dataGrid(mapData);
		int getTotalCount=dxjSbxjdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public void addInfo(DXJ_SbXjdEntity sbxm) {	
		dxjSbxjdDao.save(sbxm);
	}
	
	//根据id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return dxjSbxjdDao.findInforById(id);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		dxjSbxjdDao.deleteInfo(id);
	}
	
	//根据id2删除信息
	public void deleteById2(long id2) {
		dxjSbxjdDao.deleteById2(id2);
	}
	
	//根据id查找
	public DXJ_SbXjdEntity findById(long id) {
		return dxjSbxjdDao.find(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="设备项目表.xls";
		List<Map<String, Object>> list=dxjSbxjdDao.getExport(mapData);
		String[] title={"企业名称","设备名称","设备项目名称","标准","范围","点检方法"};  
		String[] keys={"qyname","sbm","name","standard","scope","checkmethod"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response, true);
	}
}
