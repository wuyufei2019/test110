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

import com.cczu.model.dao.IBisCkxxDao;
import com.cczu.model.entity.BIS_StorageEntity;
import com.cczu.model.service.IBisCkxxService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("BisCkxxService")
public class BisCkxxServiceImpl implements IBisCkxxService {
	
	@Resource
	private IBisCkxxDao bisCkxxDao;

	@Override
	public List<BIS_StorageEntity> findAll(long qyid) {
		// TODO Auto-generated method stub
		return bisCkxxDao.findAll(qyid);
	}

	@Override
	public void addInfo(BIS_StorageEntity bis) {
		// TODO Auto-generated method stub
		bisCkxxDao.addInfo(bis);
		
	}

	@Override
	public void updateInfo(BIS_StorageEntity bis) {
		bisCkxxDao.updateInfo(bis);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		bisCkxxDao.deleteInfo(id);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_StorageEntity> list=bisCkxxDao.dataGrid(mapData);
		int getTotalCount=bisCkxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public BIS_StorageEntity findById(Long id) {
		// TODO Auto-generated method stub
		return bisCkxxDao.findById(id);
	}

	@Override
	public Map<String, Object> findById2(Long id) {
		// TODO Auto-generated method stub
		return bisCkxxDao.findById2(id);
	}

	
	@Override
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="仓库信息表.xls";
		List<Map<String, Object>> list=bisCkxxDao.getExport(mapData);
		
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}
	
	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=bisCkxxDao.dataGrid2(mapData);
		int getTotalCount=bisCkxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> dataGridAJ(Map<String, Object> map) {
		List<Map<String, Object>> list=bisCkxxDao.dataGridAJ(map);
		int getTotalCount=bisCkxxDao.getTotalCountAJ(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("rows", list);
		map2.put("total", getTotalCount);
		return map2;
	}  
	
	@Override
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
					BIS_StorageEntity ws = new BIS_StorageEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					ws.setS1(t);
					ws.setS2(t);
					ws.setS3(0);
					ws.setID1(Long.valueOf(map.get("qyid").toString()));
					ws.setM1(bis.get(0).toString());
					ws.setM2(bis.get(1).toString());
					ws.setM8(Double.parseDouble(bis.get(2).toString()));
					ws.setM3(Double.parseDouble(bis.get(3).toString()));
					if(bis.get(4)!=null&&bis.get(4).toString()!=""){
						String m6=bis.get(4).toString();
						switch (m6) {			
						case "一层":ws.setM6(1);break;
						case "二层":ws.setM6(2);break;
						case "三层":ws.setM6(3);break;
						case "四层":ws.setM6(4);break;
						case "五层":ws.setM6(5);break;
						case "六层":ws.setM6(6);break;
						case "七层":ws.setM6(7);break;
						case "八层":ws.setM6(8);break;
						case "九层":ws.setM6(9);break;
						case "十层":ws.setM6(10);break;
						case "十一层":ws.setM6(11);break;
						case "十二层":ws.setM6(12);break;
						case "十三层":ws.setM6(13);break;
						case "十四层":ws.setM6(14);break;
						case "十五层":ws.setM6(15);break;
						case "十六层":ws.setM6(16);break;
						case "十七层":ws.setM6(17);break;
						case "十八层":ws.setM6(18);break;
						case "十九层":ws.setM6(19);break;
						case "二十层":ws.setM6(20);break;
						default:
							break;
						}
					}
					
					if(bis.get(5)!=null&&bis.get(5).toString()!=""){
						String m5=bis.get(5).toString();
						switch (m5) {
						case "钢混结构":ws.setM5("0");break;
						case "砖混":ws.setM5("1");break;
						case "钢结构":ws.setM5("2");break;
						case "框架":ws.setM5("3");break;
						case "其他":ws.setM5("4");break;
						default:
							break;
						}
					}

					if(bis.get(6)!=null&&bis.get(6).toString()!=""){
						String m4=bis.get(6).toString();
						switch (m4) {
						case "甲类":ws.setM4("1");break;
						case "乙类":ws.setM4("2");break;
						case "丙类":ws.setM4("3");break;
						case "丁类":ws.setM4("4");break;
						case "戊类":ws.setM4("5");break;
						default:
							break;
						}
					}
					
					if(bis.get(7)!=null&&bis.get(7).toString()!=""){
						String m9=bis.get(7).toString();
						switch (m9) {
						case "一级":ws.setM9("1");break;
						case "二级":ws.setM9("2");break;
						case "三级":ws.setM9("3");break;
						case "四级":ws.setM9("4");break;
						default:
							break;
						}
					}
					
					ws.setM10(bis.get(8).toString());	
					ws.setM7(bis.get(9).toString());
					ws.setM13(bis.get(10).toString());// 所属仓库区
					bisCkxxDao.addInfo(ws);
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
	public Map<String, Object> statistics(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bisCkxxDao.statistics(map);
	}

	@Override
	public List<Map<String, Object>> findListByMap(Map<String, Object> map) {
		return bisCkxxDao.findListByMap(map);
	}

}


