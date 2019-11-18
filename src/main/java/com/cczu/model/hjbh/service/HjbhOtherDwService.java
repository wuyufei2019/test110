package com.cczu.model.hjbh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.hjbh.dao.HjbhOtherDwDao;
import com.cczu.model.hjbh.entity.HJBH_OtherDw;

/**
 * 
 * @author zpc
 */
@Service("HjbhOtherDwService")
public class HjbhOtherDwService {
	
	@Resource
	private HjbhOtherDwDao hjbhOtherDwDao;
	
	/**
	 * 添加外单位信息
	 * @param hOtherDw
	 */
	public long addInfo(HJBH_OtherDw entity) {
		return hjbhOtherDwDao.addInfo(entity);
	}

	/**
	 * 获取查看页面的外单位list
	 * @param id1
	 * @return
	 */
	public List<HJBH_OtherDw> findAllById1(long id1) {
		// TODO Auto-generated method stub
		List<HJBH_OtherDw> list=hjbhOtherDwDao.findBy("ID1", id1);
		return list;
	}
	
	/**
	 * 通过外单位的id获取该外单位的信息
	 * @param id
	 * @return
	 */
	public HJBH_OtherDw findById(Long id) {
		// TODO Auto-generated method stub
		HJBH_OtherDw entity = hjbhOtherDwDao.find(id);
		hjbhOtherDwDao.flush();
		hjbhOtherDwDao.clear();
		return entity;
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		hjbhOtherDwDao.delete(id);
	}
	
}
