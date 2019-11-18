package com.cczu.model.lydw.service;


import com.cczu.model.lydw.dao.LYDW_DzwlDao;
import com.cczu.model.lydw.dao.LYDW_QyqxDao;
import com.cczu.model.lydw.entity.LYDW_QYQX;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位-区域权限Service
 * @author jason
 * @date 2017年8月3日
 */
@Transactional(readOnly=true)
@Service("LYDW_QyqxService")
public class LYDW_QyqxService {

	@Resource
	private LYDW_QyqxDao lydw_qyqxDao;
	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=lydw_qyqxDao.dataGrid(mapData);
		int getTotalCount=lydw_qyqxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 区域权限总览
	 * @return
	 */
	public String dzwlData() {
		List<Map<String, Object>> list = lydw_qyqxDao.dzwlData(UserUtil.getCurrentShiroUser().getQyid());
		return JsonMapper.getInstance().toJson(list);
	}

	/**
	 * 围栏json
	 */
	public String wljson() {
		List<Map<String, Object>> list = lydw_qyqxDao.wljson(UserUtil.getCurrentShiroUser().getQyid());
		List<Map<String, Object>> arrylist = new ArrayList<Map<String, Object>>();
		for (Map wl:list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", wl.get("name"));
			map.put("text", wl.get("name"));
			arrylist.add(map);
		}
		return JsonMapper.getInstance().toJson(arrylist);
	}

	/**
	 * 添加
	 * @param entity
	 */
	public void addInfo(LYDW_QYQX entity) {
		lydw_qyqxDao.save(entity);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public LYDW_QYQX findById(Long id) {
		return lydw_qyqxDao.find(id);
	}
	
	/**
	 * 修改
	 * @param entity
	 */
	public void updateInfo(LYDW_QYQX entity) {
		lydw_qyqxDao.save(entity);
	}
	
	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		lydw_qyqxDao.delete(id);
	}

}
