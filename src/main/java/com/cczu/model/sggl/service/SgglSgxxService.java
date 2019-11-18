package com.cczu.model.sggl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cczu.model.sggl.dao.SgglSgxxDao;
import com.cczu.model.sggl.dao.SgglSwryDao;
import com.cczu.model.sggl.entity.SGGL_AccidentManageEntity;
import com.cczu.model.sggl.entity.SGGL_CasualtyEntity;

@Transactional(readOnly=true)
@Service("SgglSgxxService")
public class SgglSgxxService {
	
	@Resource
	private SgglSgxxDao sgglSgxxDao;
	@Resource
	private SgglSwryDao sgglSwryDao;
	
	public List<SGGL_AccidentManageEntity> findAll() {
		return sgglSgxxDao.findAllInfo();
	}

	
	public String addInfo(SGGL_AccidentManageEntity erm,String swryids,String shcdids) {
		String datasuccess = "success";
		
		sgglSgxxDao.save(erm);
		long id=erm.getID();
		//事故信息添加成功后，绑定伤亡人员
		if(id>0&&(!swryids.equals(""))){
			String[] arrids = swryids.split(",");
			String[] arrids2 = shcdids.split(",");
			try{
				for (int i = 0; i < arrids.length; i++) {
					bulidCasualty(id,Long.parseLong(arrids[i]),Long.parseLong(arrids2[i]));
				}
			}catch(Exception e){
				datasuccess="bderror";
			}
		}
		// 返回结果
		return datasuccess;
	}

	//添加伤亡人员
	public void bulidCasualty(long id1, long id2, long m1) {
		SGGL_CasualtyEntity swry=new SGGL_CasualtyEntity();
		swry.setID1(id1);
		swry.setID2(id2);
		swry.setM1(m1);
		sgglSwryDao.save(swry);
	}
	
	//修改事故编号
	public void updateInfo(SGGL_AccidentManageEntity erm) {
		sgglSgxxDao.save(erm);
	}
	
	//获取本年度最新的事故编号
	public String getMaxNum(Long qyid) {
		return sgglSgxxDao.getMaxNum(qyid);
	}
	
