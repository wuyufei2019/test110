package com.cczu.model.hjbh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.hjbh.dao.HjbhThreeSimulDao;
import com.cczu.model.hjbh.entity.HJBH_ThreeSimul;
import com.cczu.sys.comm.mapper.JsonMapper;

/**
 * 
 * @author wbth
 */
@Service("HjbhThreeSimulService")
public class HjbhThreeSimulService {
	
	@Resource
	private HjbhThreeSimulDao hjbhThreeSimulDao;
	
	/**
	 * 获取三同时管理list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=hjbhThreeSimulDao.dataGrid(mapData);
		int getTotalCount=hjbhThreeSimulDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 获取三同时管理list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getWxfwIdNamejson(Map<String, Object> map) {
		List<Map<String, Object>> list = hjbhThreeSimulDao.getWxfwIdName(map);
		return list;
	}

    /**
     * 添加三同时信息并返回主键id
     * @param entity
     * @return
     */
	public Long addInfoReID(HJBH_ThreeSimul entity) {
		// TODO Auto-generated method stub
		hjbhThreeSimulDao.save(entity);
		return entity.getID();
	}
	
	/**
	 * 通过 id 查找三同时信息
	 * @param id
	 * @return
	 */
	public HJBH_ThreeSimul findById(Long id) {
		// TODO Auto-generated method stub
		return hjbhThreeSimulDao.find(id);
	}
	
	/**
	 * 保存修改的三同时信息
	 * @param entity
	 */
	public void updateInfo(HJBH_ThreeSimul entity) {
		hjbhThreeSimulDao.save(entity);
	}
	
	/**
	 * 删除三同时信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		hjbhThreeSimulDao.deleteInfo(id);//删除危险废物特性信息
	}
	
	/**
	 * 查询批准部门下拉
	 * @return
	 */
	public String findpz() {
		return JsonMapper.getInstance().toJson(hjbhThreeSimulDao.findpz());
	}
	
	/**
	 * 查询审核部门下拉
	 * @return
	 */
	public String findsh() {
		return JsonMapper.getInstance().toJson(hjbhThreeSimulDao.findsh());
	}
	
	/**
	 * 查询三同时验收部门下拉
	 * @return
	 */
	public String findys() {
		return JsonMapper.getInstance().toJson(hjbhThreeSimulDao.findys());
	}
}
