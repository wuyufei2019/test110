package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.XZCF_InterrogationRecordEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XzcfXwblDao")
public class XzcfXwblDao extends BaseDao<XZCF_InterrogationRecordEntity, Long>{

	/**
	 * 查询询问笔录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id desc) rownum,a.* from xzcf_interrogationrecord a left join xzcf_ybcflasp b on b.id = a.id2 left join bis_enterprise c on c.id = b.id1 left join xzcf_enquirynote d on d.id3 = b.id left join t_user u on b.userid = u.id WHERE a.s3 = 0 and b.s3=0 and c.s3=0 and d.s3=0 "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ORDER BY a.id";
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
		String sql="SELECT COUNT(a.id) FROM xzcf_interrogationrecord a left join xzcf_ybcflasp b on b.id = a.id2 left join bis_enterprise c on c.id = b.id1 left join xzcf_enquirynote d on d.id3 = b.id left join t_user u on b.userid = u.id WHERE a.s3 = 0 and b.s3=0 and c.s3=0 and d.s3=0 "+content;
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
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + " AND a.m8 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content + " AND a.m1 >='"+mapData.get("m1")+"' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content + " AND a.m2 <='"+mapData.get("m2")+"' "; 
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
		String sql=" UPDATE xzcf_interrogationrecord SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加询问笔录信息
	 * @param zfry
	 */
	public void addInfo(XZCF_InterrogationRecordEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 根据id查找询问笔录信息
	 * @param id
	 * @return
	 */
	public XZCF_InterrogationRecordEntity findInfoById(long id) {	
		XZCF_InterrogationRecordEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(XZCF_InterrogationRecordEntity zfry) {
		save(zfry);
	}
}
