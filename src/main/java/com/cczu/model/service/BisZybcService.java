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

import com.cczu.model.dao.BisZybcDao;
import com.cczu.model.entity.BIS_WorkorderEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
/**
 * 
 * @Description: 作业班次信息Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("BisZybcService")
public class BisZybcService {
	@Resource
	private BisZybcDao bisZybcDao;
	
	/**
	 * tab页分页显示,根据一个企业id精确查询该企业所有冶金信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_WorkorderEntity> list=bisZybcDao.dataGrid(mapData);
		int getTotalCount=bisZybcDao.getTotalCount(mapData);
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
		
		List<Map<String, Object>> list=bisZybcDao.dataGrid2(mapData);
		
		int getTotalCount=bisZybcDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="作业班次信息表.xls";
		List<Map<String, Object>> list=bisZybcDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	/**
	 * 导入
	 * @param response
	 * @param mapData
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map) {
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
					BIS_WorkorderEntity zybc = new BIS_WorkorderEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					zybc.setS1(t);
					zybc.setS2(t);
					zybc.setS3(0);
					zybc.setID1(Long.valueOf(map.get("qyid").toString()));
					zybc.setM1(bis.get(0).toString());
					zybc.setM4(Integer.parseInt(bis.get(1).toString()));
					zybc.setM2(bis.get(2).toString());
					zybc.setM3(bis.get(3).toString());
					zybc.setM5(bis.get(4).toString());	
					bisZybcDao.save(zybc);
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
	
	public void addInfo(BIS_WorkorderEntity bis) {
		bisZybcDao.save(bis);
	}
	
	//根据id查询详细信息
	public BIS_WorkorderEntity findById(Long id) {
		return bisZybcDao.find(id);
	}
	
	//更新信息
	public void updateInfo(BIS_WorkorderEntity bis) {
		bisZybcDao.save(bis);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		bisZybcDao.deleteInfo(id);
	}
}
