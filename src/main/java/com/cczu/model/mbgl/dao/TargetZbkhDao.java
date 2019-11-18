package com.cczu.model.mbgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.mbgl.entity.Target_Examine;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;


/**
 * 目标管理-指标考核DAO
 * @author YZH
 *
 */
@Repository("TargetZbkhDao")
public class TargetZbkhDao extends BaseDao<Target_Examine, Long>{
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY bis.m1,a.year desc,ROUND(COALESCE(CAST(a.khnum AS FLOAT),0)/a.tnum,2) DESC, a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+")"
				+ " AS RowNumber,a.*,bis.m1 qyname,td.m1 dpname FROM target_examine a left join t_department td on td.id=a.id2"
				+ " left join bis_enterprise bis on bis.id= a.id1 WHERE a.S3=0 and bis.s3=0"+content+" )as a "
				+ " WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND bis.id ='"+mapData.get("qyid")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND bis.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("deptid")!=null&&mapData.get("deptid")!=""){
			content = content +" AND a.id2 ='"+mapData.get("deptid")+"' "; 
		}
		if(mapData.get("deptname")!=null&&mapData.get("deptname")!=""){
			content = content +" AND td.m1 like'%"+mapData.get("deptname")+"%' "; 
		}
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content +" AND a.year ='"+mapData.get("year")+"' "; 
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstartnf")!=null&&mapData.get("aqtzstartnf")!=""){
			content = content +" AND a.year >='"+mapData.get("aqtzstartnf")+"' "; 
		}
		if(mapData.get("aqtzfinishnf")!=null&&mapData.get("aqtzfinishnf")!=""){
			content = content +" AND a.year <='"+mapData.get("aqtzfinishnf")+"' "; 
		}
		
		return content;
		
	}
    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(a.id) sum FROM target_examine a left join t_department td on td.id=a.id2"
				+ " left join bis_enterprise bis on bis.id=a.id1 WHERE a.s3=0 and bis.s3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE target_examine SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
   /**
     * 初始化数据
     * @param id
     */
    public void init(String year,long qyid) {
    	String sql="{Call init_target_examine(:p1,:p2)}";
    	Parameter  parameter=new Parameter(year,qyid);
		updateBySql(sql,parameter);
    }
 
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
    	String content=content(mapData);
		String sql=" SELECT a.*,bis.m1 qyname,td.m1 dpname,case a.m4 when '1' then '达标' when '0' then '未达标' else '' end khjl,CONVERT(varchar(100), a.m3, 23) khsj"
				+ " FROM target_examine a left join t_department td on td.id=a.id2"
				+ " left join bis_enterprise bis on bis.id= a.id1 WHERE a.S3=0 and bis.s3=0 "+ content
				+ " ORDER BY bis.m1,a.year desc,ROUND(COALESCE(CAST(a.khnum AS FLOAT),0)/a.tnum,2) DESC, a.id desc";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
}
