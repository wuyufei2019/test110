package com.cczu.model.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: Tree_ExpenseType
 * @Description: 费用类型-tree
 * @author jason
 *
 */
public class Tree_ExpenseType{
	@Setter
	@Getter
	private long id; 
	
	@Setter
	@Getter
	private long fid;//父id(上级目录)
	
	@Setter
	@Getter
	private String m1;//类型名称
	
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
	private List<Tree_ExpenseType> children;//子
	
}