package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.FXGK_HazopRiskStep;
import com.cczu.sys.comm.persistence.HibernateDao;

@Repository("FxgkFxpgHazopStepDao")
public class FxgkFxpgHazopStepDao extends HibernateDao<FXGK_HazopRiskStep, Long> {

	public void deleteInfoById1(long id1) {
		String hql = " DELETE  from FXGK_HazopRiskStep WHERE id1=?0";
		batchExecute(hql, id1);
	}
	
}
