package com.cczu.model.sbssgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLSbglDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBGLEntity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

/**
 * 设备设施管理-设备管理Service
 *
 */
@Service("SBSSGLSbglService")
public class SBSSGLSbglService {

	@Resource
	private SBSSGLSbglDao sBSSGLSbglDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbglDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除设备管理信息
	 * @param id
	 */
	public void deleteSbglById(long id) {
		sBSSGLSbglDao.deleteInfo(id);
	}
	
	/**
	 * 根据id查找设备管理详细信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbglDao.findById(id);
	}
	
	public SBSSGL_SBGLEntity find(Long id) {
		return sBSSGLSbglDao.find(id);
	}
	
	public List<Map<String, Object>> findListByMap(Map<String, Object> map) {
		return sBSSGLSbglDao.findListByMap(map);
	}
	
	/**
	 * 添加设备管理信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBGLEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbglDao.save(entity);
	}

	/**
	 * 更新设备管理信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBGLEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbglDao.save(entity);
	}
	
	/**
	 * 导出普通设备excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="设备清单.xls";
		List<Map<String, Object>> list=sBSSGLSbglDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
	 * 导出特种设备excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel2(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="设备清单.xls";
		List<Map<String, Object>> list=sBSSGLSbglDao.getExport2(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
     * 根据企业id获得未报废设备信息
     * @param qyid
     * @return
     */
    public List<SBSSGL_SBGLEntity> getQysblistByQyid(long qyid) {
		return sBSSGLSbglDao.getQysblistByQyid(qyid);
	}
    
    /**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbglDao.dataGrid2(mapData);
		int getTotalCount=sBSSGLSbglDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	 /**
     * 获得用户所在公司的未报废普通设备id, 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getSbJson() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByQyid(UserUtil.getCurrentShiroUser().getQyid()));
	}
	
	/**
     * 根据企业id获得未报废普通设备id, 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getSbJsonByQyid(Long qyid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByQyid(qyid));
	}
    
    /**
     * 获得用户所在公司的未报废普通设备编号, 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getSbJson2() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByQyid2(UserUtil.getCurrentShiroUser().getQyid()));
	}
	
	/**
     * 根据企业id获得未报废普通设备编号, 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getSbJsonByQyid2(Long qyid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByQyid2(qyid));
	}
    
    /**
     * 获得用户所在公司的未报废普通设备编号, 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getSbJson3() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByQyid3(UserUtil.getCurrentShiroUser().getQyid()));
	}
    
    /**
     * 根据企业id获得未报废普通设备编号, 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getSbJsonByQyid3(Long qyid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByQyid3(qyid));
	} 
    
    /**
     * 获得用户所在部门的未报废普通设备id, 设备名称信息json数据
     * @param deptid
     * @return
     */
    public String getSbJson4() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByDeptid4(UserUtil.getCurrentShiroUser().getDep().getId()));
	}
    
    /**
     * 根据部门id获得未报废普通设备id, 设备名称信息json数据
     * @param deptid
     * @return
     */
    public String getSbJsonByDeptid4(Long deptid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByDeptid4(deptid));
	}
    
    /**
     * 获得用户所在部门的未报废普通设备名称, 设备编号信息json数据
     * @param deptid
     * @return
     */
    public String getSbJson5() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByDeptid5(UserUtil.getCurrentShiroUser().getDep().getId()));
	}
    
    /**
     * 根据部门id获得未报废普通设备名称, 设备编号信息json数据
     * @param deptid
     * @return
     */
    public String getSbJsonByDeptid5(Long deptid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQysblistJsonByDeptid5(deptid));
	}
    
    /**
     * 根据设备id获得未报废设备信息的json数据
     * @param qyid
     * @return
     */
    public String getSbInfoJsonBySbid(Long sbid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.findById(sbid));
	}
    
    /**
     * 根据设备编号获得未报废设备信息的json数据
     * @param qyid
     * @return
     */
    public Map<String, Object> getSbInfoJsonBySbbh(String sbbh) {
		return sBSSGLSbglDao.findByBh(sbbh);
	}
    
    /**
     * 获得用户所在公司的未报废特种设备id， 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJson() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByQyid(UserUtil.getCurrentShiroUser().getQyid()));
	}
	
	/**
     * 根据企业id获得未报废特种设备id， 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJsonByQyid(Long qyid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByQyid(qyid));
	}
    
    /**
     * 获得用户所在公司的未报废特种设备编号， 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJson2() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByQyid2(UserUtil.getCurrentShiroUser().getQyid()));
	}
	
	/**
     * 根据企业id获得未报废特种设备编号， 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJsonByQyid2(Long qyid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByQyid2(qyid));
	}
    
    /**
     * 获得用户所在公司的未报废特种设备id， 设备编号信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJson3() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByQyid2(UserUtil.getCurrentShiroUser().getQyid()));
	}
	
	/**
     * 根据企业id获得未报废特种设备id， 设备编号信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJsonByQyid3(Long qyid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByQyid2(qyid));
	}
    
    /**
     * 获得用户所在部门的未报废特种设备id， 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJson4() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByDeptid4(UserUtil.getCurrentShiroUser().getDep().getId()));
	}
	
	/**
     * 根据部门id获得未报废特种设备id， 设备名称信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJsonByQyid4(Long deptid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByDeptid4(deptid));
	}
    
    /**
     * 获得用户所在部门的未报废特种设备名称， 设备编号信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJson5() {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByDeptid5(UserUtil.getCurrentShiroUser().getDep().getId()));
	}
	
	/**
     * 根据部门id获得未报废特种设备名称， 设备编号信息json数据
     * @param qyid
     * @return
     */
    public String getTzsbJsonByQyid5(Long deptid) {
		return JsonMapper.toJsonString(sBSSGLSbglDao.getQytzsblistJsonByDeptid5(deptid));
	}
}
