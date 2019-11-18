package com.cczu.model.xfssgl.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.xfssgl.dao.XfssglXfssjcDao;

/**
 * 
 * @Description:消防设施检查Service
 * @author: wbth
 * @date: 2018年4月24日
 */
@Transactional(readOnly=true)
@Service("XfssglXfssjcService")
public class XfssglXfssjcService {
	
	@Resource
	private XfssglXfssjcDao xfssglXfssjcDao;
	
	/**
	 * 查询消防设施检查信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=xfssglXfssjcDao.dataGrid(mapData);
		for (Map<String,Object> map : list) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp time = (Timestamp) map.get("checktime");
			String checktime = format.format(time);
			map.put("checktime", checktime);
		}
		int getTotalCount=xfssglXfssjcDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//根据id、企业id、检查人员 id查询详细信息
	public Map<String, Object> findInfoByIds(Map<String, Object> mapData) {
		return xfssglXfssjcDao.findInfoByIds(mapData);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		xfssglXfssjcDao.deleteInfo(id);
	}
}
