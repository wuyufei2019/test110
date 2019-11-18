package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.AqpxPxdcztDao;
import com.cczu.model.dao.AqpxTpDao;
import com.cczu.model.dao.AqpxXqdcDao;
import com.cczu.model.entity.AQPX_DemandInforEntity;
import com.cczu.model.entity.AQPX_PxdcztEntity;
import com.cczu.model.entity.AQPX_TpEntity;

/**
 *  安全培训调查课程Service
 * @author YZH
 *
 */
@Service("AqpxXqdcService")
public class AqpxXqdcService {
	
	@Resource
	private AqpxXqdcDao aqpxXqdcDao;
	@Resource
	private AqpxTpDao aqpxTpDao;
	@Resource
	private AqpxPxdcztDao aqpxPxdcztDao;
	
	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {	
		List<Map<String,Object>> list=aqpxPxdcztDao.dataGrid(mapData);
		int getTotalCount=aqpxPxdcztDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除调查主题
	 * @param ztid
	 */
	public void deleteInfo(long ztid) {
		aqpxPxdcztDao.deleteById(ztid);
	}
	
	/**
	 * 保存调查主题
	 */
	public void save(AQPX_PxdcztEntity entity) {
		aqpxPxdcztDao.save(entity);
	}
	
	/**
	 * 根据id查找调查主题信息
	 */
	public AQPX_PxdcztEntity find(long id) {
		return aqpxPxdcztDao.find(id);
	}
	
	/**
	 * 查询所有课程
	 * @return
	 */
	public List<Map<String,Object>> kcgetallDataGrid(long ztid) {
		return aqpxXqdcDao.getallDataGrid(ztid);
	}
	
	/**
	 * 根据企业id删除所有课程
	 * @param qyid
	 */
	public void kcdeleteInfo(long qyid) {
		aqpxXqdcDao.deleteInfo(qyid);
	}
	
	/**
	 * 根据课程id删除课程
	 * @param id
	 */
	public void kcdeleteById(long id) {
		aqpxXqdcDao.deleteById(id);
	}
	
	/**
	 * 根据课程id查找信息
	 * @param id
	 * @return
	 */
	public AQPX_DemandInforEntity kcfindById(String id) {
		return aqpxXqdcDao.findById(id);
	}
	
	/**
	 * 保存课程
	 * @param kc
	 */
	public void kcsave(AQPX_DemandInforEntity kc) {
		aqpxXqdcDao.save(kc);
	}
	
	/**
	 * 根据用户id获得投票信息
	 * @param userid
	 * @return
	 */
	public AQPX_TpEntity tpfindById2(String userid,Long ztid) {
		return aqpxTpDao.findById2(userid,ztid);
	}

	/**
	 * 根据qyid删除所有投票信息
	 * @param qyid
	 */
    public void tpdeleteInfo(long qyid) {
    	aqpxTpDao.deleteInfo(qyid);
	}
    
    /**
     * 保存投票
     * @param tp
     */
  	public void tpsave(AQPX_TpEntity tp) {
  		aqpxTpDao.save(tp);
  	}
  	
  	/**
  	 * 根据课程id删除投票信息
  	 * @param kcid
  	 */
    public void tpdeleteById1(long kcid) {
    	aqpxTpDao.deleteById1(kcid);
	}
}
