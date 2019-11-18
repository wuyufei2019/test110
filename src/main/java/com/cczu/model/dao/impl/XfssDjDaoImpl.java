package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IXfssDjDao;
import com.cczu.model.entity.XFSS_RegisterEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XfssDjDao")
public class XfssDjDaoImpl extends BaseDao<XFSS_RegisterEntity,Long> implements IXfssDjDao {
	@Override
	public void addInfo(XFSS_RegisterEntity xfss) {
		save(xfss);
	}

	@Override
	public void updateInfo(XFSS_RegisterEntity xfss) {
		save(xfss);
	}

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		
		sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id) AS RowNumber,a.id,"
				+ " a.m1,a.m2,a.m3,a.m4,CONVERT(varchar(100), a.m5, 20) as m5,(case a.m6 when '1' then '每月' when '2' then '每季' when '3' then '每半年' else '' end) as m6,a.m7,"
				+ " (case a.m8 when '1' then '有效' when '2' then '过期' when '3' then '报废' else '' end) as m8,a.m10 "
				+ " FROM xfss_register a WHERE a.s3=0 " + content + " ) "
				+ "as h WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		
		return list;
	}

	

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="";
		sql =" SELECT COUNT(*) FROM xfss_register a WHERE a.s3=0 " + content;
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
		if(mapData.get("con_nm")!=null && mapData.get("con_nm")!=""){
			content = content +" AND a.M1 like '%"+mapData.get("con_nm")+"%'"; 
		}
		if(mapData.get("qyid")!=null && mapData.get("qyid")!=""){
			content = content +" AND a.qyid ="+ mapData.get("qyid"); 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE xfss_register SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public XFSS_RegisterEntity findById(Long id) {
		String sql ="SELECT * FROM xfss_register WHERE s3=0 AND ID="+id;
		List<XFSS_RegisterEntity> list=findBySql(sql, null,XFSS_RegisterEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map<String,Object>> findAllInfo(Long qyid) {
		String sql ="SELECT a.id,a.pid"
				+ " a.m1,a.m2,a.m3,a.m4,a.m5,(case a.m6 when '1' then '每月' when '2' then '每季' when '3' then '每半年' else '' end) as m6,a.m7,"
				+ " (case a.m8 when '1' then '有效' when '2' then '过期' when '3' then '报废' else '' end) as m8 "
				+ " FROM xfss_register a WHERE s3=0 AND qyid="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findAllInfo(Long qyid, Long pmtId) {
		String sql ="SELECT a.id,"
				+ " a.m1,a.m2,a.m3,a.m4,a.m5,(case a.m6 when '1' then '每月' when '2' then '每季' when '3' then '每半年' else '' end) as m6,a.m7,"
				+ " (case a.m8 when '1' then '有效' when '2' then '过期' when '3' then '报废' else '' end) as m8 "
				+ " FROM xfss_register a WHERE s3=0 AND qyid="+qyid + " AND PMT_ID="+pmtId;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findAllZb(Long qyid) {
		String sql ="SELECT a.m3,a.m4 "
				+ " FROM xfss_register a WHERE s3=0 AND qyid="+qyid;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getExcel(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		sql ="SELECT "
				+ " a.m1,a.m2,a.m3,a.m4,a.m5,(case a.m6 when '1' then '每月' when '2' then '每季' when '3' then '每半年' else '' end) as m6,a.m7,"
				+ " (case a.m8 when '1' then '有效' when '2' then '过期' when '3' then '报废' else '' end) as m8,a.m9,b.name as djnm "
				+ " FROM xfss_register a "
				+ " left join t_user b on a.id2=b.id "
				+ " WHERE a.s3=0 " + content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

}
