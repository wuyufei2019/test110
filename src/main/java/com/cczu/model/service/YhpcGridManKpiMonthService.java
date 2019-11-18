package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcGridManKpiMonthDao;
import com.cczu.model.dao.YhpcGridManKpiMonthOverviewDao;
import com.cczu.model.entity.YHPC_GridKpiMonth;
import com.cczu.model.entity.YHPC_GridKpiMonthOverview;
import com.cczu.model.entity.YHPC_GridManKpiMonth;
import com.cczu.model.entity.YHPC_GridManKpiMonthOverview;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  网格化灌流--网格员月度绩效考核结果Service
 *
 */
@Transactional(readOnly=true)
@Service("YhpcGridManKpiMonthService")
public class YhpcGridManKpiMonthService {

	@Resource
	private YhpcGridManKpiMonthDao yhpcGridManKpiMonthDao;
	@Resource
	private YhpcGridManKpiMonthOverviewDao dao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcGridManKpiMonthDao.dataGrid(mapData);
		int getTotalCount=yhpcGridManKpiMonthDao.getTotalCount(mapData);
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
		
		List<Map<String,Object>> list=yhpcGridManKpiMonthDao.dataGridExamine(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		return map;
	}
	/**
	 * 评分页面分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridDetail(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcGridManKpiMonthDao.dataGridDetail(mapData);
		int getTotalCount=yhpcGridManKpiMonthDao.getTotalCountDetail(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public int getCount(Map<String, Object> mapData){
		int getTotalCount=yhpcGridManKpiMonthDao.getTotalCount(mapData);
		return getTotalCount;
	}
	//全部保存
	public void addAllInfo(List<YHPC_GridManKpiMonth> list,YHPC_GridManKpiMonthOverview ov) {
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
			for(YHPC_GridManKpiMonth mon:list){
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
	
	//添加信息
	public Long addInfoReturnId(YHPC_GridManKpiMonth mon) {
		yhpcGridManKpiMonthDao.save(mon);
		return mon.getID();
	}
	//添加信息
	public void addInfo(YHPC_GridManKpiMonth mon) {
		yhpcGridManKpiMonthDao.save(mon);
	}
	//初始化考核
    public void initMonthKpi(){
    	yhpcGridManKpiMonthDao.initMonthKpi();
    }
	
	//更新信息
	public void updateInfo(YHPC_GridManKpiMonth mon) {
		yhpcGridManKpiMonthDao.updateInfo(mon);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		yhpcGridManKpiMonthDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public  Map<String,Object> findById(Long id) {
		return yhpcGridManKpiMonthDao.findInfoById(id);
	}
	//根据id查找详细信息
	  public YHPC_GridManKpiMonth findInfoByMap(long ID1,long ID2){
		return yhpcGridManKpiMonthDao.findInfoByMap(ID1,ID2);
	}
	
}
