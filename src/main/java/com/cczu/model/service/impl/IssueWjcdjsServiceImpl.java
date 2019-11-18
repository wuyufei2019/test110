package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.IIssueWjcdjsDao;
import com.cczu.model.entity.ISSUE_FileTransmissionReceivingEntity;
import com.cczu.model.service.IIssueWjcdjsService;
import com.cczu.sys.comm.utils.ExportExcel;
/**
 * 文件传递接收service接口实现类
 * @author jason
 *
 */

@Service("IssueWjcdjsService")
public class IssueWjcdjsServiceImpl implements IIssueWjcdjsService {
	@Resource
	private IIssueWjcdjsDao issueWjcdjsDao;
	
	
	@Override
	public int addInfor(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.addInfor(mapData);
	}
	@Override
	public String addHzInfor(ISSUE_FileTransmissionReceivingEntity e) {
		// TODO Auto-generated method stub
		String result="success";
		 try {
			issueWjcdjsDao.updateHzInfo(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result="error";
		}
		 return result;
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=issueWjcdjsDao.dataGrid(mapData);
		int getTotalCount=issueWjcdjsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.getTotalCount(mapData);
	}

	@Override
	public int deleteInfo(long id) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.deleteInfo(id);
	}
	
	@Override
	public int deleteInfoByFid(long fid) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.deleteInfoByFid(fid);
	}
	
	@Override
	public ISSUE_FileTransmissionReceivingEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.findInfoById(id);
	}

	@Override
	public int updateIsorNotLook(long uid,long fid) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.updateIsorNotLook(uid, fid);
	}



	@Override
	public List<ISSUE_FileTransmissionReceivingEntity> findInfoByUId(long uid) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.findInfoByUId(uid);
	}

	@Override
	public int updateIsorNotDownload(long uid, long fid) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.updateIsorNotDownload(uid, fid);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String[] Title={"文件名称","企业名称","是否查看","是否下载","下载日期","发布时间"};  
		String[] keys={"wj","qy","M1","M2","M3","S1"};
		List <Map<String, Object>> list=issueWjcdjsDao.getExport(mapData);
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			Title = (mapData.get("coltext").toString()).split(",") ;
			keys = (mapData.get("colval").toString()).split(",") ;
	}
		new ExportExcel("安全文件传递与接收记录表.xls", Title, keys,list, response, true);
		
	
	}

	@Override
	public List<Map<String, Object>> findUserListByState(int wjid, int state,int type) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.findUserListByState(wjid, state, type);
	}
	@Override
	public ISSUE_FileTransmissionReceivingEntity findInfoByIds(long uid,long fid) {
		// TODO Auto-generated method stub
		return issueWjcdjsDao.findInfoByIds(uid, fid);
	}


}
