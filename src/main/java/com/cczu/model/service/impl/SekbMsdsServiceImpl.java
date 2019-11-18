package com.cczu.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.ISekbMsdsDao;
import com.cczu.model.entity.TMESK_MsdsEntity;
import com.cczu.model.service.ISekbMsdsService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.ExportExcel;


@Transactional(readOnly=true)
@Service("SekbMsdsService")
public class SekbMsdsServiceImpl implements ISekbMsdsService {
	
	@Resource
	private ISekbMsdsDao sekbMsdsDao;

	@Override
	public Page<TMESK_MsdsEntity> search(Page<TMESK_MsdsEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}

	
	
	@Override
	public String content(Map<String, Object> mapData) {
		return sekbMsdsDao.content(mapData);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		
		List<TMESK_MsdsEntity> list=sekbMsdsDao.dataGrid(mapData);
		int getTotalCount=sekbMsdsDao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public TMESK_MsdsEntity findById(Long id) {
		return sekbMsdsDao.findById(id);
	}



	@Override
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData) {
		
		String[] title={"化学品中文名","化学品英文名称","中文名称2","英文名称2","技术说明书编号","CASNo","分子式","分子量","含量","健康危害","环境危害",
				"燃爆危险","皮肤接触","眼睛接触","吸入","食入","危险特性","有害燃烧产物","灭火方法","应急处理","操作注意事项","储存注意事项","中国MAC","前苏联","TLVTN",
				"TLVWN","工程控制","呼吸系统防护","眼睛防护","身体防护","手防护","其他防护","主要成分","外观与性状","熔点","沸点","相对密度","相对蒸气密度","饱和蒸气压",
				"燃烧热","临界温度","临界压力","水分配系数的对数值","闪点","引燃温度","爆炸上限","爆炸下限","主要用途","禁配物","急性毒性LD50","急性毒性LC50","其它有害作用",
				"废弃物性质废弃处置方法","危险货物编号","UN编号","包装类别","包装方法","运输注意事项","法规信息"};  
		String[] keys={"m1","m2","m3","m4","m5","m6","m7","m8","m9","m10","m11","m12","m13","m14","m15","m16","m17","m18","m19","m20",
				"m21","m22","m23","m24","m25","m26","m27","m28","m29","m30","m31","m32","m33","m34","m35","m36","m37","m38","m39","m40",
				"m41","m42","m43","m44","m45","m46","m47","m48","m49","m50","m51","m52","m53","m54","m55","m56","m57","m58","m59"};
		if(StringUtils.isNotEmpty(mapData.get("colval").toString())){
			title = mapData.get("coltext").toString().split(",") ;
			keys = mapData.get("colval").toString().split(",") ;
		}
		String fileName="MSDS.xls";
		List<Map<String, Object>> list=sekbMsdsDao.getExcel(mapData);
		new ExportExcel(fileName, title, keys, list, response);
	}
	
	
	@Override
	public List<TMESK_MsdsEntity> findByWzName(String name) {
		List<TMESK_MsdsEntity> list=sekbMsdsDao.findByM1(name);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> listAll() {
		return sekbMsdsDao.listAll();
	}
}
