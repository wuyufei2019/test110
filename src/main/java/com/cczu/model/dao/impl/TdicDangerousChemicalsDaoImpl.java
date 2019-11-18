package com.cczu.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.ITdicDangerousChemicalsDao;
import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;
import com.cczu.util.dao.BaseDao;

@Repository("ITdicDangerousChemicalsDao")
public class TdicDangerousChemicalsDaoImpl extends BaseDao<TdicDangerousChemicalsDaoImpl,Long> implements ITdicDangerousChemicalsDao {

	@Override
	public TMESK_ChemicalsdirectoryEntity findByM(String m1) {
		// TODO Auto-generated method stub
		String sql=" select * from tmesk_chemicalsdirectory where s3=0 and m2='"+m1+"' " ;
		List<TMESK_ChemicalsdirectoryEntity> list=findBySql(sql, null,TMESK_ChemicalsdirectoryEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<TMESK_ChemicalsdirectoryEntity> findlist() {
		// TODO Auto-generated method stub
		String sql=" select * from tmesk_chemicalsdirectory where s3=0 " ;
		List<TMESK_ChemicalsdirectoryEntity> list=findBySql(sql, null,TMESK_ChemicalsdirectoryEntity.class);
		return list;
	}

	@Override
	public List<TMESK_ChemicalsdirectoryEntity> findByMs(String m1) {
		// TODO Auto-generated method stub
		String sql=" select * from tmesk_chemicalsdirectory where s3=0 and m2 LIKE'%"+m1+"%'";
		List<TMESK_ChemicalsdirectoryEntity> list=findBySql(sql, null,TMESK_ChemicalsdirectoryEntity.class);
		return list;
	}

}
