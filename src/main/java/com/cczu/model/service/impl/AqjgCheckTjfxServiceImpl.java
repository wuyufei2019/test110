package com.cczu.model.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqjgCheckTjfxDao;
import com.cczu.model.service.IAqjgCheckTjfxService;
@Transactional(readOnly=true)
@Service("aqjgchecktjfxservice")
public class AqjgCheckTjfxServiceImpl implements IAqjgCheckTjfxService{
	AqjgCheckTjfxDao aqjgchecktjfxdao;
	@Override
	public List<Object[]> getYearDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
