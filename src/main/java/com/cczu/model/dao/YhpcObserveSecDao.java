package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_Observations_Sec;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查-观察记录DAO
 *
 */
@Repository("YhpcObserveSecDao")
public class YhpcObserveSecDao extends BaseDao<YHPC_Observations_Sec, Long> {
    
    //app添加
	public void addInfoForApp(YHPC_Observations_Sec entity) {
		// TODO Auto-generated method stub
		save(entity);
	}
}
