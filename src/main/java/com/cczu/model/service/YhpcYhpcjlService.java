package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cczu.model.entity.ERM_ExerciseRecordEntity;
import com.cczu.model.entity.YHPC_CheckHiddenInfoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.WghglWgxjjlzgDao;
import com.cczu.model.dao.YhpcYhpcjlDao;


/**
 * 
 * @Description: 隐患排查记录Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("YhpcYhpcjlService")
public class YhpcYhpcjlService {
	@Resource
	private YhpcYhpcjlDao yhpcYhpcjlDao;
	@Resource
	private WghglWgxjjlzgDao wghglWgxjjlzgDao;
	
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
    public void updateInfo(YHPC_CheckHiddenInfoEntity erm) {
        yhpcYhpcjlDao.updateInfo(erm);
    }


    //根据id查找数据
    public YHPC_CheckHiddenInfoEntity findById(Long id) {
        return yhpcYhpcjlDao.findById(id);
    }
	
	//根据id删除巡检隐患记录
	public void deleteById(Long id) {
		//删除隐患记录对应的整改记录
		wghglWgxjjlzgDao.deleteYhrByYchid(id);
		//删除隐患记录
		yhpcYhpcjlDao.deleteById(id+"");
	}
	
	/**
	 * 检查记录list app
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridForApp(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcYhpcjlDao.dataGridForApp(mapData);
		int getTotalCount=yhpcYhpcjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 首页隐患app
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridForSyApp(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcYhpcjlDao.dataGridForSyApp(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
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

	/**
	 * 根据状态获取风险隐患整改信息
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getYhInfoByMap(Map<String, Object> mapData) {
		return yhpcYhpcjlDao.getYhInfoByMap(mapData);
	}

	/**
	 * 根据风险点id获取各个状态值的数量
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getStateCountByMap(Map<String, Object> mapData) {
		return yhpcYhpcjlDao.getStateCountByMap(mapData);
	}

}
