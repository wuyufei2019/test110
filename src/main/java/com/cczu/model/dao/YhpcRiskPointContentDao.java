package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_RiskPoint_Content;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查---风险点检查内容中间表
 * @author zpc
 *
 */
@Repository("YhpcRiskPointContentDao")
public class YhpcRiskPointContentDao extends BaseDao<YHPC_RiskPoint_Content, Long>{
	public void deleteInfoByID1(long id1){
		String sql="delete from yhpc_riskpoint_content where id1=:p1";
		Parameter  parameter=new Parameter(id1);
		updateBySql(sql,parameter);
	}

}
