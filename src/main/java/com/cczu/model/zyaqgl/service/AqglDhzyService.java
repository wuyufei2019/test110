package com.cczu.model.zyaqgl.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.zyaqgl.dao.AqglAqcsDao;
import com.cczu.model.zyaqgl.dao.AqglAqzyAqcsDao;
import com.cczu.model.zyaqgl.dao.AqglDhzyDao;
import com.cczu.model.zyaqgl.dao.AqglDhzyFxDao;
import com.cczu.model.zyaqgl.entity.AQGL_FireWork;
import com.cczu.model.zyaqgl.entity.AQGL_Zyaq_Aqcs;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  安全管理-动火作业Service
 * @author YZH
 *
 */
@Transactional(readOnly=true)
@Service("AqglDhzyService")
public class AqglDhzyService{
	
	@Resource
	private AqglDhzyDao dhzyDao;
	@Resource
	private AqglAqzyAqcsDao aqglAqzyAqcsDao;
	@Resource
	private AqglDhzyFxDao aqglDhzyFxDao;
	@Resource
	private AqglAqcsDao aqcsDao;
	
	//添加动火作业
	public void addInfo(AQGL_FireWork entity) {
		dhzyDao.save(entity);
	}

	//修改动火作业
	public void updateInfo(AQGL_FireWork entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		dhzyDao.save(entity);
	}
	
	//查找动火作业
	public AQGL_FireWork find(long id) {
		return dhzyDao.find(id);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = dhzyDao.dataGrid(mapData);
		int getTotalCount = dhzyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 根据id查找动火作业信息
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> findInforById(long id) {
		
		Map<String, Object> map=dhzyDao.findInforById(id);
		return map;
	}
	
	/**
	 * 根据id查找动火作业信息2
	 * @param mapData
	 * @return
	 */
	public AQGL_FireWork findById(long id) {
		
		AQGL_FireWork dhzy=dhzyDao.find(id);
		return dhzy;
	}

	//根据资质id删除
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		dhzyDao.deleteInfo(id);
	}
	
	//安全措施datagrid
	public Map<String, Object> aqscdataGrid(Map<String, Object> mapData) {

		List<Map<String, Object>> list = dhzyDao.aqcsList(mapData);
		int getTotalCount = dhzyDao.aqcsCount();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//编制安全措施
	public void addAqcs(AQGL_Zyaq_Aqcs entity) {
		aqglAqzyAqcsDao.save(entity);
	}
	
	//确认安全措施
	public void updateAqcs(AQGL_Zyaq_Aqcs entity) {
		aqglAqzyAqcsDao.save(entity);
	}
	
	//根据id查找全部详情
	public Map<String,Object> findallById(long id) {
		return dhzyDao.findallById(id);
	}
	
	//导出excel
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="动火作业安全作业证表.xls";
		List<Map<String, Object>> list=dhzyDao.getExport(mapData);
		String[] title={"企业名称","作业证编号","申请人","申请时间","申请单位","动火作业级别","动火方式","动火地点","动火时间起","动火时间止",
				"动火作业负责人","动火人","涉及的其他特殊作业","危害辨识","分析人","安全措施编制人","生产单位负责人","监火人","动火初审人","实施安全教育人","申请单位意见","申请单位负责人","确认时间",
				"安全管理部门意见","安全管理部门负责人","确认时间","动火审批人意见","动火审批人","审批时间","验收人","验收时间"};  
		String[] keys={"qyname","m1","sqr","s1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","fxr","bzcsr","sqdwfzr","jhr","dhcsr","aqjyr","m22_1","sqdwfzr","m22_2",
				"m23_1","aqglr","m23_2","m24_1","dhspr","m24_2","ysr","m25_1"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response,true);
	}
	
	//根据id获得受限空间作业word表数据
	public Map<String, Object> getExportWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapret = dhzyDao.findallById(id);
		
