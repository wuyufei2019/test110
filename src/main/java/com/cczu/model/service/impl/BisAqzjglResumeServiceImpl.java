package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.BIS_DirectorResumeEntity;
import com.cczu.model.dao.IBisAqzjglResumeDao;
import com.cczu.model.service.IBisAqzjglResumeService;
import com.cczu.sys.comm.utils.DateUtils;

@Service("IBisAqzjglResumeService")
public class BisAqzjglResumeServiceImpl implements IBisAqzjglResumeService {
	@Resource
	private IBisAqzjglResumeDao bisAqzjglResume;

	@Override
	public BIS_DirectorResumeEntity findInfoById(long id) {
		return bisAqzjglResume.findInfoById(id);
	}

	@Override
	public Long addInfo(BIS_DirectorResumeEntity bis) {
		Timestamp t=DateUtils.getSysTimestamp();
		bis.setS1(t);
		bis.setS2(t);
		bis.setS3(0);
		
		long l=bisAqzjglResume.addInfore(bis);
		if(l>0){
			return l;
		}else{
			return (long) 0;
		}
	}
	
	@Override
	public void updateInfo(BIS_DirectorResumeEntity bis) {
		Timestamp t=DateUtils.getSysTimestamp();
		bis.setS2(t);
		bis.setS3(0);
		bisAqzjglResume.updateInfo(bis);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		bisAqzjglResume.deleteInfo(id);
	}


	@Override
	public List<BIS_DirectorResumeEntity> findAll() {
		return bisAqzjglResume.findAlllist();
	}

	@Override
	public List<BIS_DirectorResumeEntity> findAllById1(long id1) {
		return bisAqzjglResume.findAllById1(id1);
	}


}
