package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IXfssPmtDao;
import com.cczu.model.entity.XFSS_RegisterEntity;
import com.cczu.util.dao.BaseDao;

@Repository("XfssPmtDao")
public class XfssPmtDaoImpl extends BaseDao<XFSS_RegisterEntity,Long> implements IXfssPmtDao {
	@Override
	public void addInfo(Map<String,Object> xfss) {
		
	}

	@Override
	public void deleteInfo(Long id) {
		String sql=" UPDATE xfss_register SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public Map<String,Object> findById(Long id) {
		String sql ="SELECT * FROM xfss_register WHERE s3=0 AND ID="+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
