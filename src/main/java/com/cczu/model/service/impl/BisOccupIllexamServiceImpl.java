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

import com.cczu.model.dao.IBisOccupIllexamDao;
import com.cczu.model.entity.BIS_OccupIllexamEntity;
import com.cczu.model.service.IBisOccupIllexamService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("BisOccupIllexamService")
public class BisOccupIllexamServiceImpl implements IBisOccupIllexamService {
	@Resource
	private IBisOccupIllexamDao bisOccupIllexamDao;
	
	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<BIS_OccupIllexamEntity> list = bisOccupIllexamDao.dataGrid(mapData);
		int getTotalCount = bisOccupIllexamDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public List<BIS_OccupIllexamEntity> findAll(Long qyid) {
		// TODO Auto-generated method stub
		return bisOccupIllexamDao.findAll(qyid);
	}

	@Override
	public void addInfo(BIS_OccupIllexamEntity bis) {
		// TODO Auto-generated method stub
		bisOccupIllexamDao.addInfo(bis);
	}

	@Override
	public BIS_OccupIllexamEntity findById(Long id) {
		// TODO Auto-generated method stub
		return bisOccupIllexamDao.findById(id);
	}

	@Override
	public void updateInfo(BIS_OccupIllexamEntity bis) {
		// TODO Auto-generated method stub
		bisOccupIllexamDao.updateInfo(bis);
	}

	@Override
	public void deleteInfo(Long id) {
		// TODO Auto-generated method stub
		bisOccupIllexamDao.deleteInfo(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
			String fileName="职业病体检信息表.xls"; 
			List<Map<String, Object>> list=bisOccupIllexamDao.getExport(mapData);
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
					BIS_OccupIllexamEntity zybtj = new BIS_OccupIllexamEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					zybtj.setS1(t);
					zybtj.setS2(t);
					zybtj.setS3(0);
					zybtj.setID1(Long.valueOf(map.get("qyid").toString()));
					zybtj.setM1(DateUtils.getTimestampFromStr(bis.get(0).toString()));
					zybtj.setM3(Integer.parseInt(bis.get(1).toString()));
					zybtj.setM2(bis.get(2).toString());
					zybtj.setM4(bis.get(3).toString());
					zybtj.setM5("");
					zybtj.setM6(bis.get(4).toString());
					bisOccupIllexamDao.addInfo(zybtj);
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
		public Map<String, Object> ajdataGrid(Map<String, Object> mapData) {
			
			List<Map<String, Object>> list=bisOccupIllexamDao.ajdataGrid(mapData);
			int getTotalCount=bisOccupIllexamDao.ajgetTotalCount(mapData);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", list);
			map.put("total", getTotalCount);
			return map;
		}
}
