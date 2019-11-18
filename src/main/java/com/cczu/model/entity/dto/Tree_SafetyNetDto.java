package com.cczu.model.entity.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @ClassName: Tree_SafetyNetDto
 * @Description: 安全网络结构Tree
 * @author jason
 *
 */
public class Tree_SafetyNetDto{
	
	@Setter
	@Getter
	private String id;//ID
	
	@Setter
	@Getter
	private String name;//名称
	
	@Setter
	@Getter
	private String value;//值
	
	@Setter
	@Getter
	private String pid;//父ID
	
	@Setter
	@Getter
	private List<Tree_SafetyNetDto> children;//子
}
