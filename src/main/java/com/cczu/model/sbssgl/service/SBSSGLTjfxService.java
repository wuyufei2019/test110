package com.cczu.model.sbssgl.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cczu.model.sbssgl.dao.SBSSGLTjfxDao;
import com.cczu.sys.system.dao.DepartmentDao;


/**
 * 设备设施管理-设备管理Service
 *
 */
@Service("SBSSGLTjfxService")
public class SBSSGLTjfxService {

	@Resource
	private SBSSGLTjfxDao sBSSGLTjfxDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 按部门统计全部设备台数、主要设备台数、主要设备完好台数、主要设备完好率
	 * @param mapData
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> getSbwhCount(Map<String, Object> mapData) throws ParseException {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=sBSSGLTjfxDao.getCountByDept(mapData);
		List<String> deptnameList=new ArrayList<>();
		List<String> sbTotalCountList=new ArrayList<>();
		List<String> mainSbCountList=new ArrayList<>();
		List<String> mainSbWhCountList=new ArrayList<>();
		List<String> mainWhRateList=new ArrayList<>();
		for(Map<String, Object> m:list){
			deptnameList.add(m.get("deptname").toString());//部门名称
			sbTotalCountList.add(m.get("sb_total_count").toString());//全部设备台数
			mainSbCountList.add(m.get("mainsb_total_count").toString());//主要设备台数
			String mainSbWhCount = m.get("mainsb_wh_count").toString();// 设备完好数量
			if (Integer.parseInt(mainSbWhCount) < 0) {
				mainSbWhCount = "0";
				mainSbWhCountList.add("0");
			} else {
				mainSbWhCountList.add(m.get("mainsb_wh_count").toString()); //主要设备完好台数
			}
			double dMainSbTotalCount = Double.parseDouble(m.get("mainsb_total_count").toString());
			double dMainSbWhCount = Double.parseDouble(mainSbWhCount);
			double whRate = 0;
			if (dMainSbWhCount != 0) {
				whRate = dMainSbTotalCount/dMainSbWhCount;
			} 
			DecimalFormat format = new DecimalFormat("0.00");
			mainWhRateList.add(format.format(whRate*100));
		}
		Map<String, Object> map=new HashMap<>();
		map.put("deptname", deptnameList);
		map.put("sb_total_count", sbTotalCountList);
		map.put("mainsb_total_count", mainSbCountList);
		map.put("mainsb_wh_count", mainSbWhCountList);
		map.put("mainsb_wh_rate", mainWhRateList);
		
		return map;
	}
	
