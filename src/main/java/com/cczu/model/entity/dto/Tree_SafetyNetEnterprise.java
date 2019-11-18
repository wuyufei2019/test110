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
public class Tree_SafetyNetEnterprise{
	
	@Setter
	@Getter
	private long id;//ID
	
	@Setter
	@Getter
	private long pid;//父ID
	
	@Setter
	@Getter
	private String text;//结构名称
	
	@Setter
	@Getter
	private String m2;//负责人
 
	@Setter
	@Getter
	private String m3;//负责人联系方式
	
	@Setter
	@Getter
	private String m4;//职责
	
	@Setter
	@Getter
	private String m5;//备注

	@Setter
	@Getter
	private Long id1;//企业id
	
	
	@Setter
	@Getter
	private List<Tree_SafetyNetEnterprise> children;//子
}
