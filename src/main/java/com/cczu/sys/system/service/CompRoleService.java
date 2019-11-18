package com.cczu.sys.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.CompRoleDao;
import com.cczu.sys.system.entity.CompRole;
import com.cczu.sys.system.entity.User;

/**
 * @description 企业内部角色service
 * @author jason
 * @date 2017年12月26日
 */
@Service
@Transactional(readOnly = true)
public class CompRoleService extends BaseService<CompRole, Integer> {

	@Autowired
	private CompRoleDao compRoleDao;

	@Override
	public HibernateDao<CompRole, Integer> getEntityDao() {
		return compRoleDao;
	}
	
	/**
	 * 查询用户的角色
	 * @param userId 用户id
	 * @return 结果集合
	 */
	public List<CompRole> findCompRoleById(Integer userId){
		return compRoleDao.findCompRoleById(userId);
	}
	
	/**
	 * 查询用户的角色
	 * @param CompRoleCode 角色编码
	 * @return 结果集合
	 */
	public CompRole findCompRoleByCompRoleCode(String CompRoleCode){
		return compRoleDao.findCompRoleByRoleCode(CompRoleCode);
	}
	
	/**
	 * 根据角色查询用户
	 * @param CompRoleCode
	 * @return 结果集合
	 */
	public List<User> findUserByCompRoleCode(String CompRoleCode, Long qyid){
		return compRoleDao.findUserByCompRoleCode(CompRoleCode, qyid);
	}
	
	/**
	 * 根据企业id查询企业内部角色集合
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> getCompRole(Long qyid) {
		List<Map<String, Object>> role = new ArrayList<>();
		List<Map<String,Object>> list = compRoleDao.getCompRole(qyid);
		for (Map<String, Object> map : list) {
			Map<String, Object> mapRole = new HashMap<String, Object>();
			mapRole.put("id", map.get("id"));
			mapRole.put("text", map.get("name"));
			role.add(mapRole);
		}
		return role;
	}
	
}
