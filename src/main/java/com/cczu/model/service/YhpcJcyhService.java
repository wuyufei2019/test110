package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cczu.model.dao.YhpcJcyhDao;
import com.cczu.model.dao.YhpcJcyhzgDao;
import com.cczu.model.dao.YhpcRcjcbkDao;
import com.cczu.model.entity.YHPC_DailyHandleRecordEntity;
import com.cczu.model.entity.YHPC_DailyHiddenInfoEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.system.entity.Message;
import com.cczu.sys.system.service.ShiroRealm.ShiroUser;
import com.cczu.sys.system.utils.MessageUtil;
import com.cczu.sys.system.utils.UserUtil;
import com.cczu.util.common.StringUtils;

/**
 *  隐患排查_检查隐患Service
 *
 */
@Transactional(readOnly=true)
@Service("YhpcJcyhService")
public class YhpcJcyhService {

	@Resource
	private YhpcJcyhDao yhpcJcyhDao;
	@Resource
	private YhpcRcjcbkDao yhpcRcjcbkDao;
	@Resource
	private YhpcJcyhzgDao yhpcJcyhzgDao;
	
	/**
	 * tab页分页显示(自查隐患)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcyhDao.dataGrid(mapData);
		int getTotalCount=yhpcJcyhDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * tab页分页显示(我要处理)
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> myDataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcyhDao.myDataGrid(mapData);
		int getTotalCount=yhpcJcyhDao.getMyTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 首页查询登录人是否有隐患需要整改
	 * @param mapData
	 * @return
	 */
	public List<Map<String,Object>> dataGrid2(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcyhDao.dataGrid2(mapData);
		return list;
	}
	
	//添加信息
	public void addInfo(YHPC_DailyHiddenInfoEntity jcyh) {
		//添加检查任务
		Timestamp t=DateUtils.getSysTimestamp();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		jcyh.setS1(t);
		jcyh.setS2(t);
		jcyh.setS3(0);
		yhpcJcyhDao.save(jcyh);
		final long qyid = sessionuser.getQyid();
		final long yhid = jcyh.getID();
		//发送msg消息
		Map<String,Object>  map = new HashMap<String,Object>(){{
			put(Message.MSGTARGET_H5,"aqjc/hidden/myentview.do?id="+qyid);//h5页面
			put(Message.MSGTARGET_PC,"yhpc/jcyh/update/"+yhid);//后台页面
		}};
		MessageUtil.sendMsg(jcyh.getM9()+"", sessionuser.getId()+"", "您有隐患待整改", Message.MessageType.DZG.getMsgType(),JSON.toJSONString(map),"您有隐患待整改");
	}

	//更新信息
	public void updateInfo(YHPC_DailyHiddenInfoEntity jcyh) {
		Timestamp t=DateUtils.getSysTimestamp();
		ShiroUser sessionuser= UserUtil.getCurrentShiroUser();
		jcyh.setS2(t);
		yhpcJcyhDao.save(jcyh);//添加检查隐患
		
		YHPC_DailyHandleRecordEntity zgjl=new YHPC_DailyHandleRecordEntity();
		zgjl.setUserid(Long.parseLong(sessionuser.getId()+""));//整改复查人id
		zgjl.setHandletime(jcyh.getM5());//整改复查时间
		zgjl.setHandleDesc(jcyh.getM8());//备注
		zgjl.setHandleMoney(jcyh.getM10());//费用
		zgjl.setHandleUploadPhoto(jcyh.getM6());//照片
		if(Integer.parseInt(jcyh.getM11())<3){
			zgjl.setHandleType(1);//整改
		}else if(Integer.parseInt(jcyh.getM11())>=3){
			zgjl.setHandleType(2);//复查
		}
		zgjl.setDangerid(jcyh.getID());//隐患id
		zgjl.setHandlestatus(Integer.parseInt(jcyh.getM11()));//检查结果
		yhpcJcyhzgDao.save(zgjl);//添加整改复查记录
		
		final long qyid = sessionuser.getQyid();
		final long yhid = jcyh.getID();
		if(Integer.parseInt(jcyh.getM11())<2){
			//发送整改消息
			Map<String,Object>  map = new HashMap<String,Object>(){{
				put(Message.MSGTARGET_H5,"aqjc/hidden/myentview.do?id="+qyid);//h5页面
				put(Message.MSGTARGET_PC,"yhpc/jcyh/update/"+yhid);//后台页面
			}};
			MessageUtil.sendMsg(jcyh.getM9()+"", sessionuser.getId()+"", "您有隐患需要整改", Message.MessageType.DJC.getMsgType(),JSON.toJSONString(map),"您有隐患需要整改");
		}

		if(Integer.parseInt(jcyh.getM11())==3){
			//发送整改消息
			Map<String,Object>  map = new HashMap<String,Object>(){{
				put(Message.MSGTARGET_H5,"aqjc/hidden/myentview.do?id="+qyid);//h5页面
				put(Message.MSGTARGET_PC,"yhpc/jcyh/update/"+yhid);//后台页面
			}};
			MessageUtil.sendMsg(jcyh.getM9()+"", sessionuser.getId()+"", "您有隐患需要再次整改", Message.MessageType.DJC.getMsgType(),JSON.toJSONString(map),"您有隐患需要再次整改");
		}
		
		if(jcyh.getM11().equals("2")&&jcyh.getM12()!=null){
			//发送复查消息
			Map<String,Object>  map = new HashMap<String,Object>(){{
				put(Message.MSGTARGET_H5,"aqjc/hidden/myentview.do?id="+qyid);//h5页面
				put(Message.MSGTARGET_PC,"yhpc/jcyh/review/"+yhid);//后台页面
			}};
			MessageUtil.sendMsg(jcyh.getM12()+"", sessionuser.getId()+"", "您有隐患整改需要复查", Message.MessageType.DJC.getMsgType(),JSON.toJSONString(map),"您有隐患整改需要复查");
		}
	}
	
