package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_DictEntity;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

/**
 * 安全执法_字典DAO
 * @author jason
 * @date 2017年8月3日
 */
@Repository("AqzfDictDao")
public class AqzfDictDao extends BaseDao<AQZF_DictEntity, Long> {
	/**
	 * 分页查询list
	 * @param mapData
	 * @return
	 */
	public List<AQZF_DictEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + " * FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by id) rownum, * FROM aqzf_dict WHERE 0 = 0 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ";
		List<AQZF_DictEntity> list=findBySql(sql,null,AQZF_DictEntity.class);
		return list;
	}

	/**
	 * 分页总数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM aqzf_dict WHERE 0 = 0 "+content;
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
		if(mapData.get("label")!=null&&mapData.get("label")!=""){
			content = content + "AND m1 like '%"+mapData.get("label")+"%' "; 
		}
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + "AND m3 = '"+mapData.get("type")+"' "; 
		}
	 
		return content;
	}
	
	/**
	 * 获取字典类别
	 * @return
	 */
	public List<Map<String, Object>> getTpyeList() {
		 
		String sql ="select MIN(id) id,m2,m3 FROM aqzf_dict group by m2,m3" ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	
	/**
	 * 根据类别查询字典
	 * @return
	 */
	public List<Map<String, Object>> findByType(String type) {
		 
		String sql ="select m1 id,m1 text FROM aqzf_dict where m3 like :p1 order by m4" ;
		Parameter  parameter=new Parameter(type);
		List<Map<String, Object>> list=findBySql(sql, parameter,Map.class);
		return list;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public void deleteInfo(Long id) {
		String sql=" delete from aqzf_dict  WHERE ID=:p1";
		Parameter  parameter=new Parameter(id);
		updateBySql(sql,parameter);
	}
	 
}
