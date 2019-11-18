package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FXGK_SclRiskAction;
import com.cczu.sys.comm.persistence.HibernateDao;

@Repository("FxgkFxpgSclActionDao")
public class FxgkFxpgSclActionDao extends HibernateDao<FXGK_SclRiskAction, Long> {

	public void deleteInfoById1(long id1) {
		String hql = " DELETE  from FXGK_SclRiskAction WHERE id1=?0";
		batchExecute(hql, id1);
	}
	
}
