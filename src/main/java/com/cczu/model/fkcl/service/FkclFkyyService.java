package com.cczu.model.fkcl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.fkcl.dao.FkclFkyyDao;
import com.cczu.model.fkcl.entity.FKCL_FkyyEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  访客预约Service
 */
@Service("FkclFkyyService")
public class FkclFkyyService {

	@Resource
	private FkclFkyyDao fkclFkyyDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=fkclFkyyDao.dataGrid(mapData);
		int getTotalCount=fkclFkyyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		fkclFkyyDao.deleteInfo(id);
	}
	
	/**
	 * 添加信息
	 * @param entity
	 */
	public void addInfo(FKCL_FkyyEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		fkclFkyyDao.save(entity);
	}
	
	/**
	 * 更新信息
	 * @param czgc
	 */
	public void updateInfo(FKCL_FkyyEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		fkclFkyyDao.save(entity);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public FKCL_FkyyEntity find(Long id) {
		return fkclFkyyDao.find(id);
	}
	
	/**
	 * 根据id查找详细信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findInfoById(Long id) {
		return fkclFkyyDao.findInfoById(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="访客预约记录表.xls";
		List<Map<String, Object>> list=fkclFkyyDao.getExport(mapData);
		String[] title={"预约时间","被预约人","事由","预约人","手机号码","预约确认人员","预约确认时间","进厂确认人员","进厂时间","进厂人数","出厂确认人员","出厂时间","出厂人数","预约状态"};  
		String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","m12","m13","zt"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response);
	}
}
