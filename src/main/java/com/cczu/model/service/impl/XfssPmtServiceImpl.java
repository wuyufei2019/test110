package com.cczu.model.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IXfssPmtDao;
import com.cczu.model.service.IXfssPmtService;


@Transactional(readOnly=true)
@Service("XfssPmtService")
public class XfssPmtServiceImpl implements IXfssPmtService {
	
	@Resource
	private IXfssPmtDao xfssPmtDao;

	@Override
	public void addInfo(Map<String,Object> xfss) {
		xfssPmtDao.addInfo(xfss);
		
	}

	@Override
	public void deleteInfo(long id) {
		xfssPmtDao.deleteInfo(id);
	}
	
	@Override
	public Map<String,Object> findById(Long id) {
		return xfssPmtDao.findById(id);
	}
}
