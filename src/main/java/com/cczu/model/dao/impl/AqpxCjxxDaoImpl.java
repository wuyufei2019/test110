package com.cczu.model.dao.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxCjxxDao;
import com.cczu.model.entity.AQPX_ExamresultsEntity;
import com.cczu.util.dao.BaseDao;
@Repository("AqpxCjxxDao")
public class AqpxCjxxDaoImpl extends BaseDao<AQPX_ExamresultsEntity,Long> implements IAqpxCjxxDao {

	@Override
	public void addInfo(AQPX_ExamresultsEntity ae) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO aqpx_examresults (S1,S2,S3,ID1,ID2,ID3,M1,M2) "
				+ "VALUES ( getdate() ,getdate(),0,"+ae.getID1()+","+ae.getID2()+""
						+ ","+ae.getID3()+",'"+ae.getM1()+"','"+ae.getM2()+"')";
		updateBySql(sql);
	}

	@Override
	public void updateInfo(AQPX_ExamresultsEntity ae) {
		// TODO Auto-generated method stub
		String sql ="UPDATE aqpx_examresults SET M1="+ae.getM1()+" "
				+ "WHERE ID2="+ae.getID2()+" AND ID3="+ae.getID3();
		updateBySql(sql);
	}

	@Override
	public List<AQPX_ExamresultsEntity> getlist(Long ygid, Long kcid, Date kssj) {
		// TODO Auto-generated method stub
		String sql =" SELECT * FROM aqpx_examresults WHERE ID2="+kcid+" AND ID3="+kcid+" AND M2='"+kssj+"'";
		List<AQPX_ExamresultsEntity> list = findBySql(sql,null,AQPX_ExamresultsEntity.class);
		return list;
	}

}
