package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_DailyHiddenInfoEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查_企业日常检查隐患DAO
 *
 */
@Repository("YhpcJcyhDao")
public class YhpcJcyhDao extends BaseDao<YHPC_DailyHiddenInfoEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.createtime desc,a.id desc) AS RowNumber,a.* ,d.m7 taskname,b.m3 jcnr,f.name zgr,c.m1 jcsj,g.m1 qyname "
				+ " FROM yhpc_dailyhiddeninfo a"
				+ " left join yhpc_dailycheck b on b.id=a.m1_1"
				+ " LEFT JOIN yhpc_dailycheckresult c on c.id=a.id1"
				+ " LEFT JOIN yhpc_inspectiontask d on d.id= c.id1"
				+ " LEFT JOIN t_user f on f.id=a.m9 "
				+ " LEFT JOIN bis_enterprise g on g.id=a.qyid"
				+ " where a.s3=0 and g.s3=0 "+content+" ) "
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
		String sql=" SELECT COUNT(*) sum  FROM yhpc_dailyhiddeninfo a"
				+ " left join yhpc_dailycheck b on b.id=a.m1_1"
				+ " LEFT JOIN yhpc_dailycheckresult c on c.id=a.id1"
				+ " LEFT JOIN yhpc_inspectiontask d on d.id= c.id1"
				+ " LEFT JOIN t_user f on f.id=a.m9 "
				+ " LEFT JOIN bis_enterprise g on g.id=a.qyid"
				+ " where a.s3=0 and g.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 我要处理datagrid
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> myDataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.createtime desc,a.id desc) AS RowNumber,a.* ,d.m7 taskname,b.m3 jcnr,e.name jcr,f.name zgr,c.m1 jcsj,g.m1 qyname "
				+ " FROM yhpc_dailyhiddeninfo a"
				+ " left join yhpc_dailycheck b on b.id=a.m1_1"
				+ " LEFT JOIN yhpc_dailycheckresult c on c.id=a.id1"
				+ " LEFT JOIN yhpc_inspectiontask d on d.id= c.id1"
				+ " LEFT JOIN t_user e on ','+d.m2+',' like '%,'+CAST(e.id as varchar)+',%' "
				+ " LEFT JOIN t_user f on f.id=a.m9 "
				+ " LEFT JOIN bis_enterprise g on g.id=a.qyid"
				+ " where a.s3=0 and g.s3=0 and (((cast(a.m11 as int) <2 or a.m11='3') and a.m9 = '"+mapData.get("userid")+"')"
				+ " or ((a.m11='2' or a.m11='4') and a.m12 = "+mapData.get("userid")+")) "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

    /**
     * 分页统计
     * @param mapData
     * @return
     */
    public int getMyTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM yhpc_dailyhiddeninfo a"
				+ " left join yhpc_dailycheck b on b.id=a.m1_1"
				+ " LEFT JOIN yhpc_dailycheckresult c on c.id=a.id1"
				+ " LEFT JOIN yhpc_inspectiontask d on d.id= c.id1"
				+ " LEFT JOIN t_user e on e.id=d.m2 "
				+ " LEFT JOIN t_user f on f.id=a.m9 "
				+ " LEFT JOIN bis_enterprise g on g.id=a.qyid"
				+ " where a.s3=0 and g.s3=0 and (((cast(a.m11 as int) <2 or a.m11='3') and a.m9 = '"+mapData.get("userid")+"')"
				+ " or ((a.m11='2' or a.m11='4') and a.m12 = "+mapData.get("userid")+")) "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
	/**
	 * 首页查询登录人是否有隐患需要整改
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT a.*  "
				+ " FROM yhpc_dailyhiddeninfo a"
				+ " left join yhpc_dailycheck b on b.id=a.m1_1"
				+ " LEFT JOIN yhpc_dailycheckresult c on c.id=a.id1"
				+ " LEFT JOIN yhpc_inspectiontask d on d.id= c.id1"
				+ " LEFT JOIN t_user e on ','+d.m2+',' like '%,'+CAST(e.id as varchar)+',%' "
				+ " LEFT JOIN t_user f on f.id=a.m9 "
				+ " LEFT JOIN bis_enterprise g on g.id=a.qyid"
				+ " where a.s3=0 and g.s3=0 and (((cast(a.m11 as int) <2 or a.m11='3') and a.m9 = '"+mapData.get("userid")+"')"
				+ " or ((a.m11='2' or a.m11='4') and a.m12 = "+mapData.get("userid")+")) "+content ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
	/**
	 * 根据id查询检查任务信息，因为带企业名称，不能用自带的，故重写
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(long id) {		
		String sql =" SELECT a.* ,(case a.m11 when '0' then '未整改' when '1' then '整改未完成' when '2' then '整改完成' end) zgzt,b.m3 jcnr,f.name zgr,h.name fxr,g.m1 qyname,c.m1 jcsj,"
				+ " CAST(STUFF(( SELECT ',' + j.name FROM  (select e.name,d.id from yhpc_inspectiontask d left join t_user e on ','+d.m2+',' like '%,'+convert(varchar(100),e.id)+',%' where d.s3=0) j "
				+ " WHERE j.id=d.id ORDER BY j.id FOR XML PATH('') ), 1, 1, '') as varchar(1000)) jcr "
				+ " from yhpc_dailyhiddeninfo a"
				+ " left join yhpc_dailycheck b on b.id=a.m1_1"
				+ " LEFT JOIN yhpc_dailycheckresult c on c.id=a.id1"
				+ " LEFT JOIN yhpc_inspectiontask d on d.id= c.id1"
				+ " LEFT JOIN t_user f on f.id=a.m9"
				+ " LEFT JOIN t_user h on h.id=a.m12"
				+ " LEFT JOIN bis_enterprise g on g.id=a.qyid"
				+ " where a.s3=0 and a.id="+ id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}

	/**
	 * 根据检查任务id查询检查隐患信息
	 * @param rwid
	 * @return
	 */
	public List<YHPC_DailyHiddenInfoEntity> findByRwid(long rwid) {		
		String sql =" SELECT a.* from yhpc_dailyhiddeninfo a"
				+ " LEFT JOIN yhpc_dailycheckresult c on c.id=a.id1"
				+ " LEFT JOIN yhpc_inspectiontask d on d.id= c.id1"
				+ " LEFT JOIN t_user e on ','+d.m2+',' like '%,'+CAST(e.id as varchar)+',%' "
				+ " LEFT JOIN t_user f on f.id=a.m9"
				+ " LEFT JOIN t_user h on h.id=a.m12"
				+ " LEFT JOIN bis_enterprise g on g.id=a.qyid"
				+ " where a.s3=0 and d.id="+ rwid;
		List<YHPC_DailyHiddenInfoEntity> list=findBySql(sql, null,YHPC_DailyHiddenInfoEntity.class);
		return list;
	}
	
	/**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("jcr")!=null&&mapData.get("jcr")!=""){
			content = content +" AND d.m2 = '"+mapData.get("jcr")+"' "; 
		}
		if(mapData.get("time")!=null&&mapData.get("time")!=""){
			content = content +" AND a.m3 = '"+mapData.get("time")+"' "; 
		}
		if(mapData.get("qyid")!=null&&mapData.get("qyid")!=""){
			content = content +" AND a.qyid = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("zgr")!=null&&mapData.get("zgr")!=""){
			content = content +" AND a.m9 = '"+mapData.get("zgr")+"' "; 
		}
		if(mapData.get("starttime")!=null&&mapData.get("starttime")!=""){
			content = content +" AND a.createtime > '"+mapData.get("starttime")+"' "; 
		}
		if(mapData.get("endtime")!=null&&mapData.get("endtime")!=""){
			content = content +" AND a.createtime < '"+mapData.get("endtime")+"' "; 
		}
		if(mapData.get("yhlb")!=null&&mapData.get("yhlb")!=""){
			content = content +" AND a.m3 = '"+mapData.get("yhlb")+"' "; 
		}
		if(mapData.get("yhdj")!=null&&mapData.get("yhdj")!=""){
			content = content +" AND a.m7 = '"+mapData.get("yhdj")+"' "; 
		}
		if(mapData.get("clzt")!=null&&mapData.get("clzt")!=""){
			content = content +" AND a.m11 = '"+mapData.get("clzt")+"' "; 
		}
		if (mapData.get("qyname") != null && mapData.get("qyname") != "") {
			content = content + " AND g.m1 like '%" + mapData.get("qyname") + "%' ";
		}
		
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + " AND ( g.fid='"+mapData.get("fid")+"' or g.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE yhpc_dailyhiddeninfo SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
	 /**
     * 根据id查找
     * @param id
     * @return
     */
	public YHPC_DailyHiddenInfoEntity findbyid2(Long id) {
		YHPC_DailyHiddenInfoEntity a = find(id);
		flush();
		clear();
		return a;
	}
}
