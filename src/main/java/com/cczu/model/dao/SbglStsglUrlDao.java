package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.Sbgl_StsglUrlEntity;
import com.cczu.util.dao.BaseDao;

@Repository("SbglStsglUrlDao")
public class SbglStsglUrlDao extends BaseDao<Sbgl_StsglUrlEntity, Long> {
	
	public void deleteInfosById1(long id){
		String sql="delete from Sbgl_StsglUrlEntity where id1="+id;
		updateBySql(sql);
	}

}
