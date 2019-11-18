package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_GridManKpiMonth;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

/**
 * 网格化管理--网格员绩效考核(考核结果)
 * @author
 */
@Repository("YhpcGridManKpiMonthDao")
public class YhpcGridManKpiMonthDao extends BaseDao<YHPC_GridManKpiMonth, Long>{

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=Content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER "
				+ "(order by a.time desc) AS RowNumber,"
				+ " a.id,a.time,a.score,b.name,b.content,u.name wgyname,a.note,b.score allscore,c.total FROM yhpc_gridmankpimonth"
				+ " a left join yhpc_gridmankpicontent b on a.id2=b.id left join  t_user u on u.id=a.id1 left join"
				+ " (SELECT a.time, SUM(a.score) AS total, a.id1 FROM yhpc_gridmankpimonth a LEFT JOIN yhpc_gridmankpicontent b"
				+ " ON b.id = a.id2 WHERE a.S3 = 0 and b.wgcode='"+mapData.get("wgcode")+"' GROUP BY a.id1, a.time) c on c.id1=a.id1 and c.time=a.time"
				+ " WHERE a.S3=0 "+content+" )as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGridDetail(Map<String, Object> mapData) {
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (order by b.name ) AS RowNumber,"
				+" a.id mid,ov.id ovid, u.id uid,ov.time,b.content,b.name,b.score allscore,u.name wgyname,a.note,a.score from yhpc_gridmankpimonth a left join yhpc_gridmankpimonthoverview ov on ov.id=a.id1 left join yhpc_gridmankpicontent b on a.id2=b.id left join t_user u on u.id=ov.id1 "
				+ " where a.s3=0 and a.id1="+ mapData.get("id1")+") as a  WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	/**
	 * 评分界面分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGridExamine(Map<String, Object> mapData) {
		String content=Content(mapData);
		String time= mapData.get("examtime").toString();
		String sql =" SELECT a.id as mid,ov.id ovid,ov.time,u.id AS uid, u.name AS wgyname, b.content, b.name, b.score AS allscore , a.score, a.note, b.id AS id2 FROM "
				+ "t_user u LEFT JOIN t_user_role ur ON u.id = ur.USER_ID LEFT JOIN t_role r ON r.id = ur.ROLE_ID LEFT JOIN t_barrio"
				+ " tb on tb.code= u.xzqy left join yhpc_gridmankpicontent b ON b.id1 = tb.id left join yhpc_gridmankpimonthoverview ov"
				+ " on ov.id1 = u.id and ov.time='"+time+"' LEFT JOIN yhpc_gridmankpimonth a ON a.id1 = ov.id and a.id2=b.id"
				+" WHERE usertype = '0' AND b.s3 = 0 AND (a.s3 = 0 OR a.id IS NULL) "+content+" AND b.name != '安全巡查' "
				+ " UNION ALL"
				+ " SELECT a.id as mid,ov.id ovid,ov.time,u.id AS uid, u.name AS wgyname, b.content, b.name, b.score AS allscore , u.xccs / u.yxcs * b.score AS score ,"
				+ " '应巡检' + CAST(u.yxcs AS varchar) + '家企业  已巡检' + CAST(u.xccs AS varchar) + '家企业' AS note , b.id AS id2 FROM "
				+ "( SELECT u.id, u.xzqy, u.name , isnull(SUM(xccs), 0) AS xccs , yxcs FROM t_user u LEFT JOIN t_barrio c ON u.xzqy = c.code"
				+ " LEFT JOIN ( SELECT a.userid, b.id AS wgdid, b.name AS wgdname, COUNT(a.userid) AS xccs FROM ( SELECT a.* FROM ( SELECT "
				+ "ROW_NUMBER() OVER (PARTITION BY a.checkplan_id, a.checkpoint_id, c.starttime, c.endtime, convert(varchar(7), a.createtime, 120)"
				+ " ORDER BY a.createtime DESC) AS rn, a.* FROM yhpc_checkresult a LEFT JOIN yhpc_checkplan b ON a.checkplan_id = b.id "
				+ "LEFT JOIN yhpc_checkplan_time c ON b.id = c.id1 WHERE a.checkpointtype = '2' AND b.id1 = 0 AND b.type = '3' AND "
				+ "Day(a.createtime) >= c.starttime AND Day(a.createtime) <= c.endtime ) a WHERE a.rn = 1 ) a LEFT JOIN yhpc_checkpoint b "
				+ "ON b.id = a.checkpoint_id WHERE a.checkpointtype = '2' AND b.usetype = '1' AND "
				+ "convert(varchar(7), a.createtime, 120) = '"+time+"' GROUP BY a.userid, b.name, b.id ) d ON d.userid = u.ID LEFT JOIN "
				+ "( SELECT b.xzqy, COUNT(*) AS yxcs FROM yhpc_checkplan a LEFT JOIN t_user b ON a.userid = b.ID LEFT JOIN t_barrio tb"
				+ " ON tb.id = a.wgid LEFT JOIN bis_enterprise bis ON bis.id2 = tb.code LEFT JOIN yhpc_checkpoint yc ON yc.id1 = bis.id"
				+ " LEFT JOIN yhpc_checkplan_time d ON d.id1 = a.id WHERE yc.usetype = '1' AND a.id1 = 0 AND a.type = '3' AND bis.s3 = 0 "
				+ "GROUP BY b.xzqy ) e ON e.xzqy = c.code WHERE u.usertype = 0  GROUP BY u.id, e.yxcs, u.name, u.xzqy ) u"
				+ " LEFT JOIN t_barrio tb on tb.code=u.xzqy left join  yhpc_gridmankpicontent b ON b.id1 = tb.id LEFT JOIN yhpc_gridmankpimonthoverview ov"
				+ " on ov.id1 = u.id and ov.time='"+time+"' LEFT JOIN yhpc_gridmankpimonth a ON a.id1 = ov.id and a.id2=b.id"
				+ " WHERE b.s3 = 0 AND (a.s3 = 0 OR a.id IS NULL) AND b.name = '安全巡查'"+content+" ORDER BY u.id DESC, b.name";
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	   /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=Content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM yhpc_gridmankpiMonth a left join t_user u on u.id=a.id1 WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCountDetail(Map<String, Object> mapData) {
    	String sql="select count(1) from yhpc_gridmankpimonth a where a.s3=0 and a.id1="+mapData.get("id1");
    	List<Object> list=findBySql(sql);
    	return (int) list.get(0);
    }
	
    public void initMonthKpi(){
    	String content="";
    	String sql="INSERT INTO dbo.yhpc_gridmankpimonth (id1,id2, time, score , note, s1, s2, s3)"
    			+ " SELECT u.id, a.id, convert(varchar(7), dateadd(month, -1, getdate()), 120),"
    			+ " score, '', GETDATE(), GETDATE(), 0 FROM yhpc_gridmankpicontent a LEFT JOIN t_user u ON a.wgcode = u.xzqy LEFT "
    			+ "JOIN t_user_role ur ON u.id = ur.USER_ID LEFT JOIN t_role r ON r.id = ur.ROLE_ID WHERE a.s3=0 and usertype = '0' and a.id1 not exists(select id1 from yhpc_gridmankpimonth where s3=0) and id2 not exists(select id2 from yhpc_gridmankpimonth where s3=0) "+content;
    	updateBySql(sql);
    }
    
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String Content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("wgcode")!=null&&mapData.get("wgcode")!=""){
			content = content +" AND tb.code ='"+mapData.get("wgcode")+"' "; 
		}
		if(mapData.get("wgyid")!=null&&mapData.get("wgyid")!=""){
			content = content +" AND u.id ='"+mapData.get("wgyid")+"' "; 
		}
		if(mapData.get("time")!=null&&mapData.get("time")!=""){
			content = content +" AND ov.time ='"+mapData.get("time")+"' "; 
		}
		if(mapData.get("ovid")!=null&&mapData.get("ovid")!=""){
			content = content +" AND ov.ID="+mapData.get("ovid"); 
		}
		return content;
	}
    
    public void updateInfo(YHPC_GridManKpiMonth mon){
    	String sql="update yhpc_gridmankpiMonth set score="+mon.getScore()+",note='"+mon.getNote()+"',s2=getDate() where id="+mon.getID();
    	updateBySql(sql);
    }
    /**
     * 根据id查询form页面信息
     * @param id
     * @return
     */
    public Map<String,Object> findInfoById(long id){
    	String sql=" SELECT a.id,a.time,b.name,b.content,a.score,a.note FROM yhpc_gridmankpiMonth a "
    			+ "left join yhpc_gridmankpicontent b on b.id=a.id2 where a.id="+id;
    	List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
    }
    /**
     * 查询信息 ，ID1 总分表id，ID2 规则id ，time 时间 
     * @param id
     * @return
     */
    public YHPC_GridManKpiMonth findInfoByMap(long ID1,long ID2){
    	String sql=" SELECT a.* FROM yhpc_gridmankpiMonth a left join yhpc_gridmankpimonthoverview b on b.id=a.id1 "
    			+ " where b.id1=:p1 and a.id2=:p2";
    	Parameter p= new Parameter(ID1,ID2);
    	List<YHPC_GridManKpiMonth> list=findBySql(sql, p,YHPC_GridManKpiMonth.class);
    	if(list.size()>0)
    		return list.get(0);
    	else
    		return null;
    }
    
    /**
     * 删除信息
     * @param id
     */
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE yhpc_gridmankpiMonth SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
    
}
