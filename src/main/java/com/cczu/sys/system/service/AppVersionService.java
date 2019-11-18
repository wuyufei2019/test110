package com.cczu.sys.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.sys.comm.persistence.HibernateDao;
import com.cczu.sys.comm.service.BaseService;
import com.cczu.sys.system.dao.AppVersionDao;
import com.cczu.sys.system.entity.AppVersionEntity;

/**
 * APP版本更新service
 * @author jason
 * @date 2017年5月31日
 */
@Service
@Transactional(readOnly=true)
public class AppVersionService extends BaseService<AppVersionEntity, Long> {
	
	@Autowired
	private AppVersionDao appVersionDao;

	@Override
	public HibernateDao<AppVersionEntity, Long> getEntityDao() {
		return appVersionDao;
	}

	public AppVersionEntity findVersionNoBytype(String type) {
		List<AppVersionEntity> list= appVersionDao.findVersionNoByType(type);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}

	public void saveApp(AppVersionEntity app) {
		AppVersionEntity oldapp = findVersionNoBytype(app.getType());
		if(oldapp!=null&&oldapp.getVersionNo()>0){
			app.setVersionNo(oldapp.getVersionNo()+1);
		}else{
			app.setVersionNo(101);
		}
		appVersionDao.save(app);
	}
}

