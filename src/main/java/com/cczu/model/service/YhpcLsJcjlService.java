package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.ShiroRealm;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcLsJcjlDao;
import com.cczu.model.entity.YHPC_InterimCheckContentEntity;
import com.cczu.model.entity.YHPC_InterimCheckRecordEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  临时检查记录Service
 *
 */
@Transactional(readOnly=true)
@Service("YhpcLsJcjlService")
public class YhpcLsJcjlService {

	@Resource
	private YhpcLsJcjlDao yhpcLsJcjlDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcLsJcjlDao.dataGrid(mapData);
		int getTotalCount=yhpcLsJcjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(YHPC_InterimCheckRecordEntity jcjl) {
		Timestamp t=DateUtils.getSysTimestamp();
		ShiroRealm.ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		jcjl.setS1(t);
		jcjl.setS2(t);
		jcjl.setS3(0);
		yhpcLsJcjlDao.save(jcjl);
        final long jcjlid = jcjl.getID();
        //发送msg消息
        Map<String,Object>  map = new HashMap<String,Object>(){{
            put(Message.MSGTARGET_H5,"aqjc/lsjcjl/view.do?jlid="+jcjlid);//h5页面
            put(Message.MSGTARGET_PC,"yhpc/lsjcjl/addReCheck/"+jcjlid);//后台页面
        }};
        MessageUtil.sendMsg(jcjl.getM5_id()+"", sessionuser.getId()+"", "您有日常检查待整改", Message.MessageType.DZG.getMsgType(),JSON.toJSONString(map),"您有日常检查待整改");
	}
	
	public long addInfoReturnID(YHPC_InterimCheckRecordEntity jcjl) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS1(t);
		jcjl.setS2(t);
		jcjl.setS3(0);
		yhpcLsJcjlDao.save(jcjl);
		return jcjl.getID();
	}
	
	//更新信息
	public void updateInfo(YHPC_InterimCheckRecordEntity jcjl) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS2(t);
		yhpcLsJcjlDao.save(jcjl);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		yhpcLsJcjlDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public YHPC_InterimCheckRecordEntity findById(Long id) {
		return yhpcLsJcjlDao.find(id);
	}
	
	//根据id获得现场检查记录word表数据
	public Map<String, Object> getJcjlWord(long id){
		//根据id获取检查记录
		Map<String, Object> mapret = yhpcLsJcjlDao.getWord(id);
		Map<String, Object> map=new HashMap<String, Object>();
		//检查企业
		if(mapret.get("qyname")!=null&&!mapret.get("qyname").toString().equals("")){
			String a = mapret.get("qyname").toString();
			map.put("qyname", a);
		}
		//检查起始时间
		if(mapret.get("m2")!=null&&!mapret.get("m2").toString().equals("")){
			String date=mapret.get("m2").toString();
			String[] d=date.split(" ");
			map.put("jcsj", d[0]);
		}else{
			map.put("jcsj", "无");
		}
		//整改期限
		if(mapret.get("m3")!=null&&!mapret.get("m3").toString().equals("")){
			String date=mapret.get("m3").toString();
			String[] d=date.split(" ");
			map.put("zgqx", d[0]);
		}else{
			map.put("zgqx", "");
		}
		//检查人员
		if(mapret.get("m4")!=null&&!mapret.get("m4").toString().equals("")){
			String a = mapret.get("m4").toString();
			map.put("jcry", a);
		}
		//整改负责人
		if(mapret.get("m5")!=null&&!mapret.get("m5").toString().equals("")){
			String a = mapret.get("m5").toString();
			map.put("fzr", a);
		}
		//复查时间
		if(mapret.get("m8")!=null&&!mapret.get("m8").toString().equals("")){
			String date=mapret.get("m8").toString();
			String[] d=date.split(" ");
			map.put("fcsj", d[0]);
		}else{
			map.put("fcsj", "");
		}
		//复查人员
		if(mapret.get("m9")!=null&&!mapret.get("m9").toString().equals("")){
			String a = mapret.get("m9").toString();
			map.put("fcry", a);
		}else{
			map.put("fcry", "");
		}

		//检查情况
		String jcyj = "";
		String fcqk = "";
		int i = 1;
		//根据检查记录id获取检查问题list
		List <YHPC_InterimCheckContentEntity> nrlist= yhpcLsJcjlDao.getNr(id);
		for (YHPC_InterimCheckContentEntity scc : nrlist) {
			jcyj = jcyj+i+"： "+scc.getM1()+"<w:p></w:p>";
			fcqk = fcqk+i+"： "+scc.getM3()+"<w:p></w:p>";
				i++;
		}
		map.put("jcyj", jcyj);
		map.put("fcqk", fcqk);
		return map;
	}
	
	}

