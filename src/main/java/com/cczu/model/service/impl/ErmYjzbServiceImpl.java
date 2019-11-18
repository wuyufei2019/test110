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

import com.cczu.model.dao.IErmYjzbDao;
import com.cczu.model.entity.ERM_EmergencyResInstrumentEntity;
import com.cczu.model.service.IErmYjzbService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("ErmYjzbService")
public class ErmYjzbServiceImpl implements IErmYjzbService {
	
	@Resource
	private IErmYjzbDao ermYjzbDao;

	@Override
	public List<ERM_EmergencyResInstrumentEntity> findAll() {
		return ermYjzbDao.findAllInfo();
	}

	@Override
	public void addInfo(ERM_EmergencyResInstrumentEntity erm) {
		ermYjzbDao.addInfo(erm);
		
	}

	@Override
	public void updateInfo(ERM_EmergencyResInstrumentEntity erm) {
		ermYjzbDao.updateInfo(erm);
	}
	
	@Override
	public String content(Map<String, Object> mapData) {
		return ermYjzbDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=ermYjzbDao.dataGrid(mapData);
		int getTotalCount=ermYjzbDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		ermYjzbDao.deleteInfo(id);
	}

	@Override
	public ERM_EmergencyResInstrumentEntity findById(Long id) {
		return ermYjzbDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"类别","名称","规格型号","数量","功能用途","自储数量","代储数量","储存单位","储存地址","主要负责人","应急电话","备注"};
			String[] keys={"M1","M2","M3","M4","M5","M6","M7","M8","M9","M10","M11","M13"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="应急装备.xls";
			List<Map<String, Object>> list=ermYjzbDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			if("1".equals(mapData.get("cxtype").toString())){
				String[] title={"类别","名称","规格型号","数量","功能用途","自储数量","代储数量","储存单位","储存地址","主要负责人","应急电话","备注"};
				String[] keys={"M1","M2","M3","M4","M5","M6","M7","M8","M9","M10","M11","M13"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
				String fileName="应急装备.xls";
				List<Map<String, Object>> list=ermYjzbDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response);
			}else{
				String[] title={"企业","类别","名称","规格型号","数量","功能用途","自储数量","代储数量","储存单位","储存地址","主要负责人","应急电话","备注"};
				String[] keys={"qynm","M1","M2","M3","M4","M5","M6","M7","M8","M9","M10","M11","M13"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					if(!mapData.get("colval").toString().equals("qynm")){
						title =("企业名称,"+mapData.get("coltext").toString()).split(",") ;
						keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
					}
				}
				String fileName="应急装备.xls";
				List<Map<String, Object>> list=ermYjzbDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response, true);
			}
		}
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
					ERM_EmergencyResInstrumentEntity yjzb = new ERM_EmergencyResInstrumentEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yjzb.setS1(t);
					yjzb.setS2(t);
					yjzb.setS3(0);
					if(map.get("usertype").equals("1")){
						yjzb.setQyid(Long.valueOf(map.get("qyid").toString()));
					}
					yjzb.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
					yjzb.setM1(bis.get(0).toString());
					yjzb.setM2(bis.get(1).toString());
					yjzb.setM3(bis.get(2).toString());
					if(bis.get(3).toString()!=null&&bis.get(3).toString()!=""){
						yjzb.setM4(Float.parseFloat(bis.get(3).toString()));
					}
					yjzb.setM5(bis.get(4).toString());
					if(bis.get(5).toString()!=null&&bis.get(5).toString()!=""){
						yjzb.setM6(Float.parseFloat(bis.get(5).toString()));
					}
					if(bis.get(6).toString()!=null&&bis.get(6).toString()!=""){
						yjzb.setM7(Float.parseFloat(bis.get(6).toString()));
					}
					yjzb.setM8(bis.get(7).toString());
					yjzb.setM9(bis.get(8).toString());
					yjzb.setM10(bis.get(9).toString());
					yjzb.setM11(bis.get(10).toString());
					yjzb.setM13(bis.get(11).toString());
					ermYjzbDao.addInfo(yjzb);
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
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata) {
		// TODO Auto-generated method stub
		return ermYjzbDao.findMapList(mapdata);
	}
	
	@Override
	public String getqylistapp(Map<String, Object> tmap) {
		return JsonMapper.toJsonString(ermYjzbDao.getqylistapp(tmap));
	}
}
