package com.cczu.model.jtjcsj.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.TMESK_ChemicalsdirectoryEntity;
import com.cczu.model.jtjcsj.dao.JtjcsjQyhxpckpzxxDao;
import com.cczu.model.jtjcsj.entity.Jtjcsj_QyhxpckpzxxEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;

/**
 * 静态基础数据-企业化学品仓库配置信息Service
 * @author Administrator
 *
 */
@Transactional(readOnly=true)
@Service("JtjcsjQyhxpckpzxxService")
public class JtjcsjQyhxpckpzxxService {
	
	@Resource
	private JtjcsjQyhxpckpzxxDao jtjcsjQyhxpckpzxxDao;
	
	
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=jtjcsjQyhxpckpzxxDao.dataGrid(mapData);
		int getTotalCount=jtjcsjQyhxpckpzxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}


	/**
	 * 添加信息
	 * @param entity
	 */
	public void addInfo(Jtjcsj_QyhxpckpzxxEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);//创建时间
		entity.setS2(t);//最后修改时间
		entity.setS3(0);//删除标识
		jtjcsjQyhxpckpzxxDao.save(entity);
	}

	/**
	 * 根据id查询详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findInfoById(Long id) {
		return jtjcsjQyhxpckpzxxDao.findInfoById(id);
	}

	/**
	 * 修改信息
	 * @param entity
	 */
	public void updateInfo(Jtjcsj_QyhxpckpzxxEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);//最后修改时间
		entity.setS3(0);//删除标识
		jtjcsjQyhxpckpzxxDao.save(entity);
	}

	/**
	 * 根据id删除信息
	 * @param id
	 */
	public void deleteInfo(Long id) {
		jtjcsjQyhxpckpzxxDao.deleteInfo(id);
		
	}

	/**
	 * Cas号json
	 * @return
	 */
	public List<Map<String, Object>> findCasJson() {
		return jtjcsjQyhxpckpzxxDao.findCasJson();
	}

	/**
	 * 根据cas号查询中文名称和英文名称
	 * @param m5
	 * @return
	 */
	public String wlnameJson(String m5) {
		TMESK_ChemicalsdirectoryEntity tdanger=jtjcsjQyhxpckpzxxDao.wlnameJson(m5);
		Map<String, Object> map = new HashMap<String, Object>();
		if(tdanger != null) {
			map.put("zwm", tdanger.getM2());
			map.put("ywm", tdanger.getM4());
		}
		return JsonMapper.getInstance().toJson(map);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
