package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQZF_SafetyCheckRecordEntity;
import com.cczu.model.entity.AQZF_SafetyCheckSchemeEntity;
import com.cczu.model.entity.AQZF_SafetyCheckUnitEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 安全执法_检查方案DAO
 *
 */
@Repository("AqzfJcfaDao")
public class AqzfJcfaDao extends BaseDao<AQZF_SafetyCheckSchemeEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY c.m1 DESC,convert(int,replace(c.m2,'月','')) DESC) AS RowNumber,a.*,b.m1 qyname,(CAST(c.m1 as varchar(10))+'年'+CAST(c.m2 as varchar(10)))plantime"
				+ " FROM aqzf_safetycheckscheme a"
				+ " LEFT JOIN bis_enterprise b on a.id2=b.id"
				+ " LEFT JOIN aqzf_safetycheckplan c on a.id1=c.id"
				+ " LEFT JOIN t_user d on d.id=c.id1 "
				+ " where a.S3=0 and b.S3=0 and c.S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1)" ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}
	
	/**
	 * 查询检查单元内容，不包含已被删除的
	 * @param mapData
	 * @return
	 */
	public List<AQZF_SafetyCheckUnitEntity> findDy() {		
		String sql =" SELECT * from aqzf_safetycheckunit where S3=0 ";
		List<AQZF_SafetyCheckUnitEntity> list=findBySql(sql, null,AQZF_SafetyCheckUnitEntity.class);
		return list;
	}

	/**
	 * 查询检查记录内容，不包含已被删除的
	 * @param mapData
	 * @return
	 */
	public AQZF_SafetyCheckSchemeEntity findfa() {		
		String sql =" SELECT * from aqzf_safetycheckscheme where S3=0 ";
		List<AQZF_SafetyCheckSchemeEntity> list=findBySql(sql, null,AQZF_SafetyCheckSchemeEntity.class);
		return list.get(0);
	}
	
	/**
	 * 根据id查询方案信息，因为带企业名称，不能用自带的，故重写
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findById(long id) {		
		String sql =" SELECT a.*,b.m1 qyname,b.m33 dz,b.m5 dbr,b.m6 lxdh from aqzf_safetycheckscheme a"
				+ " LEFT JOIN bis_enterprise b on a.id2=b.id "
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
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("year")!=null&&mapData.get("year")!=""){
			content = content +" AND c.m1 like '%"+mapData.get("year")+"%' "; 
		}
		if(mapData.get("month")!=null&&mapData.get("month")!=""){
			content = content +" AND c.m2 like '%"+mapData.get("month")+"%' "; 
		}
		if(mapData.get("cz")!=null&&mapData.get("cz")!=""){
			content = content +" AND a.m11 like "+mapData.get("cz"); 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			content = content +" AND d.xzqy = '"+mapData.get("xzqy")+"' ";
		}
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND d.userroleflg='"+mapData.get("jglx")+"' ";
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
		String sql=" SELECT COUNT(*) sum  FROM aqzf_safetycheckscheme a"
				+ " LEFT JOIN bis_enterprise b on a.id2=b.id"
				+ " LEFT JOIN aqzf_safetycheckplan c on a.id1=c.id "
				+ " LEFT JOIN t_user d on d.id=c.id1 "
				+ " where a.S3=0 and b.S3=0 and c.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqzf_safetycheckscheme SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
    /**
     * 根据id查找符合word表的数据
     * @param id
     * @return
     */
	public Map<String, Object> findInfo(Long id) {
		String sql ="SELECT a.*,b.m1 qyname FROM aqzf_safetycheckscheme a LEFT JOIN bis_enterprise b ON b.id = a.id2 WHERE a.id = "+id;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
	
	 /**
     * 根据id查找
     * @param id
     * @return
     */
	public AQZF_SafetyCheckSchemeEntity findbyid2(Long id) {
		AQZF_SafetyCheckSchemeEntity a = find(id);
		flush();
		clear();
		return a;
	}
	
	 /**
     * 根据检查方案id，企业id查找检查记录
     * @param id
     * @return
     */
	public AQZF_SafetyCheckRecordEntity findJcjl(Long id1,Long id2) {
		String sql ="SELECT a.* FROM aqzf_safetycheckrecord a where a.id1= "+id1+" and a.id2="+id2+" and a.s3=0";
		List<AQZF_SafetyCheckRecordEntity> list=findBySql(sql, null,AQZF_SafetyCheckRecordEntity.class);
		return list.get(0);
	}
	
	 /**
     * 根据检查记录id修改检查内容
     * @param id
     * @return
     */
	public void deleteJcnr(Long id1,String id2) {
		String sql ="UPDATE aqzf_safetycheckcontent SET S3=1 WHERE ID1="+id1;
		updateBySql(sql);
	}

}
