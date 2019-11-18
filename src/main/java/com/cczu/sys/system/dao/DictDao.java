package com.cczu.sys.system.dao;

import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.Dict;

/**
 * 字典DAO
 * @author jason
 * @date 2017年5月31日
 */
@Repository
public class DictDao extends HibernateDao<Dict, Integer>{

}

