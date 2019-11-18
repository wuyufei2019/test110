package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.IssueZfWjcdjsDao;
import com.cczu.model.entity.ISSUE_ZfFileTransmissionReceivingEntity;
import com.cczu.sys.comm.utils.ExportExcel;
/**
 * 文件传递接收service接口实现类
 * @author jason
 *
 */

@Service("IssueZfWjcdjsService")
public class IssueZfWjcdjsService{
	@Resource
	private IssueZfWjcdjsDao issueZfWjcdjsDao;
	
	
	public int addInfor(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.addInfor(mapData);
	}

	public String addHzInfor(ISSUE_ZfFileTransmissionReceivingEntity e) {
		// TODO Auto-generated method stub
		String result="success";
		 try {
			issueZfWjcdjsDao.updateHzInfo(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result="error";
		}
		 return result;
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=issueZfWjcdjsDao.dataGrid(mapData);
		int getTotalCount=issueZfWjcdjsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.getTotalCount(mapData);
	}

	public int deleteInfo(long id) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.deleteInfo(id);
	}
	
	public int deleteInfoByFid(long fid) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.deleteInfoByFid(fid);
	}
	
	public ISSUE_ZfFileTransmissionReceivingEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.findInfoById(id);
	}

	public int updateIsorNotLook(long uid,long fid) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.updateIsorNotLook(uid, fid);
	}

	public List<ISSUE_ZfFileTransmissionReceivingEntity> findInfoByUId(long uid) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.findInfoByUId(uid);
	}

	public int updateIsorNotDownload(long uid, long fid) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.updateIsorNotDownload(uid, fid);
	}

	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String[] Title={"文件名称","企业名称","是否查看","是否下载","下载日期","发布时间"};  
		String[] keys={"wj","qy","M1","M2","M3","S1"};
		List <Map<String, Object>> list=issueZfWjcdjsDao.getExport(mapData);
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			Title = (mapData.get("coltext").toString()).split(",") ;
			keys = (mapData.get("colval").toString()).split(",") ;
	}
		new ExportExcel("安全文件传递与接收记录表.xls", Title, keys,list, response, true);
		
	
	}

	public List<Map<String, Object>> findUserListByState(int wjid, int state,int type) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.findUserListByState(wjid, state, type);
	}
	
	public ISSUE_ZfFileTransmissionReceivingEntity findInfoByIds(long uid,long fid) {
		// TODO Auto-generated method stub
		return issueZfWjcdjsDao.findInfoByIds(uid, fid);
	}


}
