package com.cczu.model.lbyp.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.lbyp.dao.LbypApplyDao;
import com.cczu.model.lbyp.dao.LbypFfjlDao;
import com.cczu.model.lbyp.dao.LbypWpxxDao;
import com.cczu.model.lbyp.entity.Lbyp_ApplyRecord;
import com.cczu.model.lbyp.entity.Lbyp_DistributeRecord;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("LbypApplyService")
public class LbypApplyService {
	
	@Resource
	private LbypApplyDao lbypApplyDao;
	@Resource
	private LbypFfjlDao lbypFfjlDao;
	@Resource
	private LbypWpxxDao lbypWpxxDao;
	
	public void addInfo(Lbyp_ApplyRecord entity,String[] wpqd,String[] amount) {
		// TODO Auto-generated method stub
		
		Timestamp t=DateUtils.getSysTimestamp();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		entity.setID1(sessionuser.getQyid());
		entity.setID4(UserUtil.getCurrentUser().getDepartmen());
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		lbypApplyDao.save(entity);//保存申请记录
		final long id = entity.getID();
		//发送msg消息
		Map<String,Object>  map = new HashMap<String,Object>(){{
			put(Message.MSGTARGET_H5,"weixin/enterprise/lbyp/laobaoprove.jsp");
			put(Message.MSGTARGET_PC,"lbyp/lssq/review/"+id);
		}};
		MessageUtil.sendMsg(entity.getID3()+"", sessionuser.getId()+"", "劳保用品临时申请", Message.MessageType.DSH.getMsgType(),JSON.toJSONString(map),"劳保用品临时申请");
		addRecord(entity,wpqd,amount,t,sessionuser);
	}

	public void addRecord(Lbyp_ApplyRecord entity,String[] wps,String[] amount,Timestamp t,ShiroUser sessionuser){
		Lbyp_DistributeRecord e= new Lbyp_DistributeRecord();
		e.setS1(t);
		e.setS2(t);
		e.setS3(1);//没有审核通过之前 s3为1
		e.setID3(entity.getID());
		e.setReceiveperson(sessionuser.getName());
		//保存领取记录，审核不成功将记录的s3=1
		if(amount.length>0&&wps.length>0){
		    for(int i=0;i<amount.length;i++){
				e.setGoodsname(wps[i]);
				e.setAmount(Integer.parseInt(amount[i]));
				lbypFfjlDao.save(e);
				e.setID(null);
			}
		}
	}
	
	public void updateInfo(Lbyp_ApplyRecord entity,String[] wpqd,String[] amount) {
		//保存信息 记录信息后，再删除领取记录，之后再增加
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		lbypApplyDao.save(entity);
		lbypFfjlDao.deleteInfos(entity.getID());
		addRecord(entity,wpqd,amount,t,UserUtil.getCurrentShiroUser());
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		//删除申请
		lbypApplyDao.deleteInfo(id);
		//删除领取记录
		lbypFfjlDao.deleteInfos(id);
	}

	public void updateProperty(Lbyp_ApplyRecord e) {
		// TODO Auto-generated method stub
		// 先读取一遍数据库获取result字段判读值是否改变
		Lbyp_ApplyRecord old = findById(e.getID());
		lbypApplyDao.updateProperty(e);
		if (!e.getResult().equals(old.getResult())) {
			// 通过
			if ("1".equals(e.getResult())) {
				// 更新发放记录的s3标志位为0
				lbypFfjlDao.updateProperty(e.getID(), 0);
				// 更新物品的储量
				lbypWpxxDao.updateStoragerateSh(e.getID(), "-");
			} else if ("0".equals(e.getResult())&& StringUtils.isNoneBlank(old.getResult())) {// 不通过
				lbypFfjlDao.updateProperty(e.getID(), 1);
				lbypWpxxDao.updateStoragerateSh(e.getID(), "+");
			}
		}
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lbypApplyDao.dataGrid(mapData);
		int getTotalCount=lbypApplyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public Lbyp_ApplyRecord findById(Long id) {
		// TODO Auto-generated method stub
		return lbypApplyDao.find(id);
	}


}


