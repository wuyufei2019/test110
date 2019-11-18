package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLSbbfDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBBFEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 设备设施管理-设备报废Service
 *
 */
@Service("SBSSGLSbbfService")
public class SBSSGLSbbfService {

	@Resource
	private SBSSGLSbbfDao sBSSGLSbbfDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbbfDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbbfDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除设备报废信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLSbbfDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找设备报废信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbbfDao.findById(id);
	}
	
	public SBSSGL_SBBFEntity find(Long id) {
		return sBSSGLSbbfDao.find(id);
	}
	
	/**
	 * 添加设备报废信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBBFEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbbfDao.save(entity);
	}

	/**
	 * 更新设备报废信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBBFEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbbfDao.save(entity);
	}
	
	/**
	 * 根据id获得设备验收单word表数据
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> getWord(long id) throws ParseException{
		//设备验收信息
		Map<String, Object> sbbf = sBSSGLSbbfDao.findById(id);
		//格式化购入时间
		sbbf.put("m6", DateUtils.formatDate((Date)sbbf.get("m6"), "yyyy-MM-dd"));
		return sbbf;
	}
	
}
