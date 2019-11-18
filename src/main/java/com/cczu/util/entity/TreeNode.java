package com.cczu.util.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 树形
 * @author jason
 *
 */
public class TreeNode{
	@Setter
	@Getter
    private String id;
	@Setter
	@Getter
    private String name;
	@Setter
	@Getter
    private String pid;
	@Setter
	@Getter
    private List<TreeNode> children;
} 