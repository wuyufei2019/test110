package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_DaliyCheckEntity;
import com.cczu.model.entity.YHPC_InspectionTaskEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查_检查任务DAO
 *
 */
@Repository("YhpcJcrwDao")
public class YhpcJcrwDao extends BaseDao<YHPC_InspectionTaskEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id DESC) AS RowNumber,a.*,b.m1 qyname,"
				+ " CAST(stuff((select ','+m1 from t_department where id in("
				+ " select t.id from yhpc_inspectiontask aa left join t_department t on ','+aa.m1+',' like '%,'+cast(t.id as varchar(50))+',%' where aa.id=a.id"
				+ " ) for xml path('')),1,1,'') as varchar(1000)) as depname,"
				+ " CAST(stuff((select ','+name from t_user where id in("
				+ " select t.id from yhpc_inspectiontask aa left join t_user t on ','+aa.m2+',' like '%,'+cast(t.id as varchar(50))+',%' where aa.id=a.id"
				+ ") for xml path('')),1,1,'') as varchar(1000))as jcr "
				+ " FROM yhpc_inspectiontask a"
				+ " LEFT JOIN bis_enterprise b on a.id1=b.id"
				+ " where a.S3=0 and b.S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 查询检查单元类型，不包含已被删除的
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> findDy() {		
		String sql =" SELECT distinct m1 from yhpc_dailycheck where S3=0 ";
		List<Map<String, Object>> list=null;
		try {
			list = findBySql(sql, null,Map.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询检查单元内容，不包含已被删除的
	 * @param mapData
	 * @return
	 */
	public List<YHPC_DaliyCheckEntity> findJcx(String m1 ) {		
		String sql =" SELECT * from yhpc_dailycheck where M1='"+m1+"' and S3=0 ";
		List<YHPC_DaliyCheckEntity> list=findBySql(sql, null,YHPC_DaliyCheckEntity.class);
		return list;
	}

	/**
	 * 根据id查询检查任务信息，因为带企业名称，不能用自带的，故重写
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findById(long id) {		
		String sql =" SELECT a.*,b.m1 qyname,d.name fpr, "
				+ " CAST(stuff((select ','+m1 from t_department where id in("
				+ " select t.id from yhpc_inspectiontask aa left join t_department t on ','+aa.m1+',' like '%,'+cast(t.id as varchar(50))+',%' where aa.id=a.id"
				+ " ) for xml path('')),1,1,'')as varchar(1000)) as depname,"
				+ " CAST(stuff((select ','+name from t_user where id in("
				+ " select t.id from yhpc_inspectiontask aa left join t_user t on ','+aa.m2+',' like '%,'+cast(t.id as varchar(50))+',%' where aa.id=a.id"
				+ ") for xml path('')),1,1,'')as varchar(1000)) as jcr "
				+ " from yhpc_inspectiontask a"
				+ " LEFT JOIN bis_enterprise b on a.id1=b.id "
				+ " LEFT JOIN t_user d on d.id=a.id2 "
				+ " where a.S3=0 and b.S3=0 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("jcr")!=null&&mapData.get("jcr")!=""){
			content = content +" AND ','+a.m2+',' like '%,"+mapData.get("jcr")+",%' "; 
		}
		if(mapData.get("time")!=null&&mapData.get("time")!=""){
			content = content +" AND a.m3 = '"+mapData.get("time")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.id1 = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content +" AND ','+a.m2+',' like '%,"+mapData.get("userid")+",%' "; 
		}
		if(mapData.get("jclb")!=null&&mapData.get("jclb")!=""){
			content = content +" AND a.m5 = '"+mapData.get("jclb")+"' "; 
		}
		if(mapData.get("zt")!=null&&mapData.get("zt")!=""){
			content = content +" AND a.m6 = '"+mapData.get("zt")+"' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM yhpc_inspectiontask a"
				+ " LEFT JOIN bis_enterprise b on a.id1=b.id"
				+ " where a.S3=0 and b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE yhpc_inspectiontask SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
	 /**
     * 根据id查找
     * @param id
     * @return
     */
	public YHPC_InspectionTaskEntity findbyid2(Long id) {
		YHPC_InspectionTaskEntity a = find(id);
		flush();
		clear();
		return a;
	}

	/**
	 * 根据检查内容的id去检查表库查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridNr(String id) {
		String sql =" SELECT a.* from yhpc_dailycheck a "
				+ " where a.id in("+id+") and a.S3=0 " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
}
