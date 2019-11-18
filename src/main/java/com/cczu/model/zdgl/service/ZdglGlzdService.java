package com.cczu.model.zdgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.zdgl.entity.ZDGL_GLZDEntityHistory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cczu.model.zdgl.dao.ZdglGlzdDao;
import com.cczu.model.zdgl.entity.ZDGL_GLZDEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

/**
 *  制度管理-安全管理制度Service
 *
 */
@Service("ZdglGlzdService")
public class ZdglGlzdService {

	@Resource
	private ZdglGlzdDao zdglGlzdDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglGlzdDao.dataGrid(mapData);
		int getTotalCount=zdglGlzdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		zdglGlzdDao.deleteInfo(id);
	}
	
	//添加信息
	public void addInfo(ZDGL_GLZDEntity glzd) {
		Timestamp t=DateUtils.getSysTimestamp();
		glzd.setS1(t);
		glzd.setS2(t);
		glzd.setS3(0);
		glzd.setID1(UserUtil.getCurrentUser().getId2());
		glzd.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
		zdglGlzdDao.save(glzd);
	}
	
	public ZDGL_GLZDEntity find(Long id) {
		return zdglGlzdDao.find(id);
	}
	
	//更新信息
	public void updateInfo(ZDGL_GLZDEntity glzd) {
		Timestamp t=DateUtils.getSysTimestamp();
		glzd.setS2(t);
		zdglGlzdDao.save(glzd);
	}
	
	//根据id查找详细信息
	public Map<String,Object> findById(Long id) {
		return zdglGlzdDao.findById(id);
	}
	
	//导出
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="安全管理制度表.xls";
		List<Map<String, Object>> list=zdglGlzdDao.getExport(mapData);
		String[] title={"制度编号","制度名称","版本号","发布日期","制度正文","编辑部门","适用部门","审核人","审核意见","审核日期","批准人","批准意见","批准日期"};  
		String[] keys={"m2","m1","m3","m4","m5","bjbm","sybm","spr","spyj","m11","pzr","pzyj","m14"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	public Map<String, Object> getid(){
		return zdglGlzdDao.getid();
	}


    /**
     * 将安全管理制度 转换为修订记录
     * @param glzd
     * @return
     */
    public ZDGL_GLZDEntityHistory changeModel(ZDGL_GLZDEntity glzd) {
        ZDGL_GLZDEntityHistory history = new ZDGL_GLZDEntityHistory();
        history.setID2(glzd.getID());
        history.setM1(glzd.getM1());
        history.setM2(glzd.getM2());
        history.setM3(glzd.getM3());
        history.setM4(glzd.getM4());
        history.setM5(glzd.getM5());
        history.setM6(glzd.getM6());
        history.setM7(glzd.getM7());
        history.setM8(glzd.getM8());
        history.setM15(glzd.getM15());
        history.setM16(glzd.getM16());
	    return history;
    }

    public ZDGL_GLZDEntity zdglGlzdEntityChange(ZDGL_GLZDEntity newGlzd) {
        ZDGL_GLZDEntity oldGlzd = find(newGlzd.getID());
        oldGlzd.setM1(newGlzd.getM1());
        oldGlzd.setM2(newGlzd.getM2());
        oldGlzd.setM3(newGlzd.getM3());
        oldGlzd.setM4(newGlzd.getM4());
        oldGlzd.setM5(newGlzd.getM5());
        oldGlzd.setM6(newGlzd.getM6());
        oldGlzd.setM7(newGlzd.getM7());
        oldGlzd.setM8(newGlzd.getM8());
        oldGlzd.setM15(newGlzd.getM15());
        oldGlzd.setM16(newGlzd.getM16());
        return oldGlzd;
    }

	/**
	 * 安全管理制度与操作规程list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String,Object>> list=zdglGlzdDao.dataGrid2(mapData);
		int getTotalCount=zdglGlzdDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
