package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_InterimCheckRecordEntity;
import com.cczu.model.entity.YHPC_InterimCheckContentEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 临时检查记录DAO
 *
 */
@Repository("YhpcLsJcjlDao")
public class YhpcLsJcjlDao extends BaseDao<YHPC_InterimCheckRecordEntity, Long> {

	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
   		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.m2 desc,b.m1) AS RowNumber,a.*,t.name zgname,b.M1 qyname FROM yhpc_interimcheckrecord a "
   				+ " left join bis_enterprise b on b.id=a.id1 "
                + " left join t_user t on a.m5_id = t.id "
   				+ " WHERE a.S3=0 AND b.S3=0 "+content+") "
   				+ "as s WHERE  RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
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
		if(mapData.get("jcjd")!=null&&mapData.get("jcjd")!=""){
			content = content +" AND a.m13 ="+mapData.get("jcjd")+" "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.M1 like '%"+mapData.get("qyname")+"%' "; 
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
		String sql=" SELECT COUNT(*) sum  FROM  yhpc_interimcheckrecord a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 "+ content;
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
		String sql=" UPDATE yhpc_interimcheckrecord SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}
	
	public Map<String, Object> getWord(long id) {
		String sql = "  SELECT b.m1 as qyname,a.m2,a.m4,a.m3,a.m5,a.m8,a.m9,a.m10, case a.m13 when '1' then '已复检' else  '未复检'  end as m13  FROM  yhpc_interimcheckrecord a left join bis_enterprise b on b.id=a.id1 WHERE a.S3=0 AND b.S3=0 and a.id=" + id
				+ " order by  a.id desc";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
   
	public List<YHPC_InterimCheckContentEntity> getNr(long id) {
		String sql = "  SELECT * from yhpc_interimsafetycheckcontent where id1="+id;
		List<YHPC_InterimCheckContentEntity> list = findBySql(sql, null, YHPC_InterimCheckContentEntity.class);
		return list;
	}
}
