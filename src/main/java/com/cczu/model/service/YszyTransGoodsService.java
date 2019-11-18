package com.cczu.model.service;

import com.cczu.model.dao.YszyTransGoodsDao;
import com.cczu.model.entity.YSZY_TransportationGoods;
import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 
 * @Description: 卡口作业申报运送货物Service
 * @author: wbth
 * @date: 2018年8月29日
 */
@Transactional(readOnly=true)
@Service
public class YszyTransGoodsService extends BaseService<YSZY_TransportationGoods,Long> {
	@Resource
	private YszyTransGoodsDao transGoodsDao;

	@Override
	public HibernateDao<YSZY_TransportationGoods, Long> getEntityDao() {
		return transGoodsDao;
	}

	public List<YSZY_TransportationGoods> listByTransId(Long transId){
		List<YSZY_TransportationGoods> list = transGoodsDao.find(Restrictions.eq("transId",transId));
		return list;
	}


	public List<Map<String,Object>> listStatisticsWhpCount(Map<String,Object> map) {
		String sql ="SELECT whpname as name,count(1) as value from yszy_transoods where 1=1 GROUP BY whpname";
		SQLQuery sqlQuery = transGoodsDao.createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String,Object>> list = sqlQuery.list();
		return list;
	}


}
