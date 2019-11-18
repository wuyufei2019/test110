package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.Tree_XFSS_RegistEntity;
import com.cczu.model.entity.XFSS_RegisterEntity;


public interface IXfssDjService {
	/**
	 * 添加消防设施登记
	 * @param xwaq
	 */
	public void addInfo(XFSS_RegisterEntity xwaq);
	
	/**
	 * 修改
	 * @param xwaq
	 */
	public void updateInfo(XFSS_RegisterEntity xwaq);
	
	/**
	 * 删除
	 * @param xwaq
	 */
	public void deleteInfo(long id);
	
	/**
	 * 生成二维码
	 * @param xwaq
	 */
	public String createQr(String path, String name, String text);
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	
	/**
	 * 通过id查消防设施登记
	 * @param id
	 * @return
	 */
	public XFSS_RegisterEntity findById(Long id);
	
	/**
	 * 查所有消防设施登记
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findAllInfo(Long qyid);
	
	/**
	 * 查所有消防设施登记
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findAllInfo(Long qyid, Long pmtId);

	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);

	/**
	 * 根据企业ID获取treelist集合
	 * @param qyid
	 * @return
	 */
	List<Tree_XFSS_RegistEntity> getWltson(Long qyid);
}
