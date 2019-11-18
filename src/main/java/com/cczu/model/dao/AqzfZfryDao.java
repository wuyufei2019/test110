package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_TipstaffEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqzfZfryDao")
public class AqzfZfryDao extends BaseDao<AQZF_TipstaffEntity, Long>{

	/**
	 * 查询执法人员list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.m1) rownum, a.* FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 WHERE a.s3 = 0 "+content +") a "+
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
		String sql="SELECT COUNT(*) FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 WHERE a.s3 = 0 "+content;
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
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND b.xzqy = '"+mapData.get("xzqy")+"' "; 
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.userroleflg='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("xm")!=null&&mapData.get("xm")!=""){
			content = content + "AND a.m1 like'%"+mapData.get("xm")+"%' "; 
		}
		if(mapData.get("zc")!=null&&mapData.get("zc")!=""){
			content = content + "AND a.m4 like'%"+mapData.get("zc")+"%' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" UPDATE aqzf_tipstaff SET s3=1 WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加执行人员信息
	 * @param zfry
	 */
	public void addInfo(AQZF_TipstaffEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 根据id查找执行人员信息
	 * @param id
	 * @return
	 */
	public AQZF_TipstaffEntity findInfoById(long id) {	
		AQZF_TipstaffEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param zfry
	 */
	public void updateInfo(AQZF_TipstaffEntity zfry) {
		save(zfry);
	}
	
	/**
	 * 获得导出list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.* FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 WHERE a.s3 = 0 "+content+" order by a.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	/**
	 * 获得执法人员list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getZfryIdlist(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.id as id,a.m1 as text FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 WHERE a.s3 = 0 "+content+" order by a.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据姓名查找
	 * @param M1
	 * @return
	 */
	public AQZF_TipstaffEntity findByM1(String m1) {
		String sql="SELECT * FROM aqzf_tipstaff WHERE s3 = 0 and m1='"+m1+"'";
		List<AQZF_TipstaffEntity> list=findBySql(sql, null,AQZF_TipstaffEntity.class); 
		return list.get(0);
	}

	/**
	 * 获得执法人员姓名职务list填充下拉框
	 * @return
	 */
	public List<Map<String, Object>> getZfryXmzwlist(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.id as id,a.m1+'-'+a.m4 as text FROM aqzf_tipstaff a left join t_user b on b.ID=a.id1 WHERE a.s3 = 0 "+content+" order by a.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
