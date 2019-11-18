package com.cczu.sys.system.dao;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.system.entity.VersionEntity;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 项目版本升级DAO
 * @author jason
 * @date 2019年4月1日
 */
@Repository
public class VersionDao extends HibernateDao<VersionEntity, Long>{

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
	public List<VersionEntity> findMapByContent(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT * FROM t_version WHERE 0=0 "+content;
		SQLQuery sqlQuery=createSQLQuery(sql);
		sqlQuery.addEntity(VersionEntity.class);
		return sqlQuery.list();
	}


    public List<VersionEntity> findAll() {
        String sql=" SELECT * FROM t_version order by updatetime desc ";
        SQLQuery sqlQuery=createSQLQuery(sql);
        sqlQuery.addEntity(VersionEntity.class);
        return sqlQuery.list();
    }

}

