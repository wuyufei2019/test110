package com.cczu.model.jtjcsj.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.jtjcsj.dao.JtjcsjQyscdyqyxxDao;
import com.cczu.model.jtjcsj.entity.Jtjcsj_QyscdyqyxxEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 静态基础数据-企业生产单元区域信息Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("JtjcsjQyscdyqyxxService")
public class JtjcsjQyscdyqyxxService {
	
	@Resource
	private JtjcsjQyscdyqyxxDao jtjcsjQyscdyqyxxDao;
	
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=jtjcsjQyscdyqyxxDao.dataGrid(mapData);
		int getTotalCount=jtjcsjQyscdyqyxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	
	/**
	 * 添加信息
	 * @param entity
	 */
	public void addInfo(Jtjcsj_QyscdyqyxxEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setCreatedate(t);//创建时间
		entity.setUpdatedate(t);//最后修改时间
		entity.setCreateby(UserUtil.getCurrentUser().getName());//创建人
		entity.setUpdateby(UserUtil.getCurrentUser().getName());//最后修改人
		entity.setStatus("0");//删除标识
		jtjcsjQyscdyqyxxDao.save(entity);
	}

	
	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findInfoById(Long id) {
		return jtjcsjQyscdyqyxxDao.findInfoById(id);
	}
	
	
	/**
	 * 根据id查询详细信息(查看页面)
	 * @param id
	 * @return
	 */
	public Map<String, Object> findInfoById2(Long id) {
		return jtjcsjQyscdyqyxxDao.findInfoById2(id);
	}
	

	
	/**
	 * 修改信息
	 * @param entity
	 */
	public void updateInfo(Jtjcsj_QyscdyqyxxEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setUpdatedate(t);//最后修改时间
		entity.setUpdateby(UserUtil.getCurrentUser().getName());//最后修改人
		jtjcsjQyscdyqyxxDao.save(entity);
	}
	
	/**
	 * 根据id删除信息
	 * @param id
	 */
	public void deleteInfo(Long id) {
		jtjcsjQyscdyqyxxDao.deleteInfo(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
