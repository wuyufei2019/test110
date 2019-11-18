package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.BIS_DangerOperationEntity;
import com.cczu.sys.comm.persistence.Page;
import com.cczu.sys.comm.persistence.PropertyFilter;

public interface IBisWxzyService {
	
	public Page<BIS_DangerOperationEntity> search(Page<BIS_DangerOperationEntity> page,
			List<PropertyFilter> filters);
	
	/**
	 * 通过id查信息
	 * @param id
	 * @return
	 */
	public BIS_DangerOperationEntity findById(Long id);
	
	/**
	 * 根据qyID查企业获取其危险作业信息
	 * @param userid
	 * @return
	 */
	public BIS_DangerOperationEntity findAll(long qyid);
	
	/**
	 * 添加信息
	 * @param bis
	 */
	public void addInfo(BIS_DangerOperationEntity bis);
	
	/**
	 * 修改
	 * @param bis
	 */
	public void updateInfo(BIS_DangerOperationEntity bis);
	
	/**
	 * 删除
	 * @param bis
	 */
	public void deleteInfo(long id);
	
	public String content(Map<String, Object> mapData);


	public Map<String, Object> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 导出excel
	 * @param response
	 * @param mapData
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	

}
