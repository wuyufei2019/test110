package com.cczu.sys.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.DictDao;
import com.cczu.sys.system.entity.Dict;

/**
 * 字典service
 * @author jason
 * @date 2017年5月31日
 */
@Service
@Transactional(readOnly=true)
public class DictService extends BaseService<Dict, Integer> {
	
	@Autowired
	private DictDao dictDao;

	@Override
	public HibernateDao<Dict, Integer> getEntityDao() {
		return dictDao;
	}
}

