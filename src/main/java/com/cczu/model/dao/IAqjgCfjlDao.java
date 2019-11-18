package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQJG_DSFCfjlEntity;

/**
 * 第三方处罚记录dao
 * @author zpc
 * @date 2017/07/07
 */
public interface IAqjgCfjlDao {

	/**
	 * 查询处罚记录list
	 * @param mapData
	 * @return
	 */
	public List<Map<String , Object>> dataGrid(Map<String, Object> mapData);
	
	/**
     * 统计处罚记录数
     * @return
     */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * 用过第三方名字找id
	 * @param Dname
	 * @return
	 */
    public long getDsfid(String Dname);
    
    /**
     * 添加
     * @param cfjl
     */
    public void addInfo(AQJG_DSFCfjlEntity cfjl);

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
	public AQJG_DSFCfjlEntity findInfoById(long id);

	/**
	 * 修改
	 * @param cfjllist
	 */
	public void updateInfo(AQJG_DSFCfjlEntity cfjllist);
    
}