		if(mapret.get("m6")!=null&&!mapret.get("m6").toString().equals("")){
			String a = mapret.get("m6").toString();
			String[] as1 = a.substring(0,10).split("-");
			map.put("year1", as1[0]);
			map.put("month1", as1[1]);
			map.put("day1", as1[2]);
			String[] bs1 = a.substring(11,16).split(":");
			map.put("hour1", bs1[0]);
			map.put("min1", bs1[1]);
		}else{
			map.put("year1", "    ");
			map.put("month1", "  ");
			map.put("day1", "  ");
			map.put("hour1", "  ");
			map.put("min1", "  ");
		}
		if(mapret.get("m7")!=null&&!mapret.get("m7").toString().equals("")){
			String a = mapret.get("m7").toString();
			String[] as1 = a.substring(0,10).split("-");
			map.put("year2", as1[0]);
			map.put("month2", as1[1]);
			map.put("day2", as1[2]);
			String[] bs1 = a.substring(11,16).split(":");
			map.put("hour2", bs1[0]);
			map.put("min2", bs1[1]);
		}else{
			map.put("year2", "    ");
			map.put("month2", "  ");
			map.put("day2", "  ");
			map.put("hour2", "  ");
			map.put("min2", "  ");
		}
		
		map.put("m2", mapret.get("m2")==null||mapret.get("m2").toString().equals("")?"":mapret.get("m2").toString());
		map.put("sqr", mapret.get("sqr")==null||mapret.get("sqr").toString().equals("")?"":mapret.get("sqr").toString());
		map.put("m1", mapret.get("m1")==null||mapret.get("m1").toString().equals("")?"":mapret.get("m1").toString());
		map.put("m3", mapret.get("m3")==null||mapret.get("m3").toString().equals("")?"":mapret.get("m3").toString());
		map.put("m4", mapret.get("m4")==null||mapret.get("m4").toString().equals("")?"":mapret.get("m4").toString());
		map.put("m5", mapret.get("m5")==null||mapret.get("m5").toString().equals("")?"":mapret.get("m5").toString());
		map.put("m8", mapret.get("m8")==null||mapret.get("m8").toString().equals("")?"":mapret.get("m8").toString());
		map.put("m9", mapret.get("m9")==null||mapret.get("m9").toString().equals("")?"":mapret.get("m9").toString());
		map.put("m10", mapret.get("m10")==null||mapret.get("m10").toString().equals("")?"":mapret.get("m10").toString());
		map.put("m11", mapret.get("m11")==null||mapret.get("m11").toString().equals("")?"":mapret.get("m11").toString());
		map.put("fxr", mapret.get("fxr")==null||mapret.get("fxr").toString().equals("")?"":mapret.get("fxr").toString());
		map.put("bzcsr", mapret.get("bzcsr")==null||mapret.get("bzcsr").toString().equals("")?"":mapret.get("bzcsr").toString());
		map.put("scdwr", mapret.get("scdwr")==null||mapret.get("scdwr").toString().equals("")?"":mapret.get("bzcsr").toString());
		map.put("jhr", mapret.get("jhr")==null||mapret.get("jhr").toString().equals("")?"":mapret.get("jhr").toString());
		map.put("dhspr", mapret.get("csr")==null||mapret.get("csr").toString().equals("")?"":mapret.get("csr").toString());
		map.put("aqjyr", mapret.get("aqjyr")==null||mapret.get("aqjyr").toString().equals("")?"":mapret.get("aqjyr").toString());
		map.put("m22_1", mapret.get("m22_1")==null||mapret.get("m22_1").toString().equals("")?"":mapret.get("m22_1").toString());
		map.put("m23_1", mapret.get("m23_1")==null||mapret.get("m23_1").toString().equals("")?"":mapret.get("m23_1").toString());
		map.put("m24_1", mapret.get("m24_1")==null||mapret.get("m24_1").toString().equals("")?"":mapret.get("m24_1").toString());
		
		String zt = mapret.get("zt").toString();
		if(zt.equals("0")||zt.equals("1")||zt.equals("2")||zt.equals("3")){
			map.put("qrcsr", "");
		}else{
			map.put("qrcsr", mapret.get("qrcsr")==null||mapret.get("qrcsr").toString().equals("")?"":mapret.get("qrcsr").toString());
		}
		
		Map<String,Object>  mapz= new HashMap<>();
		mapz.put("id1", id);
		List<Map<String, Object>> list = aqcsDao.bzaqcsAllList(mapz);
		map.put("AQCS", list);
		List<Map<String, Object>> list2 = aqglDhzyFxDao.findAllByid1(id);
		map.put("DHFX", list2);
		
		return map;
	}
}
