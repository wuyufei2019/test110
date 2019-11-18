package com.cczu.model.entity.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @ClassName: Tdic_NoteTreeDto
 * @Description: 字典-tree共通
 * @author jason
 *
 */
public class Tdic_NoteTreeDto{
	
	@Setter
	@Getter
	private String id;//ID
	
	@Setter
	@Getter
	private String text;//名称
	
	@Setter
	@Getter
	private String pid;//父ID

	@Setter
	@Getter
	private Integer sort;
	
	@Setter
	@Getter
	private List<Tdic_NoteTreeDto> children;//子
}
