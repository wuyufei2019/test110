package com.cczu.model.dxj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dxj.dao.DxjCheckPlanDao;
import com.cczu.model.dxj.dao.DxjCheckPlanPointDao;
import com.cczu.model.dxj.dao.DxjCheckPlanTimeDao;
import com.cczu.model.dxj.dao.DxjCheckPlanUserDao;
import com.cczu.model.dxj.entity.DXJ_CheckPlanEntity;
import com.cczu.model.dxj.entity.DXJ_CheckPlan_Point;
import com.cczu.model.dxj.entity.DXJ_CheckPlan_Time;
import com.cczu.model.dxj.entity.DXJ_CheckPlan_User;

/**
 * 巡检班次任务设置
 * @author zpc
 */
@Service("DxjCheckPlanService")
public class DxjCheckPlanService {

	@Resource
	private DxjCheckPlanDao dxjCheckPlanDao;
	@Resource
	private DxjCheckPlanPointDao dxjCheckPlanPointDao;
	@Resource
	private DxjCheckPlanUserDao dxjCheckPlanUserDao;
	@Resource
	private DxjCheckPlanTimeDao dxjCheckPlanTimeDao;
	
	/**
	 * 查询巡检班次任务list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<DXJ_CheckPlanEntity> list=dxjCheckPlanDao.dataGrid(mapData);
		int getTotalCount=dxjCheckPlanDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 我的任务list
	 * @param map
	 * @return
	 */
	public Map<String, Object> myrwdataGrid(Map<String, Object> mapData) {
		List<DXJ_CheckPlanEntity> list=dxjCheckPlanDao.myrwdataGrid(mapData);
		int getTotalCount=dxjCheckPlanDao.getmyrwTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		dxjCheckPlanDao.deleteInfo(id);
	}

	/**
	 * 根据id1删除时间中间表
	 * @param parseLong
	 */
	public void deletexjbcsjInfo(long id1) {
		dxjCheckPlanDao.deletexjbcsjInfo(id1);
	}
	
	/**
	 * 根据id1删除人员中间表
	 * @param parseLong
	 */
	public void deletexjbcryInfo(long id1) {
		dxjCheckPlanDao.deletexjbcryInfo(id1);
	}

	/**
	 * 根据id1删除检查点中间表
	 * @param parseLong
	 */
	public void deletexjbcjcdInfo(long id1) {
		dxjCheckPlanDao.deletexjbcjcdInfo(id1);
	}
	
	/**
	 * 根据id查找巡检班次任务
	 * @param id
	 * @return
	 */
	public DXJ_CheckPlanEntity findById(Long id) {
		return dxjCheckPlanDao.find(id);
	}
	
	/**
	 * 查询班次任务时间list
	 * @param id1
	 * @return
	 */
	public List<DXJ_CheckPlan_Time> rwsjList(Long id1) {
		return dxjCheckPlanDao.rwsjList(id1);
	}
	
	public List<Map<String,Object>> getidname(long id1){
		return dxjCheckPlanDao.getidname(id1);
	}
	
	public List<Map<String,Object>> getidname2(long id1){
		return dxjCheckPlanDao.getidname2(id1);
	}
	
	/**
	 * 添加巡检班次任务
	 * @param zfry
	 */
	public long addInfo(DXJ_CheckPlanEntity bcrw) {
		return dxjCheckPlanDao.addInfo(bcrw);
	}
	
	/**
	 * 添加巡检班次任务时间中间表
	 * @param zfry
	 */
	public void addxjbcsjInfo(DXJ_CheckPlan_Time bcrw) {
		dxjCheckPlanTimeDao.save(bcrw);
	}
	
	/**
	 * 添加巡检班次人员中间表
	 * @param zfry
	 */
	public void addxjbcryInfo(DXJ_CheckPlan_User bcrw) {
		dxjCheckPlanUserDao.save(bcrw);
	}
	
	/**
	 * 添加巡检班次检查点中间表
	 * @param zfry
	 */
	public void addxjbcjcdInfo(DXJ_CheckPlan_Point bcrw) {
		dxjCheckPlanPointDao.save(bcrw);
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(DXJ_CheckPlanEntity bcrw) {
		dxjCheckPlanDao.save(bcrw);
	}
}
