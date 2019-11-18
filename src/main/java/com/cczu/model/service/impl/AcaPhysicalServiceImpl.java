package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAcaPhysicalDao;
import com.cczu.model.entity.ACA_PhysicalEntity;
import com.cczu.model.service.IAcaPhysicalService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("AcaPhysicalService")
public class AcaPhysicalServiceImpl implements IAcaPhysicalService {
	@Resource
	private IAcaPhysicalDao acaPhysical;

	@Override
	public String countSave(ACA_PhysicalEntity aca) throws Exception {
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
//		Timestamp t=DateUtils.getSysTimestamp();
//		aca.setS1(t);
//		aca.setS2(t);
//		aca.setS3(0);
//		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaPhysical.saveInfo(aca));
	}
	
	@Override
	public String jccountSave(ACA_PhysicalEntity aca) throws Exception {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		aca.setS1(t);
		aca.setS2(t);
		aca.setS3(0);
		aca.setID1(sessionuser.getId());
		return JsonMapper.getInstance().writeValueAsString(acaPhysical.jcsaveInfo(aca));
	}

	@Override
	public List<ACA_PhysicalEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACA_PhysicalEntity> findAllByUserId(long id1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ACA_PhysicalEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> appPhysical(String str1, String str2)
			throws Exception {
		// TODO Auto-generated method stub
		return acaPhysical.appPhysical(str1, str2);
	}
}
