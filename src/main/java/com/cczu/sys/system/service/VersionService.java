package com.cczu.sys.system.service;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.VersionDao;
import com.cczu.sys.system.entity.VersionEntity;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目版本更新service
 * @author jason
 * @date 2019年4月1日
 */
@Service
@Transactional(readOnly=true)
public class VersionService extends BaseService<VersionEntity, Long> {
	
	@Autowired
	private VersionDao versionDao;

	@Override
	public HibernateDao<VersionEntity, Long> getEntityDao() {
		return versionDao;
	}

	public void save(VersionEntity app) {
		versionDao.save(app);
	}

    public String getMaxVersion() {
	    String ver="V1.0";
        String sql=" SELECT top(1) * FROM t_version order by id desc ";
        SQLQuery sqlQuery=versionDao.createSQLQuery(sql);
        sqlQuery.addEntity(VersionEntity.class);
        List<VersionEntity> list=sqlQuery.list();
        if(list.size()>0)
            ver=list.get(0).getName();
        return ver;
    }

    public List<VersionEntity> findAll() {
	    return  versionDao.findAll();
    }
}

