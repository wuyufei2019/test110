package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxPxjhbmDao;
import com.cczu.model.entity.AQPX_PlanDepartmentEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqpxPxjhbmDao")
public class AqpxPxjhbmDaoImpl extends BaseDao<AQPX_PlanDepartmentEntity,Long> implements IAqpxPxjhbmDao {

	@Override
	public void addInfo(AQPX_PlanDepartmentEntity ap) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO aqpx_plandepartment (S1,S2,S3,ID1,ID2,ID3)"
				+ " VALUES (getdate() ,getdate(),0,"+ap.getID1()+","+ap.getID2()+","+ap.getID3()+") ";
		updateBySql(sql);
	}

	@Override
	public void updateInfo(AQPX_PlanDepartmentEntity ap) {
		// TODO Auto-generated method stub
		String sql=" UPDATE aqpx_plandepartment SET "
				+ " S2=getdate(),ID2="+ap.getID2()+",ID3="+ap.getID3()+"";
		updateBySql(sql);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		String sql=" UPDATE aqpx_plandepartment SET S3=1 WHERE ID="+id;
		updateBySql(sql);
	}

	@Override
	public AQPX_PlanDepartmentEntity findpxjh(Long jhid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plandepartment WHERE s3=0 AND id2 ="+jhid;
		List<AQPX_PlanDepartmentEntity> list=findBySql(sql, null,AQPX_PlanDepartmentEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public AQPX_PlanDepartmentEntity findpxbm(Long bmid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plandepartment WHERE s3=0 AND id3 ="+bmid;
		List<AQPX_PlanDepartmentEntity> list=findBySql(sql, null,AQPX_PlanDepartmentEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public AQPX_PlanDepartmentEntity findAll(Long qyid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plandepartment WHERE s3=0 AND id1 ="+qyid;
		List<AQPX_PlanDepartmentEntity> list=findBySql(sql, null,AQPX_PlanDepartmentEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AQPX_PlanDepartmentEntity> getlist(Long qyid) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM aqpx_plandepartment WHERE s3=0 AND id1 ="+qyid;
		List<AQPX_PlanDepartmentEntity> list=findBySql(sql, null,AQPX_PlanDepartmentEntity.class);
		return list;
		
	}

}