	/**
	 * 按部门统计一级保养计划台数、一级保养完成台数、二级保养计划台数、二级保养完成台数
	 * @param mapData
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> getSbbyCount(Map<String, Object> mapData) throws ParseException {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=sBSSGLTjfxDao.getCountByBy(mapData);
		List<String> deptnameList=new ArrayList<>();
		List<String> firPalnCountList=new ArrayList<>();
		List<String> firCompleteCountList=new ArrayList<>();
		List<String> secPalnCountList=new ArrayList<>();
		List<String> secCompleteCountList=new ArrayList<>();
		for(Map<String, Object> m:list){
			deptnameList.add(m.get("deptname").toString());// 部门名称
			firPalnCountList.add(m.get("fir_plan_count").toString());// 一级保养计划台数
			firCompleteCountList.add(m.get("fir_complete_count").toString());// 一级保养完成台数
			secPalnCountList.add(m.get("sec_plan_count").toString()); // 二级保养计划台数
			secCompleteCountList.add(m.get("sec_complete_count").toString());// 二级保养完成台数
		}
		Map<String, Object> map=new HashMap<>();
		map.put("deptname", deptnameList);
		map.put("fir_plan_count", firPalnCountList);
		map.put("fir_complete_count", firCompleteCountList);
		map.put("sec_plan_count", secPalnCountList);
		map.put("sec_complete_count", secCompleteCountList);
		
		return map;
	}
	
	/**
	 * 按部门统计封存台数、故障次数、事故次数
	 * @param mapData
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> getSbqtCount(Map<String, Object> mapData) throws ParseException {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=sBSSGLTjfxDao.getCountByQt(mapData);
		List<String> deptnameList=new ArrayList<>();
		List<String> fcCountList=new ArrayList<>();
		List<String> gzCountList=new ArrayList<>();
		List<String> sgCountList=new ArrayList<>();
		for(Map<String, Object> m:list){
			deptnameList.add(m.get("deptname").toString());// 部门名称
			fcCountList.add(m.get("fc_count").toString());// 封存台数
			gzCountList.add(m.get("gz_count").toString());// 故障次数
			sgCountList.add("0"); // 事故次数
		}
		Map<String, Object> map=new HashMap<>();
		map.put("deptname", deptnameList);
		map.put("fc_count", fcCountList);
		map.put("gz_count", gzCountList);
		map.put("sg_count", sgCountList);
		
		return map;
	}
	
	public Map<String, Object> getWord(Map<String, Object> mapData){
		Map<String, Object> map = new HashMap<>();
		String starttime = mapData.get("starttime").toString();
		map.put("starttime", starttime.substring(0, 4).concat(" 年").concat(starttime.substring(5, 7)).concat(" 月"));
		Long deptid = Long.parseLong(mapData.get("deptid").toString());
		map.put("deptname", departmentDao.find(deptid).getM1());// 部门名称
		
		Map<String, Object> map1 = sBSSGLTjfxDao.getCountByDept(mapData).get(0);
		Map<String, Object> map2 = sBSSGLTjfxDao.getCountByBy(mapData).get(0);
		Map<String, Object> map3 = sBSSGLTjfxDao.getCountByQt(mapData).get(0);
		
		if (map1 != null) {
			if (map1.get("sb_total_count") != null) map.put("sbTotalCount", map1.get("sb_total_count"));// 全部设备数量
			if (map1.get("mainsb_total_count") != null) map.put("mainSbTotalCount", map1.get("mainsb_total_count"));// 主要设备数量
			String mainSbWhCount = "";
			if (map1.get("mainsb_wh_count") != null) mainSbWhCount = map1.get("mainsb_wh_count").toString();// 设备完好数量
			if (Integer.parseInt(mainSbWhCount) < 0) {
				mainSbWhCount = "0";
				map.put("mainSbWhCount","0");
			} else {
				if (map1.get("mainsb_wh_count") != null) map.put("mainSbWhCount",map1.get("mainsb_wh_count").toString()); //主要设备完好台数
			}
			double dMainSbTotalCount = 0;
			if (map1.get("mainsb_wh_count") != null) dMainSbTotalCount = Double.parseDouble(map1.get("mainsb_total_count").toString());
			double dMainSbWhCount = Double.parseDouble(mainSbWhCount);
			double whRate = 0;
			if (dMainSbWhCount != 0) {
				whRate = dMainSbTotalCount/dMainSbWhCount;
			} 
			DecimalFormat format = new DecimalFormat("0.00");
			map.put("mainSbWhRate",format.format(whRate*100));// 设备完好率
		}
		
		if (map2 != null) {
			if (map2.get("fir_plan_count") != null) map.put("firPlanCount", map2.get("fir_plan_count"));// 一级保养计划台数
			if (map2.get("fir_complete_count") != null) map.put("firCompleteCount", map2.get("fir_complete_count"));// 一级保养计划完成台数
			if (map2.get("sec_plan_count") != null) map.put("secPlanCount", map2.get("sec_plan_count"));// 二级保养计划台数
			if (map2.get("sec_complete_count") != null) map.put("secCompleteCount", map2.get("sec_complete_count"));// 二级保养计划完成台数
		}
		
		if (map3 != null) {
			if (map3.get("fc_count") != null) map.put("fcCount", map3.get("fc_count"));// 封存台数
			if (map3.get("gz_count") != null) map.put("gzCount", map3.get("gz_count"));// 故障次数
			map.put("sgCount", "0");// 事故次数
		}
		
		return map;
	}
	
	/**
	 * 按设备类别（A类、B类、C类）统计对应设备的完好数量
	 * @param mapData
	 * @return
	 */
	/*public Map<String, Object> getCountBySbkind(Map<String, Object> mapData) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		list=sBSSGLTjfxDao.getCountBySbkind(mapData);
		List<String> namelist=new ArrayList<>();
		List<Map<String, Object>> whlist=new ArrayList<>();
		for(Map<String, Object> m:list){
			Map<String, Object> whMap=new HashMap<>();
			if ("0".equals(m.get("sbtype").toString())) {
				namelist.add("A类");    //饼状图左侧的设备类别   
				whMap.put("name", "A类");
			} else if ("1".equals(m.get("sbtype").toString())) {
				namelist.add("B类");    //饼状图左侧的设备类别   
				whMap.put("name", "B类");
			} else if ("2".equals(m.get("sbtype").toString())) {
				namelist.add("C类");    //饼状图左侧的设备类别   
				whMap.put("name", "C类");
			} 
			//饼状图的data要求是[{name: xx, value: xx}]格式
			String whcount = m.get("wh_count").toString();
			if (StringUtils.isNotEmpty(whcount)) {
				if (Integer.parseInt(whcount) < 0) {
					whMap.put("value", 0);
				} else {
					whMap.put("value", whcount);
				}
			} else {
				whMap.put("value", 0);
			}
			whlist.add(whMap);
		}
		Map<String, Object> map=new HashMap<>();
		map.put("name", namelist);
		map.put("whcount", whlist);
		return map;
	}*/
    
}
