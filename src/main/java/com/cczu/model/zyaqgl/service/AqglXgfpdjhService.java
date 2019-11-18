package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglXgfpdDao;
import com.cczu.model.zyaqgl.dao.AqglXgfpdjhDao;
import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation;
import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_content;
import com.cczu.sys.comm.mapper.JsonMapper;

/**
 *  安全管理-相关方评定Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglXgfpdjhService")
public class AqglXgfpdjhService {

	@Resource
	private AqglXgfpdjhDao aqglxgfpdjhDao;
	
	@Resource
	private AqglXgfpdDao aqglxgfpdDao;


	public void addInfo(AQGL_RelevantEvaluation entity) {
		aqglxgfpdjhDao.save(entity);
	}


	public void updateInfo(AQGL_RelevantEvaluation entity) {
		aqglxgfpdjhDao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = aqglxgfpdjhDao.dataGrid(mapData);
		int getTotalCount = aqglxgfpdjhDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public Map<String, Object> findcount(Long ID1) {
		return aqglxgfpdjhDao.findcount(ID1);
	}
	
	/**
	 * 相关方单位下拉框list
	 * @param map
	 * @return
	 */
	public String getXgfdwjson(Map<String, Object> map) {
		List<Map<String, Object>> list = aqglxgfpdjhDao.findXgfdwList(map);
		return JsonMapper.getInstance().toJson(list);
	}
	
	//根据资质id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return aqglxgfpdjhDao.findInforById(id);
	}
	
	//根据资质id查询
	public AQGL_RelevantEvaluation findById(Long id) {
		return aqglxgfpdjhDao.find(id);
	}
	
	//根据id查询评定人员
	public List<Map<String,Object>> getidname(long id1){
		return aqglxgfpdjhDao.getidname(id1);
	}

	//根据资质id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqglxgfpdjhDao.deleteInfo(id);
	}
	
	/**
	 * 添加巡检班次人员中间表
	 * @param zfry
	 */
	public void addpdnrInfo(AQGL_RelevantEvaluation_content pdry) {
		aqglxgfpdDao.save(pdry);
	}
	
	/**
	 * 根据id1删除评定内容表
	 * @param parseLong
	 */
	public void deletepdnrInfo(long id1) {
		aqglxgfpdjhDao.deletepdnrInfo(id1);
	}
	
	/**
	 * 根据id1,ID2删除评定内容表
	 * @param parseLong
	 */
	public void deletepdnrInfo(Map<String, Object> mapData) {
		aqglxgfpdDao.deleteInfor(mapData);
	}
}
