package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckPlan_User;
import com.cczu.util.dao.BaseDao;

/**
 * 巡检任务人员中间表
 * @author zpc
 *
 */
@Repository("YhpcCheckPlanUserDao")
public class YhpcCheckPlanUserDao extends BaseDao<YHPC_CheckPlan_User, Long>{

}
