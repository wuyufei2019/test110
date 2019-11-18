package com.cczu.model.dao.impl;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IBisAqwlglDao;
import com.cczu.model.entity.BIS_T_Safetynetenterprise;
import com.cczu.util.dao.BaseDao;

@SuppressWarnings("unchecked")
@Repository("BisAqwlglDao")
public class BisAqwlglDaoImpl extends BaseDao<BIS_T_Safetynetenterprise, Long> implements IBisAqwlglDao{

	@Override
	public List<BIS_T_Safetynetenterprise> findAll(Long uid) {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from bis_t_safetynetenterprise b ");
		sb.append("where id1=?0 ");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(),uid);
		sqlQuery.addEntity(BIS_T_Safetynetenterprise.class);
		return sqlQuery.list();
	}

	@Override
	public BIS_T_Safetynetenterprise findById(Long id) {
		return find(id);
	}
}
