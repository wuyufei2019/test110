package com.cczu.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.YhpcCheckPointContentDao;
import com.cczu.model.dao.YhpcYhpcdDao;
import com.cczu.model.dao.impl.BisQyjbxxDaoImpl;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.YHPC_CheckPointEntity;
import com.cczu.model.entity.YHPC_CheckPoint_Content;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;


/**
 * 
 * @Description: 隐患排查点Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("YhpcYhpcdService")
public class YhpcYhpcdService {
	@Resource
	private YhpcYhpcdDao yhpcYhpcdDao;
	@Resource
	private YhpcCheckPointContentDao yhpcCheckPointContentDao;
	@Resource
	private BisQyjbxxDaoImpl bisQyjbxxDaoImpl;
	
	/**
	 * 查询公共检查表库信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid1(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcYhpcdDao.dataGrid(mapData);
		int getTotalCount=yhpcYhpcdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 查询企业自增表库信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=yhpcYhpcdDao.dataGrid(mapData);
		int getTotalCount=yhpcYhpcdDao.getTotalCount(mapData);
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
		String fileName="隐患排查点表.xls";
		List<Map<String, Object>> list=yhpcYhpcdDao.getExport(mapData);
		String[] title={"企业","巡查点名称","绑定二维码","绑定rfid","rfid卡批次代码"};  
		String[] keys={"qyname","name","bindcontent","rfid","area"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
			keys = ("qyname,"+mapData.get("colval").toString()).split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	public String addInfo(YHPC_CheckPointEntity bis,String bdnrids) {	
		String datasuccess = "success";
		yhpcYhpcdDao.save(bis);
		long id=bis.getID();
		//风险点添加成功后，绑定巡检内容
		if(id>0){
			String[] arrids = bdnrids.split(",");
			try{
				for (int i = 0; i < arrids.length; i++) {
					bulidCheckContent(id,Long.parseLong(arrids[i]));
				}
			}catch(Exception e){
				datasuccess="bderror";
			}
		}
		return datasuccess;
	}
	
//	//根据id查询详细信息
//	public YHPC_CheckPointEntity findById(Long id) {
//		return yhpcYhpcdDao.findInforById(id);
//	}
	
	//根据id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return yhpcYhpcdDao.findInforById(id);
	}
	
	//更新信息
	public String updateInfo(YHPC_CheckPointEntity bis,String bdnrids) {
		String datasuccess = "success";
		yhpcYhpcdDao.save(bis);
		//删除绑定内容重新绑定
		long id=bis.getID();
		yhpcYhpcdDao.deletexjnrbyid1(bis.getID());
		String[] arrids = bdnrids.split(",");
		int len=arrids.length;
		try{
			for (int i = 0; i < len; i++) {
				bulidCheckContent(id,Long.parseLong(arrids[i]));
			}
		}catch(Exception e){
			datasuccess="bderror";
		}
		return datasuccess;
	}
	
	//删除信息
	public void deleteInfo(long id) {
		yhpcYhpcdDao.deleteInfo(id);
	}
	
	//根据id1删除巡检内容
	public void deletexjnrbyid1(long id) {
		yhpcYhpcdDao.deletexjnrbyid1(id);
	}
	
	/**
	 * 根据企业id查询企业平面图地址
	 * @return
	 */
	public String findpmtByqyid(long qyid) {
		BIS_EnterpriseEntity bis =bisQyjbxxDaoImpl.find(qyid);
		return StringUtils.defaultString(bis.getM33_3());
	}

	public Map<String, Object> xjnrdataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=yhpcYhpcdDao.xjnrdataGrid(mapData);
		int getTotalCount=yhpcYhpcdDao.getxjnrTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void deleteXjnr(long id) {
		yhpcYhpcdDao.deleteXjnr(id);
	}
	
	public void bulidCheckContent(long id1, long id2) {
		YHPC_CheckPoint_Content y = new YHPC_CheckPoint_Content();
		y.setID1(id1);
		y.setID2(id2);
		yhpcCheckPointContentDao.save(y);
	}
	
	public Map<String, Object> xjnralldataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=yhpcYhpcdDao.xjnralldataGrid(mapData);
		int getTotalCount=yhpcYhpcdDao.getxjnrallTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//app二维码扫描
	public List<Map<String, Object>> findInforByewmForApp(String bindcontent){
		return yhpcYhpcdDao.findInforByewmForApp(bindcontent);
	}
	
	//根据自查点id及类型查询自查点的巡检内容 app
	public List<Map<String, Object>> zcdxjnrForApp(Map<String, Object> mapData){
		return yhpcYhpcdDao.zcdxjnrForApp(mapData);
	}
	
	//app rfid扫描
	public List<Map<String, Object>> findInforByrfidForApp(String rfid){
		return yhpcYhpcdDao.findInforByrfidForApp(rfid);
	}
}
