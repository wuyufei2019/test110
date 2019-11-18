package com.cczu.model.service;

import java.util.List;

import com.cczu.model.entity.dto.Tdic_NoteTreeDto;

public interface ITdicZybflService {
	
	/**
	 * 获取所有
	 * @return 集合
	 */
	public List<Tdic_NoteTreeDto> getzybflAll();

	/**
	 * 职业病分类字典json
	 * @return 集合
	 */
	public String gbtjson();

}
