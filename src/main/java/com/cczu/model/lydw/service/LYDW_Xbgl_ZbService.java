package com.cczu.model.lydw.service;

import com.cczu.model.lydw.dao.LYDW_Xbgl_ZbDao;
import com.cczu.model.lydw.entity.LYDW_XBGL_Zb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 蓝牙定位---信标管理标注坐标管理
 */
@Transactional(readOnly=true)
@Service("LYDW_Xbgl_ZbService")
public class LYDW_Xbgl_ZbService {
    @Resource
    private LYDW_Xbgl_ZbDao lydw_xbgl_zbDao;

	//添加信息
	public void addInfo(LYDW_XBGL_Zb entity) {
		lydw_xbgl_zbDao.save(entity);
	}

	//删除信息
	public void delInfo(Long id) {
		lydw_xbgl_zbDao.delete(id);
	}
}
