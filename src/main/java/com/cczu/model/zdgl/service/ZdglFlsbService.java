package com.cczu.model.zdgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.zdgl.dao.ZdglFlsbDao;
import com.cczu.model.zdgl.entity.ZDGL_FLFGSBEntity;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  制度管理-法律法规识别Service
 *
 */
@Service("ZdglFlsbService")
public class ZdglFlsbService {

	@Resource
	private ZdglFlsbDao zdglFlsbDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglFlsbDao.dataGrid(mapData);
		int getTotalCount=zdglFlsbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		zdglFlsbDao.deleteInfo(id);
	}
	
	//添加信息
	public void addInfo(ZDGL_FLFGSBEntity flfgsb) {
		zdglFlsbDao.save(flfgsb);
	}
	
	//根据id查找详细信息
	public Map<String,Object> findById(Long id) {
		return zdglFlsbDao.findById(id);
	}

	public ZDGL_FLFGSBEntity find(Long id) {
		return zdglFlsbDao.find(id);
	}
	
	//更新信息
	public void updateInfo(ZDGL_FLFGSBEntity flfgsb) {
		zdglFlsbDao.save(flfgsb);
	}
	
	//导出
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="法律法规识别表.xls";
		List<Map<String, Object>> list=zdglFlsbDao.getExport(mapData);
		String[] title={"大类别","小类别","法律法规名称","颁布单位","文件编号","发布日期","实施日期","适用条款","适用部门","Gap（差异）","备注","识别人","识别部门","识别日期","审核人","审核意见","审核日期","批准人","批准意见","批准日期"};  
		String[] keys={"m1_1","lb","flmc","bbdw","wjbh","fbrq","ssrq","m1","m2","m13","m3","sbr","sbbm","m6","spr","spyj","m9","pzr","pzyj","m12"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response);
	}
}
