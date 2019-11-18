package com.cczu.model.jtjcsj.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.jtjcsj.dao.JtjcsjQyhxpckxxDao;
import com.cczu.model.jtjcsj.entity.Jtjcsj_QyhxpckxxEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 静态基础数据-企业化学品仓库信息Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("JtjcsjQyhxpckxxService")
public class JtjcsjQyhxpckxxService {
	
	@Resource
	private JtjcsjQyhxpckxxDao jtjcsjQyhxpckxxDao;
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=jtjcsjQyhxpckxxDao.dataGrid(mapData);
		int getTotalCount=jtjcsjQyhxpckxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}	
	
	
	/**
	 * 添加信息
	 * @param entity
	 */
	public void addInfo(Jtjcsj_QyhxpckxxEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setCreatetime(t);//创建时间
		entity.setUpdatetime(t);//最后修改时间
		entity.setCreator(UserUtil.getCurrentUser().getName());//创建人
		entity.setUpdator(UserUtil.getCurrentUser().getName());//最后修改人
		entity.setStatus("0");//删除标识
		jtjcsjQyhxpckxxDao.save(entity);
	}

	
	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findInfoById(Long id) {
		return jtjcsjQyhxpckxxDao.findInfoById(id);
	}

	
	/**
	 * 修改信息
	 * @param entity
	 */
	public void updateInfo(Jtjcsj_QyhxpckxxEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setUpdatetime(t);//最后修改时间
		entity.setUpdator(UserUtil.getCurrentUser().getName());//最后修改人
		jtjcsjQyhxpckxxDao.save(entity);
	}

	
	/**
	 * 根据id删除信息
	 * @param parseLong
	 */
	public void deleteInfo(Long id) {
		jtjcsjQyhxpckxxDao.deleteInfo(id);
	}

	/**
	 * 企业化学品仓库json
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> findHxpckJson(Long qyid) {
		return jtjcsjQyhxpckxxDao.findHxpckJson(qyid);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
