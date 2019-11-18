package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.ISSUE_TfsjEntity;
import com.cczu.util.dao.BaseDao;

@Repository("IssueTdsjDao")
public class IssueTdsjDao extends BaseDao<ISSUE_TfsjEntity, Long>{

	/**
	 * 查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id desc) rownum, a.*,b.NAME fbr FROM issue_tfsj a left join t_user b on b.ID = a.id1 WHERE a.s3 = 0 "+content +") a "+
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
		String sql="SELECT COUNT(*) FROM issue_tfsj a left join t_user b on b.ID = a.id1 WHERE a.s3 = 0 "+content;
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
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND a.s1 >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content +" AND a.s1 <= '"+mapData.get("endtime")+"' "; 
		}
		if(mapData.get("m5")!=null&&mapData.get("m5")!=""){
			content = content +" AND a.m5 = '"+mapData.get("m5")+"' "; 
		}
		/*if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.xzqy like '"+mapData.get("xzqy")+"%' ";
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.userroleflg='"+mapData.get("jglx")+"' ";
		}*/
		return content;
	}

	/**
	 * 根据id删除突发事件
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE issue_tfsj SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public ISSUE_TfsjEntity findInfoById(long id) {	
		ISSUE_TfsjEntity a = find(id);
		flush();
		clear();
		return a;
	}

}
