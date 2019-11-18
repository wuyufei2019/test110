package com.cczu.sys.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cczu.sys.system.entity.User_Define;
import com.cczu.util.dao.BaseDao;

/**
 * 用户自定义主页dao
 *
 */
@Repository("UserDefineDao")
public class UserDefineDao extends BaseDao<User_Define, Long> {
	
	/**
	 * 根据用户id查询用户所选菜单
	 * @param id
	 */
	public User_Define findByUserid(Integer userid) {
		String sql=" select * from user_define where userid="+userid;
		List<User_Define> list=findBySql(sql, null, User_Define.class);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}	
}
