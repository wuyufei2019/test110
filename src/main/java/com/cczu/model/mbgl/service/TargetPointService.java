package com.cczu.model.mbgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.TargetPointDao;
import com.cczu.model.mbgl.dao.TargetPointJfDao;
import com.cczu.model.mbgl.entity.Target_Points;
import com.cczu.model.mbgl.entity.Target_Points_jf;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  积分管理Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("TargetPointService")
public class TargetPointService {
	
	@Resource
	private TargetPointDao targetPointDao;
	@Resource
	private TargetPointJfDao targetPointJfDao;
	
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=targetPointDao.dataGrid(mapData);
		int getTotalCount=targetPointDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	public Map<String, Object> dataGridOverview(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=targetPointDao.dataGridOverview(mapData);
		int getTotalCount=targetPointDao.getTotalCountOverview(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public void addInfo(Target_Points tatget) {
		Target_Points_jf jf = targetPointJfDao.findInfor();
		if(tatget.getM1().equals("1")){
			tatget.setM2(jf.getM1());
			tatget.setM3(tatget.getM3()+jf.getM1()+"积分");
		}else if(tatget.getM1().equals("2")){
			tatget.setM2(jf.getM2());
			tatget.setM3(tatget.getM3()+jf.getM2()+"积分");
		}else if(tatget.getM1().equals("3")){
			tatget.setM2(jf.getM3());
			tatget.setM3(tatget.getM3()+jf.getM3()+"积分");
		}else if(tatget.getM1().equals("4")){
			tatget.setM2(jf.getM4());
			tatget.setM3(tatget.getM3()+jf.getM4()+"积分");
		}else if(tatget.getM1().equals("5")){
			tatget.setM2(jf.getM5());
			tatget.setM3(tatget.getM3()+jf.getM5()+"积分");
		}
		targetPointDao.save(tatget);
	}
	
	public long addInfoReturnID(Target_Points tatget) {
		targetPointDao.save(tatget);
		return tatget.getID();
	}

	public void updateInfo(Target_Points tatget) {
		targetPointDao.save(tatget);
	}
	
	public void deleteInfo(long id) {
		targetPointDao.deleteInfo(id);
	}

	public Target_Points findInfoById(Long id) {
		return targetPointDao.find(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="积分管理.xls";
		List<Map<String, Object>> list=targetPointDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
}
