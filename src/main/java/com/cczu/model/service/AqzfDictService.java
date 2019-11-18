package com.cczu.model.service;

 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqzfDictDao;
import com.cczu.model.entity.AQZF_DictEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
 
/**
 * 安全执法_字典Service
 * @author jason
 * @date 2017年8月3日
 */
@Transactional(readOnly=true)
@Service("AqzfDictService")
public class AqzfDictService {

	@Resource
	private AqzfDictDao aqzfDictDao;
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<AQZF_DictEntity> list=aqzfDictDao.dataGrid(mapData);
		int getTotalCount=aqzfDictDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 添加
	 * @param entity
	 */
	public void addInfo(AQZF_DictEntity entity) {
		aqzfDictDao.save(entity);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public AQZF_DictEntity findById(Long id) {
		return aqzfDictDao.find(id);
	}
	
	/**
	 * 修改
	 * @param entity
	 */
	public void updateInfo(AQZF_DictEntity entity) {
		aqzfDictDao.save(entity);
	}
	
	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		aqzfDictDao.deleteInfo(id);
	}
	
	/**
	 * 获取类型
	 * @param id
	 */
	public String getTypeList() {
		List<Map<String, Object>> list=aqzfDictDao.getTpyeList(); 
		List<Map<String, Object>> reslist=new ArrayList<>();
		for(Map<String, Object> map:list){
			Map<String, Object> map1=new HashMap<>();
			map1.put("id", map.get("m3"));
			map1.put("text", map.get("m2"));
			reslist.add(map1);
		}
		return JsonMapper.getInstance().toJson(reslist);
	}
	
	/**
	 * 根据类别获取字典
	 * @return
	 */
	public String findByType(String type) {
		List<Map<String, Object>> list=aqzfDictDao.findByType(type);
		return JsonMapper.getInstance().toJson(list);
	}
}
