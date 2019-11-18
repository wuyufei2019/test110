package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.YHPC_DailyHandleRecordEntity;
import com.cczu.util.dao.BaseDao;

/**
 * 隐患排查_企业日常检查隐患整改复查DAO
 *
 */
@Repository("YhpcJcyhzgDao")
public class YhpcJcyhzgDao extends BaseDao<YHPC_DailyHandleRecordEntity, Long> {

	/**
	 * 整改记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String sql ="SELECT top "+mapData.get("pageSize")+" * FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.*,b.NAME zgr FROM yhpc_dailyhandlerecord a LEFT JOIN t_user b ON b.ID = a.userid where dangerid ="+mapData.get("yhid")
				+ ")as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String, Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	/**
	 * 查询整改list的个数
	 * @param mapData
	 * @return
	 */
	public int TotalCount(Map<String, Object> mapData) {
		String sql="SELECT COUNT(*) FROM yhpc_dailyhandlerecord where dangerid ="+mapData.get("yhid");
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}
}
