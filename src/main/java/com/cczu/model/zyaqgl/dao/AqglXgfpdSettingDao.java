package com.cczu.model.zyaqgl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_Setting;
import com.cczu.util.common.Parameter;
import com.cczu.util.dao.BaseDao;

/**
 * 安全管理-相关方评定内容设置DAO
 * 
 * @author YZH
 * 
 */
@Repository("AqglXgfpdSettingDao")
public class AqglXgfpdSettingDao extends
		BaseDao<AQGL_RelevantEvaluation_Setting, Long> {

	public void deleteInfo(long id) {
		String sql = "update AQGL_RelevantEvaluation_Setting set s3=1 where id=:p1";
		updateBySql(sql, new Parameter(id));
	}

	public List<AQGL_RelevantEvaluation_Setting> findListByMap(Map<String, Object> map) {
		String sql = "SELECT * FROM AQGL_RelevantEvaluation_Setting where s3=0 and id1=:p1";
		List<AQGL_RelevantEvaluation_Setting> list = findBySql(sql,
				new Parameter(map.get("qyid")),
				AQGL_RelevantEvaluation_Setting.class);
		return list;
	}

}
