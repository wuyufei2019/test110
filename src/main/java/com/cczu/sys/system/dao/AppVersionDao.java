package com.cczu.sys.system.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.AppVersionEntity;

/**
 * APP版本升级DAO
 * @author jason
 * @date 2017年5月31日
 */
@Repository
public class AppVersionDao extends HibernateDao<AppVersionEntity, Long>{

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		
		if(mapData.get("type")!=null&&mapData.get("type")!=""){
			content = content + "AND type ='"+mapData.get("type")+"' "; 
		}
		return content;
	}
	
	@SuppressWarnings("unchecked")
	public List<AppVersionEntity> findMapByContent(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT * FROM t_app_version WHERE version_no=( SELECT MAX(version_no) FROM t_app_version ) "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(AppVersionEntity.class);
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	public List<AppVersionEntity> findVersionNoByType(String type) {
		String sql=" SELECT * FROM t_app_version WHERE version_no=( SELECT MAX(version_no) FROM t_app_version where type=?0 ) AND type=?1 ";
		SQLQuery sqlQuery=createSQLQuery(sql,type,type);
		sqlQuery.addEntity(AppVersionEntity.class);
		return sqlQuery.list();
	}
}

