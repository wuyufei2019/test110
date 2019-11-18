package com.cczu.model.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.AqpxWlfpxHistoryDao;
import com.cczu.model.entity.AQPX_ExamresultsEntity;
import com.cczu.model.entity.AQPX_OutSourceEducationHistoryEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;

/**
 * @description 安全培训外来方培训记录Service
 * @author jason
 * @date 2018年1月27日
 */
@Transactional(readOnly=true)
@Service("AqpxWlfpxHistoryService")
public class AqpxWlfpxHistoryService {
	@Resource
	private AqpxWlfpxHistoryDao aqpxWlfpxHistoryDao;
	 
	/**
	 * 分页显示
	 * @param mapData
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=aqpxWlfpxHistoryDao.dataGrid(mapData);
		int getTotalCount=aqpxWlfpxHistoryDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public void addInfo(AQPX_OutSourceEducationHistoryEntity sjjy) {
		sjjy.setS1(DateUtils.getSystemTime());
		sjjy.setS2(DateUtils.getSystemTime());
		sjjy.setS3(0);
		aqpxWlfpxHistoryDao.save(sjjy);
	}
	 
	public void updateInfo(AQPX_OutSourceEducationHistoryEntity sjjy) {
		sjjy.setS2(DateUtils.getSystemTime());
		aqpxWlfpxHistoryDao.saveUp(sjjy);
	}
	
	public void deleteInfo(long id) {
		aqpxWlfpxHistoryDao.deleteInfo(id);
	}

	public AQPX_OutSourceEducationHistoryEntity findById(Long id) {
		return aqpxWlfpxHistoryDao.find(id);
	}
	
	
	/**
	 * 根据在线考试生成培训记录
	 * @param examresults 考试记录
	 */
	public void CreateHistoryByOnlineTest(AQPX_ExamresultsEntity examresults,HttpServletRequest request){
		HttpSession session=request.getSession();
		AQPX_OutSourceEducationHistoryEntity outhis=new AQPX_OutSourceEducationHistoryEntity();
		outhis.setID1(examresults.getID1());//企业id
		outhis.setM1((String)session.getAttribute("unit"));//外来方单位名称
		outhis.setM1_1((String)session.getAttribute("wlfname"));//身份证号
		outhis.setM1_2((String)session.getAttribute("idcard"));//所属单位
		outhis.setM1_3((String)session.getAttribute("post"));//岗位
		outhis.setM1_4((String)session.getAttribute("kind"));//培训种类
		outhis.setM2(DateUtils.getSystemTime());//培训时间
		outhis.setM3("外来方培训在线学习和考试");//培训内容
		outhis.setM7(examresults.getM1()+"");//考试成绩
		outhis.setM8(examresults.getM3());//考试结果
		outhis.setState("1");
		addInfo(outhis);
	}
	
	/**
	 * 导出 
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String[] title={"培训类别","外来方单位名称","姓名","身份证号","工作岗位","培训日期","培训内容","培训人员","教育人","培训方式","培训成绩","培训结果","备注" };  
		String[] keys={"m1_4","m1","m1_1","m1_2","m1_3","rq","m3","m4","m5","state","m7","m8","m9"};
		String fileName="外来方培训记录.xls";
		List<Map<String, Object>> list=aqpxWlfpxHistoryDao.getExport(mapData);
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
		String[] title={"企业名称","外来方单位名称","培训日期","培训内容","培训人员","教育人","培训成绩","培训结果","备注" };  
		String[] keys={"qyname","m1","rq","m3","m4","m5","m7","m8","m9"};
		String fileName="外来方培训记录.xls";
		List<Map<String, Object>> list=aqpxWlfpxHistoryDao.getExport(mapData);
		new ExportExcel(fileName, title, keys, list, response,true);

	}
	
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		int result = 0;
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0,  error = 0;
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
					AQPX_OutSourceEducationHistoryEntity pxjl = new AQPX_OutSourceEducationHistoryEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					pxjl.setS1(t);
					pxjl.setS2(t);
					pxjl.setS3(0);
					pxjl.setID1(Long.valueOf(map.get("qyid").toString()));
					pxjl.setM1(bis.get(0).toString());
					pxjl.setM1_1(bis.get(1).toString());
					pxjl.setM1_2(bis.get(2).toString());
					pxjl.setM1_3(bis.get(3).toString());
					pxjl.setM2(DateUtils.getTimestampFromStr(bis.get(4).toString()));
					pxjl.setM3(bis.get(9).toString());
					pxjl.setM4(bis.get(5).toString());
					pxjl.setM5(bis.get(6).toString());
					pxjl.setM7((bis.get(7).toString()==null||bis.get(7).toString()=="")?"0":bis.get(7).toString());
					pxjl.setM8(bis.get(8).toString());
					pxjl.setM9(bis.get(10).toString());

					aqpxWlfpxHistoryDao.save(pxjl);
					result++;
				}catch(Exception e){
					e.printStackTrace();
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
