package com.cczu.model.lydw.dao;

import com.cczu.model.lydw.entity.LYDW_QYQX;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 区域权限
 * @author jason
 * @date 2017年8月3日
 */
@Repository("LYDW_QyqxDao")
public class LYDW_QyqxDao extends BaseDao<LYDW_QYQX, Long> {

	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + " * FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by id) rownum, * FROM lydw_qyqx WHERE 0 = 0 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ";
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 分页总数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM lydw_qyqx WHERE 0 = 0 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 区域权限总览
	 */
	public List<Map<String,Object>> dzwlData(Long qyid) {
		String sql = " SELECT * FROM lydw_qyqx where mappoint <> '' and id1 =" + qyid;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 区域权限总览
	 */
	public List<Map<String,Object>> wljson(Long qyid) {
		String sql = " SELECT DISTINCT name FROM lydw_qyqx where mappoint <> '' and id1 =" + qyid;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND id1 = "+mapData.get("qyid")+" ";
		}
		if(mapData.get("name")!=null&&mapData.get("name")!=""){
			content = content + "AND name like '%"+mapData.get("name")+"%' ";
		}
		if(mapData.get("floor")!=null&&mapData.get("floor")!=""){
			content = content + "AND floor = '"+mapData.get("floor")+"' ";
		}
		return content;
	}
	
}
