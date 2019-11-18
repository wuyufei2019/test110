package com.cczu.model.entity.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: Tree_Department
 * @Description: 部门-tree
 * @author jason
 *
 */
public class Tree_Department{
	@Setter
	@Getter
	private long id; 
	
	@Setter
	@Getter
	private Long fid;//父id
	
	@Setter
	@Getter
	private String m1;//部门名称
	
	@Setter
	@Getter
	private Integer m2;//排序
	
	@Setter
	@Getter
	private String m3;//备注
	
	@Setter
	@Getter
	private String flag;//所属类别
	
	@Setter
	@Getter
	private String code;//编码
	
	@Setter
	@Getter
	private List<Tree_Department> children;//子
	
}