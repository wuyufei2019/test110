package com.cczu.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqpxSjjyHistoryDao;
import com.cczu.model.dao.impl.AqpxKcxxDaoImpl;
import com.cczu.model.dao.impl.BisYgxxDaoImpl;
import com.cczu.model.entity.AQPX_CourseEntity;
import com.cczu.model.entity.AQPX_ExamresultsEntity;
import com.cczu.model.entity.AQPX_ThreeLevelEducationHistoryEntity;
import com.cczu.model.entity.BIS_EmployeeEntity;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

/**
 * @description 安全培训三级教育记录Service
 * @author jason
 * @date 2018年1月24日
 */
@Transactional(readOnly=true)
@Service("AqpxSjjyHistoryService")
public class AqpxSjjyHistoryService {
	@Resource
	private AqpxSjjyHistoryDao aqpxSjjyHistoryDao;
	@Resource
	private BisYgxxDaoImpl ygxxDaoImpl;
	@Resource
	private AqpxKcxxDaoImpl aqpxKcxxDaoImpl;
	 
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=aqpxSjjyHistoryDao.dataGrid(mapData);
		int getTotalCount=aqpxSjjyHistoryDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	/**
	 * 分页查询（考试时长）
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGridStudyLength(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=aqpxSjjyHistoryDao.dataGridStudyLength(mapData);
		int getTotalCount=aqpxSjjyHistoryDao.getTotalCountStudyLength(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(AQPX_ThreeLevelEducationHistoryEntity sjjy) {
		sjjy.setS1(DateUtils.getSystemTime());
		sjjy.setS2(DateUtils.getSystemTime());
		sjjy.setS3(0);
		aqpxSjjyHistoryDao.save(sjjy);
	}
	 
	public void updateInfo(AQPX_ThreeLevelEducationHistoryEntity sjjy) {
		sjjy.setS2(DateUtils.getSystemTime());
		aqpxSjjyHistoryDao.saveUp(sjjy);
	}
	
	public void deleteInfo(long id) {
		aqpxSjjyHistoryDao.deleteInfo(id);
	}

	public AQPX_ThreeLevelEducationHistoryEntity findById(Long id) {
		return aqpxSjjyHistoryDao.find(id);
	}
	
	public AQPX_ThreeLevelEducationHistoryEntity findAllById2(Long id2) {
		return aqpxSjjyHistoryDao.findAllById2(id2);
	}
	
	public AQPX_ThreeLevelEducationHistoryEntity findByYgID(Long qyid,Long ygid) {
		List<PropertyFilter> filters = new ArrayList<>();
		filters.add(new PropertyFilter("EQL_ID1", qyid.toString()));
		filters.add(new PropertyFilter("EQL_ID2", ygid.toString()));
		filters.add(new PropertyFilter("EQI_S3", "0"));
		List<AQPX_ThreeLevelEducationHistoryEntity> list = aqpxSjjyHistoryDao.find(filters);
		aqpxSjjyHistoryDao.flush();
		aqpxSjjyHistoryDao.clear();
		if(list.size()>0)
		  return list.get(0);
		else
		 return	null;
	}
	
	/**
	 * 根据三级教育在线考试生成三级教育培训记录
	 * @param yhid  用户id
	 * @param examresults 考试记录
	 * @param kc 课程信息
	 */
	public void CreateHistoryByOnlineTest(long yhid, AQPX_ExamresultsEntity examresults,AQPX_CourseEntity kc){
		List<BIS_EmployeeEntity> list=ygxxDaoImpl.findBy("ID1", yhid);
		BIS_EmployeeEntity yg=null;
		AQPX_ThreeLevelEducationHistoryEntity sjjy=null;
		if(list.size()>0){//判断该用户是否有对应的员工档案信息
			yg=list.get(0);
			//查询该用户是否有对应的三级教育培训信息，没有则新增，有则修改成绩
			sjjy=findByYgID(examresults.getID1(), yg.getID());
			if(sjjy==null){
				sjjy=new AQPX_ThreeLevelEducationHistoryEntity();
				sjjy.setState("1");//线上培训
				sjjy.setID1(examresults.getID1());
				sjjy.setID2(yg.getID());
				sjjy.setM1(kc.getM1());
				sjjy.setM2(DateUtils.getSystemTime());
				sjjy.setM3("三级安全教育培训在线学习和考试");
				sjjy.setM7(examresults.getM1());//考试成绩
				sjjy.setM8(examresults.getM3());//考试结果
				addInfo(sjjy);
			}else{
				sjjy.setM2(DateUtils.getSystemTime());
				sjjy.setM7(examresults.getM1());//考试成绩
				sjjy.setM8(examresults.getM3());//考试结果
				updateInfo(sjjy);
			}
		}
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"姓名","性别","身份证号","出生日期","所属部门","课程名称","培训时间","考试成绩","培训结果","厂级教育人","车间教育人","班组教育人","培训内容","备注" };  
		String[] keys={"name","sex","idcard","birthday","dep","m1","rq","m7","m8","m4","m5","m6","m3","m9"};
		 
		String fileName="三级教育培训记录.xls";
		List<Map<String, Object>> list=aqpxSjjyHistoryDao.getExport(mapData);
		new ExportExcel(fileName, title, keys, list, response,true);

	}
	
	/**
	 * 导出 (集团)
	 * @param response
	 * @param mapData
	 */
	public void exportExcel2(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"企业名称","姓名","性别","身份证号","出生日期","所属部门","课程名称","培训时间","考试成绩","培训结果","厂级教育人","车间教育人","班组教育人","培训内容","备注" };  
		String[] keys={"qyname","name","sex","idcard","birthday","dep","m1","rq","m7","m8","m4","m5","m6","m3","m9"};
		 
		String fileName="三级教育培训记录.xls";
		List<Map<String, Object>> list=aqpxSjjyHistoryDao.getExport(mapData);
		new ExportExcel(fileName, title, keys, list, response,true);

	}
	
	/**
	 * 统计页面跳转
	 * @param id,model
	 */
	public Map<String, Object> statistics(Map<String, Object> map) {
		Map<String, Object> statisticsData = aqpxSjjyHistoryDao.statistics(map);
		if(statisticsData!=null){
			statisticsData.put("wpx", Integer.parseInt(statisticsData.get("zry").toString())-Integer.parseInt(statisticsData.get("ypx").toString()));
		}
		return statisticsData;
	}
	
	/**
	 * 导出三级教育卡
	 * @param response
	 * @param mapData
	 */
	public Map<String, Object> exportCard(long id) {
		Map<String, Object> map=aqpxSjjyHistoryDao.getExportCard(id);
		return map;
	}
}
