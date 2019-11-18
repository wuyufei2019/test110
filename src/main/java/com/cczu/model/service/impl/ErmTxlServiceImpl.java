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

import com.cczu.model.dao.IErmTxlDao;
import com.cczu.model.entity.ERM_EmergencyContactsEntity;
import com.cczu.model.service.IErmTxlService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExinExcel;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.utils.UserUtil;

@Transactional(readOnly=true)
@Service("ErmTxlService")
public class ErmTxlServiceImpl implements IErmTxlService {
	
	@Resource
	private IErmTxlDao ermTxlDao;

	@Override
	public void addInfo(ERM_EmergencyContactsEntity erm) {
		ermTxlDao.addInfo(erm);
		
	}

	@Override
	public void updateInfo(ERM_EmergencyContactsEntity erm) {
		ermTxlDao.updateInfo(erm);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=ermTxlDao.dataGrid(mapData);
		int getTotalCount=ermTxlDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		ermTxlDao.deleteInfo(id);
	}

	@Override
	public ERM_EmergencyContactsEntity findById(Long id) {
		return ermTxlDao.findById(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"姓名","部门","职务","应急电话(手机)","固定电话","备注"};  
			String[] keys={"m1","m2","m3","m4","m5","m6"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			String fileName="通讯录.xls";
			List<Map<String, Object>> list=ermTxlDao.getExcel(mapData);
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			if("1".equals(mapData.get("cxtype").toString())){
				String[] title={"姓名","部门","职务","应急电话(手机)","固定电话","备注"};  
				String[] keys={"m1","m2","m3","m4","m5","m6"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					title = mapData.get("coltext").toString().split(",") ;
					keys = mapData.get("colval").toString().split(",") ;
				}
				String fileName="通讯录.xls";
				List<Map<String, Object>> list=ermTxlDao.getExcel(mapData);
				new ExportExcel(fileName, title, keys, list, response);
			}else{
				String[] title={"企业","姓名","部门","职务","应急电话(手机)","固定电话","备注"};  
				String[] keys={"qynm","m1","m2","m3","m4","m5","m6"};
				if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					if(!mapData.get("colval").toString().equals("qynm")){
						title =("企业名称,"+mapData.get("coltext").toString()).split(",") ;
						keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
					}
				}
				String fileName="通讯录.xls";
				List<Map<String, Object>> list=ermTxlDao.getExcel(mapData);
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
					ERM_EmergencyContactsEntity txl = new ERM_EmergencyContactsEntity();
					Timestamp t = DateUtils.getSysTimestamp();
					txl.setS1(t);
					txl.setS2(t);
					txl.setS3(0);
					if(map.get("usertype").equals("1")){
						txl.setQyid(Long.valueOf(map.get("qyid").toString()));
					}
					txl.setUserid(Long.parseLong(UserUtil.getCurrentUser().getId().toString()));
					txl.setM1(bis.get(0).toString());
					txl.setM2(bis.get(1).toString());
					txl.setM3(bis.get(2).toString());
					txl.setM4(bis.get(3).toString());
					txl.setM5(bis.get(4).toString());
					txl.setM6(bis.get(5).toString());
					ermTxlDao.addInfo(txl);
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
		return JsonMapper.toJsonString(ermTxlDao.getqylistapp(tmap));
	}
}
