package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_DailyCheckResultEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查_企业日常检查记录DAO
 *
 */
@Repository("YhpcJcjlDao")
public class YhpcJcjlDao extends BaseDao<YHPC_DailyCheckResultEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.createtime DESC) AS RowNumber,a.*,b.m7 taskname,c.m1 qyname, "
				+ " CAST(stuff((select ','+name from t_user where id in("
				+ " select t.id from yhpc_inspectiontask aa left join t_user t on ','+aa.m2+',' like '%,'+cast(t.id as varchar(50))+',%' where aa.id=b.id"
				+ ") for xml path('')),1,1,'') as varchar(1000))as jcr "
				+ " FROM yhpc_dailycheckresult a"
				+ " LEFT JOIN yhpc_inspectiontask b on a.id1=b.id"
				+ " LEFT JOIN bis_enterprise c on c.id=b.id1 "
				+ " where a.S3=0 and c.S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM yhpc_dailycheckresult a"
				+ " LEFT JOIN yhpc_inspectiontask b on a.id1=b.id"
				+ " LEFT JOIN bis_enterprise c on c.id=b.id1 "
				+ " where a.S3=0 and b.S3=0 and c.S3=0"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
	 * 根据id查询检查任务信息，因为带企业名称，不能用自带的，故重写
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(long id) {		
		String sql =" SELECT a.*,c.m1 qyname,e.m1 depname,"
				+ " CAST(stuff((select ','+name from t_user where id in("
				+ " select t.id from yhpc_inspectiontask aa left join t_user t on ','+aa.m2+',' like '%,'+cast(t.id as varchar(50))+',%' where aa.id=b.id"
				+ ") for xml path('')),1,1,'') as varchar(1000))as jcr "
				+ " from yhpc_dailycheckresult a"
				+ " LEFT JOIN yhpc_inspectiontask b on a.id1=b.id"
				+ " LEFT JOIN bis_enterprise c on c.id=b.id1 "
				+ " LEFT JOIN t_department e on e.id=a.m2"
				+ " where a.S3=0 and b.S3=0 and a.id="+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	/**
	 * 根据id1查询检查任务信息，因为带企业名称，不能用自带的，故重写
	 * @param id1
	 * @return
	 */
	public Map<String, Object> findById1(long id1) {
		String sql =" SELECT a.*,c.m1 qyname,e.m1 depname,"
				+ " CAST(stuff((select ','+name from t_user where id in("
				+ " select t.id from yhpc_inspectiontask aa left join t_user t on ','+aa.m2+',' like '%,'+cast(t.id as varchar(50))+',%' where aa.id=b.id"
				+ ") for xml path('')),1,1,'') as varchar(1000))as jcr "
				+ " from yhpc_dailycheckresult a"
				+ " LEFT JOIN yhpc_inspectiontask b on a.id1=b.id"
				+ " LEFT JOIN bis_enterprise c on c.id=b.id1 "
				+ " LEFT JOIN t_department e on e.id=a.m2"
				+ " where a.S3=0 and b.S3=0 and a.id1="+id1;
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
			content = content +" AND a.m2 = '"+mapData.get("jcr")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND c.id = '"+mapData.get("qyid")+"' "; 
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND c.m1 like '%" + mapData.get("qyname") + "%' ";
		}
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content +" AND a.m2 = '"+mapData.get("userid")+"' "; 
		}
		if(mapData.get("userid2")!=null&&mapData.get("userid2")!=""){
			content = content +" AND a.m7 = '"+mapData.get("userid2")+"' "; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND a.m1 >= '"+mapData.get("starttime")+"' "; 
		}		
		if(mapData.get("finishtime")!=null&&mapData.get("finishtime")!=""){
			content = content +" AND a.m1 <= '"+mapData.get("finishtime")+"' "; 
		}
		if(mapData.get("jcjg")!=null&&mapData.get("jcjg")!=""){
			if(mapData.get("jcjg").equals("1")){
				content = content +" AND a.m4 is not null "; 
			}
			if(mapData.get("jcjg").equals("0")){
				content = content +" AND a.m4 is null "; 
			}
		}
		
		/*安全台账条件*/
		if(mapData.get("aqtzstarttime")!=null&&mapData.get("aqtzstarttime")!=""){
			content = content +" AND CONVERT(varchar(100), a.m1, 23) >='"+mapData.get("aqtzstarttime")+"' "; 
		}
		if(mapData.get("aqtzfinishtime")!=null&&mapData.get("aqtzfinishtime")!=""){
			content = content +" AND CONVERT(varchar(100), a.m1, 23) <='"+mapData.get("aqtzfinishtime")+"' "; 
		}
		
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( c.fid='"+mapData.get("fid")+"' or c.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE yhpc_dailycheckresult SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
	 /**
     * 根据id查找
     * @param id
     * @return
     */
	public YHPC_DailyCheckResultEntity findbyid2(Long id) {
		YHPC_DailyCheckResultEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	/**
	 * 根据检查内容的id去检查表库查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridNr(Map<String, Object> mapData) {
		String sql =" SELECT b.id,a.m3,b.m2 pic,b.m1 wtms from yhpc_dailycheck a "
				+ " LEFT JOIN (SELECT id,m2,m1,m1_1 from yhpc_dailyhiddeninfo where s3=0 and id1="+mapData.get("jlid")+")b  on a.id=b.m1_1 "
				+ " where a.id in("+mapData.get("nrid")+") and a.S3=0";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 根据检查内容的id去检查表库查询list数量
	 * @param mapData
	 * @return
	 */
	public int countNr(Map<String, Object> mapData) {
		String sql =" SELECT COUNT(*) sum from yhpc_dailycheck a "
				+ " LEFT JOIN (SELECT m2,m1,m1_1 from yhpc_dailyhiddeninfo where s3=0 and id1="+mapData.get("jlid")+")b  on a.id=b.m1_1 "
				+ " where a.id in("+mapData.get("nrid")+") and a.S3=0";
		List<Object> list=findBySql(sql);
		
		return (int) list.get(0);
	}
	
	/**
	 * 导出
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="select a.*,b.m7 taskname,c.m1 qyname,"
				+ " CAST(stuff((select ','+name from t_user where id in("
				+ " select t.id from yhpc_inspectiontask aa left join t_user t on ','+aa.m2+',' like '%,'+cast(t.id as varchar(50))+',%' where aa.id=b.id"
				+ ") for xml path('')),1,1,'') as varchar(1000))as jcr,CONVERT(varchar(100), a.m1, 23)jcsj,case when a.m4 is null or a.m4 = '' then '无隐患' else '有隐患' end jcjg "
				+ " FROM yhpc_dailycheckresult a"
				+ " LEFT JOIN yhpc_inspectiontask b on a.id1=b.id"
				+ " LEFT JOIN bis_enterprise c on c.id=b.id1 "
				+ " where a.S3=0 and c.S3=0 "+content+" ORDER BY a.id DESC";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