	//根据事故信息
	public String updateInfoById1(SGGL_AccidentManageEntity erm,String swryids,String shcdids) {
		String datasuccess = "success";
		
		sgglSgxxDao.save(erm);
		long id=erm.getID();
		//删除已绑定伤亡人员
		sgglSwryDao.deleteInfoById1(id);
		//事故信息添加成功后，绑定伤亡人员
		if(id>0&&(!swryids.equals(""))){
			String[] arrids = swryids.split(",");
			String[] arrids2 = shcdids.split(",");
			for (int i = 0; i < arrids.length; i++) {
			  bulidCasualty(id,Long.parseLong(arrids[i]),Long.parseLong(arrids2[i]));
			}
		}
		// 返回结果
		return datasuccess;
	}
	
	
	public String content(Map<String, Object> mapData) {
		return sgglSgxxDao.content(mapData);
	}

	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=sgglSgxxDao.dataGrid(mapData);
		int getTotalCount=sgglSgxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		sgglSgxxDao.deleteInfo(id);
	}

	
	public SGGL_AccidentManageEntity findById(Long id) {
		return sgglSgxxDao.findById(id);
	}

	public Map<String, Object> swrydataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=sgglSgxxDao.swrydataGrid(mapData);
		int getTotalCount=sgglSgxxDao.getswryTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void deleteSwry(long id) {
		sgglSgxxDao.deleteSwry(id);
	}
	
	//事故统计获取年份下拉
	public Object[] getMaxYearAndMinYear() {
		return sgglSgxxDao.getMaxYearAndMinYear();
	}

	//按月份统计事故数量
	public Map<String, Object> getCountEveryMonth(Map<String, Object> mapData) {
		// 返回map
		Map<String, Object> remap = new HashMap<String, Object>();

		String[] month = { "01月", "02月", "03月", "04月", "05月", "06月", "07月","08月", "09月", "10月", "11月", "12月" };
		List<Map<String, Object>> list = sgglSgxxDao.getCountEveryMonth(mapData);
		List<Object> listcount = new ArrayList<Object>();
		List<Object> listmonth = new ArrayList<Object>();
		int j = 0;
		for (int i = 0; i < 12; i++) {
			if (j < list.size()) {
				Map<String, Object> m = list.get(j);
				if (m.get("month").toString().equals(i + 1 + "")) {
					listcount.add(m.get("count"));
					j++;
				}else{
					listcount.add("0");
				}
			} else {
				listcount.add("0");
			}
			listmonth.add(month[i]);
		}
		remap.put("xdate", JSON.toJSON(listmonth).toString());
		remap.put("ydate", JSON.toJSON(listcount).toString());
		return remap;
	}
	
	//按类型统计事故数量
	public Map<String, Object> getCountEveryType(Map<String, Object> mapData) {
		String[] sglx = {"死亡Fatal","损工事故","医疗处理事故","急救事故","幸免事故"};
		Map<String, Object> remap = new HashMap<String, Object>();
		List<Map<String, Object>> list = sgglSgxxDao.getCountEveryType(mapData);
		List<Object> listcount = new ArrayList<Object>();
		List<Object> listtype = new ArrayList<Object>();
		for(int i = 0;i < sglx.length;i++){
			int z = 0;
			for (Map<String, Object> map : list) {
				if(map.get("m3").toString().equals(sglx[i])){
					listtype.add(sglx[i]);
					listcount.add(map.get("sl"));
					z = 1;
					break;
				}
			}
			if(z==0){
				listtype.add(sglx[i]);
				listcount.add(0);
			}
		}
		remap.put("xdate", JSON.toJSON(listtype).toString());
		remap.put("ydate", JSON.toJSON(listcount).toString());
		return remap;
	}
	
	//按类型统计事故数量
	public Map<String, Object> getCountEveryBm(Map<String, Object> mapData) {
		Map<String, Object> remap = new HashMap<String, Object>();
		List<Map<String, Object>> list = sgglSgxxDao.getCountEveryBm(mapData);
		List<Object> listcount = new ArrayList<Object>();
		List<Object> listbm = new ArrayList<Object>();
		for (Map<String, Object> map : list) {
			listbm.add(map.get("bmname"));
			listcount.add(map.get("sl")); 
		}
		remap.put("xdate", JSON.toJSON(listbm).toString());
		remap.put("ydate", JSON.toJSON(listcount).toString());
		return remap;
	}

	/*
批量上报
 */
	public void plsb(long id) {
		// TODO Auto-generated method stub
		sgglSgxxDao.plsb(id);
	}

	/*
	批量取消
	 */
	public void plqx(long id) {
		// TODO Auto-generated method stub
		sgglSgxxDao.plqx(id);
	}
	/*
	安全生产事故报告统计分析
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {

		List<Map<String, Object>> list=sgglSgxxDao.dataGrid2(mapData);
		int getTotalCount=sgglSgxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/*
	应急预案编制与演练统计分析
	 */
	public Map<String, Object> dataGrid3(Map<String, Object> mapData) {

		List<Map<String, Object>> list=sgglSgxxDao.dataGrid3(mapData);
		int getTotalCount=sgglSgxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/*
	安全制度规程及预案
 	*/
	public Map<String, Object> dataGrid4(Map<String, Object> mapData) {

		List<Map<String, Object>> list=sgglSgxxDao.dataGrid4(mapData);
		int getTotalCount=sgglSgxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/*
	日常不安全行为监察记录
	 */
	public Map<String, Object> dataGrid5(Map<String, Object> mapData) {

		List<Map<String, Object>> list=sgglSgxxDao.dataGrid5(mapData);
		int getTotalCount=sgglSgxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/*
	组织应急演练与记录
	 */
	public Map<String, Object> dataGrid6(Map<String, Object> mapData) {

		List<Map<String, Object>> list=sgglSgxxDao.dataGrid6(mapData);
		int getTotalCount=sgglSgxxDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
