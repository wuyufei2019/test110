package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglBgsqDao;
import com.cczu.model.zyaqgl.entity.AQGL_ChangeRequest;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  安全管理-变更申请Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglBgsqService")
public class AqglBgsqService {
	@Resource
	private AqglBgsqDao aqglBgsqDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=aqglBgsqDao.dataGrid(mapData);
		int getTotalCount=aqglBgsqDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据id查找费用使用信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findInforById(long id) {
		
		Map<String, Object> map=aqglBgsqDao.findInforById(id);
		return map;
	}
	
	public void addInfo(AQGL_ChangeRequest bis) {
		aqglBgsqDao.save(bis);
	}
	public long addInfoReturnID(AQGL_ChangeRequest bis) {
		aqglBgsqDao.save(bis);
		return bis.getID();
	}

	public void updateInfo(AQGL_ChangeRequest bis) {
		aqglBgsqDao.save(bis);
	}
	
	public void deleteInfo(long id) {
		aqglBgsqDao.deleteInfo(id);
	}

	public Map<String ,Object> findById(Long id) {
		return aqglBgsqDao.findInforById(id);
	}
	
	/**
	 * 导出
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="安全费用使用表.xls";
		List<Map<String, Object>> list=aqglBgsqDao.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
		String[] title={"日期","使用部门","支出项目类别","具体用途","金额(万元)","经办人","审核人","批准人","备注"};  
		String[] keys={"m1","depart","m2","m3","m4","m5","m6","m7","m8"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			 title = (mapData.get("coltext").toString()).split(",") ;
			 keys = (mapData.get("colval").toString()).split(",") ;
			}
		new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","日期","使用部门","支出项目类别","具体用途","金额(万元)","经办人","审核人","批准人","备注"};  
			String[] keys={"qyname","m1","depart","m2","m3","m4","m5","m6","m7","m8"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
				keys = ("qyname,"+mapData.get("colval").toString()).split(",") ;
			 }
		new ExportExcel(fileName, title, keys, list, response, true);
		}
	}

	/**
	 * 获取变更信息数量
	 * @param map
	 * @return
	 */
	public int getBgxxCount(Map<String, Object> map) {
		return aqglBgsqDao.getTotalCount(map);
	}
	
}
