package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.ISSUE_SjplEntity;
import com.cczu.util.dao.BaseDao;

@Repository("IssueSjpjDao")
public class IssueSjpjDao extends BaseDao<ISSUE_SjplEntity, Long>{

	/**
	 * 根据事件id删除事件评论
	 * @param id
	 */
	public void deleteInfoByid1(long id1) {
		String sql=" delete issue_sjpl WHERE id1="+id1;
		updateBySql(sql);
	}
	
	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.m2 ) rownum, a.*,b.NAME plr FROM issue_sjpl a left join t_user b on b.ID = a.id2 WHERE 1=1 "+content +") a "+
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
		String sql="SELECT COUNT(*) FROM issue_sjpl a WHERE 1=1 "+content;
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
		if(mapData.get("id1")!=null&&mapData.get("id1")!=""){
			content = content +" AND a.id1 = "+mapData.get("id1")+" "; 
		}
		return content;
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public ISSUE_SjplEntity findInfoById(long id) {	
		ISSUE_SjplEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 根据id删除事件评论
	 * @param id
	 */
	public void deleteInfoByid(long id) {
		String sql=" delete issue_sjpl WHERE id="+id;
		updateBySql(sql);
	}
}
