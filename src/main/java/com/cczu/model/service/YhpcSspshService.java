package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cczu.model.dao.YhpcSspshDao;
import com.cczu.model.entity.YHPC_CheckHiddenInfoApproveEntity;
import com.cczu.sys.system.dao.UserDao;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 
 * @Description: 随手拍审核Service
 * @author: zpc
 * @date: 2018年1月6日
 */
@Service("YhpcSspshService")
public class YhpcSspshService {
	
	@Resource
	private YhpcSspshDao yhpcSspshDao;
	@Resource
	private UserDao userDao;
	
	/**
	 * 记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcSspshDao.dataGrid(mapData);
		int getTotalCount=yhpcSspshDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//根据id删除
	public void deleteById(long id){
		yhpcSspshDao.deleteById(id);
	}
	
	//根据id查找数据
	public YHPC_CheckHiddenInfoApproveEntity findById(Long id) {
		return yhpcSspshDao.find(id);
	}
	
	//根据id查找详细数据
	public Map<String,Object> findInforById(Long id) {
		Map<String,Object> map=yhpcSspshDao.findInforById(id);
		return map;
	}
	
	//修改随手拍审核信息
	public void updatesspsh(YHPC_CheckHiddenInfoApproveEntity sspsh){
		//发送msg消息
		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"anyphoto/entAnyPhotoMainView.do");
		msgmap.put(Message.MSGTARGET_PC,"yhpc/ssp/index");
		MessageUtil.sendMsg(sspsh.getHandlepersons(), sspsh.getHandlepersons(), "您有随手拍待整改，请立即整改！",Message.MessageType.DJC.getMsgType(),JSON.toJSONString(msgmap), "您有随手拍待整改，请立即整改！");
		yhpcSspshDao.save(sspsh);
	}
	
	/**
	 * 添加随手拍审核信息
	 */
	public void addsspsh(YHPC_CheckHiddenInfoApproveEntity sspsh){
		yhpcSspshDao.save(sspsh);
		Map<String,Object>  msgmap = new HashMap<String,Object>();
		msgmap.put(Message.MSGTARGET_H5,"weixin/enterprise/danger/anyphoto/anyphotoapprovemain.jsp");
		msgmap.put(Message.MSGTARGET_PC,"yhpc/sspsh/sh/"+sspsh.getID());
		List<User> userlist = userDao.findUserByPermission(UserUtil.getCurrentShiroUser().getQyid(), "yhpc:sspsh:sh");
		for (User user : userlist) {
			MessageUtil.sendMsg(UserUtil.getCurrentShiroUser().getId()+"", user.getId()+"", "随手拍审核", Message.MessageType.DSH.getMsgType(),JSON.toJSONString(msgmap),"随手拍审核");
		}
	}
}
