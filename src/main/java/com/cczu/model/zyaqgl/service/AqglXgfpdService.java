package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglXgfpdDao;
import com.cczu.model.zyaqgl.dao.AqglXgfpdRecordDao;
import com.cczu.model.zyaqgl.dao.AqglXgfpdjhDao;
import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation;
import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_Record;
import com.cczu.model.zyaqgl.entity.AQGL_RelevantEvaluation_content;
import com.cczu.sys.comm.utils.DateUtils;

/**
 *  安全管理-相关方评定Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglXgfpdService")
public class AqglXgfpdService {

	@Resource
	private AqglXgfpdDao aqglxgfpdDao;
	@Resource
	private AqglXgfpdjhDao aqglxgfpdjhDao;
	@Resource
	private AqglXgfpdRecordDao aqglXgfpdRecordDao;

	public void addInfo(AQGL_RelevantEvaluation_content entity) {
		aqglxgfpdDao.save(entity);
	}

	public void updateInfo(AQGL_RelevantEvaluation_content entity) {
		aqglxgfpdDao.save(entity);
	}
	public void updateInfoTransactional(AQGL_RelevantEvaluation_content entity,String[] setids,
			String[] points,String[] rids) throws Exception{
		entity.setS2(DateUtils.getSystemTime());
		entity.setS3(0);
		updateInfo(entity);//更新评定信息
		Long Id =entity.getID();
		if(setids.length!=points.length){
			throw new Exception("数据不对应");
		}else{
			AQGL_RelevantEvaluation_Record record =null;
			for (int i = 0; i < setids.length; i++) {
				record = new AQGL_RelevantEvaluation_Record(
						rids[i].equals("")?null:Long.parseLong(rids[i]),
						Id, Long.parseLong(setids[i]),
						points[i].equals("")?null:Integer.parseInt(points[i]));
				aqglXgfpdRecordDao.save(record);
				record = null;
			}
		}
		Map<String, Object> map=aqglxgfpdjhDao.findcount(entity.getID1());
		Integer count=Integer.parseInt(map.get("cj").toString());
		String level="";
		if(count>=80){
			level="优秀";
		}else if(count>=70&&count<80){
			level="良好";
		}else if(count>=60&&count<70){
			level="合格";
		}else{
			level="不合格";
		}
		AQGL_RelevantEvaluation pdjh=aqglxgfpdjhDao.find(entity.getID1());
		pdjh.setM4(Integer.parseInt(map.get("cj").toString()));
		pdjh.setM5(level);
		aqglxgfpdjhDao.save(pdjh);//更新计划信息
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = aqglxgfpdDao.dataGrid(mapData);
		int getTotalCount = aqglxgfpdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {

		List<Map<String, Object>> list = aqglxgfpdDao.dataGrid2(mapData);
		int getTotalCount = aqglxgfpdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//根据资质id查询
	public Map<String, Object> findInforById(Long id) {
		return aqglxgfpdDao.findInforById(id);
	}

	//根据资质id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		aqglxgfpdDao.deleteInfo(id);
	}
}
