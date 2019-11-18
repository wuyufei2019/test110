package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.WghglWgdDao;
import com.cczu.model.dao.YhpcCheckPointContentDao;
import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.entity.YHPC_CheckPointEntity;
import com.cczu.model.entity.YHPC_CheckPoint_Content;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;


/**
 * 
 * @Description: 网格点Service
 * @author: YZH
 * @date: 2017年12月27日
 */
@Transactional(readOnly=true)
@Service("WghglWgdService")
public class WghglWgdService {
	@Resource
	private WghglWgdDao wghglWgdDao;
	@Resource
	private YhpcCheckPointContentDao yhpcCheckPointContentDao;
	
	/**
	 * 查询公共检查表库信息list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid1(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=wghglWgdDao.dataGrid(mapData);
		int getTotalCount=wghglWgdDao.getTotalCount(mapData);
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
		
		List<Map<String,Object>> list=wghglWgdDao.dataGrid(mapData);
		int getTotalCount=wghglWgdDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 查询网格巡检内容list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridwgxjnr(Map<String, Object> mapData) {	
		List<Map<String,Object>> list=wghglWgdDao.dataGridwgxjnr(mapData);
		int getTotalCount=wghglWgdDao.getwgxjnrTotalCount(mapData);
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
		String fileName="网格点表.xls";
		List<Map<String, Object>> list=wghglWgdDao.getExport(mapData);
		String[] title={"网格名称","企业名称","巡查点名称","创建时间","二维码","rfid","rfid卡批次代码"};  
		String[] keys={"wgname","qyname","name","createtime","bindcontent","rfid","area"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
			keys = ("qyname,"+mapData.get("colval").toString()).split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	public void addInfo(YHPC_CheckPointEntity bis) {
		bis.setCreatetime(new Timestamp(System.currentTimeMillis()));
		wghglWgdDao.save(bis);
	}
	
	public boolean checkSameBuildContent(long  fxxxid,String bindcontent) {
		return wghglWgdDao.checkSameBuildContent(fxxxid,bindcontent);
	}
	
	public boolean checkSameRfid(long  fxxxid,String rfid) {
		return wghglWgdDao.checkSameRfid(fxxxid,rfid);
	}
	
//	//根据id查询详细信息
//	public YHPC_CheckPointEntity findById(Long id) {
//		return wghglWgdDao.findInforById(id);
//	}
	
	//根据id查询详细信息
	public Map<String, Object> findInforById(Long id) {
		return wghglWgdDao.findInforById(id);
	}
	//根据id查询详细信息
	public Map<String, Object> getExportgzk(Long id) {
		return wghglWgdDao.getExportgzk(id);
	}
	
	//更新信息
	public void updateInfo(YHPC_CheckPointEntity bis) {
		wghglWgdDao.save(bis);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		wghglWgdDao.deleteInfo(id);
	}
	
	
	/**
	 * 根据企业id查询企业平面图地址
	 * @return
	 */
	public String findpmtByqyid(String qyid) {
		String pmt= wghglWgdDao.findpmtByqyid(qyid);
		return pmt;
	}

	public Map<String, Object> xjnrdataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=wghglWgdDao.xjnrdataGrid(mapData);
		int getTotalCount=wghglWgdDao.getxjnrTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void deleteXjnr(long id) {
		wghglWgdDao.deleteXjnr(id);
	}
	
	public void bulidCheckContent(long id1, long id2) {
		YHPC_CheckPoint_Content y = new YHPC_CheckPoint_Content();
		y.setID1(id1);
		y.setID2(id2);
		yhpcCheckPointContentDao.save(y);
	}
	
	public Map<String, Object> xjnralldataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=wghglWgdDao.xjnralldataGrid(mapData);
		int getTotalCount=wghglWgdDao.getxjnrallTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//根据网格点id查询已绑定内容
	public List<YHPC_CheckPoint_Content> findxjnrbyid1(Long id) {
		return wghglWgdDao.findxjnrbyid1(id);
	}

	//查询改行政区域内未生成的网格点
	public List<BIS_EnterpriseEntity> getwsclist(Map<String, Object> map) {
		return wghglWgdDao.getwsclist(map);
	}
	
    //获取未生成企业的企业名称和id
	public String getQyIdjson(Map<String, Object> map) {
		List<Map<String, Object>> list = wghglWgdDao.findQyIdTextList(map);
		return JsonMapper.getInstance().toJson(list);
	}
	
	//网格点批量绑定巡检内容
	public void plbdxjnr(long id) {
		wghglWgdDao.plbdxjnr(id);
	}
	
	//网格信息 for app
	public Map<String, Object> WgxxForApp(Map<String, Object> mapData) {
		return wghglWgdDao.WgxxForApp(mapData);
	}
	
	//下属网格 for app
	public List<Map<String, Object>> XswgForApp(Map<String, Object> mapData) {
		return wghglWgdDao.XswgForApp(mapData);
	}
	
	//根据二维码查询隐患排查点详细信息 app
	public List<Map<String, Object>> findInforByewmForApp(String bindcontent) {
		return wghglWgdDao.findInforByewmForApp(bindcontent);
	}
	
	//根据网格点id查询网格点的巡检内容 app
	public List<Map<String, Object>> wgdxjnrForApp(Long wgdid){
		return wghglWgdDao.wgdxjnrForApp(wgdid);
	}
	
	//根据rfid查询隐患排查点详细信息 app
	public List<Map<String, Object>> findInforByrfidForApp(String rfid) {
		return wghglWgdDao.findInforByrfidForApp(rfid);
	}
}
