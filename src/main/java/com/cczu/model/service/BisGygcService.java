package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisGygcDao;
import com.cczu.model.entity.BIS_Utilities;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;


/**
 *  公用工程Service
 *
 */
@Transactional(readOnly=true)
@Service("BisGygcService")
public class BisGygcService {
	
	@Resource
	private BisGygcDao bisGygcDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=bisGygcDao.dataGrid(mapData);
		int getTotalCount=bisGygcDao.getTotalCount(mapData);
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
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=bisGygcDao.dataGrid2(mapData);
		int getTotalCount=bisGygcDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(BIS_Utilities aqjg) {
		Timestamp t=DateUtils.getSysTimestamp();
		aqjg.setS1(t);
		aqjg.setS2(t);
		aqjg.setS3(0);
		bisGygcDao.save(aqjg);
	}
	
	public long addInfoReturnID(BIS_Utilities aqjg) {
		Timestamp t=DateUtils.getSysTimestamp();
		aqjg.setS1(t);
		aqjg.setS2(t);
		aqjg.setS3(0);
		bisGygcDao.save(aqjg);
		return aqjg.getID();
	}
	
	//更新信息
	public void updateInfo(BIS_Utilities aqjg) {
		Timestamp t=DateUtils.getSysTimestamp();
		aqjg.setS2(t);
		aqjg.setS3(0);
		bisGygcDao.save(aqjg);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		bisGygcDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public BIS_Utilities findById(Long id) {
		return bisGygcDao.find(id);
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="公用工程信息表.xls";
		List<Map<String, Object>> list=bisGygcDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response,true);
	}
	
	
	/**
	 * 导入
	 * @param map
	 * @return
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
		if (list.size() > 3) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				try{
					BIS_Utilities ws = new BIS_Utilities();
					Timestamp t = DateUtils.getSysTimestamp();
					ws.setS1(t);
					ws.setS2(t);
					ws.setS3(0);
					ws.setID1(Long.valueOf(map.get("qyid").toString()));
					ws.setM1(bis.get(0).toString());
					ws.setM2(Double.parseDouble(bis.get(1).toString()));
					ws.setM3(bis.get(2).toString());
					ws.setM4(bis.get(3).toString());
					ws.setM6(bis.get(4).toString());
					 
					bisGygcDao.save(ws);
					result++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",result);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",result);
			resultmap.put("error", error);
			resultmap.put("returncode", "ext");
		}
		if(Integer.valueOf(resultmap.get("success").toString())==0){
			resultmap.put("returncode", "warn");
		}
		return resultmap;

	}
}
