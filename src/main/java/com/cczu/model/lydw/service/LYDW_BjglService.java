package com.cczu.model.lydw.service;


import com.cczu.model.lydw.dao.LYDW_BjglDao;
import com.cczu.model.lydw.entity.LYDW_BJGL;
import com.cczu.sys.comm.mapper.JsonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 蓝牙定位-报警管理Service
 * @author jason
 * @date 2019年月9日
 */
@Transactional(readOnly=true)
@Service("LYDW_BjglService")
public class LYDW_BjglService {

	@Resource
	private LYDW_BjglDao lydw_bjglDao;
	
	/**
	 * 报警管理list
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=lydw_bjglDao.dataGrid(mapData);
		int getTotalCount=lydw_bjglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 添加
	 */
	public void updateInfo(LYDW_BJGL entity) {
		lydw_bjglDao.save(entity);
	}

	/**
	 * 一键求救信息删除（假删）
	 */
	public void deleteInfo(Long id) {
		lydw_bjglDao.deleteInfo(id);
	}

	/**
	 * 根据id查询
	 */
	public LYDW_BJGL findById(Long id) {
		return lydw_bjglDao.find(id);
	}

	/**
	 * 报警类别json
	 * @return
	 */
	public String bjlb(Long qyid) {
		return JsonMapper.getInstance().toJson(lydw_bjglDao.bjlb(qyid));
	}
}
