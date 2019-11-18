package com.cczu.sys.system.utils;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.MessageService;

/**
 * 消息添加工具类
 * 
 * @author XY
 * 
 */
@Component
public class MessageUtil {

	private static MessageService msgservice;

	@Autowired
	public void setMessageService(MessageService msgservice) {
		MessageUtil.msgservice = msgservice;
	}

	public static void sendMsg(String TOUSER, String FROMUSER, String title,
			String MSGTYPE, String CONTENT) {
		Message msg = new Message(TOUSER, FROMUSER, title, MSGTYPE, CONTENT);
		msg.setCREATETIME(DateUtils.getSystemTime());
		msg.setSENDSTATUE("0");
		msgservice.addMsg(msg);
	}

	public static void sendMsg(String TOUSER, String FROMUSER, String TITLE,
			String MSGTYPE, String url, String CONTENT) {
		Message msg = new Message(TOUSER, FROMUSER, TITLE, MSGTYPE, CONTENT,
				url);
		msg.setCREATETIME(DateUtils.getSystemTime());
		msg.setSENDSTATUE("0");
		msgservice.addMsg(msg);
	}

	public static void sendMsg(String TOUSER, String FROMUSER, String TITLE,
			String MSGTYPE, String CONTENT, String url, Timestamp SCHSENDTIME,
			Timestamp SUCCESSTIME) {
		Message msg = new Message(TOUSER, FROMUSER, TITLE, MSGTYPE, CONTENT,
				url, SCHSENDTIME, SUCCESSTIME);
		msg.setCREATETIME(DateUtils.getSystemTime());
		msg.setSENDSTATUE("0");
		msgservice.addMsg(msg);
	}

	public static void sendMsg(Message msg) {
		msg.setCREATETIME(DateUtils.getSystemTime());
		msg.setSENDSTATUE("0");
		msgservice.addMsg(msg);
	}
	
	public static void sendMultiUserMsg(Message msg,Map<String, Object> mapData) {
		msg.setCREATETIME(DateUtils.getSystemTime());
		msg.setSENDSTATUE("0");
		msgservice.addMultiUserMsg(msg, mapData);
	}
	
	public static void sendMsgByPermission(Message msg,long qyid, String permission) {
		msg.setCREATETIME(DateUtils.getSystemTime());
		msg.setSENDSTATUE("0");
		msgservice.addMsgByPermission(msg, qyid,permission);
	}

}
