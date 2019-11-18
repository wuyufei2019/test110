package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglBgysDao;
import com.cczu.model.zyaqgl.entity.AQGL_ChangeAcceptance;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  安全管理-变更验收Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglBgysService")
public class AqglBgysService {
	@Resource
	private AqglBgysDao aqglBgysDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=aqglBgysDao.dataGrid(mapData);
		int getTotalCount=aqglBgysDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 变更项目下拉框list
	 * @param map
	 * @return
	 */
	public String getBgxmjson(Map<String, Object> map) {
		List<Map<String, Object>> list = aqglBgysDao.findBgxmList(map);
		return JsonMapper.getInstance().toJson(list);
	}
	
	/**
	 * 根据id查找变更验收信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findInforById(long id) {
		
		Map<String, Object> map=aqglBgysDao.findInforById(id);
		return map;
	}
	
	public void addInfo(AQGL_ChangeAcceptance bis) {
		aqglBgysDao.save(bis);
	}
	public long addInfoReturnID(AQGL_ChangeAcceptance bis) {
		aqglBgysDao.save(bis);
		return bis.getID();
	}

	public void updateInfo(AQGL_ChangeAcceptance bis) {
		aqglBgysDao.save(bis);
	}
	
	public void deleteInfo(long id) {
		aqglBgysDao.deleteInfo(id);
	}

	public Map<String ,Object> findById(Long id) {
		return aqglBgysDao.findInforById(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="变更验收表.xls";
		List<Map<String, Object>> list=aqglBgysDao.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
		String[] title={"日期","使用部门","支出项目类别","具体用途","金额(万元)","经办人","审核人","批准人","备注"};  
		String[] keys={"m1","depart","m2","m3","m4","m5","m6","m7","m8"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","日期","使用部门","支出项目类别","具体用途","金额(万元)","经办人","审核人","批准人","备注"};  
			String[] keys={"qyname","m1","depart","m2","m3","m4","m5","m6","m7","m8"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
				keys = ("qyname,"+mapData.get("colval").toString()).split(",") ;
			 }
		new ExportExcel(fileName, title, keys, list, response, true);
		}
	}
	
}
