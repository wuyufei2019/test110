package com.cczu.model.mbgl.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetAqzrsDao;
import com.cczu.model.mbgl.dao.TargetAqzrsscDao;
import com.cczu.model.mbgl.entity.Target_SafetyDutyAgreement;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;

/**
 *  安全责任书信息Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetAqzrsService")
public class TargetAqzrsService {
	@Resource
	private TargetAqzrsDao targetAqzrsDao;
	@Resource
	private TargetAqzrsscDao targetAqzrsscDao;
	
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=targetAqzrsDao.dataGrid(mapData);
		int getTotalCount=targetAqzrsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(Target_SafetyDutyAgreement target,String[] departments) {		
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			target.setID1(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS1(t);
		target.setS2(t);
		target.setS3(0);
		targetAqzrsDao.save(target);
		final long id=target.getID();
		//责任书下发内容插入成功，插入消息提醒
		if(id>0){
			Map<String,Object> map = new HashMap<>();
			map.put("qyid", sessionuser.getQyid());
			map.put("departmens", StringUtils.join(departments, ","));
			Message msg=new Message();
			msg.setMSGTYPE(Message.MessageType.XWJ.getMsgType());
			msg.setCONTENT("您有新的安全责任书待查阅，请及时填写回执！");
			msg.setTITLE("您有新的安全责任书待查阅！");
			msg.setFROMUSER(sessionuser.getId()+"");
			Map msgmap = new HashMap(){{
				put("PC","target/aqzrs/view/"+id);
				put("H5","target/aqzrsxf/index.do");
			}};
			msg.setURL(JSON.toJSONString(msgmap));
			MessageUtil.sendMultiUserMsg(msg, map);
		}
	}
	public long addInfoReturnID(Target_SafetyDutyAgreement target) {
		targetAqzrsDao.save(target);
		return target.getID();
	}

	public void updateInfo(Target_SafetyDutyAgreement target,String deleteid,String insertid) {
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		if("1".equals(sessionuser.getUsertype())){
			target.setID1(sessionuser.getQyid());
		}
		Timestamp t = DateUtils.getSysTimestamp();
		target.setS2(t);
		targetAqzrsDao.save(target);
		//插入接收文件信息
		if(StringUtils.isNoneBlank(insertid)){
			String str=Arrays.toString(insertid.split(","));
			targetAqzrsscDao.insertRecData(target.getID(),str.substring(1, str.length()-1),sessionuser.getQyid());
		}
		//删除接收文件信息
		if(StringUtils.isNoneBlank(deleteid)){
				targetAqzrsscDao.deleteRecInfoByDepId(deleteid);
		}
	}
	
	public void deleteInfo(long id) {
		targetAqzrsDao.deleteInfo(id);
	}

	public Target_SafetyDutyAgreement findInfoById(Long id) {
		return targetAqzrsDao.find(id);
	}
}
