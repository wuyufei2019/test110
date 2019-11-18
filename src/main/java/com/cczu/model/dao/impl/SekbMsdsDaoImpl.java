package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ISekbMsdsDao;
import com.cczu.model.entity.TMESK_MsdsEntity;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

@Repository("SekbMsdsDao")
public class SekbMsdsDaoImpl extends BaseDao<TMESK_MsdsEntity,Long> implements ISekbMsdsDao {

	
	@Override
	public List<TMESK_MsdsEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM tmesk_msds WHERE s3=0 "+content+") "
				+ "as a WHERE s3=0 " + content + " AND RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<TMESK_MsdsEntity> list=findBySql(sql, null,TMESK_MsdsEntity.class);
		
		return list;
	}

	
	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) sum  FROM tmesk_msds WHERE s3=0 "+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

    /**
     * 查询条件
     * @param mapData
     * @return
     */
    public String content(Map<String, Object> mapData) {
		
		String content="";
		if(mapData.get("zwname")!=null&&mapData.get("zwname")!=""){
			content = content +"AND M1 LIKE'%"+mapData.get("zwname")+"%'"; 
		}
		if(mapData.get("zwname2")!=null&&mapData.get("zwname2")!=""){
			content = content +"AND M3 LIKE'%"+mapData.get("zwname2")+"%'"; 
		}
		
		return content;
	}

	@Override
	public TMESK_MsdsEntity findById(Long id) {
		String sql ="SELECT * FROM tmesk_msds WHERE s3=0 AND ID="+id;
		List<TMESK_MsdsEntity> list=findBySql(sql, null,TMESK_MsdsEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18,m19,m20,"
				+ "m21,m22,m23,m24,m25,m26,m27,m28,m29,m30,m31,m32,m33,m34,m35,m36,m37,m38,m39,m40,"
				+ "m41,m42,m43,m44,m45,m46,m47,m48,m49,m50,m51,m52,m53,m54,m55,m56,m57,m58,m59 FROM tmesk_msds WHERE s3=0" + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> getExcelDataWhp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT m1 as m1,('健康危害:'+m10+'环境危害:'+m11+'燃爆危害:'+m12) as m2,m20 as m3 FROM tmesk_msds WHERE s3=0" + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}


	@Override
	public List<TMESK_MsdsEntity> findByM1(String M1) {
		String sql =" SELECT * FROM tmesk_msds WHERE S3=0 AND ( M1=:p1 or m3=:p2 ) ";
		Parameter parameter=new Parameter(M1,M1);
		List<TMESK_MsdsEntity> list=findBySql(sql, parameter,TMESK_MsdsEntity.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findInfoForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,m1 as m1,('健康危害:'+m10+'环境危害:'+m11+'燃爆危害:'+m12) as m2,m20 as m3 FROM tmesk_msds WHERE s3=0 " + content
				+ ")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> listAll() {
		String hql = "select a.M1 as whpName,a.M6 as cas,a.ID as id,a.M55 as unNum,a.M56 as pt ,M17 as type " +
				"from TMESK_MsdsEntity a where a.S3=0";
		Query query = createQuery(hql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		return list;
	}
}
