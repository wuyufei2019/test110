package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLSbjfDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBJFEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.util.common.StringUtils;

/**
 * 设备设施管理-设备交付Service
 *
 */
@Service("SBSSGLSbjfService")
public class SBSSGLSbjfService {

	@Resource
	private SBSSGLSbjfDao sBSSGLSbjfDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbjfDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbjfDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除设备交付信息
	 * @param id
	 */
	public void deleteSbjfById(long id) {
		sBSSGLSbjfDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找设备交付信息
	 * @param id
	 * @return
	 */
	public SBSSGL_SBJFEntity find(Long id) {
		return sBSSGLSbjfDao.find(id);
	}
	
	/**
	 * 添加设备交付信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBJFEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbjfDao.save(entity);
	}

	/**
	 * 更新设备交付信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBJFEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbjfDao.save(entity);
	}
	
	/**
	 * 根据id获得设备交付单word表数据
	 * @param id
	 * @return
	 */
	public Map<String, Object> getWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		//设备交付信息
		SBSSGL_SBJFEntity sbjf = sBSSGLSbjfDao.find(id);
		
		map.put("qyrq", DateUtils.formatDate(DateUtils.getSysDate(),"yyyy年MM月dd日"));
		map.put("m1", sbjf.getM1()==null?"":sbjf.getM1());
		map.put("m2", sbjf.getM2()==null?"":sbjf.getM2());
		map.put("m3", sbjf.getM3()==null?"":sbjf.getM3());
		map.put("m4", sbjf.getM4()==null?"":sbjf.getM4());
		map.put("m5", sbjf.getM5()==null?"":sbjf.getM5());
		map.put("m6", sbjf.getM6()==null?"":sbjf.getM6());
		map.put("m7", StringUtils.isEmpty(sbjf.getM7())?"                     ":sbjf.getM7());
		map.put("m8", StringUtils.isEmpty(sbjf.getM8())?"                        ":sbjf.getM8());
		map.put("m9", StringUtils.isEmpty(sbjf.getM9())?"                        ":sbjf.getM9());
		map.put("m10", StringUtils.isEmpty(sbjf.getM10())?"                     ":sbjf.getM10());
		map.put("m11", StringUtils.isEmpty(sbjf.getM11())?"                        ":sbjf.getM11());
		map.put("m12", StringUtils.isEmpty(sbjf.getM12())?"                     ":sbjf.getM12());
		map.put("m13", sbjf.getM13()==null?"":sbjf.getM13().replaceAll("\\s*", ""));
		map.put("m14", sbjf.getM14()==null?"":sbjf.getM14());
		map.put("m15", sbjf.getM15()==null?"":DateUtils.formatDate(sbjf.getM15(),"yyyy年MM月dd日"));
		map.put("m16", sbjf.getM16()==null?"    年      月":DateUtils.formatDate(sbjf.getM16(),"yyyy年MM月"));
		map.put("m17", sbjf.getM17()==null?"    年      月":DateUtils.formatDate(sbjf.getM17(),"yyyy年MM月"));
		map.put("m18", sbjf.getM18()==null?"":sbjf.getM18().replaceAll("\\s*", ""));
		return map;
	}
	
	/**
	 * 根据id查找设备交付信息
	 * @param id
	 * @return
	 */
	public String getDeptid(Map<String, Object> mapData) {
		return sBSSGLSbjfDao.getDeptidById(mapData);
	}
}
