package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.TsWarningDataDao;
import com.cczu.model.entity.FMEW_AlarmEntity;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 * 报警数据Service
 * @author jason
 *
 */

@Transactional(readOnly=true)
@Service("TsWarningDataService")
public class TsWarningDataService {
	@Resource
	private TsWarningDataDao tsWarningDataDao;
	
	public void addInfo(FMEW_AlarmEntity bis) {
		tsWarningDataDao.save(bis);
	}
	

	public void updateInfo(FMEW_AlarmEntity bis) {
		tsWarningDataDao.save(bis);
	}
	
	public void deleteInfo(long id) {
		tsWarningDataDao.delete(id);
	}

	public FMEW_AlarmEntity findInfoByID(long id){
		return tsWarningDataDao.find(id);
	}
	
	
	public List<FMEW_AlarmEntity> findAllList(){
		List<FMEW_AlarmEntity> list=tsWarningDataDao.findAll();
		return list;
	}
	
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=tsWarningDataDao.dataGrid(mapData);
		int getTotalCount=tsWarningDataDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public Map<String, Object> dataGridApp(Map<String, Object> mapData) {
		List<Map<String, Object>> list=tsWarningDataDao.dataGridApp(mapData);
		int getTotalCount=tsWarningDataDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//根据id查找详细信息
	public FMEW_AlarmEntity findById(Long id) {
		return tsWarningDataDao.find(id);
	}

	//根据id查找详细信息2
	public Map<String, Object> findInforById(long id) {
		return tsWarningDataDao.findInforById(id);
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="";
		List<Map<String, Object>> list=tsWarningDataDao.getExport(mapData);
		if("1".equals(mapData.get("type").toString())){
			fileName="储罐报警信息表.xls";
		}else if("2".equals(mapData.get("type").toString())){
			fileName="气体报警信息表.xls";
		}else if("3".equals(mapData.get("type").toString())){
			fileName="高危工艺报警信息表.xls";
		}	
		if("1".equals(mapData.get("usertype").toString())){
		String[] title={"报警时间","报警情况","报警原因"};  
		String[] keys={"colltime","situation","reason"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","报警时间","报警情况","报警原因"};  
			String[] keys={"qyname","colltime","situation","reason"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
				keys = ("qyname,"+mapData.get("colval").toString()).split(",") ;
			 }
		new ExportExcel(fileName, title, keys, list, response, true);
		}
	}
}
