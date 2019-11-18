package com.cczu.model.mbgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetMeetingDao;
import com.cczu.model.mbgl.entity.Target_SafetyMeeting;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.dao.MessageDao;
import com.cczu.sys.system.dao.UserDao;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;

/**
 *  安全会议Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetMeetingService")
public class TargetMeetingService {
	@Resource
	private TargetMeetingDao targetMeetingDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserDao userDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=targetMeetingDao.dataGrid(mapData);
		int getTotalCount=targetMeetingDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(Target_SafetyMeeting target) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS1(t);
		target.setS2(t);
		target.setS3(0);
		if("1".equals(sessionuser.getUsertype())){
			target.setID1(sessionuser.getQyid());
		}
		targetMeetingDao.save(target);
		final long id = target.getID();
		//消息推送
		if("1".equals(target.getState())){//待开
			Map<String,Object> map = new HashMap<>();
			map.put("qyid", sessionuser.getQyid());
			map.put("departmens", target.getAttenddeps());
			Message msg=new Message();
			msg.setMSGTYPE(Message.MessageType.AQHY.getMsgType());
			msg.setCONTENT("请您于"+DateUtils.formatDate(target.getTime(), "MM月dd日 hh时mm分")+"准时到"+target.getAddress()+"参加"+target.getTheme()+"安全会议");
			msg.setTITLE("会议通知");
			msg.setFROMUSER(sessionuser.getId()+"");
			Map msgmap = new HashMap(){{
				put("PC","target/aqhy/view/"+id);
				put("H5","/target/aqhy/view.do?id="+id);
			}};
			msg.setURL(JSON.toJSONString(msgmap));
			MessageUtil.sendMultiUserMsg(msg, map);
		}
	}
	public long addInfoReturnID(Target_SafetyMeeting tatget) {
		targetMeetingDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_SafetyMeeting tatget) {
		targetMeetingDao.save(tatget);
	}
	
	public void delayMeeting(final long id, String time, String reason) {
		// 发送推迟信息
		Target_SafetyMeeting target = findInfoById(id);
		targetMeetingDao.delayMeeting(id, time, reason);
		ShiroUser sessionuser = UserUtil.getCurrentShiroUser();
		Map<String, Object> map = new HashMap<>();
		map.put("qyid", sessionuser.getQyid());
		map.put("departmens", target.getAttenddeps());
		Message msg = new Message();
		msg.setMSGTYPE(Message.MessageType.AQHY.getMsgType());
		msg.setTITLE("会议推迟");
		msg.setFROMUSER(sessionuser.getId() + "");
		msg.setCONTENT("由于"+ reason+ ",原定于"+ DateUtils.formatDate(target.getTime(), "MM月dd日 hh时mm分")+ target.getTheme()
				+ "安全会议现推迟至"+ DateUtils.formatDate(DateUtils.parseDate(time),"MM月dd日 hh时mm分"));
		Map msgmap = new HashMap(){{
			put("PC","target/aqhy/view/"+id);
			put("H5","/target/aqhy/view.do?id="+id);
		}};
		msg.setURL(JSON.toJSONString(msgmap));
		MessageUtil.sendMultiUserMsg(msg, map);
	}
	
	public void feedbackMeeting(long id,String feedback) {
		targetMeetingDao.feedbackMeeting(id,feedback);
	}
	
	public void deleteInfo(long id) {
		targetMeetingDao.deleteInfo(id);
	}

	public Target_SafetyMeeting findInfoById(Long id) {
		return targetMeetingDao.find(id);
	}
	
}
