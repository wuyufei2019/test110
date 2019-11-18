package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcGridKpiMonthDao;
import com.cczu.model.dao.YhpcGridKpiMonthOverviewDao;
import com.cczu.model.entity.YHPC_GridKpiMonth;
import com.cczu.model.entity.YHPC_GridKpiMonthOverview;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  网格化灌流--网格月度绩效考核结果Service
 */
@Transactional(readOnly=true)
@Service("YhpcGridKpiMonthService")
public class YhpcGridKpiMonthService {

	@Resource
	private YhpcGridKpiMonthDao yhpcGridKpiMonthDao;
	@Resource
	private YhpcGridKpiMonthOverviewDao dao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=yhpcGridKpiMonthDao.dataGrid(mapData);
		for(Map<String, Object> m : list){
			
		}
		int getTotalCount=yhpcGridKpiMonthDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	/**
	 * 评分页面分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridExamine(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcGridKpiMonthDao.dataGridExamine(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
	/**
	 * 评分页面分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridOverview(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcGridKpiMonthDao.dataGridOverview(mapData);
		int getTotalCount=yhpcGridKpiMonthDao.getTotalCountOverview(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 评分页面分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridDetail(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcGridKpiMonthDao.dataGridDetail(mapData);
		int getTotalCount=yhpcGridKpiMonthDao.getTotalCountDetail(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public Long addInfoReturnId(YHPC_GridKpiMonth mon) {
		yhpcGridKpiMonthDao.save(mon);
		return mon.getID();
	}
	//添加信息
	public void addInfo(YHPC_GridKpiMonth mon) {
		yhpcGridKpiMonthDao.save(mon);
	}
	//全部保存
	public void addAllInfo(List<YHPC_GridKpiMonth> list,YHPC_GridKpiMonthOverview ov) {
		Timestamp t = DateUtils.getSysTimestamp();
        long id;
        if(ov.getID()==null){
        	ov.setS1(t);
        	ov.setS2(t);
        	ov.setS3(0);
        	dao.save(ov);
        	id=ov.getID();
        }else{
        	id=ov.getID();
        	dao.updateInfo(ov);
        }
		int size=list.size();
		if(size>0){
			for(YHPC_GridKpiMonth mon:list){
				if(mon.getID()==null){//为空代表未添加，执行添加操作
					mon.setS2(t);
					mon.setS1(t);
					mon.setS3(0);
					mon.setId1(id);
					addInfo(mon);//添加月度考核细则
				}else{//执行更新操作
					updateInfo(mon);
				}
			}
		}
	}
	
	//初始化考核
    public void initMonthKpi(){
    	yhpcGridKpiMonthDao.initMonthKpi();
    }
	
	//更新信息
	public void updateInfo(YHPC_GridKpiMonth mon) {
		yhpcGridKpiMonthDao.updateInfo(mon);
	}
	
	//更新信息
	  public YHPC_GridKpiMonth findInfoByMap(long ID1,String wgcode,String time){
		return yhpcGridKpiMonthDao.findInfoByMap(ID1,wgcode,time);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		yhpcGridKpiMonthDao.delete(id);
	}

	//根据id查找详细信息
	public Map<String, Object> findInfoById(Long id) {
		return yhpcGridKpiMonthDao.findInfoById(id);
	}
	
}
