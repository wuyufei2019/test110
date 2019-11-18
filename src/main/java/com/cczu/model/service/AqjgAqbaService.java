package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqjgAqbaDao;
import com.cczu.model.entity.AQJG_SafetyRecord;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 *  安全备案Service
 *
 */
@Transactional(readOnly=true)
@Service("AqjgAqbaService")
public class AqjgAqbaService {

	@Resource
	private AqjgAqbaDao aqjgAAqbaDao;

	/**
	 * tab页分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {

		List<Map<String,Object>> list=aqjgAAqbaDao.dataGrid(mapData);
		int getTotalCount=aqjgAAqbaDao.getTotalCount(mapData);
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

		List<Map<String,Object>> list=aqjgAAqbaDao.dataGrid2(mapData);
		int getTotalCount=aqjgAAqbaDao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	//添加信息
	public void addInfo(AQJG_SafetyRecord aqjg) {
		Timestamp t=DateUtils.getSysTimestamp();
		aqjg.setS1(t);
		aqjg.setS2(t);
		aqjg.setS3(0);
		aqjgAAqbaDao.save(aqjg);
	}

	public long addInfoReturnID(AQJG_SafetyRecord aqjg) {
		Timestamp t=DateUtils.getSysTimestamp();
		aqjg.setS1(t);
		aqjg.setS2(t);
		aqjg.setS3(0);
		aqjgAAqbaDao.save(aqjg);
		return aqjg.getID();
	}

	//更新信息
	public void updateInfo(AQJG_SafetyRecord aqjg) {
		Timestamp t=DateUtils.getSysTimestamp();
		aqjg.setS2(t);
		aqjg.setS3(0);
		aqjgAAqbaDao.save(aqjg);
	}

	//删除信息
	public void deleteInfo(long id) {
		aqjgAAqbaDao.deleteInfo(id);
	}

	//根据id查找详细信息
	public AQJG_SafetyRecord findById(Long id) {
		return aqjgAAqbaDao.find(id);
	}

	//获取企业list
	public List<Map<String, Object>> findQynmList(String xzqy,String type,Integer jglx) {
		List<Map<String, Object>> qynmList = aqjgAAqbaDao.findQynmList(xzqy,type,jglx);
		return qynmList;
	}

	//根据id查询详细信息
	public Map<String, Object> findInfoById(long id) {
		return aqjgAAqbaDao.findInfoById(id);
	}

	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="安全备案信息表.xls";
		List<Map<String, Object>> list=aqjgAAqbaDao.getExport(mapData);
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"备案类别","备案日期","备案经手人","备案意见","备注"};
			String[] keys={"m3","m2","m7","m5","m8"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = (mapData.get("coltext").toString()).split(",") ;
				keys = (mapData.get("colval").toString()).split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","备案类别","备案日期","备案经手人","备案意见","备注"};
			String[] keys={"qyname","m3","m2","m7","m5","m8"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
				keys = ("qyname,"+mapData.get("colval").toString()).split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response, true);
		}
	}

	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel2(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName="评价报告信息表.xls";
		List<Map<String, Object>> list=aqjgAAqbaDao.getExport2(mapData);
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"备案类别","备案日期","备案经手人","备案意见","备注"};
			String[] keys={"label","m2","m7","m5","m8"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = (mapData.get("coltext").toString()).split(",") ;
				keys = (mapData.get("colval").toString()).split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","备案类别","备案日期","备案经手人","备案意见","备注"};
			String[] keys={"qyname","label","m2","m7","m5","m8"};
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
					AQJG_SafetyRecord aqba = new AQJG_SafetyRecord();
					Timestamp t = DateUtils.getSysTimestamp();
					aqba.setS1(t);
					aqba.setS2(t);
					aqba.setS3(0);
					aqba.setID1(Long.valueOf(map.get("qyid").toString()));
					aqba.setM3(bis.get(0).toString());
					aqba.setM1(bis.get(1).toString());//生成备案编号
					aqba.setM2(DateUtils.getTimestampFromStr(bis.get(2).toString()));
					//aqba.setM4(0);
					aqba.setM7(bis.get(3).toString());
					aqba.setM8(bis.get(4).toString());
					aqjgAAqbaDao.save(aqba);
					result++;
				}catch(Exception e){
					error++;
				}
			}
			resultmap.put("success",result);
			resultmap.put("error", error);
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

	public String getqylistapp(Map<String, Object> mapData) {
		return JsonMapper.toJsonString(aqjgAAqbaDao.getqylistapp(mapData));
	}
}
