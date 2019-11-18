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

import com.cczu.model.dao.IErmYjzzzzDao;
import com.cczu.model.entity.ERM_EmergencyOrgEntity;
import com.cczu.model.service.IErmYjzzzzService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;


@Transactional(readOnly=true)
@Service("ErmYjzzzzService")
public class ErmYjzzzzServiceImpl implements IErmYjzzzzService {
	
	@Resource
	private IErmYjzzzzDao ermYjzzzzDao;

	@Override
	public List<ERM_EmergencyOrgEntity> findAll() {
		return ermYjzzzzDao.findAllInfo();
	}

	@Override
	public void addInfo(ERM_EmergencyOrgEntity erm) {
		ermYjzzzzDao.addInfo(erm);
		
	}

	@Override
	public void updateInfo(ERM_EmergencyOrgEntity erm) {
		ermYjzzzzDao.updateInfo(erm);
	}

	@Override
	public String content(Map<String, Object> mapData) {
		return ermYjzzzzDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=ermYjzzzzDao.dataGrid(mapData);
		int getTotalCount=ermYjzzzzDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	@Override
	public void deleteInfo(long id) {
		ermYjzzzzDao.deleteInfo(id);
	}

	@Override
	public ERM_EmergencyOrgEntity findById(Long id) {
		return ermYjzzzzDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"组织名称","组织负责人","负责人联系电话","组织联系人","组织联系人电话","组织应急职责","备注"};  
			String[] keys={"m1","m2","m3","m4","m5","m6","m7"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="应急组织职责.xls";
			List<Map<String, Object>> list=ermYjzzzzDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			if("1".equals(mapData.get("cxtype").toString())){
				String[] title={"组织名称","组织负责人","负责人联系电话","组织联系人","组织联系人电话","组织应急职责","备注"};  
				String[] keys={"m1","m2","m3","m4","m5","m6","m7"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
				String fileName="应急组织职责.xls";
				List<Map<String, Object>> list=ermYjzzzzDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response);
			}else{
				String[] title={"企业","组织名称","组织负责人","负责人联系电话","组织联系人","组织联系人电话","组织应急职责","备注"};  
				String[] keys={"qynm","m1","m2","m3","m4","m5","m6","m7"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					if(!mapData.get("colval").toString().equals("qynm")){
						title =("企业名称,"+mapData.get("coltext").toString()).split(",") ;
						keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
					}
				}
				String fileName="应急组织职责.xls";
				List<Map<String, Object>> list=ermYjzzzzDao.getExcel(mapData);
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
					ERM_EmergencyOrgEntity yjzzzz = new ERM_EmergencyOrgEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					yjzzzz.setS1(t);
					yjzzzz.setS2(t);
					yjzzzz.setS3(0);
					if(map.get("usertype").equals("1")){
						yjzzzz.setQyid(Long.valueOf(map.get("qyid").toString()));
					}
					yjzzzz.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
					yjzzzz.setM1(bis.get(0).toString());
					yjzzzz.setM2(bis.get(1).toString());
					yjzzzz.setM3(bis.get(2).toString());
					yjzzzz.setM4(bis.get(3).toString());
					yjzzzz.setM5(bis.get(4).toString());
					yjzzzz.setM6(bis.get(5).toString());
					yjzzzz.setM7(bis.get(6).toString());
					ermYjzzzzDao.addInfo(yjzzzz);
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
	public String getqylistapp(Map<String, Object> tmap) {
		return JsonMapper.toJsonString(ermYjzzzzDao.getqylistapp(tmap));
	}
}
