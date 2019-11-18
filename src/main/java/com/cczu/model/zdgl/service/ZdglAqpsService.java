package com.cczu.model.zdgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.zdgl.dao.ZdglAqpsDao;
import com.cczu.model.zdgl.entity.ZDGL_AQPSEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 *  制度管理-安全评审Service
 *
 */
@Service("ZdglAqpsService")
public class ZdglAqpsService {

	@Resource
	private ZdglAqpsDao zdglAqpsDao;
	
	/**
	 * 制度评审list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglAqpsDao.dataGrid(mapData);
		int getTotalCount=zdglAqpsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//删除安全评审信息
	public void deleteInfo(long id) {
		zdglAqpsDao.deleteInfo(id);
	}
	
	public String getzdIdjson(long qyid) {
		List<Map<String, Object>> list = zdglAqpsDao.findzdIdTextList(qyid);
		return JsonMapper.getInstance().toJson(list);
	}
	
	//添加制度评审信息
	public void addInfo(ZDGL_AQPSEntity aqps) {
		Timestamp t=DateUtils.getSysTimestamp();
		aqps.setM19(t);
		aqps.setM20(UserUtil.getCurrentUser().getId().toString());
		aqps.setID1(UserUtil.getCurrentUser().getId2());
		zdglAqpsDao.save(aqps);
	}
	
	public ZDGL_AQPSEntity find(Long id) {
		return zdglAqpsDao.find(id);
	}
	
	//更新制度评审信息
	public void updateInfo(ZDGL_AQPSEntity aqps) {
		zdglAqpsDao.save(aqps);
	}
	
	//根据id查找评审详细信息
	public Map<String,Object> findById(Long id) {
		return zdglAqpsDao.findById(id);
	}
	
	/**
	 * 操作规程list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglAqpsDao.dataGrid2(mapData);
		int getTotalCount=zdglAqpsDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public String getgcIdjson(long qyid) {
	List<Map<String, Object>> list = zdglAqpsDao.findgcIdTextList(qyid);
		return JsonMapper.getInstance().toJson(list);
	}
	
	//根据id获得word表数据
	public Map<String, Object> getWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapret = zdglAqpsDao.findById(id);
		map.put("m2", mapret.get("m2")==null||mapret.get("m2").toString().equals("")?"":mapret.get("m2").toString());
		map.put("m3", mapret.get("m3")==null||mapret.get("m3").toString().equals("")?"":mapret.get("m3").toString().substring(0, 10));
		map.put("m4", mapret.get("m4")==null||mapret.get("m4").toString().equals("")?"":mapret.get("m4").toString());
		map.put("m5", mapret.get("m5")==null||mapret.get("m5").toString().equals("")?"":mapret.get("m5").toString());
		map.put("m6", mapret.get("m6")==null||mapret.get("m6").toString().equals("")?"":mapret.get("m6").toString());
		map.put("m7", mapret.get("m7")==null||mapret.get("m7").toString().equals("")?"":mapret.get("m7").toString());
		map.put("m8", mapret.get("m8")==null||mapret.get("m8").toString().equals("")?"":mapret.get("m8").toString());
		map.put("m9", mapret.get("m9")==null||mapret.get("m9").toString().equals("")?"":mapret.get("m9").toString());
		map.put("m10", mapret.get("m10")==null||mapret.get("m10").toString().equals("")?"":mapret.get("m10").toString());
		map.put("spyj", mapret.get("spyj")==null||mapret.get("spyj").toString().equals("")?"":mapret.get("spyj").toString());
		map.put("spr", mapret.get("spr")==null||mapret.get("spr").toString().equals("")?"":mapret.get("spr").toString());
		map.put("pzyj", mapret.get("pzyj")==null||mapret.get("pzyj").toString().equals("")?"":mapret.get("pzyj").toString());
		map.put("pzr", mapret.get("pzr")==null||mapret.get("pzr").toString().equals("")?"":mapret.get("pzr").toString());
		map.put("m14", mapret.get("m14")==null||mapret.get("m14").toString().equals("")?"":mapret.get("m14").toString().substring(0, 10));
		map.put("m17", mapret.get("m17")==null||mapret.get("m17").toString().equals("")?"":mapret.get("m17").toString().substring(0, 10));
		return map;
	}
}
