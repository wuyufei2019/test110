package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FXGK_RiskAction;
import com.cczu.sys.comm.persistence.HibernateDao;

@Repository("FxgkFxpgJhaActionDao")
public class FxgkFxpgJhaActionDao extends HibernateDao<FXGK_RiskAction, Long> {

	public void deleteInfoById1(long id1) {
		String hql = " DELETE  from FXGK_RiskAction WHERE id1=?0";
		batchExecute(hql, id1);
	}
	
}
