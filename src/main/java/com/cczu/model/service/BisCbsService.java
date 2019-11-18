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

import com.cczu.model.dao.BisCbsxxDao;
import com.cczu.model.entity.BIS_ContractorEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
/**
 * 
 * @Description: 承包商信息Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("BisCbsService")
public class BisCbsService {
	@Resource
	private BisCbsxxDao bisCbsxxDao;
	
	/**
	 * tab页分页显示,根据一个企业id精确查询该企业所有承包商信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_ContractorEntity> list=bisCbsxxDao.dataGrid(mapData);
		int getTotalCount=bisCbsxxDao.getTotalCount(mapData);
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
		
		List<Map<String, Object>> list=bisCbsxxDao.dataGrid2(mapData);
		
		int getTotalCount=bisCbsxxDao.getTotalCount2(mapData);
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
		String fileName="承包商信息表.xls";
		List<Map<String, Object>> list=bisCbsxxDao.getExport(mapData);
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
					BIS_ContractorEntity cbs = new BIS_ContractorEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					cbs.setS1(t);
					cbs.setS2(t);
					cbs.setS3(0);
					cbs.setID1(Long.valueOf(map.get("qyid").toString()));
					cbs.setM1(bis.get(0).toString());
					cbs.setM2(bis.get(1).toString());
					cbs.setM3(bis.get(2).toString());
					cbs.setM4(bis.get(3).toString());
					cbs.setM8(bis.get(4).toString());	
					cbs.setM9(bis.get(5).toString());
					bisCbsxxDao.save(cbs);
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
	
	public void addInfo(BIS_ContractorEntity bis) {
		bisCbsxxDao.save(bis);
	}
	
	//根据id查询详细信息
	public BIS_ContractorEntity findById(Long id) {
		return bisCbsxxDao.find(id);
	}
	
	//更新信息
	public void updateInfo(BIS_ContractorEntity bis) {
		bisCbsxxDao.save(bis);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		bisCbsxxDao.deleteInfo(id);
	}
}
