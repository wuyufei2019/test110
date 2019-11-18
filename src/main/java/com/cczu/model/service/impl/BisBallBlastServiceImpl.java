package com.cczu.model.service.impl;

import com.cczu.model.dao.impl.BisBallBlastDaoImpl;
import com.cczu.model.entity.BIS_BallBlastEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抛丸信息
 */
@Transactional(readOnly=true)
@Service("BisBallBlastService")
public class BisBallBlastServiceImpl {
	
	@Resource
	private BisBallBlastDaoImpl bisBallBlastDao;

	public List<BIS_BallBlastEntity> findAll(long qyid) {
		// TODO Auto-generated method stub
		return bisBallBlastDao.findAll(qyid);
	}

	public void addInfo(BIS_BallBlastEntity bis) {
		// TODO Auto-generated method stub
		bisBallBlastDao.addInfo(bis);
		
	}

	public void updateInfo(BIS_BallBlastEntity bis) {
		bisBallBlastDao.updateInfo(bis);
	}

	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bisBallBlastDao.deleteInfo(id);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_BallBlastEntity> list=bisBallBlastDao.dataGrid(mapData);
		int getTotalCount=bisBallBlastDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public BIS_BallBlastEntity findById(Long id) {
		// TODO Auto-generated method stub
		return bisBallBlastDao.findById(id);
	}

	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="抛丸信息表.xls";
		List<Map<String, Object>> list=bisBallBlastDao.getExport(mapData);
		
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}
	
	public Map<String, Object> dataGridAJ(Map<String, Object> map) {
		List<Map<String, Object>> list=bisBallBlastDao.dataGridAJ(map);
		int getTotalCount=bisBallBlastDao.getTotalCountAJ(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("rows", list);
		map2.put("total", getTotalCount);
		return map2;
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
			resultmap.put("success",result);
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				 
				try{
					BIS_BallBlastEntity entity = new BIS_BallBlastEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					entity.setS1(t);
					entity.setS2(t);
					entity.setS3(0);
					entity.setId1(Long.valueOf(map.get("qyid").toString()));
					String m1 = bis.get(0).toString();
					if (m1.equals("立式"))
						entity.setM1(1);
					else if (m1.equals("卧式"))
						entity.setM1(2);
					else if (m1.equals("手动"))
						entity.setM1(3);
					if(StringUtils.isNotEmpty(bis.get(1).toString()))
						entity.setM2(Integer.valueOf(bis.get(1).toString()));
					if(StringUtils.isNotEmpty(bis.get(2).toString()))
						entity.setM3(Integer.valueOf(bis.get(2).toString()));
					entity.setM4(bis.get(3).toString());
					entity.setM5(bis.get(4).toString());
					entity.setM6(bis.get(5).toString());
					String m7 = bis.get(6).toString();
					if (m7.equals("有"))
						entity.setM7(0);
					else if (m7.equals("无"))
						entity.setM7(1);
					String m8 = bis.get(7).toString();
					if (m8.equals("有"))
						entity.setM8(0);
					else if (m8.equals("无"))
						entity.setM8(1);

					bisBallBlastDao.addInfo(entity);
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


