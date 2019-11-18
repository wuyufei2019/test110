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

import com.cczu.model.mbgl.dao.AqscFysyDao;
import com.cczu.model.mbgl.entity.AQSC_ExpenseUse;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  安全生产-费用使用Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqscFysyService")
public class AqscFysyService {
	@Resource
	private AqscFysyDao aqscFysyDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=aqscFysyDao.dataGrid(mapData);
		int getTotalCount=aqscFysyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据id查找费用使用信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findInforById(long id) {
		
		Map<String, Object> map=aqscFysyDao.findInforById(id);
		return map;
	}
	
	public void addInfo(AQSC_ExpenseUse bis) {
		aqscFysyDao.save(bis);
	}
	public long addInfoReturnID(AQSC_ExpenseUse bis) {
		aqscFysyDao.save(bis);
		return bis.getID();
	}

	public void updateInfo(AQSC_ExpenseUse bis) {
		aqscFysyDao.save(bis);
	}
	
	public void deleteInfo(long id) {
		aqscFysyDao.deleteInfo(id);
	}

	public AQSC_ExpenseUse findById(Long id) {
		return aqscFysyDao.find(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="安全费用使用表.xls";
		List<Map<String, Object>> list=aqscFysyDao.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
		String[] title={"日期","使用部门","支出项目类别","具体用途","金额（万元）","经办人","审核人","批准人"};  
		String[] keys={"m1","depart","lx","m3","m4","m5","m6","m7"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","日期","使用部门","支出项目类别","具体用途","金额（万元）","经办人","审核人","批准人"};  
			String[] keys={"qyname","m1","depart","lx","m3","m4","m5","m6","m7"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
				keys = ("qyname,"+mapData.get("colval").toString()).split(",") ;
			 }
		new ExportExcel(fileName, title, keys, list, response, true);
		}
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
					AQSC_ExpenseUse fc = new AQSC_ExpenseUse();
					Timestamp t = DateUtils.getSysTimestamp();
					fc.setS1(t);
					fc.setS2(t);
					fc.setS3(0);
//					fc.setM1(bis.get(0).toString());
//					fc.setM2(Float.valueOf(bis.get(1).toString()));
//					fc.setM3(Float.valueOf(bis.get(2).toString()));
//					fc.setM4(Float.valueOf(bis.get(3).toString()));
//					fc.setM5(Float.valueOf(bis.get(4).toString()));
					aqscFysyDao.save(fc);
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
