package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.MSG_detailEntity;



public interface IMsgDao {
	
	/**
	 * 添加消息
	 * @param dw
	 */
	public void addInfo(MSG_detailEntity dw);
	/**
	 * 安全文件添加消息
	 * @param dw
	 */
	public void addWjInfo(Map<String, Object> map); 
	/**
	 * 批量添加消息
	 * @param uid：操作人id；content：文件标题；remind：提醒内容；提醒类型：type；状态：status；企业的id：qyids
	 * 
	 */
	public void addAllInfo(long uid,String content,String remind,String type,String status,String qyids); 
	/**
	 * 修改消息
	 * @param dw
	 */
	public void updateInfo(MSG_detailEntity dw);
	
	/**
	 * 删除消息
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 全部已读
	 * @param id
	 */
	public void updateInfoByUserId(Long id);
	
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String,Object> mapData);
	/**
	 * 查询数据
	 * @param mapData
	 * @return
	 */
	public List<MSG_detailEntity> findAllMsgList(Map<String,Object> mapData);
	
	
	/**
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);
	
	/**
	 * @param mapData
	 * @return
	 */
	public int getMsgNoReadCnt(Map<String, Object> mapData);

	/**
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 
	 * 通过id查找消息
	 * @param id
	 * @return
	 */
	public MSG_detailEntity findInfoById(Long id);
	
	/**
	 * 获得导出数据
	 * @return
	 */
	public List<Map<String, Object>> getExcelData(Map<String, Object> mapData);
}
