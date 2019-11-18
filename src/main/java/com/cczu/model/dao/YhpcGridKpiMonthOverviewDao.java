package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_GridKpiMonthOverview;
import com.cczu.util.dao.BaseDao;

/**
 * 网格化管理--网格员绩效考核(考核结果)(总分页)
 * @author
 */
@Repository("YhpcGridKpiMonthOverviewDao")
public class YhpcGridKpiMonthOverviewDao extends BaseDao<YHPC_GridKpiMonthOverview, Long>{

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGridOverview(Map<String, Object> mapData) {
		String content=Content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY a.time desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ( "+ordercont+") AS RowNumber,"
				+" a.*,tb.m1 from YHPC_GridKpiMonthOverview a left join t_barrio tb on tb.id=a.id1"
				+ " where a.s3=0 "+content+" ) as a  WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
    	String sql=" select count(1) from YHPC_GridKpiMonthOverview a left join t_barrio tb on tb.id=a.id1"
    			  +" where a.s3=0 "+content;
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
		if(mapData.get("time")!=null&&mapData.get("time")!=""){
			content = content +" AND a.time ='"+mapData.get("time")+"' "; 
		}
		/*if(mapData.get("examtime")!=null&&mapData.get("examtime")!=""){
			content = content +" AND (a.time ='"+mapData.get("examtime")+"' or a.time is null)"; 
		}*/
		return content;
	}
    
    public void updateInfo(YHPC_GridKpiMonthOverview entity){
    	String sql="update YHPC_GridKpiMonthOverview set score="+entity.getScore()+",s2=getDate() where id="+entity.getID();
    	updateBySql(sql);
    }
}
