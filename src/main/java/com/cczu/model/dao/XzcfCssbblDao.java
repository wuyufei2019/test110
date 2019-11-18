package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.XZCF_CssbblEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XzcfCssbblDao")
public class XzcfCssbblDao extends BaseDao<XZCF_CssbblEntity, Long>{

	/**
	 * 查询陈述申辩list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id desc) rownum,a.* FROM xzcf_cssbbl a LEFT JOIN xzcf_ybcflasp b ON a.id1 = b.id left join t_user u on b.userid = u.id "
		           + "WHERE a.s3 = 0 AND b.s3 = 0 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)  ";
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
		String sql="SELECT COUNT(*) FROM xzcf_cssbbl a LEFT JOIN xzcf_ybcflasp b ON a.id1 = b.id left join t_user u on b.userid = u.id "
		           + "WHERE a.s3 = 0 AND b.s3 = 0 "+content;
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
		if(mapData.get("m11")!=null&&mapData.get("m11")!=""){
			content = content + "AND a.m11 like '%"+mapData.get("m11")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND u.xzqy = '"+mapData.get("xzqy")+"' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE xzcf_cssbbl SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id改变立案审批状态
	 * @param id
	 */
	public void updateLaspInfo(long id) {
		String sql=" update b set sbflag=0  from  xzcf_cssbbl  a LEFT JOIN xzcf_ybcflasp b on b.id=a.id1 where a.id= "+id;
		updateBySql(sql);
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public XZCF_CssbblEntity findInfoById(long id) {	
		XZCF_CssbblEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 根据立案id获取word数据
	 * @param laid
	 * @return
	 */
	public XZCF_CssbblEntity findWordByLaId(long laid) {
		String sql="select a.* FROM xzcf_cssbbl a left join xzcf_ybcflasp b on a.id1 = b.id where a.s3=0 and b.s3=0 and b.id ="+laid;
		List<XZCF_CssbblEntity> list=findBySql(sql,null,XZCF_CssbblEntity.class);
		return list.get(0);
	}
}
