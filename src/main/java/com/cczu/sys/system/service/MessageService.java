package com.cczu.sys.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.MessageDao;
import com.cczu.sys.system.entity.Message;

/**
 * 
 * @description 消息提醒service
 * @author jason
 * @date 2018年1月15日
 */
@Service
@Transactional(readOnly=true)
public class MessageService extends BaseService<Message, Integer> {
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private PermissionService permissionservice;
	@Override
	public HibernateDao<Message, Integer> getEntityDao() {
		return messageDao;
	}
	
	/**
	 * 根据id修改消息状态
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void updateStatue(Long id){
		messageDao.updateStatue(id);
	}
	/**
	 * 分类查询未读消息的数量
	 * @param id
	 */
	public List<Map<String,Object>> findTypeCount(int uid){
		return messageDao.findTypeCount(uid);
	}
	
	/**
	 * 添加msg
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void addMsg(Message msg){
		messageDao.save(msg);
	}
	
	/**
	 * 添加msg
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void addMultiUserMsg(Message msg,Map<String, Object> mapData){
		messageDao.addMultMsg(msg, mapData);
	}
	
	/**
	 * 添加msg
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void addMsgByPermission(Message msg,long qyid, String permission){
		Map<String,Object> map = new HashMap<>();
		map.put("qyid", qyid);
		map.put("permission", permission);//权限
		List<Map<String,Object>> list = permissionservice.findUsersHasThisPer(map);
		for(Map<String,Object> m :list){
			msg.setTOUSER(m.get("id")+"");
			messageDao.save(msg);
			messageDao.flush();
			msg.setID(null);
		}
	}

	/**
	 * 修改消息状态
	 */
	public void uptmsg(String id) {
		messageDao.updateStatue(Long.parseLong(id));
	}
}