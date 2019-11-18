package com.cczu.model.sbssgl.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.sbssgl.dao.SBSSGLNdbbDao;
import com.cczu.model.sbssgl.dao.SBSSGLSbgxDao;
import com.cczu.model.sbssgl.entity.SBSSGL_SBGXEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.dao.DepartmentDao;
import com.cczu.sys.system.entity.Department;

/**
 * 设备设施管理-设备更新Service
 *
 */
@Service("SBSSGLSbgxService")
public class SBSSGLSbgxService {

	@Resource
	private SBSSGLSbgxDao sBSSGLSbgxDao;
	@Autowired
	private IBisQyjbxxDao bisQyjbxx;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private SBSSGLNdbbDao sBSSGLNdbbDao;
	
	/**
	 * list
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=sBSSGLSbgxDao.dataGrid(mapData);
		int getTotalCount=sBSSGLSbgxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 删除设备大修信息
	 * @param id
	 */
	public void deleteInfoById(long id) {
		sBSSGLSbgxDao.deleteById(id);
	}
	
	/**
	 * 根据id查找设备大修信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findById(Long id) {
		return sBSSGLSbgxDao.findById(id);
	}
	
	public SBSSGL_SBGXEntity find(Long id) {
		return sBSSGLSbgxDao.find(id);
	}
	
	/**
	 * 添加设备大修信息
	 * @param entity
	 */
	public void addInfo(SBSSGL_SBGXEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS1(t);
		entity.setS2(t);
		entity.setS3(0);
		sBSSGLSbgxDao.save(entity);
	}

	/**
	 * 更新设备大修信息
	 * @param entity
	 */
	public void updateInfo(SBSSGL_SBGXEntity entity) {
		Timestamp t=DateUtils.getSysTimestamp();
		entity.setS2(t);
		sBSSGLSbgxDao.save(entity);
	}
	
	/**
	 * 修改上传状态
	 * @param qyid
	 */
	public void updStatus(Long id) {
		sBSSGLSbgxDao.updStatus(id);
	}
	
	/**
	 * 导入设备信息
	 * @param response
	 * @param mapData
	 */
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		String year = "";
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, error = 0;
		if (list.size() > 4) {
			result = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=3){ //跳过前四行
					if (i == 0) {
						year = bis.get(0).toString().substring(0, 4);
					}
					i++;
					continue;
				}
				try{
					SBSSGL_SBGXEntity sbdx = new SBSSGL_SBGXEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					sbdx.setS1(t);
					sbdx.setS2(t);
					sbdx.setS3(0);
					sbdx.setM1(bis.get(1).toString());
					sbdx.setM2(bis.get(2).toString());
					sbdx.setM3(bis.get(3).toString());
					
					Department dept = departmentDao.findInfoByName(bis.get(4).toString());
					sbdx.setM4(dept.getId());
					sbdx.setQyid(dept.getId2());
					
					if (bis.get(5) != null && bis.get(5) != "") {
						String m5 = bis.get(5).toString();
						sbdx.setM5(m5.substring(0, m5.length()-2));
					}
					if (bis.get(6) != null && bis.get(6) != "") {
						String m6 = bis.get(6).toString();
						sbdx.setM6(m6.substring(0, m6.length()-2));
					}
					if (bis.get(7) != null && bis.get(7) != "") {
						String m7 = bis.get(7).toString();
						sbdx.setM7(m7.substring(0, m7.length()-2));
					}
					
					if (bis.get(8) != null && bis.get(8) != "") {
						sbdx.setM8("0");
					}
					if (bis.get(9) != null && bis.get(9) != "") {
						sbdx.setM8("1");
					}
					if (bis.get(10) != null && bis.get(10) != "") {
						sbdx.setM9(bis.get(10).toString());
					}
					if (bis.get(11) != null && bis.get(11) != "") {
						sbdx.setM10(bis.get(11).toString());
					}
					
					sbdx.setM11(bis.get(12).toString());
					sbdx.setM14(year);
					
					sBSSGLSbgxDao.save(sbdx);
					result++;
				}catch(Exception e){
					e.printStackTrace();
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
	
	/**
	 * 导出普通设备excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData, HttpServletRequest request) {
		String fileName="设备更新计划单.xls";
		List<Map<String, Object>> list=sBSSGLSbgxDao.getExport(mapData);
		for (Map<String, Object> map : list) {
			if (map.get("m8") != null) {
				String m8 = map.get("m8").toString();
				if ("0".equals(m8)) {
					map.put("m8", "替换");
				} else if ("1".equals(m8)) {
					map.put("m8", "新增");
				}
			}
			if (map.get("m12") != null) {
				String m12 = map.get("m12").toString();
				if ("0".equals(m12)) {
					map.put("m12", "未完成");
				} else if ("1".equals(m12)) {
					map.put("m12", "已完成");
				}
			}
		}
		
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true, new String[]{"m13", "m15"}, request);
	}
	
	/**
	 * 获取大项修计划附件集合
	 * @param response
	 * @param mapData
	 */
	public List<Map<String,Object>> exportFj(Map<String, Object> mapData) {
		Map<String, Object> map = new HashMap<>();
		map.put("m1", mapData.get("m14"));
		map.put("deptid", mapData.get("m4"));
		map.put("type", "1");//设备大项修
		List<Map<String,Object>> fjList = sBSSGLNdbbDao.findFjListByMap(map);
		return fjList;
	}
}
