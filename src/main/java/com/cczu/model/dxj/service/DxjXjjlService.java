package com.cczu.model.dxj.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dxj.dao.DxjXjjlDao;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 * 巡检记录
 * @author zpc
 */
@Service("DxjXjjlService")
public class DxjXjjlService {

	@Resource
	private DxjXjjlDao dxjXjjlDao;
	
	/**
	 * 检查记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=dxjXjjlDao.dataGrid(mapData);
		int getTotalCount=dxjXjjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除巡检记录
	 * @param id
	 */
	public void deleteInfo(long id) {
		//删除巡检记录
		dxjXjjlDao.deleteInfor(id);
	}

	/**
	 * 查看详细信息
	 * @param map
	 * @return
	 */
	public Map<String,Object> findInforById(Long id) {
		return dxjXjjlDao.findInforById(id);
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="点巡检记录表.xls";
		List<Map<String, Object>> list=dxjXjjlDao.getExport(mapData);
		//格式化时间
		for (Map<String, Object> map : list) {
			//检查时间
			Timestamp createtime = (Timestamp) map.get("createtime");
			if (createtime != null) {
				map.put("createtime", DateUtils.formatDate(createtime, "yyyy-MM-dd HH:mm:ss"));
			}
		}
		String[] title={"企业名称","设备名称","班次名称","检查人","检查时间","检查结果","检查备注"};  
		String[] keys={"qyname","sbname","bcname","jcr","createtime","checkresult","note"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
	 * 隐患记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> yhdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=dxjXjjlDao.yhdataGrid(mapData);
		int getTotalCount=dxjXjjlDao.getyhTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
