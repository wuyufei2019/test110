package com.cczu.model.dao;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_CheckPlan_Time;
import com.cczu.util.dao.BaseDao;

/**
 * 巡检任务任务时间中间表dao层
 * @author zpc
 *
 */
@Repository("YhpcCheckPlanTimeDao")
public class YhpcCheckPlanTimeDao extends BaseDao<YHPC_CheckPlan_Time, Long>{

}
