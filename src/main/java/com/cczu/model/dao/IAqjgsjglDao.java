package com.cczu.model.dao;
import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJG_AccidentInforEntity;

/**
 *  安全监督管理_事件管理DAO
 */
public interface IAqjgsjglDao {
	/**
     * 查询安全监管_事件管理数据表格
     * @return
     */
	public List<AQJG_AccidentInforEntity> dataGrid(Map<String, Object> mapData);
	
	/**
     * 查询安全监督管理_事件管理数据表格_总记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
     * 根据id查找安全监督管理_事件管理_view页面
     * @return
     */
	public AQJG_AccidentInforEntity findInfoById(long id);
	/**
     * 根据id查找安全监督管理_事件管理_update页面
     * @return
     */
	public AQJG_AccidentInforEntity findInfoById2(long id);
	
	/**
     * 添加安全监督管理_事件管理
     * @return
     */
	public void saveInfo(AQJG_AccidentInforEntity aie);
	/**
     * 修改安全监督管理_事件管理
     * @return
     */
	public void updateInfo(AQJG_AccidentInforEntity aie);
	/**
     * 删除安全监督管理_事件管理
     * @return
     */
	public void deleteInfo(long id);
	/**
     * 获取每月的事故数
     * @return
     */
	public int getCountEveryMonth(Map<String, Object> mapData);
	/**
     * 获取数据库中最大的年份
     * @return
     */
	public Object[] getMaxYearAndMinYear();
	
}
