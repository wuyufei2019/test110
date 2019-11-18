package com.cczu.model.mbgl.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.mbgl.dao.AqscFytqDao;
import com.cczu.model.mbgl.entity.AQSC_ExpenseExtraction;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  安全生产-费用提取Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqscFytqService")
public class AqscFytqService {
	@Resource
	private AqscFytqDao aqscFytqDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=aqscFytqDao.dataGrid(mapData);
		int getTotalCount=aqscFytqDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(AQSC_ExpenseExtraction bis) {
		aqscFytqDao.save(bis);
	}
	public long addInfoReturnID(AQSC_ExpenseExtraction bis) {
		aqscFytqDao.save(bis);
		return bis.getID();
	}

	public void updateInfo(AQSC_ExpenseExtraction bis) {
		aqscFytqDao.save(bis);
	}
	
	public void deleteInfo(long id) {
		aqscFytqDao.deleteInfo(id);
	}

	public AQSC_ExpenseExtraction findById(Long id) {
		return aqscFytqDao.find(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="安全费用提取信息表.xls";
		List<Map<String, Object>> list=aqscFytqDao.getExport(mapData);
		String[] title={"年度","销售收入（万元）","行业类型","提取标准","提取数（万元）"};  
		String[] keys={"m1","m2","m3","m4","m5"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	/**
	 * 导入
	 * @param response
	 * @param mapData
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
					AQSC_ExpenseExtraction fc = new AQSC_ExpenseExtraction();
					Timestamp t = DateUtils.getSysTimestamp();
					fc.setS1(t);
					fc.setS2(t);
					fc.setS3(0);
					fc.setM1(bis.get(0).toString());
					fc.setM2(Float.valueOf(bis.get(1).toString()));
//					fc.setM3(Float.valueOf(bis.get(2).toString()));
//					fc.setM4(Float.valueOf(bis.get(3).toString()));
					fc.setM5(Float.valueOf(bis.get(4).toString()));
					aqscFytqDao.save(fc);
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

	/**
	 * 主体责任-年度安全生产投入，tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {

		List<Map<String, Object>> list=aqscFytqDao.dataGrid2(mapData);
		int getTotalCount=aqscFytqDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
