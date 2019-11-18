package com.cczu.model.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisCkxxDao;
import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.model.dao.IFmewWdytylDao;
import com.cczu.model.dao.TsWarningDataDao;
import com.cczu.model.entity.BIS_StorageEntity;
import com.cczu.model.service.IFmewWdytylService;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("fmewWdytylService")
public class FmewWdytylServiceImpl implements IFmewWdytylService {
	@Resource
	private IFmewWdytylDao fmewWdytylDao;
	@Resource
	private IBisQyjbxxDao bisQyjbxx;
	@Resource
	private IBisCkxxDao bisCkxxDao; 
	@Resource
	private TsWarningDataDao tsWarningDataDao;

	@Override
	public  Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=fmewWdytylDao.dataGrid(mapData);
		int getTotalCount=fmewWdytylDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return fmewWdytylDao.getTotalCount(mapData);
	}

	@Override
	public List<Map<String, Object>> findInfoById(long id) {
		// TODO Auto-generated method stub
		return fmewWdytylDao.findInfoById(id);
	}

	@Override
	public List<Map<String, Object>> findCangKuInfoByQyid(long qyid) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> result=new ArrayList<>();
		List<BIS_StorageEntity> cklist=bisCkxxDao.findAll(qyid);
		for(BIS_StorageEntity sto:cklist){
			Map<String, Object> map=new HashMap<>();
			List<Map<String, Object>> list=fmewWdytylDao.findKcInfoByCkid(sto.getID());
			if(list!=null&&list.size()>0){
				map.put(sto.getM1(), list);
				result.add(map);
			}
		}
		return result;
	}

 

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		 	
		String[] title={"企业名称","储罐位号","物料名称","储罐类型","容积(m³)","罐高(m)","罐径(m)","实时储量(t)","液位(m)","报警阈值（上）","报警阈值（下）","最大储量(t)","储量比例"}; 
		String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","m12","m13"};
		String fileName="实时数据.xls";
		List<Map<String, Object>> list=fmewWdytylDao.getExcel(mapData);
		for(Map<String, Object> map:list){
			
			if(map.get("m4")==null||map.get("m4").equals("")){
				map.put("m4", "仓库");
			}else if(map.get("m4").equals("1")){
				map.put("m4", "立式圆筒形储罐");
			}else if(map.get("m4").equals("2")){
				map.put("m4", "卧式圆筒形储罐");
			}else if(map.get("m4").equals("3")){
				map.put("m4", "球形储罐");
			}else if(map.get("m4").equals("4")){
				map.put("m4", "其他储罐");
			} 
			
		}
		new ExportExcel(fileName, title, keys, list, response, true);
	}

//	@Override
//	public void findLastOverData() {
//		// TODO Auto-generated method stub
//		List<Map<String, Object>> list=fmewWdytylDao.findLastOverData();
//		
//		if(list!=null&&list.size()>0){			
//			//将超标数据插入到报警数据表
//			TS_WarningData warningData=null;
//			for (Map<String, Object> map : list) {
//				warningData=new TS_WarningData();
//				warningData.setType(1);
//				warningData.setID1(Long.parseLong(""+map.get("ID1"))); 
//				System.out.println(map.get("acceptdatetime"));
//				warningData.setAcceptDatetime(Timestamp.valueOf(""+map.get("acceptdatetime")));
//				warningData.setData1(Float.parseFloat(""+map.get("data1")));
//				if(map.get("minlmt")!=null)
//					warningData.setMin(Float.parseFloat(""+map.get("minlmt")));
//				if(map.get("maxlmt")!=null)
//					warningData.setMax(Float.parseFloat(""+map.get("maxlmt")));
//				tsWarningDataDao.save(warningData);
//			}
//		}
//	}

}
