package com.cczu.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.cczu.model.entity.MSG_detailEntity;

public interface IMsgService {
	/**
	 * 添加消息
	 * @param obj
	 */
	public Long addInfo(MSG_detailEntity obj);
	/**
	 * 批量添加消息
	 * @param uid：操作人id；content：文件标题；remind：提醒内容；提醒类型：type；状态：status；企业的id：qyids
	 */
	public void addAllInfo(long uid,String content,String remind,String type,String status,String qyids);
	
	/**
	 * 安全文件添加消息
	 * @param obj
	 */
	public void addWjInfo(Map<String, Object> map);
	
	/**
	 * 修改
	 * @param obj
	 */
	public void updateInfo(MSG_detailEntity obj);
	
	/**
	 * 删除
	 * @param obj
	 */
	public void deleteInfo(long id);

	/**
	 * list数据
	 * @param map
	 * @return
	 */
	public Map<String, Object> dataGrid(Map<String, Object> map);
	/**
	 * list数据
	 * @param map
	 * @return
	 */
	public List<MSG_detailEntity> findAllMsgList(Map<String, Object> map);
	
	/**
	 * 未读消息数量
	 * @param map
	 * @return
	 */
	public int msgCnt(Map<String, Object> mapData);
	
	/**
	 * 通过id查消息
	 * @param id
	 * @return
	 */
	public MSG_detailEntity findInfoById(Long id);

	/**
	 * 导出Excel
	 * @return
	 */
	public void exportExcel(HttpServletResponse response, Map<String, Object> mapData);
	
	/**
	 * 获取检测报告和培训计划的消息
	 * @return
	 */
	public void getTask();
	
	/**
	 * 全部已读
	 * @return
	 */
	public void updateInfoByUserId(Long id);
}
