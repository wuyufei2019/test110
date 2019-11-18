package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckResultEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 巡检点状态
 * @author zpc
 *
 */
@Repository("YhpcXjdztDao")
public class YhpcXjdztDao extends BaseDao<YHPC_CheckResultEntity, Long>{

	/**
	 * 巡检点状态list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT a.id,a.jcdname,convert(varchar(10),a.createtime,120) xcsj,a.createtime,b.NAME uname,a.checkresult,a.jcdid,a.jcdtype,a.yanse,a.qyid FROM(SELECT b.checkresult,b.createtime,b.userid,b.id,a.id jcdid,a.m1 jcdname,a.id1 qyid,1 jcdtype,m9 yanse FROM fxgk_accidentrisk a "
				+ "LEFT JOIN (SELECT h.* FROM (SELECT ROW_NUMBER() OVER (partition by checkpoint_id,checkpointtype ORDER BY createtime DESC) AS rn,* FROM yhpc_checkresult "
				+ "WHERE checkpointtype= '1' ) h WHERE h.rn<=1) b ON a.id = b.checkpoint_id WHERE a.s3 = 0 UNION SELECT b.checkresult,b.createtime,b.userid,b.id,a.id jcdid,a.name jcdname,a.id1 qyid,2 jcdtype,5 yanse "
				+ "FROM yhpc_checkpoint a LEFT JOIN (SELECT h.* FROM (SELECT ROW_NUMBER() OVER (partition by checkpoint_id,checkpointtype ORDER BY createtime DESC) AS rn,* FROM yhpc_checkresult "
				+ "WHERE checkpointtype= '2' ) h WHERE h.rn<=1) b ON a.id = b.checkpoint_id where a.usetype = '2') a LEFT JOIN t_user b ON a.userid = b.id where 1=1 "+content + "ORDER BY yanse, a.checkresult DESC";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("jcdname")!=null&&mapData.get("jcdname")!=""){
			content = content + "AND a.jcdname like '%"+mapData.get("jcdname")+"%' "; 
		}
		if(mapData.get("checkresult")!=null&&mapData.get("checkresult")!=""){
			content = content + "AND a.checkresult = '"+mapData.get("checkresult")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.qyid = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("jcdid")!=null&&mapData.get("jcdid")!=""){
			content = content + "AND a.jcdid = '"+mapData.get("jcdid")+"' ";
		}
		return content;
	}
	
	/**
	 * 获取班次最新巡检记录
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataXjGrid(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND a.id1 = "+mapData.get("qyid")+" "; 
		}
		if(mapData.get("checkpointtype")!=null&&mapData.get("checkpointtype")!=""){
			content = content + "AND b.checkpointtype = "+mapData.get("checkpointtype")+" "; 
		}
		if(mapData.get("checkpointid")!=null&&mapData.get("checkpointid")!=""){
			content = content + "AND b.id2 = "+mapData.get("checkpointid")+" "; 
		}
		String sql = "SELECT h.* FROM (SELECT ROW_NUMBER() OVER (partition by c.checkplan_id ORDER BY c.createtime DESC) AS rn,a.name bcname,c.* "
				+ "FROM yhpc_checkplan a LEFT JOIN yhpc_checkplan_point b ON a.id = b.id1 LEFT JOIN yhpc_checkresult c ON a.id = c.checkplan_id AND c.checkpoint_id = b.id2 AND c.checkpointtype = b.checkpointtype "
				+ "WHERE 1=1 " + content + ") h WHERE h.rn<=1";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}
}
