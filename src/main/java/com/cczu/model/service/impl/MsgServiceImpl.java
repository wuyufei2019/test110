package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqpxJhxxDao;
import com.cczu.model.dao.IBisOccupharmreportDao;
import com.cczu.model.dao.IMsgDao;
import com.cczu.model.entity.AQPX_PlanEntity;
import com.cczu.model.entity.BIS_OccupharmExamReportEntity;
import com.cczu.model.entity.MSG_detailEntity;
import com.cczu.model.service.IMsgService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.CompRoleService;
import com.cczu.sys.system.service.UserService;


@Transactional(readOnly=true)
@Service("MsgService")
public class MsgServiceImpl implements IMsgService {
	
	@Resource
	private IMsgDao msgDao;
	@Resource
	private IAqpxJhxxDao aqpxJhxxDao;
	@Resource
	private IBisOccupharmreportDao bisOccupharmreportDao;
	@Autowired
	private CompRoleService compRoleService;
	@Autowired
	private UserService userService;

	@Override
	public Long addInfo(MSG_detailEntity obj) {
		msgDao.addInfo(obj);
		return obj.getID();
		
	}
	@Override
	public void addAllInfo(long uid,String content,String remind,String type,String status,String qyids) {
		msgDao.addAllInfo(uid, content, remind, type, status, qyids);
	}
	@Override
	public void addWjInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		msgDao.addWjInfo(map);
	}

	@Override
	public void updateInfo(MSG_detailEntity obj) {
		msgDao.updateInfo(obj);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=msgDao.dataGrid(mapData);
		int getTotalCount=msgDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public int msgCnt(Map<String, Object> mapData) {
		int getTotalCount=msgDao.getMsgNoReadCnt(mapData);
		return getTotalCount;
	}

	@Override
	public void deleteInfo(long id) {
		msgDao.deleteInfo(id);
	}

	@Override
	public MSG_detailEntity findInfoById(Long id) {
		return msgDao.findInfoById(id);
	}
	
	@Override
	public void updateInfoByUserId(Long id) {
		msgDao.updateInfoByUserId(id);
	}
	
	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="消息列表.xls";
		List<Map<String, Object>> list=msgDao.getExcelData(mapData);
		String[] title={"发布者","类型","提醒","内容","状态"};  
		String[]keys={"id1","type","info","content","status"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	@Override
	public void getTask() {
		//把过期的检测报告加入消息
		List<BIS_OccupharmExamReportEntity> jcList = bisOccupharmreportDao.findAll();
		for(BIS_OccupharmExamReportEntity a : jcList){
			a.setM7("1");
			bisOccupharmreportDao.updateInfo(a);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qyid", a.getID1());//企业用户
			List<User> list = userService.findByAllUserByContent(map);
			for (User ur : list) {
				//添加消息对象
				MSG_detailEntity msg = new MSG_detailEntity();
				Timestamp t=DateUtils.getSysTimestamp();
				msg.setS1(t);
				msg.setS2(t);
				msg.setS3(0);
				if(a.getID2() != null){
					msg.setID1(a.getID2());
				}
				msg.setType("1");//检测报告
				msg.setInfo("已过期");
				msg.setContent(a.getM1());
				msg.setSengObj((long)ur.getId());
				msg.setStatus("2");//未读
				msg.setReleaseTime(a.getS1());
				msgDao.addInfo(msg);
			}
		}
		//把过期的培训计划加入消息
		List<AQPX_PlanEntity> pxList = aqpxJhxxDao.findAllInfo();
		for(AQPX_PlanEntity a : pxList){
			a.setM8("1");
			aqpxJhxxDao.updateInfo(a);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qyid", a.getID1());//企业用户
			List<User> list = userService.findByAllUserByContent(map);
			for (User ur : list) {
				//添加消息对象
				MSG_detailEntity msg = new MSG_detailEntity();
				Timestamp t=DateUtils.getSysTimestamp();
				msg.setS1(t);
				msg.setS2(t);
				msg.setS3(0);
				if(a.getID4()!=0){
					msg.setID1(a.getID4());
				}
				msg.setType("2");//培训计划
				msg.setInfo("已结束");
				msg.setContent(a.getM1());
				msg.setSengObj((long)ur.getId());
				msg.setStatus("2");//未读
				msg.setReleaseTime(a.getS1());
				msgDao.addInfo(msg);
			}
		}
	}

	@Override
	public List<MSG_detailEntity> findAllMsgList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  msgDao.findAllMsgList(map);
	}

}
