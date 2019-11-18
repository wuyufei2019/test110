package com.cczu.model.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.FxgkFxpgMajorRiskDao;
import com.cczu.model.entity.FXGK_MajorRisk;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;

@Transactional(readOnly=true)
@Service("FxgkFxpgMajorRiskService")
public class FxgkFxpgMajorRiskService {
	
	@Resource
	private FxgkFxpgMajorRiskDao fxgkfxpgmajorriskdao;
	
	public void addInfo(FXGK_MajorRisk entity) {
		// TODO Auto-generated method stub
		fxgkfxpgmajorriskdao.save(entity);
	}
	public Long addInfoReID(FXGK_MajorRisk entity) {
		// TODO Auto-generated method stub
		fxgkfxpgmajorriskdao.save(entity);
		return entity.getID();
	}

	public void updateInfo(FXGK_MajorRisk entity) {
		fxgkfxpgmajorriskdao.save(entity);
	}

	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String,Object>> list=fxgkfxpgmajorriskdao.dataGrid(mapData);
		int getTotalCount=fxgkfxpgmajorriskdao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
	
	public FXGK_MajorRisk findById(Long id) {
		// TODO Auto-generated method stub
		return fxgkfxpgmajorriskdao.find(id);
	}
	
	@Transactional(readOnly=false)
	public void deletinfo(Long id1) {
		// TODO Auto-generated method stub
		fxgkfxpgmajorriskdao.deleteInfoById1(id1);
	}
	public void exportExcel(HttpServletResponse response,Map<String, Object> mapData) {
		String fileName="重大风险清单表.xls";
		List<Map<String, Object>> list=fxgkfxpgmajorriskdao.getExport(mapData);
		//格式化时间
		for (Map<String, Object> map : list) {
			Timestamp analysistime = (Timestamp) map.get("analysistime");
			if (analysistime != null) {
				map.put("analysistime", DateUtils.formatDate(analysistime));
			}
		}
		String[] title=mapData.get("coltext").toString().split(",");  
		String[] keys=mapData.get("colval").toString().split(",");  
		new ExportExcel(fileName, title, keys, list, response);
	}
	
}


