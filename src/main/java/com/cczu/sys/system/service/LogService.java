package com.cczu.sys.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.LogDao;
import com.cczu.sys.system.entity.Log;

/**
 * 日志service
 * @author jason
 * @date 2017年5月31日
 */
@Service
@Transactional(readOnly=true)
public class LogService extends BaseService<Log, Integer> {
	
	@Autowired
	private LogDao logDao;
	
	@Override
	public HibernateDao<Log, Integer> getEntityDao() {
		return logDao;
	}
	
	/**
	 * 批量删除日志
	 * @param idList
	 */
	@Transactional(readOnly=false)
	public void deleteLog(List<Integer> idList){
		logDao.deleteBatch(idList);
	}
	
}