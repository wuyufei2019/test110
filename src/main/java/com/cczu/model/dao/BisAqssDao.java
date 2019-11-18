package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_SafetyFacilitiesEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全设施DAO
 *
 */
@Repository("BisAqssDao")
public class BisAqssDao extends BaseDao<BIS_SafetyFacilitiesEntity, Long> {

	/**
	 * 分页查询（企业端）
	 * @param mapData
	 * @return
	 */
	public List<BIS_SafetyFacilitiesEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"","ORDER BY a.id desc");
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.* FROM bis_safetyFacilities a"
				+ " where a.S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<BIS_SafetyFacilitiesEntity> list=findBySql(sql, null,BIS_SafetyFacilitiesEntity.class);
		
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
			content = content +" AND a.ID1 ="+mapData.get("qyid")+" "; 
		}
		if(mapData.get("m1")!=null&&mapData.get("m1")!=""){
			content = content +" AND a.m1 like'%"+mapData.get("m1")+"%' "; 
		}
		if(mapData.get("m2")!=null&&mapData.get("m2")!=""){
			content = content +" AND a.m2 like'%"+mapData.get("m2")+"%' "; 
		}
		if(mapData.get("m6")!=null&&mapData.get("m6")!=""){
			content = content +" AND a.m6 <= '"+mapData.get("m6")+"' "; 
		}
		if(mapData.get("dqsj2")!=null&&mapData.get("dqsj2")!=""){
			if("0".equals(mapData.get("dqsj2"))){
				content = content + " and DATEDIFF(day, a.m6, GETDATE())>=0" ;
			}else{
				content = content + " and ABS(DATEDIFF([day], a.m6, GETDATE()))<" + mapData.get("dqsj2") + " and a.m6>GETDATE()";
			}
		}
		if(mapData.get("m7")!=null&&mapData.get("m7")!=""){
			content = content +" AND a.m7 = '"+mapData.get("m7")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 like'%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content + "AND b.ID2 like '%"+mapData.get("xzqy")+"%' "; 
		}
		if (mapData.get("cjz") != null && mapData.get("cjz") != "") {
			content = content + " AND b.cjz=" + mapData.get("cjz");
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		if(mapData.get("m9")!=null&&mapData.get("m9")!=""){
			content = content +" AND a.m9 like'%"+mapData.get("m9")+"%' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
		
	}
    
    /**
     * 分页统计（企业端）
     * @param mapData
     * @return
     */
    public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM bis_safetyFacilities a WHERE a.s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
   	 * 分页查询（安监端）
   	 * @param mapData
   	 * @return
   	 */
   	public List<Map<String, Object>> dataGrid2(Map<String, Object> mapData) {
   		String content=content(mapData);
		String ordercont=setSortWay(mapData,"a.","ORDER BY b.M1,a.id desc");
   		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,a.*,b.M1 qyname FROM bis_safetyFacilities a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+content+") "
   				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
   		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
   		
   		return list;
   	}
    
	 /**
     * 分页统计（安监端）
     * @param mapData
     * @return
     */
    public int getTotalCount2(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM  bis_safetyFacilities a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
    
    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE bis_safetyFacilities SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

    //查询设备类别list
	public List<Map<String, Object>> findSblbList() {
		String sql="SELECT id as id,m1 as text FROM bis_deviceclass where pid='0'"; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
    //查询设备名称list
	public List<Map<String, Object>> findSbmcList(String sblb) {
		String sql="SELECT id as id,m1 as text FROM bis_deviceclass WHERE pid=(select id from bis_deviceclass where m1='"+sblb+"' and pid='0')"; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
    
    /**
     * 导出
     * @param mapData
     * @return
     */
    public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
    	String content=content(mapData);
		String sql=" SELECT b.m1 qyname,a.m9,a.m1,a.m2,a.m3,a.m4, CONVERT(varchar(100), a.m5, 23) m5, CONVERT(varchar(100),a.m6, 23) m6,(case a.m7 when '1' then '失效' when '2' then '停用' when '3' then '正常' else '' end) as m7,a.m8 FROM bis_safetyFacilities a left join bis_enterprise b on b.id=a.id1  WHERE a.S3=0 AND b.S3=0 "+ content +"ORDER BY b.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		 
		return list;
	}

	public List<Map<String, Object>> findByQyID(Long qyid) {
		String sql=" SELECT a.m1,a.m2,a.m3,a.m4, CONVERT(varchar(100), a.m5, 23) m5, CONVERT(varchar(100),a.m6, 23) m6,(case a.m7 when '1' then '失效' when '2' then '停用' when '3' then '正常' else '' end) as m7,a.m8,a.m9 FROM bis_safetyFacilities a WHERE a.S3=0 AND a.id1="+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
}
