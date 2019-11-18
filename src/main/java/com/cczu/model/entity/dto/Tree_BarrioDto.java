package com.cczu.model.entity.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: Tree_BarrioDto
 * @Description: 网格区域-tree
 * @author jason
 *
 */
public class Tree_BarrioDto{
	@Setter
	@Getter
	private long id; 
	
	@Setter
	@Getter
	private long fid;//父id
	
	@Setter
	@Getter
	private String m1;//网格名称
	
	@Setter
	@Getter
	private String m2;//图标
	
	@Setter
	@Getter
	private Integer m3;//排序
	
	@Setter
	@Getter
	private String m4;//备注
	
	@Setter
	@Getter
	private String code;//编码
	
	@Setter
	@Getter
	private long uid;
	@Setter
	@Getter
	private String mappoint;
	
	@Setter
	@Getter
	private List<Tree_BarrioDto> children;//子
	
}