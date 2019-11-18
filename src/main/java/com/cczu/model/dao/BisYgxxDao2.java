package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_Employee2Entity;
import com.cczu.util.dao.BaseDao;

@Repository("BisYgxxDao2")
public class BisYgxxDao2 extends BaseDao<BIS_Employee2Entity, Long> {
	
	/**
	 * 删除表中所有数据
	 * @param id
	 */
	public void deleteInfo() {
		String sql=" delete from bis_employee_second where 1=1";
		updateBySql(sql);
	}
	
}
