package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisOccupharmreportDao;
import com.cczu.model.entity.BIS_OccupharmExamReportEntity;
import com.cczu.model.service.IBisOccupharmreportService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("BisOccupharmreportService")
public class BisOccupharmreportServiceImpl implements IBisOccupharmreportService {
	
	@Resource
	private IBisOccupharmreportDao bisOccupharmreportDao;

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_OccupharmExamReportEntity> list = bisOccupharmreportDao.dataGrid(mapData);
		int getTotalCount = bisOccupharmreportDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public List<BIS_OccupharmExamReportEntity> findAll(Long qyid) {
		return bisOccupharmreportDao.findAll(qyid);
	}

	@Override
	public void addInfo(BIS_OccupharmExamReportEntity bis) {
		bisOccupharmreportDao.addInfo(bis);
	}

	@Override
	public BIS_OccupharmExamReportEntity findById(Long id) {
		return bisOccupharmreportDao.findById(id);
	}

	@Override
	public void updateInfo(BIS_OccupharmExamReportEntity bis) {
		bisOccupharmreportDao.updateInfo(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		bisOccupharmreportDao.deleteInfo(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
			String fileName="检测报告管理信息表.xls"; 
			List<Map<String, Object>> list=bisOccupharmreportDao.getExport(mapData);
			String[] title=mapData.get("coltext").toString().split(",");  
			String[] keys=mapData.get("colval").toString().split(",");  
			new ExportExcel(fileName, title, keys, list, response, true);
		}
		
	@Override
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
					BIS_OccupharmExamReportEntity jcbg = new BIS_OccupharmExamReportEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					jcbg.setS1(t);
					jcbg.setS2(t);
					jcbg.setS3(0);
					jcbg.setID1(Long.valueOf(map.get("qyid").toString()));
					jcbg.setM10(bis.get(0).toString());
					jcbg.setM11(bis.get(1).toString());
					jcbg.setM1(bis.get(2).toString());
					jcbg.setM2(DateUtils.getTimestampFromStr(bis.get(3).toString()));
					jcbg.setM3(bis.get(4).toString());
					jcbg.setM4(DateUtils.getTimestampFromStr(bis.get(5).toString()));
					jcbg.setM14(bis.get(6).toString());
					jcbg.setM6(bis.get(7).toString());
					bisOccupharmreportDao.addInfo(jcbg);
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
	
		@Override
		public Map<String, Object> dataGridAJ(Map<String, Object> mapData) {
			// TODO Auto-generated method stub
			List<Map<String, Object>> list=bisOccupharmreportDao.dataGridAJ(mapData);
			int getTotalCount=bisOccupharmreportDao.getTotalCountAJ(mapData);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", list);
			map.put("total", getTotalCount);
			return map;
		} 

	

}
