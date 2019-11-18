package com.cczu.model.service;

public interface ITdicDangerousChemicalsService {
	
	/**
	 * 通过m1查询类别
	 * @param m1
	 * @return
	 */
	public String dangerList(String m1);
	
	public String findlist();
	
	/**
	 * 通过m1查询危害
	 * @param m1
	 * @return
	 */
	public String dangerList2(String m1);
	
	/**
	 * 通过m1模糊查询
	 * @param m1
	 * @return
	 */
	public String findByMs(String m1);

}
