package com.cczu.model.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqpxPxjhbmDao;
import com.cczu.model.entity.AQPX_PlanDepartmentEntity;
import com.cczu.model.service.IAqpxPxjhbmService;

@Transactional(readOnly=true)
@Service("AqpxPxjhbmService")
public class AqpxPxjhbmServiceImpl implements IAqpxPxjhbmService {

	@Resource
	private IAqpxPxjhbmDao aqpxjhbmdao;
	
	@Override
	public void addInfo(AQPX_PlanDepartmentEntity ap) {
		// TODO Auto-generated method stub
		aqpxjhbmdao.addInfo(ap);
	}

	@Override
	public void updateInfo(AQPX_PlanDepartmentEntity ap) {
		// TODO Auto-generated method stub
		aqpxjhbmdao.updateInfo(ap);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		aqpxjhbmdao.deleteInfo(id);
	}

	@Override
	public AQPX_PlanDepartmentEntity findpxjh(Long jhid) {
		// TODO Auto-generated method stub
		return aqpxjhbmdao.findpxjh(jhid);
	}

	@Override
	public AQPX_PlanDepartmentEntity findpxbm(Long bmid) {
		// TODO Auto-generated method stub
		return aqpxjhbmdao.findpxbm(bmid);
	}

	@Override
	public AQPX_PlanDepartmentEntity findAll(Long qyid) {
		// TODO Auto-generated method stub
		return aqpxjhbmdao.findAll(qyid);
	}

	@Override
	public List<AQPX_PlanDepartmentEntity> getlist(Long qyid) {
		// TODO Auto-generated method stub
		return aqpxjhbmdao.getlist(qyid);
	}

}
