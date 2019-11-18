package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckResultEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 网格员巡检记录dao层
 * 
 */
@Repository("WghglWgxjjlDao")
public class WghglWgxjjlDao extends BaseDao<YHPC_CheckResultEntity, Long>{

	/**
	 * 网格巡检记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont="ORDER BY a.createtime DESC,e.id";
		if("qyname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY d.id "+mapData.get("order");
		else if("wgname".equals(mapData.get("orderBy")))
			ordercont="ORDER BY e.id "+mapData.get("order");
		else if("checkresult".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.checkresult "+mapData.get("order");
		else if("createtime".equals(mapData.get("orderBy")))
			ordercont="ORDER BY a.createtime "+mapData.get("order");
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,e.m1 wgname,b.name wgdname,d.m1 qyname,f.NAME uname,c.name bcname FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN yhpc_checkplan c ON a.checkplan_id = c.id "
				+ "LEFT JOIN bis_enterprise d ON b.id1 = d.id LEFT JOIN t_barrio e ON e.code = d.id2 "
				+ "LEFT JOIN t_user f ON f.ID = a.userid WHERE a.checkpointtype = '2' AND b.usetype = '1' AND d.s3 = 0 "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询网格巡检记录list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN yhpc_checkplan c ON a.checkplan_id = c.id "
				+ "LEFT JOIN bis_enterprise d ON b.id1 = d.id LEFT JOIN t_barrio e ON e.code = d.id2 "
				+ "LEFT JOIN t_user f ON f.ID = a.userid WHERE a.checkpointtype = '2' AND b.usetype = '1' AND d.s3 = 0 "+content;
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
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND a.createtime >='"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content + "AND a.createtime <='"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND d.id = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND d.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("checkresult")!=null&&mapData.get("checkresult")!=""){
			content = content + "AND a.checkresult = '"+mapData.get("checkresult")+"' "; 
		}
		if(mapData.get("xjjlxzqy")!=null&&mapData.get("xjjlxzqy")!=""){
			content = content + "AND d.id2 = '"+mapData.get("xjjlxzqy")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND d.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND d.m36='"+mapData.get("jglx")+"' "; 
		}
		
		//统计分析条件
		if(mapData.get("xjdid")!=null&&mapData.get("xjdid")!=""){
			content = content + "AND a.checkpoint_id = '"+mapData.get("xjdid")+"' "; 
		}
		if(mapData.get("xjryid")!=null&&mapData.get("xjryid")!=""){
			content = content + "AND a.userid = '"+mapData.get("xjryid")+"' "; 
		}
		if(mapData.get("bcid")!=null&&mapData.get("bcid")!=""){
			content = content + "AND a.checkplan_id = '"+mapData.get("bcid")+"' "; 
		}
		if(mapData.get("yf")!=null&&mapData.get("yf")!=""){
			content = content + "AND c.type = '3' AND convert(varchar(7),a.createtime,120)= '"+mapData.get("yf")+"' "; 
		}
		if(mapData.get("nf")!=null&&mapData.get("yf")!=""){
			content = content + "AND c.type = '4' AND convert(varchar(4),a.createtime,120)= '"+mapData.get("nf")+"' "; 
		}
		return content;
	}
	
	/**
	 * 根据id查找巡检记录
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql ="SELECT a.*,e.m1 wgname,b.name wgdname,d.m1 qyname,f.NAME uname,c.name bcname FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN yhpc_checkplan c ON a.checkplan_id = c.id "
				+ "LEFT JOIN bis_enterprise d ON b.id1 = d.id LEFT JOIN t_barrio e ON e.code = d.id2 "
				+ "LEFT JOIN t_user f ON f.ID = a.userid WHERE a.checkpointtype = '2' AND b.usetype = '1' AND d.s3 = 0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" delete yhpc_checkresult WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 网格巡检记录list app
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.createtime DESC) AS RowNumber,a.*,e.m1 wgname,b.name jcdname,d.m1 qyname,f.NAME uname,c.name,a.checkpointtype type,a.checkpoint_id jcdid FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN yhpc_checkplan c ON a.checkplan_id = c.id "
				+ "LEFT JOIN bis_enterprise d ON b.id1 = d.id LEFT JOIN t_barrio e ON e.code = d.id2 "
				+ "LEFT JOIN t_user f ON f.ID = a.userid WHERE a.checkpointtype = '2' AND b.usetype = '1' AND d.s3 = 0 "+content+")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 根据网格id获取该网格点最新的巡检记录 app
	 */
	public Map<String, Object> getnewXjjlForApp(Long checkpointid) {
		String sql ="SELECT top 1 a.*,e.m1 wgname,b.name jcdname,d.m1 qyname,f.NAME uname,c.name,a.checkpointtype type,a.checkpoint_id jcdid FROM yhpc_checkresult a "
				+ "LEFT JOIN yhpc_checkpoint b ON a.checkpoint_id = b.id LEFT JOIN yhpc_checkplan c ON a.checkplan_id = c.id "
				+ "LEFT JOIN bis_enterprise d ON b.id1 = d.id LEFT JOIN t_barrio e ON e.code = d.id2 "
				+ "LEFT JOIN t_user f ON f.ID = a.userid WHERE a.checkpointtype = '2' AND b.usetype = '1' AND d.s3 = 0 and a.checkpoint_id = "+ checkpointid +" order by a.createtime desc" ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
