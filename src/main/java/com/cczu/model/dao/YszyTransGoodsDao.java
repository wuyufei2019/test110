package com.cczu.model.dao;

import com.cczu.model.entity.YSZY_TransportationGoods;
import com.cczu.sys.comm.persistence.HibernateDao;
import org.springframework.stereotype.Repository;

/**
 * 卡口作业运输dao层
 *
 */
@Repository
public class YszyTransGoodsDao extends HibernateDao<YSZY_TransportationGoods, Long> {


    public void deleteAllByTransId(long transId){
        String hql ="delete from YSZY_TransportationGoods where transId= ?0";
        batchExecute(hql,transId);
    }

}
