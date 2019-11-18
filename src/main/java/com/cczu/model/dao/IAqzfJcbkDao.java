package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQZF_SafetyCheckItemEntity;

/**
 * 检查表库记录dao
 * @author zpc
 * @date 2017/07/27
 */
public interface IAqzfJcbkDao {

	/**
	 * 查询检查表库list
	 * @param mapData
	 * @return
	 */
	public List<Map<String , Object>> dataGrid(Map<String, Object> mapData);
	
	/**
     * 统计检查表库记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
    /**
     * 添加
     * @param cfjl
     */
    public void addInfo(AQZF_SafetyCheckItemEntity jcbk);

    /**
     * 删除
     * @param id
     */
	public void deleteInfo(long id);
	
    /**
     * 导出
     * @param mapData
     * @return
     */
	public List<Map<String, Object>> getExport(Map<String, Object> mapData);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public AQZF_SafetyCheckItemEntity findInfoById(long id);

	/**
	 * 修改
	 * @param jcbk
	 */
	public void updateInfo(AQZF_SafetyCheckItemEntity jcbk);

	/**
	 * 查询检查单元内容，不包含已被删除的
	 * @param jcbk
	 */
	public List<AQZF_SafetyCheckItemEntity> findJcx(Long m1 );
	
	/**
	 * 根据id查询 app
	 * @param id
	 * @return
	 */
	public AQZF_SafetyCheckItemEntity findInfoByIdForApp(long id);
}
