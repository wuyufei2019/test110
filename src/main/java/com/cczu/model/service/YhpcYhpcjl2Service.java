package com.cczu.model.service;

import com.cczu.model.dao.WghglWgxjjlzgDao;
import com.cczu.model.dao.YhpcYhpcjl2Dao;
import com.cczu.model.dao.YhpcYhpcjlDao;
import com.cczu.model.entity.YHPC_CheckHiddenInfo2Entity;
import com.cczu.model.entity.YHPC_CheckHiddenInfoEntity;
import com.cczu.model.entity.YHPC_CheckResult2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @Description: 隐患排查记录Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("YhpcYhpcjl2Service")
public class YhpcYhpcjl2Service {
	@Resource
	private YhpcYhpcjl2Dao yhpcYhpcjlDao;
	/**
	 * 检查记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcYhpcjlDao.dataGrid(mapData);
		int getTotalCount=yhpcYhpcjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//添加隐患记录
	public void add(YHPC_CheckHiddenInfo2Entity yhjl){
		yhpcYhpcjlDao.save(yhjl);
	}

	/**
	 * 双重机制大数据list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcYhpcjlDao.dataGrid2(mapData);
		int getTotalCount=yhpcYhpcjlDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//根据id查找数据
	public Map<String,Object> findInforById(Long id) {
		return yhpcYhpcjlDao.findInforById(id);
	}
    public void updateInfo(YHPC_CheckHiddenInfo2Entity erm) {
        yhpcYhpcjlDao.updateInfo(erm);
    }


    //根据id查找数据
    public YHPC_CheckHiddenInfo2Entity findById(Long id) {
        return yhpcYhpcjlDao.findById(id);
    }
	

	/**
	 * 获取整改记录
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getzglist(Long id) {
		List<Map<String,Object>> list=yhpcYhpcjlDao.getzglist(id);
		return list;
	}
}
