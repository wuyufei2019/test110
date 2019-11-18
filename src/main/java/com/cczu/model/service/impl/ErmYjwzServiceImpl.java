package com.cczu.model.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IErmYjwzDao;
import com.cczu.model.entity.ERM_EmergencyMateEntity;
import com.cczu.model.service.IErmYjwzService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.BaiDuMap;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("ErmYjwzService")
public class ErmYjwzServiceImpl implements IErmYjwzService {
	
	@Resource
	private IErmYjwzDao ermYjwzDao;

	@Override
	public List<ERM_EmergencyMateEntity> findAll() {
		return ermYjwzDao.findAllInfo();
	}

	@Override
	public void addInfo(ERM_EmergencyMateEntity erm) {
		ermYjwzDao.addInfo(erm);
		
	}

	@Override
	public void updateInfo(ERM_EmergencyMateEntity erm) {
		ermYjwzDao.updateInfo(erm);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		return ermYjwzDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=ermYjwzDao.dataGrid(mapData);
		int getTotalCount=ermYjwzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		ermYjwzDao.deleteInfo(id);
	}

	@Override
	public ERM_EmergencyMateEntity findById(Long id) {
		return ermYjwzDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"物资类别","名称","型号","数量","功能用途","自储数量","代储数量","储存单位","储存地址","联系人","联系电话","备注","经度","纬度"};
			String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","m13","m14","m15"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="应急物资.xls";
			List<Map<String, Object>> list=ermYjwzDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			if("1".equals(mapData.get("cxtype").toString())){
				String[] title={"物资类别","名称","型号","数量","功能用途","自储数量","代储数量","储存单位","储存地址","联系人","联系电话","备注","经度","纬度"};
				String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","m13","m14","m15"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
				String fileName="应急物资.xls";
				List<Map<String, Object>> list=ermYjwzDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response);
			}else{
				String[] title={"企业","物资类别","名称","型号","数量","功能用途","自储数量","代储数量","储存单位","储存地址","联系人","联系电话","备注","经度","纬度"};
				String[] keys={"qynm","m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","m13","m14","m15"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					if(!mapData.get("colval").toString().equals("qynm")){
						title =("企业名称,"+ mapData.get("coltext").toString()).split(",") ;
						keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
					}
				}
				String fileName="应急物资.xls";
				List<Map<String, Object>> list=ermYjwzDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response, true);
			}
		}
	}
	
	@Override
	public String findYjwz(Map<String, Object> mapData) {
		List<Map<String, Object>> list=ermYjwzDao.dataGrid(mapData);
		
		double lng=Double.parseDouble(mapData.get("lng")+"");
		double lat=Double.parseDouble(mapData.get("lat")+"");
		double juli=Double.parseDouble(mapData.get("juli")+"");;
		List<Map<String, Object>> reslist=new ArrayList<>();
		for(Map<String, Object> map:list){
			if(map.get("m14")!=null&&!map.get("m14").equals("")&&map.get("m15")!=null&&!map.get("m15").equals("")){
				 
				double j=Double.parseDouble(map.get("m14")+"");
				double w=Double.parseDouble(map.get("m15")+"");
				double dis=BaiDuMap.GetShortDistance(j, w, lng, lat);
				if(dis<juli){
					map.put("dis", dis);
					reslist.add(map);
				}
			}
		}
		return JsonMapper.getInstance().toJson(reslist);
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
					ERM_EmergencyMateEntity yjwz = new ERM_EmergencyMateEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yjwz.setS1(t);
					yjwz.setS2(t);
					yjwz.setS3(0);
					if(map.get("usertype").equals("1")){
						yjwz.setQyid(Long.valueOf(map.get("qyid").toString()));
					}
					yjwz.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
					yjwz.setM1(bis.get(0).toString());
					yjwz.setM2(bis.get(1).toString());
					yjwz.setM3(bis.get(2).toString());
					yjwz.setM4(Float.parseFloat(bis.get(3).toString()));
					yjwz.setM5(bis.get(4).toString());
					yjwz.setM6(bis.get(5).toString());
					yjwz.setM7(bis.get(6).toString());
					yjwz.setM8(bis.get(7).toString());
					yjwz.setM9(bis.get(8).toString());
					yjwz.setM10(bis.get(9).toString());
					yjwz.setM11(bis.get(10).toString());
					yjwz.setM13(bis.get(11).toString());
					ermYjwzDao.addInfo(yjwz);
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
		return ermYjwzDao.findMapList(mapdata);
	}
	
	@Override
	public String getqylistapp(Map<String, Object> tmap) {
		return JsonMapper.toJsonString(ermYjwzDao.getqylistapp(tmap));
	}
}
