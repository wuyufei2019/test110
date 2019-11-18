package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.Upload_FilesEntity;
import com.cczu.model.dao.IUploadFilesDao;
import com.cczu.model.service.IUploadFilesService;
import com.cczu.sys.comm.utils.DateUtils;

@Transactional(readOnly=true)
@Service("UploadFilesService")
public class UploadFilesServiceImpl implements IUploadFilesService {
	@Resource
	private IUploadFilesDao uploadFiles;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Upload_FilesEntity> list=uploadFiles.dataGrid(mapData);
		int getTotalCount=uploadFiles.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Upload_FilesEntity findInfoById(long id) {
		return uploadFiles.findInfoById(id);
	}

	@Override
	public void addInfo(Upload_FilesEntity aqjd) {
//		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t=DateUtils.getSysTimestamp();
		aqjd.setS1(t);
		aqjd.setS2(t);
		aqjd.setS3(0);
//		aqjd.setID1(sessionuser.getId());
		uploadFiles.saveInfo(aqjd);
	}

	@Override
	public void deleteInfo(long id) {
		Upload_FilesEntity aqjd=uploadFiles.findInfoById(id);
		aqjd.setS3(0);
		uploadFiles.deleteInfo(aqjd);
	}

}
