package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IAqpxKsjlDao;
import com.cczu.model.entity.AQPX_TestpaperEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqpxKsjlDao")
public class AqpxKsjlDaoImpl extends BaseDao<AQPX_TestpaperEntity,Long> implements IAqpxKsjlDao {

	@Override
	public void addInfo(AQPX_TestpaperEntity at) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO aqpx_testpaper (S1,S2,S3,ID1,ID2,ID3,ID4,ID5,H,M1,M2,M3) "
				+ "VALUES (getdate() ,getdate(),0,"+at.getID1()+","+at.getID2()+","
						+ ""+at.getID3()+","+at.getID4()+","+at.getID5()+",'"+at.getH()+"','"+at.getM1()+"','"+at.getM2()+"','"+at.getM3()+"')";
		updateBySql(sql);
	}

	@Override
	public List<AQPX_TestpaperEntity> getlist(Long ygid, String bs) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM aqpx_testpaper WHERE ID3="+ygid+" AND H='"+bs+"'";
		List<AQPX_TestpaperEntity> list = findBySql(sql,null,AQPX_TestpaperEntity.class);
		return list;
	}

	@Override
	public void updateInfo(AQPX_TestpaperEntity at) {
		// TODO Auto-generated method stub
		String sql ="UPDATE aqpx_testpaper SET S2=getdate(),M1='"+at.getM1()+"',M2='"+at.getM2()+"' WHERE H='"+at.getH()+"' AND ID2="+at.getID2()+" AND ID3="+at.getID3()+" ";
		updateBySql(sql);
	}

	@Override
	public List<AQPX_TestpaperEntity> getall(Long ygid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM aqpx_testpaper WHERE ID3="+ygid+" ";
		List<AQPX_TestpaperEntity> list = findBySql(sql,null,AQPX_TestpaperEntity.class);
		return list;
	}

	@Override
	public List<AQPX_TestpaperEntity> getkcsj(Long ygid, Long kcid) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM aqpx_testpaper WHERE ID3="+ygid+" AND ID5="+kcid+" ";
		List<AQPX_TestpaperEntity> list = findBySql(sql,null,AQPX_TestpaperEntity.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> getsjxx(String bs) {
		String sql = "select a.h,a.id4,a.m2 da,b.* from aqpx_testpaper a left join aqpx_itembank b on a.id2=b.id where h like '"+bs+"'";
		List<Map<String, Object>> list = findBySql(sql,null,Map.class);
		return list;
	}

    @Override
    public List<Map<String, Object>> getctxx(String bs) {
        String sql = "SELECT * FROM (select a.h,a.id4,a.m2 da,b.* from aqpx_testpaper a left join aqpx_itembank b on a.id2=b.id where h like '"+bs+"') a WHERE a.da<>a.m8 ";
        List<Map<String, Object>> list = findBySql(sql,null,Map.class);
        return list;
    }



}
