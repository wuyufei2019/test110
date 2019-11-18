package com.cczu.model.service;

public interface ITZybysService {
	
	/**
	 * 查询职业病所有类别
	 * @return
	 */
	public String dataList();
	
	/**
	 * 通过获取到的类别查询所有名称
	 * @param text
	 * @return
	 */
	public String dataList2(String text);
	
	/**
	 * 通过名称获取到的id查询数据
	 * @param id
	 * @return
	 */
	public String findliat(String m1);
	/**
	 * 查询所有信息
	 * */
	public String data() ;

	

}
