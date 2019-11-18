package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqzfJcdyDao;
import com.cczu.model.entity.AQZF_SafetyCheckUnitEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqzfJcdyDao")
public class AqzfJcdyDaoImpl extends BaseDao<AQZF_SafetyCheckUnitEntity, Long> implements IAqzfJcdyDao {

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String sql = "SELECT TOP " + mapData.get("pageSize") + " a.* FROM ("+
		           "SELECT ROW_NUMBER() OVER(order by id) rownum,* "+
                   "FROM aqzf_safetycheckunit WHERE s3=0 AND m1 != '')a "+
				   "WHERE rownum >" + mapData.get("pageSize") + "*("+mapData.get("pageNo")+"-1)";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM aqzf_safetycheckunit WHERE s3=0 AND m1 != ''";
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
	
	@Override
	public List<Map<String, Object>> getjcdylist() {
		String sql = "SELECT id,m1 text FROM aqzf_safetycheckunit WHERE s3=0 AND m1 != ''";
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	@Override
	public void addInfo(AQZF_SafetyCheckUnitEntity jcdy) {
		save(jcdy);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE aqzf_safetycheckunit SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}


	@Override
	public AQZF_SafetyCheckUnitEntity findInfoById(long id) {
		AQZF_SafetyCheckUnitEntity a = find(id);
		flush();
		clear();
		return a;
	}

	@Override
	public void updateInfo(AQZF_SafetyCheckUnitEntity jcdy) {
		save(jcdy);
	}
	

	
}
