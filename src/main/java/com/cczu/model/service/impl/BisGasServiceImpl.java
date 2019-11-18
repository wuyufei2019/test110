package com.cczu.model.service.impl;

import com.cczu.model.dao.impl.BisGasDaoImpl;
import com.cczu.model.entity.BIS_GasEntity;
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
 * 燃气
 */
@Transactional(readOnly=true)
@Service("BisGasService")
public class BisGasServiceImpl {
	
	@Resource
	private BisGasDaoImpl bisGasDao;

	public List<BIS_GasEntity> findAll(long qyid) {
		// TODO Auto-generated method stub
		return bisGasDao.findAll(qyid);
	}

	public void addInfo(BIS_GasEntity bis) {
		// TODO Auto-generated method stub
		bisGasDao.addInfo(bis);
		
	}

	public void updateInfo(BIS_GasEntity bis) {
		bisGasDao.updateInfo(bis);
	}

	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bisGasDao.deleteInfo(id);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_GasEntity> list=bisGasDao.dataGrid(mapData);
		int getTotalCount=bisGasDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public BIS_GasEntity findById(Long id) {
		// TODO Auto-generated method stub
		return bisGasDao.findById(id);
	}

	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="燃气信息表.xls";
		List<Map<String, Object>> list=bisGasDao.getExport(mapData);
		
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}
	
	public Map<String, Object> dataGridAJ(Map<String, Object> map) {
		List<Map<String, Object>> list=bisGasDao.dataGridAJ(map);
		int getTotalCount=bisGasDao.getTotalCountAJ(map);
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
					BIS_GasEntity ws = new BIS_GasEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					ws.setS1(t);
					ws.setS2(t);
					ws.setS3(0);
					ws.setId1(Long.valueOf(map.get("qyid").toString()));
					ws.setM1(bis.get(0).toString());
					ws.setM2(bis.get(1).toString());
					if(StringUtils.isNotEmpty(bis.get(2).toString()))
						ws.setM3(Integer.valueOf(bis.get(2).toString()));
					if(StringUtils.isNotEmpty(bis.get(3).toString()))
						ws.setM4(Float.valueOf(bis.get(3).toString()));
					if(StringUtils.isNotEmpty(bis.get(4).toString()))
						ws.setM5(Integer.valueOf(bis.get(4).toString()));
					if(StringUtils.isNotEmpty(bis.get(5).toString()))
						ws.setM6(Float.valueOf(bis.get(5).toString()));
					if(StringUtils.isNotEmpty(bis.get(6).toString()))
						ws.setM7(Float.valueOf(bis.get(6).toString()));

					bisGasDao.addInfo(ws);
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


