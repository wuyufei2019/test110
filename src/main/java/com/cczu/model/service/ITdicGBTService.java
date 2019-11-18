package com.cczu.model.service;

import java.util.List;

import com.cczu.model.entity.dto.Tdic_NoteTreeDto;

/**
 * 
 * @ClassName: ITdicGBTService
 * @Description: 字典_GBT4754
 * @author jason
 *
 */
public interface ITdicGBTService {

	/**
	 * 获取所有
	 * @return 集合
	 */
	public List<Tdic_NoteTreeDto> getGBTAll();

	/**
	 * GBT4754字典json
	 * @return 集合
	 */
	public String gbtjson();

	/**
	 * GBT4754大类字典json
	 * @return 集合
	 */
	public String gbtdljson();

	/**
	 * 获取所有
	 * @return 集合
	 */
	public List<Tdic_NoteTreeDto> getAJFLAll();
	/**
	 * 安监分类字典json
	 * @return 集合
	 */
	public String ajfljson();
	
}
