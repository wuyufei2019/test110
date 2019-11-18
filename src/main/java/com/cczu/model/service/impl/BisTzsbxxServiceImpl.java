package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IBisTzsbxxDao;
import com.cczu.model.entity.BIS_SpecialequipmentEntity;
import com.cczu.model.service.IBisTzsbxxService;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly = true)
@Service("BisTzsbxxService")
public class BisTzsbxxServiceImpl implements IBisTzsbxxService {

	@Resource
	private IBisTzsbxxDao bisTzsbxxDao;

	@Override
	public BIS_SpecialequipmentEntity findAll(long qyid) {
		return bisTzsbxxDao.findAll(qyid);
	}

	@Override
	public Map<String, Object> findById(Long id) {
		return bisTzsbxxDao.findById(id);
	}
	
	@Override
	public BIS_SpecialequipmentEntity findById2(Long id) {
		return bisTzsbxxDao.findById2(id);
	}

	@Override
	public void addInfo(BIS_SpecialequipmentEntity bis) {
		bisTzsbxxDao.addInfo(bis);
	}

	@Override
	public void updateInfo(BIS_SpecialequipmentEntity bis) {
		bisTzsbxxDao.updateInfo(bis);
	}

	@Override
	public void deleteInfo(long id) {
		bisTzsbxxDao.deleteInfo(id);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		return bisTzsbxxDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<BIS_SpecialequipmentEntity> list = bisTzsbxxDao.dataGrid(mapData);
		int getTotalCount = bisTzsbxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public Map<String, Object> asd(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = bisTzsbxxDao.asd(mapData);
		int getTotalCount = bisTzsbxxDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		String fileName = "特种设备信息表.xls";
		List<Map<String, Object>> list = bisTzsbxxDao.getExport(mapData);
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response, true);
	}

	@Override
	public Map<String,Object> exinExcel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String,Object> resultmap = new HashMap<String, Object>();
		ExinExcel exin = new ExinExcel();
		List<List<Object>> list = exin.exinExcel(map.get("filename").toString(), (InputStream) map.get("content"));
		int i = 0, success = 0, error = 0;
		if (list.size() > 3) {
			success = 0;
			resultmap.put("total", list.size()-3);
			resultmap.put("returncode", "success");
			for (List<Object> bis : list) {
				if(i<=2){ //跳过前三行
					i++;
					continue;
				}
				try{
					BIS_SpecialequipmentEntity spe = new BIS_SpecialequipmentEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					spe.setS1(t);
					spe.setS2(t);
					spe.setS3(0);
					spe.setID1(Long.valueOf(map.get("qyid").toString()));
					spe.setM1(bis.get(0).toString());
					spe.setM2(bis.get(1).toString());
					String m3=bis.get(2).toString();
					switch(m3){
					case "锅炉":
						m3="tzsblx1";
						break;
					case "压力容器":
						m3="tzsblx2";
						break;
					case "压力管道":
						m3="tzsblx3";
						break;
					case "电梯":
						m3="tzsblx4";
						break;
					case "起重机械":
						m3="tzsblx5";
						break;
					case "客运索道":
						m3="tzsblx6";
						break;
					case "大型游乐设施":
						m3="tzsblx7";
						break;
					case "场（厂）内专用机动车辆":
						m3="tzsblx8";
						break;
					case "其他设备":
						m3="tzsblx9";
						break;
					}
					spe.setM3(m3);
					spe.setM4(bis.get(3).toString());
					spe.setM5(bis.get(4).toString());
					spe.setM6(Integer.valueOf((bis.get(5).toString()==null||bis.get(5).toString()=="")?"0":bis.get(5).toString().substring(0,bis.get(5).toString().indexOf("."))));
					spe.setM7(bis.get(6).toString());
					spe.setM8(bis.get(7).toString());
					if(StringUtils.isNoneBlank(bis.get(8).toString()))
						spe.setM9(Timestamp.valueOf(bis.get(8).toString()));
					if(StringUtils.isNoneBlank(bis.get(9).toString()))
						spe.setM10(Timestamp.valueOf(bis.get(9).toString()));
					spe.setM11(bis.get(10).toString());
					if(StringUtils.isNoneBlank(bis.get(11).toString()))
						spe.setM14(Timestamp.valueOf(bis.get(11).toString()));
					bisTzsbxxDao.addInfo(spe);
					success++;
				}catch(Exception e){
					error++;
				}
				resultmap.put("success",success);
				resultmap.put("error", error);
			}
		}else if(list.size()==3){
			resultmap.put("success",success);
			resultmap.put("error", error);
			resultmap.put("returncode", "warn");
		}else if(list.size()<3){
			resultmap.put("success",success);
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

		List<Map<String, Object>> list = bisTzsbxxDao.ajdataGrid(mapData);
		int getTotalCount = bisTzsbxxDao.ajgetTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public List<Map<String, Object>> statistics(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return bisTzsbxxDao.statistics(map);
	}
	

}
