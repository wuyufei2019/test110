package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_WfxwEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqzfWfxwDao")
public class AqzfWfxwDao extends BaseDao<AQZF_WfxwEntity, Long>{

	/**
	 * 查询违法行为list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id) rownum, a.* FROM aqzf_wfxw a WHERE a.s3 = 0 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM aqzf_wfxw a WHERE a.s3 = 0 "+content;
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
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like '%"+mapData.get("m1")+"%' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE aqzf_wfxw SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加违法行为信息
	 * @param wfxw
	 */
	public void addInfo(AQZF_WfxwEntity wfxw) {
		save(wfxw);
	}
	
	/**
	 * 根据id查找违法行为信息
	 * @param id
	 * @return
	 */
	public AQZF_WfxwEntity findInfoById(long id) {	
		AQZF_WfxwEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param wfxw
	 */
	public void updateInfo(AQZF_WfxwEntity wfxw) {
		save(wfxw);
	}
	
	/**
	 * 获得导出list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.* FROM aqzf_wfxw a WHERE a.s3 = 0 "+content+" order by a.id";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
	 * 获得违法行为list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getWfxwIdlist() {
		String sql="SELECT a.id as id,a.m1 as text FROM aqzf_wfxw a WHERE a.s3 = 0 order by a.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
