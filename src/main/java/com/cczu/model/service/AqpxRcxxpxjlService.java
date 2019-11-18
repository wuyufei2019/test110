package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.system.entity.Department;
import com.cczu.sys.system.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AaqpxRcxxpxjlDao;
import com.cczu.model.entity.AQPX_RcxxpxjlEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;


@Transactional(readOnly=true)
@Service("AqpxRcxxpxjlService")
public class AqpxRcxxpxjlService {
	
	@Resource
	private AaqpxRcxxpxjlDao aqpxRcxxpxjlDao;
	

	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=aqpxRcxxpxjlDao.dataGrid(mapData);
		int getTotalCount=aqpxRcxxpxjlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	/**
	 * 添加信息
	 * @param sjjy
	 */
	public void addInfo(AQPX_RcxxpxjlEntity sjjy) {
		sjjy.setS1(DateUtils.getSystemTime());
		sjjy.setS2(DateUtils.getSystemTime());
		sjjy.setS3(0);
		aqpxRcxxpxjlDao.save(sjjy);
	}

	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteInfo(long id) {
		aqpxRcxxpxjlDao.deleteInfo(id);
	}

	/**
	 * 按id获取对象
	 * 
	 * @param id
	 * @return
	 */
	public AQPX_RcxxpxjlEntity findById(Long id) {
		return aqpxRcxxpxjlDao.find(id);
	}

	
	/**
	 * 修改信息
	 * @param sjjy
	 */
	public void updateInfo(AQPX_RcxxpxjlEntity sjjy) {
		sjjy.setS2(DateUtils.getSystemTime());
		 aqpxRcxxpxjlDao.saveUp(sjjy);
	}

	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"培训类别","姓名","工作岗位","培训日期","培训内容","培训人员","培训方式","培训成绩","培训结果","备注" };  
		String[] keys={"m1_4","m1_1","m1_3","rq","m3","m4","state","m7","m8","m9"};
		String fileName="日常线下培训记录.xls";
		List<Map<String, Object>> list=aqpxRcxxpxjlDao.getExport(mapData);
		for (Map<String, Object> map : list) {
			String state = map.get("state").toString();
			if (StringUtils.isNotEmpty(state)) {
				if ("1".equals(state)) {
					map.put("state", "线上");
				} else if("2".equals(state)){
					map.put("state", "线下");
				} 
			}
		}
		new ExportExcel(fileName, title, keys, list, response,true);

	}
	
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel2(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"企业名称","培训类别","姓名","工作岗位","培训日期","培训内容","培训人员","培训方式","培训成绩","培训结果","备注" };  
		String[] keys={"qyname","m1_4","m1_1","m1_3","rq","m3","m4","state","m7","m8","m9"};
		String fileName="日常线下培训记录.xls";
		List<Map<String, Object>> list=aqpxRcxxpxjlDao.getExport(mapData);
		new ExportExcel(fileName, title, keys, list, response,true);

	}

	/**
	 * 导入员工日常线下培训记录
	 * @param map
	 * @return
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
					AQPX_RcxxpxjlEntity em = new AQPX_RcxxpxjlEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					em.setS1(t);
					em.setS2(t);
					em.setS3(0);
					em.setState("2");
					em.setID1(Long.parseLong(map.get("qyid").toString()));
					em.setM1_4(bis.get(0).toString());//培训类别
					em.setM1_1(bis.get(1).toString());//姓名
					em.setM1_3(bis.get(2).toString());//工作岗位
					em.setM2(com.cczu.util.common.StringUtils.isBlank(bis.get(3).toString())?null:Timestamp.valueOf(bis.get(3).toString()+" 00:00:00"));//培训时间
					em.setM4(bis.get(4).toString());//培训人员
					em.setM7(bis.get(5).toString());//考试成绩
					em.setM8(bis.get(6).toString());//培训结果
					em.setM3(bis.get(7).toString());//培训结果

					//添加员工信息
					aqpxRcxxpxjlDao.save(em);
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
	
	
	
	

}
