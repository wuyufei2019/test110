package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQZF_SafetyCheckUnitEntity;

/**
 * 检查单元记录dao
 * @author zpc
 * @date 2017/07/26
 */
public interface IAqzfJcdyDao {

	/**
	 * 检查单元记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String , Object>> dataGrid(Map<String, Object> mapData);
	
	/**
     * 统计检查单元记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * 得到jcdylist
	 * @return
	 */
	public List<Map<String, Object>> getjcdylist();
	

    /**
     * 添加
     * @param jcdy
     */
    public void addInfo(AQZF_SafetyCheckUnitEntity jcdy);

    /**
     * 删除
     * @param id
     */
	public void deleteInfo(long id);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public AQZF_SafetyCheckUnitEntity findInfoById(long id);

	/**
	 * 修改
	 * @param cfjllist
	 */
	public void updateInfo(AQZF_SafetyCheckUnitEntity jcdy);
    
}
