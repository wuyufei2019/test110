package com.cczu.model.jtjcsj.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.jtjcsj.dao.JtjcsjQysczzWhppzxxDao;
import com.cczu.model.jtjcsj.entity.Jtjcsj_QysczzWhppzxxEntity;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 静态基础数据-企业生产装置危化品配置信息Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("JtjcsjQysczzWhppzxxService")
public class JtjcsjQysczzWhppzxxService {
	
	
	@Resource
	private JtjcsjQysczzWhppzxxDao jtjcsjQysczzWhppzxxDao;
	
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=jtjcsjQysczzWhppzxxDao.dataGrid(mapData);
		int getTotalCount=jtjcsjQysczzWhppzxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 添加信息
	 * @param entity
	 */
	public void addInfo(Jtjcsj_QysczzWhppzxxEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);//创建时间
		entity.setS2(t);//最后修改时间
		entity.setS3(0);//删除标识
		jtjcsjQysczzWhppzxxDao.save(entity);
	}
	
	
	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findInfoById(Long id) {
		return jtjcsjQysczzWhppzxxDao.findInfoById(id);
	}
	
	/**
	 * 修改信息
	 * @param entity
	 */
	public void updateInfo(Jtjcsj_QysczzWhppzxxEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);//最后修改时间
		entity.setS3(0);//删除标识
		jtjcsjQysczzWhppzxxDao.save(entity);
	}
	
	/**
	 * 根据id删除信息
	 * @param parseLong
	 */
	public void deleteInfo(Long id) {
		jtjcsjQysczzWhppzxxDao.deleteInfo(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
