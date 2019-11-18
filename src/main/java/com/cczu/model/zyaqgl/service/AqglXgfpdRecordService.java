package com.cczu.model.zyaqgl.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglXgfpdRecordDao;

/**
 *  安全管理-相关方评定分数记录、
 *  Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglXgfpdRecordService")
public class AqglXgfpdRecordService {

	@Resource
	private AqglXgfpdRecordDao aqglXgfpdRecordDao;
	
	//根据资质id查询
	public List<Map<String, Object>> findPdRecordById(Long id) {
		return aqglXgfpdRecordDao.findPdRecordById(id);
	}
}
