package com.cczu.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.dao.YhpcStoppointDao;
import com.cczu.model.entity.YHPC_StopPoint;

/**
 * 
 * @Description: 隐患排查风险隐患点停产Service
 */
@Service("YhpcStoppointService")
public class YhpcStoppointService {
	
	@Resource
	private YhpcStoppointDao yhpcStoppointDao;
	
	/**
	 * 根据风险点id或隐患点id和类型查找
	 */
	public List<YHPC_StopPoint> findById1AndType(Long id1,String type){
		return yhpcStoppointDao.findById1AndType(id1,type);
	}
	
	/**
	 * 根据风险点id或隐患点id和类型删除记录
	 */
	public void deleteById1AndType(Long id1,String type){
		yhpcStoppointDao.deleteById1AndType(id1,type);
	}
	
	/**
	 * 保存停产表
	 * @param stoppoint
	 */
	public void save(YHPC_StopPoint stoppoint){
		yhpcStoppointDao.save(stoppoint);
	}
	
	/**
	 * 删除过期数据
	 */
	public void deleteStaleData(){
		yhpcStoppointDao.deleteStaleData();
	}
}
