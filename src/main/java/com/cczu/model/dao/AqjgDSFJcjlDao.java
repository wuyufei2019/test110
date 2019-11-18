package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.AQJD_DSFCheckRecordEntity;
import com.cczu.model.entity.AQJD_DSFCheckContentEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 第三方检查记录DAO
 *
 */
@Repository("AqjgDSFJcjlDao")
public class AqjgDSFJcjlDao extends BaseDao<AQJD_DSFCheckRecordEntity, Long> {

	/**
	 * 分页查询（企业端）
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,c.m1 dwname FROM aqjd_dsfcheckrecord a"
				+ " LEFT JOIN aqjg_dsfmanage c on a.id2=c.id "
				+ " where a.S3=0 and c.S3=0 "+content+" ) "
				+ "as a WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		
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
			content = content +" AND a.ID1 = '"+mapData.get("qyid")+"' "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.M1 like '%"+mapData.get("qyname")+"%' "; 
		}
		if(mapData.get("jcjd")!=null&&mapData.get("jcjd")!=""){
			content = content +" AND a.m13 ="+mapData.get("jcjd")+" "; 
		}
		if (mapData.get("xzqy") != null && mapData.get("xzqy") != "") {
			content = content + "AND b.id2 like'"+mapData.get("xzqy")+"%'"; ;
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM aqjd_dsfcheckrecord a WHERE a.s3=0 "+ content;
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
   		
   		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.M1,a.id desc) AS RowNumber,a.*,b.M1 qyname,c.m1 dwname FROM aqjd_dsfcheckrecord a "
   				+ " left join bis_enterprise b on b.id=a.id1 "
   				+ " LEFT JOIN aqjg_dsfmanage c on a.id2=c.id "
   				+ " WHERE a.S3=0 AND b.S3=0 AND c.S3=0 "+content+") "
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
		String sql=" SELECT COUNT(*) sum  FROM  aqjd_dsfcheckrecord a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 根据检查记录的id去检查表库查询list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGridNr2(String id) {
		String sql =" SELECT a.m1 jcjg,a.m2 jcwt,a.m4 pic, c.m1 jcdy,b.m2 jcnr,b.m4 ,b.m5,b.m6,b.id "
				+ " from aqzf_safetycheckcontent a "
				+ " left JOIN aqzf_safetycheckitem b on a.id2=b.id "
				+ " left JOIN aqzf_safetycheckunit c on b.m1=c.id"
				+ " where a.id1 ="+id+" and a.S3=0 and b.S3=0 and c.S3=0 " ;
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
     * 删除
     * @param id
     */
    public void deleteInfo(Long id) {
		String sql=" UPDATE aqjd_dsfcheckrecord SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
    
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = "  SELECT b.m1 as qyname,c.m1 as dwname,a.m2,a.m4,a.m3,a.m5,a.m8,a.m9,a.m10, case a.m13 when '1' then '已复检' else  '未复检'  end as m13  FROM  aqjd_dsfcheckrecord a left join bis_enterprise b on b.id=a.id1 LEFT JOIN aqjg_dsfmanage c on a.id2=c.id WHERE a.S3=0 AND b.S3=0  AND c.S3=0 " + content
				+ " order by  a.id desc";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
	
	public Map<String, Object> getWord(long id) {
		String sql = "  SELECT b.m1 as qyname,c.m1 as dwname,a.m2,a.m4,a.m3,a.m5,a.m8,a.m9,a.m10, case a.m13 when '1' then '已复检' else  '未复检'  end as m13  FROM  aqjd_dsfcheckrecord a left join bis_enterprise b on b.id=a.id1 LEFT JOIN aqjg_dsfmanage c on a.id2=c.id WHERE a.S3=0 AND b.S3=0  AND c.S3=0 and a.id=" + id
				+ " order by  a.id desc";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
   
	public List<AQJD_DSFCheckContentEntity> getNr(long id) {
		String sql = "  SELECT * from aqjd_dsfsafetycheckcontent where id1="+id;
		List<AQJD_DSFCheckContentEntity> list = findBySql(sql, null, AQJD_DSFCheckContentEntity.class);
		return list;
	}
}
