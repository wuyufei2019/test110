package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IDwZyspDao;
import com.cczu.model.entity.DW_WorkApprovalEntity;
import com.cczu.model.service.IDwZyspService;
import com.cczu.sys.comm.mapper.JsonMapper;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.service.CompRoleService;
import com.cczu.sys.system.service.UserService;
import com.cczu.sys.system.utils.UserUtil;


@Transactional(readOnly=true)
@Service("DwZyspService")
public class DwZyspServiceImpl implements IDwZyspService {
	
	@Resource
	private IDwZyspDao dwZyspDao;
	@Autowired
	private CompRoleService compRoleService;
	@Autowired
	private UserService userService;

	@Override
	public Long addInfo(DW_WorkApprovalEntity obj) {
		dwZyspDao.addInfo(obj);
		return obj.getID();
		
	}

	@Override
	public void updateInfo(DW_WorkApprovalEntity obj) {
		dwZyspDao.updateInfo(obj);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<Map<String, Object>> list=dwZyspDao.dataGrid(mapData);
		int getTotalCount=dwZyspDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		map.put("usertype", UserUtil.getCurrentShiroUser().getUsertype());
		return map;
	}
	
	@Override
	public Map<String, Object> dataGridApp(Map<String, Object> mapData) {
		List<Map<String, Object>> list=dwZyspDao.dataGrid(mapData);
		int getTotalCount=dwZyspDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public void deleteInfo(long id) {
		dwZyspDao.deleteInfo(id);
	}

	@Override
	public DW_WorkApprovalEntity findInfoById(Long id) {
		return dwZyspDao.findInfoById(id);
	}
	
	@Override
	public Map<String, Object> findInfoById2(Long id) {
		return dwZyspDao.findInfoById2(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		String fileName="危险作业审批表.xls";
		List<Map<String, Object>> list=dwZyspDao.getExcelData(mapData);
		for (Map<String, Object> map : list) {
			String zyfs = "";
			String zyfs1 = (String) map.get("m1");
			String[] strings = zyfs1.split(",");
			for (String str : strings) {
				String fs = "";
				if (str.equals("1")) {
					fs = "动火作业";
				}else if (str.equals("2")) {
					fs = "受限空间作业";
				}else if (str.equals("3")){
					fs = "管道拆卸作业";
				} else if (str.equals("4")){
					fs = "盲板抽堵作业";
				} else if (str.equals("5")){
					fs = "高处安全作业";
				} else if (str.equals("6")){
					fs = "吊装安全作业";
				} else if (str.equals("7")){
					fs = "临时用电安全作业";
				} else if (str.equals("8")){
					fs = "动土安全作业";
				} else if (str.equals("9")){
					fs = "断路安全作业";
				} else if (str.equals("1")){
					fs = "其他安全作业";
				}
				zyfs = "," + fs + zyfs;
			}
			map.put("m1", zyfs.replaceFirst(",", ""));
		}
		if("1".equals(mapData.get("usertype").toString())){
			String[] title={"危险作业类型","危险作业方式","危险作业级别","作业队伍","作业地点","作业负责人","作业人员","作业内容","动火时间起","动火时间止","危险因素","其他危险因素","可能导致的事故类型","第三方服务公司","第三方公司作业负责人"};  
			String[] keys={"m20","m1","m2","m17","m3","m4","m5","m6","m7","m8","m9","m10","m11","m18","m19"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
				title = mapData.get("coltext").toString().split(",") ;
				keys = mapData.get("colval").toString().split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response);
		}else{
			String[] title={"企业","危险作业类型","危险作业方式","危险作业级别","作业队伍","作业地点","作业负责人","作业人员","作业内容","动火时间起","动火时间止","危险因素","其他危险因素","可能导致的事故类型","第三方服务公司","第三方公司作业负责人"};  
			String[] keys={"qynm","m20","m1","m2","m17","m3","m4","m5","m6","m7","m8","m9","m10","m11","m18","m19"};
			if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
					title = ("企业名称,"+mapData.get("coltext").toString()).split(",") ;
					keys = ("qynm,"+mapData.get("colval").toString()).split(",") ;
			}
			new ExportExcel(fileName, title, keys, list, response, true);
		}
		
	}
	
	@Override
	public String getqylistapp(Map<String, Object> mapData) {
		return JsonMapper.toJsonString(dwZyspDao.getqylistapp(mapData));
	}
}
