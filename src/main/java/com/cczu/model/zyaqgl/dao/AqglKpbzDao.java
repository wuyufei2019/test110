package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_Criteria;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-相关方考评标准
 */
@Repository("AqglKpbzDao")
public class AqglKpbzDao extends BaseDao<AQGL_Criteria, Long> {

	//查询考评标准类别
	public List<Map<String,Object>> findType() {
		String sql = " select distinct a.m1 as text from aqgl_criteria a " ;
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	//查询考评类别查询考评内容
	public List<Map<String,Object>> findContent(String type) {
		String sql = " select a.m2 as text from aqgl_criteria a where m1='"+type+"'" ;
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	//根据类别和内容查询详细信息
	public Map<String,Object> findInfo(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = " select * from aqgl_criteria a where 1=1 "+content ;
		List<Map<String,Object>> list = findBySql(sql, null, Map.class);
		return list.size() > 0 ? list.get(0) : null;
	}

    //查询条件
	public String content(Map<String,Object> mapData) {
		String content = "";
		if (mapData.get("type") != null && mapData.get("type") != "") {
			content += "and a.m1='" + mapData.get("type")+"'";
		}
		if (mapData.get("content") != null && mapData.get("content") != "") {
			content += "and a.m2='" + mapData.get("content")+"'";
		}
		return content;
	}
}
