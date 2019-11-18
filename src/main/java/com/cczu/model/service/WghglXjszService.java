package com.cczu.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.YhpcCheckPlanDao;
import com.cczu.model.dao.YhpcCheckPlanPointDao;
import com.cczu.model.entity.YHPC_CheckPlanEntity;
import com.cczu.model.entity.YHPC_CheckPlan_Point;

/**
 * 巡检班次任务设置
 * @author zpc
 */
@Service("WghglXjszService")
public class WghglXjszService {

	@Resource
	private YhpcCheckPlanDao yhpcCheckPlanDao;
	@Resource
	private YhpcCheckPlanPointDao yhpcCheckPlanPointDao;
	
	/**
	 * 查询巡检班次任务list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcCheckPlanDao.wgbcdataGrid(mapData);
		int getTotalCount=yhpcCheckPlanDao.wgbcgetTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 查询网格点list
	 * @param map
	 * @return
	 */
	public Map<String, Object> jcddataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcCheckPlanDao.wgddataGrid(mapData);
		int getTotalCount=yhpcCheckPlanDao.getwgdTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据网格id获取该网格直属网格点
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> getWgdByWgid(long wgid) {
		return yhpcCheckPlanDao.getWgdByWgid(wgid);
	}
	
	/**
	 * 添加网格点班次任务
	 * @param zfry
	 */
	public long addInfo(YHPC_CheckPlanEntity bcrw) {
		return yhpcCheckPlanDao.addInfo(bcrw);
	}

	/**
	 * 添加巡检班次检查点中间表
	 * @param zfry
	 */
	public void addxjbcjcdInfo(YHPC_CheckPlan_Point bcrw) {
		yhpcCheckPlanPointDao.save(bcrw);
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		yhpcCheckPlanDao.deleteInfo(id);
	}

	/**
	 * 根据id1删除检查点中间表
	 * @param parseLong
	 */
	public void deletexjbcjcdInfo(long id1) {
		yhpcCheckPlanDao.deletexjbcjcdInfo(id1);
	}
	
	/**
	 * 根据id查找巡检班次任务
	 * @param id
	 * @return
	 */
	public YHPC_CheckPlanEntity findById(Long id) {
		return yhpcCheckPlanDao.find(id);
	}
	
	public List<Map<String,Object>> getidname2(long id1){
		List<YHPC_CheckPlan_Point> list = yhpcCheckPlanDao.getlistbyid1(id1);
		List<Map<String,Object>> listall = new ArrayList<>();
		if(list.size()>0){
			for (YHPC_CheckPlan_Point y : list) {
				if((y.getCheckpointtype().trim()).equals("2")){
					Map<String,Object> map = yhpcCheckPlanDao.getidname3(y.getID());
					if(map!=null && map.get("id")!=null){
						listall.add(map);
					}
				}
			}
		}
		return listall;
	}	
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(YHPC_CheckPlanEntity bcrw) {
		yhpcCheckPlanDao.save(bcrw);
	}

	/**
	 * 查看网格员的班次 app
	 * @return
	 */
	public List<Map<String,Object>> wgybcForApp(String xzqy) {
		return yhpcCheckPlanDao.wgybcForApp(xzqy);
	}
}
