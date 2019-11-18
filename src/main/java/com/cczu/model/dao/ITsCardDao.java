package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.TS_Card;


public interface ITsCardDao {
	/**
	 * @param map
	 * @return id、text
	 */
	public List<Map<String, Object>> findCardIdTextList();
	
	/**
	 * 所有工卡最新时间的坐标信息
	 * @return
	 */
	public List<Map<String, Object>> findCardMaqList();
	
	/**
	 * 根据工卡id查询最新时间的坐标信息
	 * @return
	 */
	public List<Map<String, Object>> findCardByid(Map<String, Object> map);
	
	/**
	 * list数据
	 * @param mapData
	 * @return
	 */
	public List<TS_Card> dataGrid(Map<String, Object> mapData);
	
	/**
	 * 分页查询总记录数
	 * @param mapData
	 * @return
	 */
	public int getTotalCount(Map<String, Object> mapData);

	
	/**
	 * 修改工卡绑定的状态
	 * @param cardno 工卡编号
	 * @param state 状态
	 * @return 
	 */
	public void updateCardState(String cardno,int state);
	
	/**
	 * 删除工卡信息
	 * @param id 工卡id
	 * @return 
	 */
	public void deleteCard(long id);
	

	/**
	 * web端根据条件—地图显示员工定位信息
	 * @param map 
	 * @return
	 */
	public List<Map<String, Object>> findYgGPSList(Map<String, Object> map);
	
	/**
	 * 根据工卡编号查询工卡信息
	 * @param cardNo 工卡编号
	 * @return
	 */
	public TS_Card findCardByCardNO(String cardNo);
	
}
