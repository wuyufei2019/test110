package com.cczu.model.sggl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.sggl.entity.SGGL_CasualtyEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SgglSwryDao")
public class SgglSwryDao extends BaseDao<SGGL_CasualtyEntity,Long>  {
	
	public List<SGGL_CasualtyEntity> findAllInfo() {
		String sql ="SELECT * FROM sggl_casualty WHERE s3=0";
		List<SGGL_CasualtyEntity> list=findBySql(sql, null,SGGL_CasualtyEntity.class);
		return list;
		
	}

	public void addInfo(SGGL_CasualtyEntity erm) {
		save(erm);
	}
	
	public void updateInfo(SGGL_CasualtyEntity erm) {
		save(erm);
	}

	public void deleteInfoById1(Long id) {
		String sql=" delete from sggl_casualty WHERE ID1="+id;
		updateBySql(sql);
	}
	
}
