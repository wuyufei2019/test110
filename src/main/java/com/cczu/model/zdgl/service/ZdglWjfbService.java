package com.cczu.model.zdgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.zdgl.dao.ZdglWjfbDao;
import com.cczu.model.zdgl.entity.ZDGL_WJFBEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.utils.UserUtil;

/**
 *  制度管理-安全文件发布Service
 *
 */
@Service("ZdglWjfbService")
public class ZdglWjfbService {

	@Resource
	private ZdglWjfbDao zdglWjfbDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglWjfbDao.dataGrid(mapData);
		int getTotalCount=zdglWjfbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		zdglWjfbDao.deleteInfo(id);
	}
	
	//添加信息
	public void addInfo(ZDGL_WJFBEntity wjfb) {
		Timestamp t=DateUtils.getSysTimestamp();
		wjfb.setS1(t);
		wjfb.setS2(t);
		wjfb.setS3(0);
		wjfb.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		wjfb.setID1(UserUtil.getCurrentUser().getId2());
		zdglWjfbDao.save(wjfb);
	}
	
	public ZDGL_WJFBEntity find(Long id) {
		return zdglWjfbDao.find(id);
	}

	//更新信息
	public void updateInfo(ZDGL_WJFBEntity wjfb) {
		Timestamp t=DateUtils.getSysTimestamp();
		wjfb.setS2(t);
		zdglWjfbDao.save(wjfb);
	}
	
	//根据id查找详细信息
	public Map<String,Object> findById(Long id) {
		return zdglWjfbDao.findById(id);
	}

	/**
	 * 通过部门id查询部门人员
	 */
	public List<User> findBybmid(long bmid){
		return zdglWjfbDao.findBybmid(bmid);
	}
}
