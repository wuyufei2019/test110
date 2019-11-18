package com.cczu.model.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckPointEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查点dao层
 *
 */
@Repository("YhpcYhpcdDao")
public class YhpcYhpcdDao extends BaseDao<YHPC_CheckPointEntity, Long>{

	/**
	 * 查询隐患排查点list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.*,b.m1 qyname,z.id stoppointid FROM yhpc_checkpoint a "
				+ " left join bis_enterprise b on b.id=a.id1 LEFT JOIN yhpc_stoppoint z ON z.id1 = a.id AND z.type = '2' WHERE b.S3=0 and a.usetype='2' " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询隐患排查点list的个数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM yhpc_checkpoint a left join bis_enterprise b on b.id=a.id1 WHERE b.s3=0 and a.usetype='2' "+content;
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
		if(mapData.get("yt")!=null&&mapData.get("yt")!=""){
			content = content + "AND a.usetype ='"+mapData.get("yt")+"' "; 
		}
		if(mapData.get("yhdname")!=null&&mapData.get("yhdname")!=""){
			content = content + "AND a.name like '%"+mapData.get("yhdname")+"%' "; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content + "AND a.createtime >'"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content + "AND a.createtime <'"+mapData.get("endtime")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content + "AND b.id = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.id2 like '"+mapData.get("xzqy")+"%' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		return content;
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	public void deleteInfo(long id) {
		String sql=" delete yhpc_checkpoint WHERE id="+id;
		updateBySql(sql);
	}
	
	/**
	 * 添加隐患排查点信息
	 * @param clcs
	 */
	public void addInfo(YHPC_CheckPointEntity clcs) {
		save(clcs);
	}
	
	/**
	 * 根据id查找隐患排查点信息
	 * @param id
	 * @return
	 */
	public YHPC_CheckPointEntity findInfoById(long id) {	
		YHPC_CheckPointEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 修改
	 * @param clcs
	 */
	public void updateInfo(YHPC_CheckPointEntity clcs) {
		save(clcs);
	}

	/**
	 * 获得导出list（企业）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT a.*,b.m1 qyname FROM yhpc_checkpoint a left join bis_enterprise b on a.id1=b.id WHERE b.s3=0 and a.usetype='2' "+content+" order by b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
		
	/**
	 * 根据id查询隐患排查点详细信息
	 * @return
	 */
	public Map<String, Object> findInforById(Long id) {
		String sql="SELECT a.*,b.m1 qyname,b.m33_3 pmt FROM yhpc_checkpoint a left join bis_enterprise b on b.id=a.id1  WHERE b.S3=0 AND a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list.get(0);
	}

	//绑定巡检内容list
	public List<Map<String, Object>> xjnrdataGrid(Map<String, Object> mapData) {
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY b.dangerlevel) AS RowNumber,a.id2 id,b.checktitle,b.dangerlevel,b.content,b.checkyes,b.checkno FROM yhpc_checkpoint_content a LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id where b.usetype = '2' and a.id1 ="+ mapData.get("id1")+") "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public int getxjnrTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM yhpc_checkpoint_content a LEFT JOIN yhpc_checkcontent b ON a.id2 = b.id where b.usetype = '2' and a.id1 ="+ mapData.get("id1");
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}
	
	//删除绑定巡检内容
	public void deleteXjnr(long id) {
		String sql=" delete yhpc_checkpoint_content WHERE id="+id;
		updateBySql(sql);
	}
	
	//根据id1删除绑定巡检内容
	public void deletexjnrbyid1(long id) {
		String sql=" delete yhpc_checkpoint_content WHERE id1="+id;
		updateBySql(sql);
	}
	
	//巡检内容list
	public List<Map<String, Object>> xjnralldataGrid(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("checktitle")!=null&&mapData.get("checktitle")!=""){
			content = content + "AND checktitle LIKE '%"+mapData.get("checktitle")+"%' "; 
		}
		if(mapData.get("yhjb")!=null&&mapData.get("yhjb")!=""){
			content = content + "AND dangerlevel = '"+mapData.get("yhjb")+"' "; 
		}
		String sql  ="SELECT top  "+mapData.get("pageSize")+" * FROM ("
				+ "SELECT ROW_NUMBER() OVER (ORDER BY dangerlevel) AS RowNumber,* FROM yhpc_checkcontent "
				+ "WHERE (id1 = 0 OR id1 = "+mapData.get("qyid")+") AND usetype = '2' AND id not IN (SELECT id2 FROM yhpc_checkpoint_content WHERE id1 = '"+mapData.get("id1")+"') "+content+" ) "
				+ " as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public int getxjnrallTotalCount(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("checktitle")!=null&&mapData.get("checktitle")!=""){
			content = content + "AND checktitle LIKE '%"+mapData.get("checktitle")+"%' "; 
		}
		if(mapData.get("yhjb")!=null&&mapData.get("yhjb")!=""){
			content = content + "AND dangerlevel = '"+mapData.get("yhjb")+"' "; 
		}
		String sql="SELECT COUNT(*) FROM yhpc_checkcontent "
				+ "WHERE (id1 = 0 OR id1 = "+mapData.get("qyid")+") AND usetype = '2' AND id not IN (SELECT id2 FROM yhpc_checkpoint_content WHERE id1 = '"+mapData.get("id1") +"') "+content;
	    List<Object>list = findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 根据二维码查询隐患排查点详细信息 app
	 * @return
	 */
	public List<Map<String, Object>> findInforByewmForApp(String bindcontent) {
		String sql="SELECT * FROM (SELECT id,id1,name,rfid,bindcontent,area,'2' type FROM yhpc_checkpoint WHERE usetype = '2' UNION "
				+ "SELECT id,id1,m1,rfid,bindcontent,checkpointadderss area,'1' type FROM fxgk_accidentrisk WHERE s3 = 0) a where bindcontent = '"+bindcontent+"'";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	//根据自查点id及类型查询自查点的巡检内容 app
	public List<Map<String, Object>> zcdxjnrForApp(Map<String, Object> mapData) {
		String content = "";
		if(mapData.get("xjid")!=null&&mapData.get("xjid")!=""){
			content = content + "AND z.xjdid = "+mapData.get("xjid")+" "; 
		}
		if(mapData.get("xjtype")!=null&&mapData.get("xjtype")!=""){
			content = content + "AND z.type = '"+mapData.get("xjtype")+"' "; 
		}
		String sql="SELECT * FROM(SELECT a.id xjdid,c.id,c.content,'1' type FROM fxgk_accidentrisk a LEFT JOIN yhpc_riskpoint_content b ON a.id = b.id1 LEFT JOIN yhpc_checkcontent c ON b.id2 = c.id "
				+ "UNION SELECT a.id xjdid,c.id,c.content,'2' type FROM yhpc_checkpoint a LEFT JOIN yhpc_checkpoint_content b ON a.id = b.id1 "
				+ "LEFT JOIN yhpc_checkcontent c ON b.id2 = c.id) z where 1=1 "+content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
	
	/**
	 * 根据rfid查询隐患排查点详细信息 app
	 * @return
	 */
	public List<Map<String, Object>> findInforByrfidForApp(String rfid) {
		String sql="SELECT * FROM (SELECT id,id1,name,rfid,bindcontent,area,'2' type FROM yhpc_checkpoint WHERE usetype = '2' UNION "
				+ "SELECT id,id1,m1,rfid,bindcontent,checkpointadderss area,'1' type FROM fxgk_accidentrisk WHERE s3 = 0) a where rfid = '"+rfid+"'";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}
}
