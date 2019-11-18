package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.WghglWgxjjlzgDao;
import com.cczu.model.dao.YhpcXjjlDao;
import com.cczu.model.dao.YhpcYhpcjlDao;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 * 巡检记录
 * @author zpc
 */
@Service("YhpcXjjlService")
public class YhpcXjjlService {

	@Resource
	private YhpcXjjlDao yhpcXjjlDao;
	@Resource
	private YhpcYhpcjlDao yhpcYhpcjlDao;
	@Resource
	private WghglWgxjjlzgDao wghglWgxjjlzgDao;
	
	/**
	 * 企业检查记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjlDao.dataGrid(mapData);
		int getTotalCount=yhpcXjjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 安监
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> ajdataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjlDao.ajdataGrid(mapData);
		int getTotalCount=yhpcXjjlDao.getajTotalCount(mapData);
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
		//根据巡检记录id查询隐患排查记录list
		List<Map<String, Object>> list=yhpcYhpcjlDao.findIdByJlid(id+"");
		for (Map<String, Object> map : list) {
			//根据隐患记录id删除隐患记录对应的隐患整改复查
			wghglWgxjjlzgDao.deleteYhrByYchid(Long.parseLong(map.get("id").toString()));
			//根据隐患记录id删除隐患记录
			wghglWgxjjlzgDao.deleteInfo(Long.parseLong(map.get("id").toString()));
		}
		//删除巡检记录
		yhpcXjjlDao.deleteInfor(id);
	}

	/**
	 * 查看详细信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findInforById(Long id) {
		return yhpcXjjlDao.findInforById(id);
	}
	
	/**
	 * 企业导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="巡检记录表.xls";
		List<Map<String, Object>> list=yhpcXjjlDao.getExport(mapData);
		String[] title={"检查点","所属班次","检查时间","检查人","检查结果","问题备注"};  
		String[] keys={"jcdname","name","createtime","uname","lx","note"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
	 * 安监导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel2(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="巡检记录表.xls";
		List<Map<String, Object>> list=yhpcXjjlDao.getExport2(mapData);
		String[] title={"企业名称","检查点","所属班次","检查时间","检查人","检查结果","问题备注"};  
		String[] keys={"qyname","jcdname","name","createtime","uname","lx","note"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
	 * 获取企业list for app
	 * @param map
	 * @return
	 */
	public String getqylistForApp(Map<String, Object> map) {
		return JsonMapper.toJsonString(yhpcXjjlDao.getqylistForApp(map));
	}
	
	/**
	 * 安监 for app
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> ajdataGridForApp(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjlDao.ajdataGridforapp(mapData);
		int getTotalCount=yhpcXjjlDao.getajTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据巡检点id，类型获取该巡检点最新的巡检记录 app
	 * @return
	 */
	public Map<String, Object> getnewXjjlForApp(Long jcdid,String type){
		return yhpcXjjlDao.getnewXjjlForApp(jcdid,type);
	}

	/**
	 * 根据风险点颜色获取巡查信息
	 */
	public List<Map<String, Object>> getXcinfoByFxdColor(Map<String, Object> map) {
		return yhpcXjjlDao.getXcinfoByFxdColor(map);
	}
}