	//检查人复查
	public void review(YHPC_DailyHiddenInfoEntity jcyh) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcyh.setS2(t);
		yhpcJcyhDao.save(jcyh);
	}
	
	//根据id查找详细信息
	public Map<String, Object> findById(Long id) {
		return yhpcJcyhDao.findById(id);
	}
	
	//根据任务id查找详细信息
	public List<YHPC_DailyHiddenInfoEntity> findByRwid(Long rwid) {
		return yhpcJcyhDao.findByRwid(rwid);
	}

	//根据id查找详细信息
	public YHPC_DailyHiddenInfoEntity find(Long id) {
		return yhpcJcyhDao.find(id);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		yhpcJcyhDao.deleteInfo(id);
	}
	
	/**
	 * 导入
	 * @param response
	 * @param mapData
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				try{
					YHPC_DailyHiddenInfoEntity yh = new YHPC_DailyHiddenInfoEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yh.setS1(t);
					yh.setS2(t);
					yh.setS3(0);
					yh.setQyid(Long.valueOf(map.get("qyid").toString()));
					yh.setCreatetime(Timestamp.valueOf(bis.get(0).toString()));//检查日期
					yh.setM13(bis.get(1).toString());//工厂部门
					yh.setM14(bis.get(2).toString());//发生区域
					yh.setM1(bis.get(3).toString());//问题描述
					yh.setM3(bis.get(4).toString());//隐患类别
					yh.setM15(bis.get(5).toString());//解决措施
					if(StringUtils.isNotBlank(bis.get(8).toString()))
						yh.setM4(Timestamp.valueOf(bis.get(8).toString()));//计划完成时间
					if(StringUtils.isNotBlank(bis.get(9).toString()))
						yh.setM5(Timestamp.valueOf(bis.get(9).toString()));//实际完成时间
					if(bis.get(10).toString().equals("OK")){
						yh.setM11("2");
					}else if(bis.get(10).toString().equals("NG")){
						yh.setM11("0");
					}else{
						yh.setM11("1");
					}//状态确认
					yh.setM10(bis.get(12).toString());//整改费用
					yh.setM7(bis.get(13).toString());//隐患等级
					yh.setM8(bis.get(14).toString());//备注
					yhpcJcyhDao.save(yh);
					
					//添加整改信息
					YHPC_DailyHandleRecordEntity zgjl=new YHPC_DailyHandleRecordEntity();
					zgjl.setHandletime(Timestamp.valueOf(bis.get(9).toString()));//整改时间
					zgjl.setHandleDesc("稽核人:"+bis.get(11).toString()+"。 "+bis.get(14).toString());//备注
					zgjl.setHandleType(2);//整改
					zgjl.setDangerid(yh.getID());//隐患id
					if(bis.get(10).toString().equals("OK")){
						zgjl.setHandlestatus(2);
					}else if(bis.get(10).toString().equals("NG")){
						zgjl.setHandlestatus(0);
					}
					yhpcJcyhzgDao.save(zgjl);//添加整改复查记录
					
					result++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",result);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}
	
	/**
	 * 历史整改复查记录
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> zgdataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcJcyhzgDao.dataGrid(mapData);
		int getTotalCount=yhpcJcyhzgDao.TotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
