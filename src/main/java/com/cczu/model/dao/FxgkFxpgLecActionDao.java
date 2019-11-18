package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FXGK_LecRiskAction;
import com.cczu.sys.comm.persistence.HibernateDao;

@Repository("FxgkFxpgLecActionDao")
public class FxgkFxpgLecActionDao extends HibernateDao<FXGK_LecRiskAction, Long> {

	public void deleteInfoById1(long id1) {
		String hql = " DELETE  from FXGK_LecRiskAction WHERE id1=?0";
		batchExecute(hql, id1);
	}
	
}
