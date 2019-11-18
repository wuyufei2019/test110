package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.BisAqssDao;
import com.cczu.model.entity.BIS_SafetyFacilitiesEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;

/**
 *  安全设施Service
 *
 */
@Transactional(readOnly=true)
@Service("BisAqssService")
public class BisAqssService {

	@Resource
	private BisAqssDao bisAqssDao;
	
	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<BIS_SafetyFacilitiesEntity> list=bisAqssDao.dataGrid(mapData);
		int getTotalCount=bisAqssDao.getTotalCount(mapData);
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
		
		List<Map<String,Object>> list=bisAqssDao.dataGrid2(mapData);
		int getTotalCount=bisAqssDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	//添加信息
	public void addInfo(BIS_SafetyFacilitiesEntity scsb) {
		Timestamp t=DateUtils.getSysTimestamp();
		scsb.setS1(t);
		scsb.setS2(t);
		scsb.setS3(0);
		bisAqssDao.save(scsb);
	}
	
	public long addInfoReturnID(BIS_SafetyFacilitiesEntity scsb) {
		Timestamp t=DateUtils.getSysTimestamp();
		scsb.setS1(t);
		scsb.setS2(t);
		scsb.setS3(0);
		bisAqssDao.save(scsb);
		return scsb.getID();
	}
	
	//更新信息
	public void updateInfo(BIS_SafetyFacilitiesEntity scsb) {
		Timestamp t=DateUtils.getSysTimestamp();
		scsb.setS2(t);
		scsb.setS3(0);
		bisAqssDao.save(scsb);
	}
	
	//删除信息
	public void deleteInfo(long id) {
		bisAqssDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public BIS_SafetyFacilitiesEntity findById(Long id) {
		return bisAqssDao.find(id);
	}
	
	//获取设备类别list
	public List<Map<String, Object>> findSblbList() {
		List<Map<String, Object>> sblbList = bisAqssDao.findSblbList();
		return sblbList;
	}
	
	//获取设备名称list
	public List<Map<String, Object>> findSbmcList(String sblb) {
		List<Map<String, Object>> sbmcList = bisAqssDao.findSbmcList(sblb);
		return sbmcList;
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="安全设施信息表.xls";
		List<Map<String, Object>> list=bisAqssDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}
	
	//导入
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
					BIS_SafetyFacilitiesEntity scsb = new BIS_SafetyFacilitiesEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					scsb.setS1(t);
					scsb.setS2(t);
					scsb.setS3(0);
					scsb.setID1(Long.valueOf(map.get("qyid").toString()));
					scsb.setM9(bis.get(0).toString());
					scsb.setM1(bis.get(1).toString());
					scsb.setM2(bis.get(2).toString());
					scsb.setM3(bis.get(3).toString());
					scsb.setM4(StringUtils.toInteger(bis.get(4)));
					if(bis.get(5).toString()!=null&&!bis.get(5).toString().equals(""))
					scsb.setM5(DateUtils.getTimestampFromStr(bis.get(5).toString()) );
					if(bis.get(6).toString()!=null&&!bis.get(6).toString().equals(""))
					scsb.setM6(DateUtils.getTimestampFromStr(bis.get(6).toString()));
					
					if(bis.get(7).toString().equals("失效"))
						scsb.setM7(1);
					else if(bis.get(7).toString().equals("停用"))
						scsb.setM7(2);
					else if(bis.get(7).toString().equals("正常"))
						scsb.setM7(0);
					scsb.setM8(bis.get(7).toString());
					
					bisAqssDao.save(scsb);
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
