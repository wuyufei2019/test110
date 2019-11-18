package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqzfJcbkDao;
import com.cczu.model.entity.AQZF_SafetyCheckItemEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqzfJcbkDao")
public class AqzfJcbkDaoImpl extends BaseDao<AQZF_SafetyCheckItemEntity, Long> implements IAqzfJcbkDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql = "SELECT TOP " + mapData.get("pageSize") + "a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by a.id) rownum,a.id,b.m1,a.m2,a.m3,a.m4 FROM aqzf_safetycheckitem a LEFT JOIN aqzf_safetycheckunit b ON b.id = a.m1 WHERE a.s3=0 AND b.s3=0 and a.m1 != ''  "+content +") a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1) ORDER BY a.m1";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT COUNT(*) FROM aqzf_safetycheckitem a LEFT JOIN aqzf_safetycheckunit b ON b.id = a.m1 WHERE a.s3=0 AND b.s3=0 AND a.m1 != '' "+content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("jcdy")!=null&&mapData.get("jcdy")!=""){
			content = content + "AND b.m1 like'%"+mapData.get("jcdy")+"%' "; 
		}
		if(mapData.get("jcnr")!=null&&mapData.get("jcnr")!=""){
			content = content + "AND a.m2 like'%"+mapData.get("jcnr")+"%' "; 
		}
		return content;
	}

	@Override
	public void addInfo(AQZF_SafetyCheckItemEntity jcbk) {
		save(jcbk);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE aqzf_safetycheckitem SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public List<Map<String, Object>> getExport(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT b.m1,a.m2,a.m3,a.m4 FROM aqzf_safetycheckitem a LEFT JOIN aqzf_safetycheckunit b ON b.id = a.m1 WHERE a.s3=0 and b.s3=0 AND a.m1 != '' "+content+" order by a.m1";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class); 
		return list;
	}

	@Override
	public AQZF_SafetyCheckItemEntity findInfoById(long id) {	
		AQZF_SafetyCheckItemEntity a = find(id);
		flush();
		clear();
		if(a!=null)
			return a;
		else
			return null;
	}

	@Override
	public void updateInfo(AQZF_SafetyCheckItemEntity jcbk) {
		save(jcbk);
	}

	@Override
	/**
	 * 查询检查单元内容，不包含已被删除的
	 * @param mapData
	 * @return
	 */
	public List<AQZF_SafetyCheckItemEntity> findJcx(Long m1 ) {		
		String sql =" SELECT * from aqzf_safetycheckitem where M1="+m1+" and S3=0 ";
		List<AQZF_SafetyCheckItemEntity> list=findBySql(sql, null,AQZF_SafetyCheckItemEntity.class);
		return list;
	}

	@Override
	public AQZF_SafetyCheckItemEntity findInfoByIdForApp(long id) {
		String sql ="SELECT a.id,a.id1,a.m2,a.m3,a.m4,a.m5,a.m6,b.m1 FROM aqzf_safetycheckitem a LEFT JOIN aqzf_safetycheckunit b ON a.m1 = b.id WHERE a.s3 = 0 AND b.s3 = 0 AND a.id ="+id;
		List<AQZF_SafetyCheckItemEntity> list=findBySql(sql, null,AQZF_SafetyCheckItemEntity.class);
		return list.get(0);
	}
}
