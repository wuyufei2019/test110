package com.cczu.model.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.cczu.model.entity.WxxsCL;
import com.cczu.util.dao.BaseDao;

/**
 * 危化品储量危险系数DAO
 * @author jason
 *
 */
@Repository("ClWxxsDao")
public class ClWxxsDao extends BaseDao<WxxsCL, Integer>{

	/**
	 * 分页显示datalist
	 * @param mapData
	 * @return
	 */
	public List<WxxsCL> dataGrid(Map<String, Object> mapData) {
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY ID ASC) AS RowNumber,* FROM wxxs_wlcl ) "
				+ "AS a WHERE 0=0 and RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<WxxsCL> list=findBySql(sql, null,WxxsCL.class);

		return list;
	}

	/**
	 * 分页总数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) SUM  FROM wxxs_wlcl ";		
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 *  查询条件判断
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("id")!=null&&mapData.get("id")!=""){
			content = content + "AND ID ="+mapData.get("id")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + "AND m1 like '"+mapData.get("m1")+"' "; 
		}
		
		return content;
	}
	
	/**
	 * 根据条件查询
	 * @param mapData
	 * @return
	 */
	public WxxsCL findByContent(Map<String, Object> mapData){
		String content=content(mapData);
		String sql="SELECT * FROM wxxs_wlcl WHERE 1=1 "+content;		
		List<WxxsCL> list=findBySql(sql,null,WxxsCL.class);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	
	public void deleteInfro(int id){
		String sql="delete wxxs_wlcl where ID="+id;
		updateBySql(sql);
	}
}
