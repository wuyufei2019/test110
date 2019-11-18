package com.cczu.model.service.impl;

import com.cczu.model.dao.impl.BisFieldSupplyDaoImpl;
import com.cczu.model.entity.BIS_FieldSupplyEntity;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.util.common.StringUtils;
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
 * 现场供气信息
 */
@Transactional(readOnly=true)
@Service("BisFieldSupplyService")
public class BisFieldSupplyServiceImpl {
	
	@Resource
	private BisFieldSupplyDaoImpl bisFieldSupplyDao;

	public List<BIS_FieldSupplyEntity> findAll(long qyid) {
		// TODO Auto-generated method stub
		return bisFieldSupplyDao.findAll(qyid);
	}

	public void addInfo(BIS_FieldSupplyEntity bis) {
		// TODO Auto-generated method stub
		bisFieldSupplyDao.addInfo(bis);
		
	}

	public void updateInfo(BIS_FieldSupplyEntity bis) {
		bisFieldSupplyDao.updateInfo(bis);
	}

	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bisFieldSupplyDao.deleteInfo(id);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_FieldSupplyEntity> list=bisFieldSupplyDao.dataGrid(mapData);
		int getTotalCount=bisFieldSupplyDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	public BIS_FieldSupplyEntity findById(Long id) {
		// TODO Auto-generated method stub
		return bisFieldSupplyDao.findById(id);
	}

	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="现场供气信息表.xls";
		List<Map<String, Object>> list=bisFieldSupplyDao.getExport(mapData);
		
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}
	
	public Map<String, Object> dataGridAJ(Map<String, Object> map) {
		List<Map<String, Object>> list=bisFieldSupplyDao.dataGridAJ(map);
		int getTotalCount=bisFieldSupplyDao.getTotalCountAJ(map);
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
					BIS_FieldSupplyEntity entity = new BIS_FieldSupplyEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					entity.setS1(t);
					entity.setS2(t);
					entity.setS3(0);
					entity.setId1(Long.valueOf(map.get("qyid").toString()));
					entity.setM1(bis.get(0).toString());
					if(StringUtils.isNotBlank(bis.get(1).toString())){

						entity.setM2(Float.valueOf(bis.get(1).toString()));
					}
					if(StringUtils.isNotBlank(bis.get(2).toString())){

						entity.setM3(Float.valueOf(bis.get(2).toString()));
					}
					if(bis.get(3)!=null&&bis.get(3).toString()!=""){
						String m2=bis.get(3).toString();
						switch (m2) {
							case "租用":entity.setM4(1);break;
							case "自建":entity.setM4(0);break;
							default:
								break;
						}
					}
					entity.setM5(bis.get(4).toString());
					entity.setM6_1(bis.get(5).toString());
					entity.setM7_1(bis.get(6).toString());
					if(StringUtils.isNotBlank(bis.get(7).toString())){
						entity.setM8_1(Timestamp.valueOf(bis.get(7).toString()));
					}
					entity.setM6_2(bis.get(8).toString());
					entity.setM7_2(bis.get(9).toString());
					if(StringUtils.isNotBlank(bis.get(10).toString())){
						entity.setM8_2(Timestamp.valueOf(bis.get(10).toString()));
					}
					entity.setM6_3(bis.get(11).toString());
					entity.setM7_3(bis.get(12).toString());
					if(StringUtils.isNotBlank(bis.get(13).toString())){
						entity.setM8_3(Timestamp.valueOf(bis.get(13).toString()));
					}
					entity.setM9(bis.get(14).toString());
					bisFieldSupplyDao.addInfo(entity);
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


