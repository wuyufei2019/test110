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

import com.cczu.model.dao.AqjgDSFJcjlDao;
import com.cczu.model.entity.AQJD_DSFCheckRecordEntity;
import com.cczu.model.entity.AQJD_DSFCheckContentEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  第三方检查记录Service
 *
 */
@Transactional(readOnly=true)
@Service("AqjgDSFJcjlService")
public class AqjgDSFJcjlService {

	@Resource
	private AqjgDSFJcjlDao aqjgDsfJcjlDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String,Object>> list=aqjgDsfJcjlDao.dataGrid(mapData);
		int getTotalCount=aqjgDsfJcjlDao.getTotalCount(mapData);
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
		
		List<Map<String,Object>> list=aqjgDsfJcjlDao.dataGrid2(mapData);
		int getTotalCount=aqjgDsfJcjlDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(AQJD_DSFCheckRecordEntity jcjl) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS1(t);
		jcjl.setS2(t);
		jcjl.setS3(0);
		aqjgDsfJcjlDao.save(jcjl);
	}
	
	public long addInfoReturnID(AQJD_DSFCheckRecordEntity jcjl) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS1(t);
		jcjl.setS2(t);
		jcjl.setS3(0);
		aqjgDsfJcjlDao.save(jcjl);
		return jcjl.getID();
	}
	
	//更新信息
	public void updateInfo(AQJD_DSFCheckRecordEntity jcjl) {
		Timestamp t=DateUtils.getSysTimestamp();
		jcjl.setS2(t);
		jcjl.setS3(0);
		aqjgDsfJcjlDao.save(jcjl);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		aqjgDsfJcjlDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public AQJD_DSFCheckRecordEntity findById(Long id) {
		return aqjgDsfJcjlDao.find(id);
	}
	/**
	 * 导出excel
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData){
			// TODO Auto-generated method stub
		
			String[] Title={"企业名称","第三方单位名称","初查时间","初查人员","初查整改期限","初查整改负责人","复查时间","复查人员","复查意见","检查状态"};  
			String[] keys={"qyname","dwname","m2","m4","m3","m5","m8","m9","m10","m13"};
			List <Map<String, Object>> list=aqjgDsfJcjlDao.getExport(mapData);
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				Title = (mapData.get("coltext").toString()).split(",") ;
				keys = (mapData.get("colval").toString()).split(",") ;
		}
			new ExportExcel("第三方检查记录表.xls", Title, keys,list, response, true);
			
		
		}
	
	//根据id获得现场检查记录word表数据
	public Map<String, Object> getJcjlWord(long id){
		//根据id获取检查记录
		Map<String, Object> mapret = aqjgDsfJcjlDao.getWord(id);
		Map<String, Object> map=new HashMap<String, Object>();
		//第三方单位
		if(mapret.get("dwname")!=null&&!mapret.get("dwname").toString().equals("")){
			String a = mapret.get("dwname").toString();
			map.put("dsfdw", a);
		}
		//检查企业
		if(mapret.get("qyname")!=null&&!mapret.get("qyname").toString().equals("")){
			String a = mapret.get("qyname").toString();
			map.put("qyname", a);
		}
		//检查起始时间
		if(mapret.get("m2")!=null&&!mapret.get("m2").toString().equals("")){
			String date=mapret.get("m2").toString();
			String[] d=date.split(" ");
			map.put("jcsj", d[0]);
		}else{
			map.put("jcsj", "无");
		}
		//整改期限
		if(mapret.get("m3")!=null&&!mapret.get("m3").toString().equals("")){
			String date=mapret.get("m3").toString();
			String[] d=date.split(" ");
			map.put("zgqx", d[0]);
		}else{
			map.put("zgqx", "");
		}
		//检查人员
		if(mapret.get("m4")!=null&&!mapret.get("m4").toString().equals("")){
			String a = mapret.get("m4").toString();
			map.put("jcry", a);
		}
		//整改负责人
		if(mapret.get("m5")!=null&&!mapret.get("m5").toString().equals("")){
			String a = mapret.get("m5").toString();
			map.put("fzr", a);
		}
		//复查时间
		if(mapret.get("m8")!=null&&!mapret.get("m8").toString().equals("")){
			String date=mapret.get("m8").toString();
			String[] d=date.split(" ");
			map.put("fcsj", d[0]);
		}else{
			map.put("fcsj", "");
		}
		//复查人员
		if(mapret.get("m9")!=null&&!mapret.get("m9").toString().equals("")){
			String a = mapret.get("m9").toString();
			map.put("fcry", a);
		}else{
			map.put("fcry", "");
		}

		//检查情况
		String jcyj = "";
		String fcqk = "";
		int i = 1;
		//根据检查记录id获取检查问题list
		List <AQJD_DSFCheckContentEntity> nrlist= aqjgDsfJcjlDao.getNr(id);
		for (AQJD_DSFCheckContentEntity scc : nrlist) {
			jcyj = jcyj+i+"： "+scc.getM1()+"<w:p></w:p>";
			fcqk = fcqk+i+"： "+scc.getM3()+"<w:p></w:p>";
				i++;
		}
		map.put("jcyj", jcyj);
		map.put("fcqk", fcqk);
		
		return map;
	}
	
	}

