package com.cczu.model.service;

import com.cczu.model.dao.WghglWgxjjlzgDao;
import com.cczu.model.dao.YhpcXjjl2Dao;
import com.cczu.model.dao.YhpcXjjlDao;
import com.cczu.model.dao.YhpcYhpcjlDao;
import com.cczu.model.entity.YHPC_CheckHiddenInfoEntity;
import com.cczu.model.entity.YHPC_CheckResult2Entity;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巡检记录
 * @author zpc
 */
@Service("YhpcXjjl2Service")
public class YhpcXjjl2Service {

	@Resource
	private YhpcXjjl2Dao yhpcXjjlDao;
	@Resource
	private YhpcYhpcjlDao yhpcYhpcjlDao;

	/**
	 * 企业检查记录list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=yhpcXjjlDao.dataGrid(mapData);
		int getTotalCount=yhpcXjjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//添加巡检记录
	public void add(YHPC_CheckResult2Entity xjjl){
		yhpcXjjlDao.save(xjjl);
	}

	/**
	 * 删除巡检记录
	 * @param id
	 */
	public void deleteInfo(long id) {
		//根据巡检记录id查询隐患排查记录list
		List<Map<String, Object>> list=yhpcYhpcjlDao.findIdByJlid(id+"");
		for (Map<String, Object> map : list) {
/*			//根据隐患记录id删除隐患记录对应的隐患整改复查
			wghglWgxjjlzgDao.deleteYhrByYchid(Long.parseLong(map.get("id").toString()));
			//根据隐患记录id删除隐患记录
			wghglWgxjjlzgDao.deleteInfo(Long.parseLong(map.get("id").toString()));*/
		}
		//删除巡检记录
		yhpcXjjlDao.deleteInfor(id);
	}

	/**
	 * 查看详细信息
	 * @param map
	 * @return
	 */
	public Map<String,Object> findInforById(Long id) {
		return yhpcXjjlDao.findInforById(id);
	}
	
	/**
	 * 企业导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="巡检记录表.xls";
		List<Map<String, Object>> list=yhpcXjjlDao.getExport(mapData);
		String[] title={"检查点","所属班次","检查时间","检查人","检查结果","问题备注"};  
		String[] keys={"jcdname","name","createtime","uname","lx","note"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
}
