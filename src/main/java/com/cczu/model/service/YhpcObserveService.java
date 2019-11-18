package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcObserveDao;
import com.cczu.model.dao.YhpcObserveSecDao;
import com.cczu.model.entity.YHPC_Observations_Main;
import com.cczu.model.entity.YHPC_Observations_Sec;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;


/**
 *  观察记录Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("YhpcObserveService")
public class YhpcObserveService {
	@Resource
	private YhpcObserveDao yhpcObserveDao;
	@Resource
	private YhpcObserveSecDao yhpcObserveSecDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=yhpcObserveDao.dataGrid(mapData);
		int getTotalCount=yhpcObserveDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findObserveList(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=yhpcObserveDao.findObserveList(mapData);
		int getTotalCount=yhpcObserveDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void deleteInfo(long id) {
		yhpcObserveDao.deleteInfo(id);
	}
	
	public void deleteJl(long id) {
		yhpcObserveDao.deleteJl(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="不安全行为信息表.xls";
		List<Map<String, Object>> list=yhpcObserveDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}
	
	/**
	 * app添加观察记录主表
	 * 
	 */
	public void addMainForApp(YHPC_Observations_Main main) {
		Timestamp t=DateUtils.getSystemTime();
		main.setS1(t);
		main.setS2(t);
		main.setS3(0);
		yhpcObserveDao.save(main);
	}
	
	/**
	 * app添加观察记录副表
	 * @param str1：企业id str2:观察员id str3:区域  str4:不安全行为id(以逗号隔开)
	 */
	public void addSecForApp(YHPC_Observations_Sec sec) {
		Timestamp t=DateUtils.getSystemTime();
		sec.setS1(t);
		sec.setS2(t);
		sec.setS3(0);
		yhpcObserveSecDao.save(sec);
	}
	
	/**
	 * App观察记录详细list页面
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findObserveListForApp(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=yhpcObserveDao.findObserveList(mapData);
		Map<String, Object> map=new HashMap<String, Object>();
		String xwlx=list.get(0).get("m1").toString();
		List<Map<String, Object>> templist=new ArrayList<>();
		for(Map<String, Object> m:list){
			String tempxwlx=m.get("m1").toString();
			if(tempxwlx.equals(xwlx)){
				templist.add(m);
			}else{
				map.put(xwlx, templist);
				xwlx=tempxwlx;
				templist=new ArrayList<>();
			}
		}
		map.put(xwlx, templist);
		int getTotalCount=yhpcObserveDao.getTotalCount2(mapData);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 查询选中观察记录的详细信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findObserveViewForApp(String secid) {
		return yhpcObserveDao.findObserveViewForApp(secid);
	}
}
