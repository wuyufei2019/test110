package com.cczu.model.zyaqgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cczu.model.zyaqgl.dao.AqglAqcsDao;
import com.cczu.model.zyaqgl.dao.AqglSxzyDao;
import com.cczu.model.zyaqgl.dao.AqglSxzyFxDao;
import com.cczu.model.zyaqgl.entity.AQGL_SxkjzyEntity;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  安全管理-受限空间作业Service
 * @author zpc
 *
 */
@Service("AqglSxzyService")
public class AqglSxzyService{

	@Resource
	private AqglSxzyDao aqglSxzyDao;
	@Resource
	private AqglAqcsDao aqcsDao;
	@Resource
	private AqglSxzyFxDao aqglSxzyFxDao;

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list = aqglSxzyDao.dataGrid(mapData);
		int getTotalCount = aqglSxzyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//根据id删除
	public void deleteInfo(long id) {
		aqglSxzyDao.deleteInfo(id);
	}
	
	//添加
	public void addInfo(AQGL_SxkjzyEntity entity) {
		aqglSxzyDao.save(entity);
	}

	//根据id查询
	public AQGL_SxkjzyEntity findById(Long id) {
		return aqglSxzyDao.find(id);
	}

	//修改
	public void updateInfo(AQGL_SxkjzyEntity entity) {
		entity.setS2(new java.sql.Timestamp(new java.util.Date().getTime()));
		aqglSxzyDao.save(entity);
	}

	//根据id查找全部详情
	public Map<String,Object> findallById(long id) {
		return aqglSxzyDao.findallById(id);
	}
	
	//导出
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="受限空间安全作业证表.xls";
		List<Map<String, Object>> list=aqglSxzyDao.getExport(mapData);
		String[] title={"企业名称","作业证编号","申请人","申请时间","申请单位","受限空间所属单位","受限空间名称","作业内容","空间内介质名称","作业时间起",
				"作业时间止","作业单位负责人","作业人","监护人","rfid","涉及的其他特殊作业","危险辨识","有毒有害介质标准","可燃气标准","氧含量标准","分析人","安全措施编制人","实施安全教育人",
				"申请单位意见","申请单位负责人","确认时间","审批单位意见","审批单位负责人","审批时间","验收人","验收时间"};  
		String[] keys={"qyname","m0","sqr","s1","m1","m2","m3","m4","m5","m6","m7","zyfzr","zyr","jhr","m20","m8","m9","m29","m30","m31","fxr","bzcsr","ssjyr","m21","dwfzr","m22","m23","spr","m24","ysr","m25"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		new ExportExcel(fileName, title, keys, list, response,true);
	}
	
	//根据id获得受限空间作业word表数据
	public Map<String, Object> getExportWord(long id){
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> mapret = aqglSxzyDao.findallById(id);
		
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
		
		map.put("m1", mapret.get("m1")==null||mapret.get("m1").toString().equals("")?"":mapret.get("m1").toString());
		map.put("sqr", mapret.get("sqr")==null||mapret.get("sqr").toString().equals("")?"":mapret.get("sqr").toString());
		map.put("m0", mapret.get("m0")==null||mapret.get("m0").toString().equals("")?"":mapret.get("m0").toString());
		map.put("m2", mapret.get("m2")==null||mapret.get("m2").toString().equals("")?"":mapret.get("m2").toString());
		map.put("m3", mapret.get("m3")==null||mapret.get("m3").toString().equals("")?"":mapret.get("m3").toString());
		map.put("m4", mapret.get("m4")==null||mapret.get("m4").toString().equals("")?"":mapret.get("m4").toString());
		map.put("m5", mapret.get("m5")==null||mapret.get("m5").toString().equals("")?"":mapret.get("m5").toString());
		map.put("zyfzr", mapret.get("zyfzr")==null||mapret.get("zyfzr").toString().equals("")?"":mapret.get("zyfzr").toString());
		map.put("jhr", mapret.get("jhr")==null||mapret.get("jhr").toString().equals("")?"":mapret.get("jhr").toString());
		map.put("zyr", mapret.get("zyr")==null||mapret.get("zyr").toString().equals("")?"":mapret.get("zyr").toString());
		map.put("m8", mapret.get("m8")==null||mapret.get("m8").toString().equals("")?"":mapret.get("m8").toString());
		map.put("m9", mapret.get("m9")==null||mapret.get("m9").toString().equals("")?"":mapret.get("m9").toString());
		map.put("m29", mapret.get("m29")==null||mapret.get("m29").toString().equals("")?"":mapret.get("m29").toString());
		map.put("m30", mapret.get("m30")==null||mapret.get("m30").toString().equals("")?"":mapret.get("m30").toString());
		map.put("m31", mapret.get("m31")==null||mapret.get("m31").toString().equals("")?"":mapret.get("m31").toString());
		map.put("bzcsr", mapret.get("bzcsr")==null||mapret.get("bzcsr").toString().equals("")?"":mapret.get("bzcsr").toString());
		map.put("ssjyr", mapret.get("ssjyr")==null||mapret.get("ssjyr").toString().equals("")?"":mapret.get("ssjyr").toString());
		map.put("m21", mapret.get("m21")==null||mapret.get("m21").toString().equals("")?"":mapret.get("m21").toString());
		map.put("m23", mapret.get("m23")==null||mapret.get("m23").toString().equals("")?"":mapret.get("m23").toString());
		
		String zt = mapret.get("zt").toString();
		if(zt.equals("0")||zt.equals("1")||zt.equals("2")||zt.equals("3")){
			map.put("qrcsr", "");
		}else{
			map.put("qrcsr", mapret.get("qrcsr")==null||mapret.get("qrcsr").toString().equals("")?"":mapret.get("qrcsr").toString());
		}
		
		Map<String,Object>  mapz= new HashMap<>();
		mapz.put("id1", id);
		List<Map<String, Object>> list = aqcsDao.bzaqcsAllList2(mapz);
		map.put("AQCS", list);
		List<Map<String, Object>> list2 = aqglSxzyFxDao.findAllByid1(id);
		map.put("SXFX", list2);
		
		return map;
	}
}
