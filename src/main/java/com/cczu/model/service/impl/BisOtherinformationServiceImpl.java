package com.cczu.model.service.impl;

import com.cczu.model.dao.IBisOtherinformationDao;
import com.cczu.model.entity.BIS_OtherinformationEntity;
import com.cczu.model.service.IBisOtherinformationService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.comm.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("BisOtherinformationService")
public class BisOtherinformationServiceImpl implements IBisOtherinformationService {
	
	@Resource
	private IBisOtherinformationDao bisOtherinformationDao;

	@Override
	public List<BIS_OtherinformationEntity> findAll(long qyid) {
		return bisOtherinformationDao.findAll(qyid);
	}

	@Override
	public void addInfo(BIS_OtherinformationEntity bis) {
		bisOtherinformationDao.addInfo(bis);
	}

	@Override
	public void updateInfo(BIS_OtherinformationEntity bt) {
		bisOtherinformationDao.updateInfo(bt);
	}

	@Override
	public void deleteInfo(long id) {
		bisOtherinformationDao.deleteInfo(id);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_OtherinformationEntity> list = bisOtherinformationDao.dataGrid(mapData);
		int getTotalCount = bisOtherinformationDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public BIS_OtherinformationEntity findById(Long id) {
		return bisOtherinformationDao.findById(id);
	}
	
	@Override
	public BIS_OtherinformationEntity findById2(Long id) {
		return bisOtherinformationDao.findById2(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="其他信息表.xls";
		List<Map<String, Object>> list = bisOtherinformationDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	@Override
	public  Map<String, Object>  dataGridAJ(Map<String, Object> map) {
		List<Map<String, Object>> list=bisOtherinformationDao.dataGridAJ(map);
		int getTotalCount=bisOtherinformationDao.getTotalCountAJ(map);
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("rows", list);
		mapData.put("total", getTotalCount);
		return mapData;
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
					BIS_OtherinformationEntity OI = new BIS_OtherinformationEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					OI.setS1(t);
					OI.setS2(t);
					OI.setS3(0);
					OI.setID1(Long.valueOf(map.get("qyid").toString()));
					OI.setM2(bis.get(1).toString());
					OI.setM4(bis.get(3).toString());
					OI.setM6(bis.get(5).toString());
					OI.setM8(bis.get(7).toString());
					OI.setM10(bis.get(9).toString());
					OI.setM11(bis.get(10).toString());
					OI.setM14(bis.get(13).toString());
					OI.setM15(bis.get(14).toString());
					if(bis.get(0)!=null&&bis.get(0).toString()!=""){
						String m2=bis.get(0).toString();
						switch (m2) {
							case "是":OI.setM1(0);break;
							case "否":OI.setM1(1);break;
							default:
								break;
						}
					}
					if(bis.get(2)!=null&&bis.get(2).toString()!=""){
						String m2=bis.get(2).toString();
						switch (m2) {
							case "是":OI.setM3(0);break;
							case "否":OI.setM3(1);break;
							default:
								break;
						}
					}
					if(bis.get(4)!=null&&bis.get(4).toString()!=""){
						String m2=bis.get(4).toString();
						switch (m2) {
							case "是":OI.setM5(0);break;
							case "否":OI.setM5(1);break;
							default:
								break;
						}
					}
					if(bis.get(6)!=null&&bis.get(6).toString()!=""){
						String m2=bis.get(6).toString();
						switch (m2) {
							case "是":OI.setM7(0);break;
							case "否":OI.setM7(1);break;
							default:
								break;
						}
					}
					if(bis.get(8)!=null&&bis.get(8).toString()!=""){
						String m2=bis.get(8).toString();
						switch (m2) {
							case "是":OI.setM9(0);break;
							case "否":OI.setM9(1);break;
							default:
								break;
						}
					}
					if(bis.get(11)!=null&&bis.get(11).toString()!=""){
						String m2=bis.get(11).toString();
						switch (m2) {
							case "是":OI.setM12(0);break;
							case "否":OI.setM12(1);break;
							default:
								break;
						}
					}
					if(bis.get(12)!=null&&bis.get(12).toString()!=""){
						String m2=bis.get(12).toString();
						switch (m2) {
							case "是":OI.setM13(0);break;
							case "否":OI.setM13(1);break;
							default:
								break;
						}
					}
					bisOtherinformationDao.addInfo(OI);
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
	public void updateInfo2(BIS_OtherinformationEntity bt) {
		BIS_OtherinformationEntity bis = bisOtherinformationDao.findById(bt.getID());
//		bis.setLevel1(bt.getLevel1());
//		bis.setLevel2(bt.getLevel2());
//		bis.setTemperature1(bt.getTemperature1());
//		bis.setTemperature2(bt.getTemperature2());
//		bis.setPressure1(bt.getPressure1());
//		bis.setPressure2(bt.getPressure2());
		bisOtherinformationDao.updateInfo(bis);
	}

	@Override
	public void updateInfo3(BIS_OtherinformationEntity bt) {
		BIS_OtherinformationEntity bis = bisOtherinformationDao.findById(bt.getID());
//		bis.setLevel1(bt.getLevel1());
//		bis.setLevel2(bt.getLevel2());
//		bis.setTemperature1(bt.getTemperature1());
//		bis.setTemperature2(bt.getTemperature2());
//		bis.setPressure1(bt.getPressure1());
//		bis.setPressure2(bt.getPressure2());
//		bis.setR1(bt.getR1());
//		bis.setR2(bt.getR2());
//		bis.setR3(bt.getR3());
//		bis.setR4(bt.getR4());
		bisOtherinformationDao.updateInfo(bis);
	}

	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String, Object>> list = bisOtherinformationDao.dataGrid2(mapData);
		int getTotalCount = bisOtherinformationDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public List<Map<String, Object>> getCgMapJson(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		return bisOtherinformationDao.getCgMapJson(mapData);
	}
	
		@Override
	public Map<String, Object> statistics(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return bisOtherinformationDao.statistics(map);
	}
}
