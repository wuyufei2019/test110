package com.cczu.model.dxj.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dxj.entity.DXJ_CheckPlanEntity;
import com.cczu.model.dxj.entity.DXJ_CheckPlan_Time;
import com.cczu.util.dao.BaseDao;

/**
 * 巡检班次任务设置
 * @author zpc
 *
 */
@Repository("DxjCheckPlanDao")
public class DxjCheckPlanDao extends BaseDao<DXJ_CheckPlanEntity, Long>{

	/**
	 * 查询巡检班次任务list
	 * @param mapData
	 * @return
	 */
	public List<DXJ_CheckPlanEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id) rownum,* FROM dxj_checkplan a where 1=1 " + content + " ) a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ";
		List<DXJ_CheckPlanEntity> list=findBySql(sql,null,DXJ_CheckPlanEntity.class);
		return list;
	}

	/**
	 * 查询list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM dxj_checkplan a where 1=1 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 我的任务list
	 * @param mapData
	 * @return
	 */
	public List<DXJ_CheckPlanEntity> myrwdataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id) rownum,a.* FROM dxj_checkplan a left join dxj_checkplan_user c on c.id1 = a.id where 1=1 " + content + " ) a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ";
		List<DXJ_CheckPlanEntity> list=findBySql(sql,null,DXJ_CheckPlanEntity.class);
		return list;
	}

	/**
	 * 我的任务list个数
	 * @param mapData
	 * @return
	 */
	public int getmyrwTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM dxj_checkplan a left join dxj_checkplan_user c on c.id1 = a.id where 1=1 " + content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + " AND a.type ="+mapData.get("type"); 
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content + " AND a.name like '%"+mapData.get("name")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + " AND a.id1 ="+mapData.get("qyid"); 
		}
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content + " AND c.id2 = '"+mapData.get("userid")+"' "; 
		}
		return content;
	}
	
	//根据id1删除时间中间表
	public void deletexjbcsjInfo(long id1) {
		String sql=" delete dxj_checkplan_time WHERE id1="+id1;
		updateBySql(sql);
	}

	//根据id1删除人员中间表
	public void deletexjbcryInfo(long id1) {
		String sql=" delete dxj_checkplan_user WHERE id1="+id1;
		updateBySql(sql);
	}

	//根据id1删除检查点中间表
	public void deletexjbcjcdInfo(long id1) {
		String sql=" delete dxj_checkplan_point WHERE id1="+id1;
		updateBySql(sql);
	}

	//根据id删除
	public void deleteInfo(long id) {
		String sql=" delete dxj_checkplan WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 查询班次任务时间list
	 * @param mapData
	 * @return
	 */
	public List<DXJ_CheckPlan_Time> rwsjList(Long id1) {
		String sql = "select * from dxj_checkplan_time where id1="+id1;
		List<DXJ_CheckPlan_Time> list=findBySql(sql,null,DXJ_CheckPlan_Time.class);
		return list;
	}

	//巡检人员
	public List<Map<String,Object>> getidname(long id1) {
		String sql = "SELECT b.ID,b.NAME,b.GENDER,b.PHONE FROM dxj_checkplan_user a LEFT JOIN t_user b ON a.id2 = b.ID "
				+ "WHERE a.id1 = "+id1;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	//设备项目巡检点
	public List<Map<String,Object>> getidname2(long id1) {
		String sql = "SELECT b.*,c.m1 sbm FROM dxj_checkplan_point a"
				+ " LEFT JOIN dxj_sbxjd b ON a.id2 = b.id LEFT JOIN dxj_sb c ON b.id2 = c.id WHERE a.id1 ="+id1;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 添加巡检班次任务
	 * @param zfry
	 */
	public long addInfo(DXJ_CheckPlanEntity bcrw) {
		save(bcrw);
		return bcrw.getID();
	}
}
