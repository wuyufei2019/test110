package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_RcjcLxglEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查---检查类型管理
 * @author 
 * @date 2018年06月25日
 */
@Repository("YhpcRcjcLxglEntityDao")
public class YhpcRcjcLxglEntityDao extends BaseDao<YHPC_RcjcLxglEntity, Long> {

	public List<Map<String, Object>> lxList(String type) {
		String sql = " select * from yhpc_rcjclxgl where type = " + type;
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}

}
