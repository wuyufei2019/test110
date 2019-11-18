package com.cczu.model.hjbh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.hjbh.dao.HjbhOtherDwDao;
import com.cczu.model.hjbh.dao.HjbhWxglDao;
import com.cczu.model.hjbh.entity.HJBH_OtherDw;
import com.cczu.model.hjbh.entity.HJBH_Wxgl;

/**
 * 
 * @author zpc
 */
@Service("HjbhWxglService")
public class HjbhWxglService {
	
	@Resource
	private HjbhWxglDao hjbhWxglDao;
	@Resource
	private HjbhOtherDwDao hjbhOtherDwDao;
	
	/**
	 * 获取危险废物管理list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=hjbhWxglDao.dataGrid(mapData);
		int getTotalCount=hjbhWxglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 获取危险废物管理list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getWxfwIdNamejson(Map<String, Object> map) {
		List<Map<String, Object>> list = hjbhWxglDao.getWxfwIdName(map);
		return list;
	}

    /**
     * 添加危险废物特性信息并返回主键id
     * @param entity
     * @return
     */
	public Long addInfoReID(HJBH_Wxgl entity) {
		// TODO Auto-generated method stub
		hjbhWxglDao.save(entity);
		return entity.getID();
	}
	
	/**
	 * 通过id 查找危险废物特性信息
	 * @param id
	 * @return
	 */
	public HJBH_Wxgl findById(Long id) {
		// TODO Auto-generated method stub
		return hjbhWxglDao.find(id);
	}
	
	/**
	 * 保存修改的危险废物特性信息
	 * @param entity
	 */
	public void updateInfo(HJBH_Wxgl entity) {
		hjbhWxglDao.save(entity);
	}
	
	/**
	 * 删除危险废物特性信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		List<HJBH_OtherDw> list=hjbhOtherDwDao.findBy("ID1", id);
		for(HJBH_OtherDw action: list){
			Long idl = action.getID1();
			//删除关联的外单位信息
			hjbhOtherDwDao.deleteInfoById1(idl);
		}
		hjbhWxglDao.deleteInfo(id);//删除危险废物特性信息
	}
}
