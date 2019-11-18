package com.cczu.model.jtjcsj.service;

import com.cczu.model.jtjcsj.dao.JtjcsjSjcjsbjcsDao;
import com.cczu.model.jtjcsj.entity.Jtjcsj_SjcjsbjcsjEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 静态基础数据-数据采集设备基础数据Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("JtjcsjSjcjsbjcsService")
public class JtjcsjSjcjsbjcsService {
	
	@Resource
	private JtjcsjSjcjsbjcsDao jtjcsjSjcjsbjcsDao;

	
	/**
	 * 分页查询
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=jtjcsjSjcjsbjcsDao.dataGrid(mapData);
		int getTotalCount=jtjcsjSjcjsbjcsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 添加信息
	 * @param entity
	 */
	public void addInfo(Jtjcsj_SjcjsbjcsjEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setCreatedate(t);//创建时间
		entity.setUpdatedate(t);//最后修改时间
		entity.setCreateby(UserUtil.getCurrentUser().getName());//创建人
		entity.setUpdateby(UserUtil.getCurrentUser().getName());//最后修改人
		entity.setStatus("0");//删除标识
		jtjcsjSjcjsbjcsDao.save(entity);
	}

	/**
	 * 根据id查询信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findInfoById(Long id) {
		return jtjcsjSjcjsbjcsDao.findInfoById(id);
	}

	/**
	 * 修改信息
	 * @param entity
	 */
	public void updateInfo(Jtjcsj_SjcjsbjcsjEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setUpdatedate(t);//最后修改时间
		entity.setUpdateby(UserUtil.getCurrentUser().getName());//最后修改人
		jtjcsjSjcjsbjcsDao.save(entity);
	}

	/**
	 * 根据id删除信息
	 * @param parseLong
	 */
	public void deleteInfo(Long id) {
		jtjcsjSjcjsbjcsDao.deleteInfo(id);
		
	}
}
