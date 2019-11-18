package com.cczu.model.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.dao.IAqpxJhxxDao;
import com.cczu.model.entity.AQPX_PlanEntity;
import com.cczu.model.service.IAqpxJhxxService;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;
import com.cczu.sys.comm.utils.DateUtils;
import com.cczu.sys.comm.utils.ExportExcel;
import com.cczu.sys.system.entity.User;

@Transactional(readOnly=true)
@Service("AqpxJhxxService")
public class AqpxJhxxServiceImpl implements IAqpxJhxxService {

	@Resource
	private IAqpxJhxxDao aqpxjhxxdao;
	
	@Override
	public Page<AQPX_PlanEntity> search(
			Page<AQPX_PlanEntity> page,
			List<PropertyFilter> filters) {
		return null;
	}

	@Override
	public AQPX_PlanEntity findAllInfo(Long qyid) {
		return aqpxjhxxdao.findAllInfo(qyid);
	}

	@Override
	public void addInfo(AQPX_PlanEntity ap) {
		Timestamp t = DateUtils.getSysTimestamp();
		ap.setS1(t);
		ap.setS2(t);
		ap.setS3(0);
		ap.setM4(t);
		aqpxjhxxdao.addInfo(ap);
	}

	@Override
	public void updateInfo(AQPX_PlanEntity ap) {
		Timestamp t=DateUtils.getSysTimestamp();
		ap.setS2(t);
		aqpxjhxxdao.updateInfo(ap);
	}

	@Override
	public void deleteInfo(Long id) {
		aqpxjhxxdao.deleteInfo(id);
	}

	@Override
	public Map<String, Object> dataGrid(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqpxjhxxdao.dataGrid(mapData);
		int getTotalCount=aqpxjhxxdao.getTotalCount(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}

	@Override
	public String content(Map<String, Object> mapData) {
		return aqpxjhxxdao.content(mapData);
	}

	
	@Override
	public List<AQPX_PlanEntity> getList(Long id) {
		return aqpxjhxxdao.getList(id);
	}

	@Override
	public void exportExcel(HttpServletResponse response,
			Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String[] title={"计划名称","培训类别","培训学时","生成时间","时段起","时段止","备注"};  
		String fileName="培训计划信息表.xls";
		List<Object> list=aqpxjhxxdao.getExcel(mapData);
		new ExportExcel(fileName, title,list, response);
	}

	@Override
	public AQPX_PlanEntity findByid(Long id) {
		// TODO Auto-generated method stub
		return aqpxjhxxdao.findByid(id);
	}

	@Override
	public List<AQPX_PlanEntity> findInfoByBmid(Long qyid, Long bmid) {
		// TODO Auto-generated method stub
		return aqpxjhxxdao.findInfoByBmid(qyid, bmid);
	}

	@Override
	public List<User> findUseridByDep(String depid) {
		// TODO Auto-generated method stub
		return aqpxjhxxdao.findUseridByDep(depid);
	}

	@Override
	public Map<String, Object> dataGrid2(Map<String, Object> mapData) {
		List<Map<String, Object>> list=aqpxjhxxdao.dataGrid2(mapData);
		int getTotalCount=aqpxjhxxdao.getTotalCount2(mapData);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", getTotalCount);
		return map;
	}
}
