package com.cczu.model.zyaqgl.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglKpbzDao;
import com.cczu.sys.comm.mapper.JsonMapper;

/**
 *  安全管理-相关单位考评标准Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglKpbzService")
public class AqglKpbzService {

	@Resource
	private AqglKpbzDao kpbzDao;

	/**
	 * 查询考评标准类别
	 * @return
	 */
	public String findType() {
		return JsonMapper.getInstance().toJson(kpbzDao.findType());
	}
	
	/**
	 * 查询考评类别查询考评内容
	 * @param type
	 * @return
	 */
	public String findContent(String type) {
		return JsonMapper.getInstance().toJson(kpbzDao.findContent(type));
	}
	
	/**
	 * 根据类别和内容查询详细信息
	 * @param mapData
	 * @return
	 */
	public String findInfo(Map<String, Object> mapData) {
		return JsonMapper.getInstance().toJson(kpbzDao.findInfo(mapData));
	}
}
