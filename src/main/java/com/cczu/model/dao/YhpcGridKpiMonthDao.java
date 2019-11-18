package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_GridKpiMonth;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

/**
 * 网格化管理--网格绩效考核(考核结果)
 * @author
 */
@Repository("YhpcGridKpiMonthDao")
public class YhpcGridKpiMonthDao extends BaseDao<YHPC_GridKpiMonth, Long>{

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=Content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (order by SUBSTRING(a.time,1,4) desc,cast(SUBSTRING(a.time,6,len(a.time)) as int) desc,b.name,b.content) AS RowNumber,"
				+ " a.*,b.name,b.content,b.standard,b.score allscore, case when b.score-d<0 then 0 else b.score-d end score,d.total"
				+ " FROM yhpc_gridkpimonth a left join yhpc_gridkpicontent b on b.id=a.id1 left join"
				+ " (select b.name,sum(descore) d,a.time from yhpc_gridkpimonth a left join yhpc_gridkpicontent b  on b.id=a.id1 WHERE a.S3=0 "
				+ content+" GROUP BY a.wgcode,a.time,b.name ) c on c.name=b.name and a.time=c.time left join"
				+ " (SELECT time, SUM(d) AS total FROM ( SELECT a.wgcode , CASE  WHEN b.score - SUM(descore) < 0 THEN 0 ELSE b.score - SUM(descore) END AS d, a.time"
				+ " FROM yhpc_gridkpimonth a LEFT JOIN yhpc_gridkpicontent b ON b.id = a.id1 WHERE a.S3 = 0 "+content
				+" GROUP BY a.wgcode, a.time, b.score ) d GROUP BY time) d on d.time=a.time "
				+ " WHERE a.S3=0 "+content+" ) as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	  /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=Content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM yhpc_gridkpiMonth a WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	/**
	 * 评分界面分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGridExamine(Map<String, Object> mapData) {
		String content=Content(mapData);
		String sql =" SELECT ov.time,ov.id ovid, a.id, b.id bid, a.descore, a.note, b.standard, b.score allscore, b.name, b.content, tb.m1 wgname "
				+ " FROM  yhpc_gridkpicontent b left join yhpc_gridkpimonthoverview ov on b.id1=ov.id1 and ov.time='"+mapData.get("examtime")
				+"' left join yhpc_gridkpimonth a on b.id=a.id2 and a.id1=ov.id LEFT JOIN t_barrio tb ON b.id1 = tb.id "
				+ " WHERE (a.s3=0 or a.id is null) "+content ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGridOverview(Map<String, Object> mapData) {
		String content=Content(mapData);
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (order by a.time desc) AS RowNumber, "
				+" tb.m1,a.time from yhpc_gridkpimonth a left join  t_barrio tb on a.wgcode=tb.code  "
				+ " WHERE a.s3=0"+content+" GROUP BY tb.m1,a.time ) as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	   /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCountOverview(Map<String, Object> mapData) {
    	String content=Content(mapData);
    	String sql=" select count(*) from yhpc_gridkpimonth a left join  t_barrio tb on a.wgcode=tb.code  where a.s3=0 "+content+" GROUP BY tb.m1,a.time ";
    	List<Object> list=findBySql(sql);
    	return (int) list.get(0);
    }
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGridDetail(Map<String, Object> mapData) {
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (order by b.name ) AS RowNumber,"
				+ " ov.id ovid, a.id, b.id bid,a.descore, a.note, b.standard, b.score allscore, b.name, b.content, tb.m1 wgname,ov.time,d.total"
				+ " from yhpc_gridkpicontent b left join yhpc_gridkpimonth a on b.id=a.id2 left join "
				+ "yhpc_gridkpimonthoverview ov on a.id1=ov.id LEFT JOIN t_barrio tb ON b.id1 = tb.id LEFT JOIN"
				+ " (select sum(descore) total,b.name from yhpc_gridkpimonth a LEFT JOIN yhpc_gridkpicontent b on a.id2=b.id  where a.id1="+mapData.get("id1")+" group by b.name) d"
				+ " on d.name=b.name where a.s3=0 and a.id1="+ mapData.get("id1")+") as a "
				+ " WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCountDetail(Map<String, Object> mapData) {
    	String sql="select count(1) from yhpc_gridkpimonth a where a.s3=0 and a.id1="+mapData.get("id1");
    	List<Object> list=findBySql(sql);
    	return (int) list.get(0);
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
	/*	if(mapData.get("examtime")!=null&&mapData.get("examtime")!=""){
			content = content +" AND ov.time ='"+mapData.get("examtime")+"' "; 
		}*/
		return content;
	}
    
    public void initMonthKpi(){
    	String sql="insert into dbo.yhpc_gridkpimonth (wgcode,name,allscore,content,standard,time,score,note,s1,s2,s3) "
    			+ "select wgcode,name,score,content,standard,convert(varchar(7),dateadd(month,-1,getdate()),120),0.0,'',GETDATE(),GETDATE(),0 "
    			+ "from yhpc_gridkpicontent";
    	updateBySql(sql);
    }
  
    
    public void updateInfo(YHPC_GridKpiMonth mon){
    	String sql="update yhpc_gridkpiMonth set descore="+mon.getDescore()+",s2=getDate(),note='"+mon.getNote()+"' where id="+mon.getID();
    	updateBySql(sql);
    }
    /**
     * 查询信息
     * @param id
     * @return
     */
    public YHPC_GridKpiMonth findInfoByMap(long ID1,String wgcode,String time){
    	String sql=" SELECT *FROM yhpc_gridkpiMonth where id1=:p1 and wgcode=:p2 and time=:p3";
    	Parameter p= new Parameter(ID1,wgcode,time);
    	List<YHPC_GridKpiMonth> list=findBySql(sql, p,YHPC_GridKpiMonth.class);
    	if(list.size()>0)
    		return list.get(0);
    	else
    		return null;
    }
    /**
     * 查询信息
     * @param id
     * @return
     */
    public Map<String, Object> findInfoById(long id){
    	String sql=" SELECT a.*,b.name,b.content,b.standard FROM yhpc_gridkpiMonth a left join yhpc_gridkpicontent b on a.id1=b.id "
    			+ " where a.s3=0 and a.id=:p1";
    	Parameter p= new Parameter(id);
    	List<Map<String, Object>> list=findBySql(sql, p,Map.class);
    	if(list.size()>0)
    		return list.get(0);
    	else
    		return null;
    }
    
}
