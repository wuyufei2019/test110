package com.cczu.model.dao;

import com.cczu.model.entity.YHPC_CheckResult2Entity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 巡检记录dao层
 * zpc
 */
@Repository("YhpcXjjl2Dao")
public class YhpcXjjl2Dao extends BaseDao<YHPC_CheckResult2Entity, Long>{

	/**
	 * 企业巡检记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont="order by a.createtime DESC";
		if("checkresult".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.checkresult "+mapData.get("order");
		else if("createtime".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.createtime "+mapData.get("order");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* "
				+ "FROM yhpc_checkresult2 a WHERE 1=1 " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询企业巡检记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkresult2 a WHERE 1=1 "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 企业查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("jcdname")!=null&&mapData.get("jcdname")!=""){
			content = content + "AND a.checkname like '%"+mapData.get("jcdname")+"%' ";
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND a.createtime >= '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND a.createtime <= '"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("checkresult")!=null&&mapData.get("checkresult")!=""){
			content = content + "AND a.checkresult = '"+mapData.get("checkresult")+"' "; 
		}

		return content;
	}

	/**
	 * 根据id查找巡检记录
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql =" SELECT a.id,a.checkpointname,a.checkorder,a.checkperson,a.createtime,(case a.checkresult when '0' then '无隐患' when '1' then '有隐患' end) checkresult,a.note,a.checkphoto "
				+ "FROM yhpc_checkresult2 a WHERE 1=1 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 删除巡检记录信息
	 */
	public void deleteInfor(Long id){
		String sql="delete from yhpc_checkresult2 where id="+id;
		updateBySql(sql);
	}
	
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT h.*,(CASE h.checkresult when 0 then '无隐患' when 1 then '有隐患' ELSE '' END) lx FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,e.m1 qyname,d.jcdname,(case when b.name is null then '该班次已被删除' else b.name end) name,CONVERT(varchar(100), a.createtime, 120)createtime,c.NAME uname,a.checkresult,a.note,a.checkphoto "
				+ "FROM yhpc_checkresult2 a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id LEFT JOIN t_user c ON c.ID = a.userid LEFT JOIN "
				+ "(SELECT a.id,b.m1 jcdname,b.id1 qyid,b.m23 iszdwxy FROM yhpc_checkresult2 a LEFT JOIN fxgk_accidentrisk b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '1' AND b.s3 = 0 "
				+ "UNION SELECT a.id,b.name jcdname,b.id1 qyid,b.iszdwxy FROM yhpc_checkresult2 a LEFT JOIN yhpc_checkpoint b ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' and b.usetype = '2') d ON d.id = a.id "
				+ "LEFT JOIN bis_enterprise e ON e.id = d.qyid WHERE e.s3 = 0 " + content + " ) "
				+ "as h WHERE 1=1 order by createtime desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
}
