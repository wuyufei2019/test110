package com.cczu.model.xfssgl.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


/**
 * 
 * @ClassName: Tree_XfssdjEntity
 * @Description: 消防设施登记
 * @author wbth
 * @date 2018年4月21日
 *
 */
public class Tree_XfssdjEntity {

	@Setter
	@Getter
	public Long ID;//编号
	
	@Setter
	@Getter
	private Long pid;//父ID	
	
	@Setter
	@Getter
	private Long  ID1;//企业ID
	
	@Setter
	@Getter
	private String text;//名称
	
	@Setter
	@Getter
	private String ggxh;//规格型号
	
	@Setter
	@Getter
	private String bindcontent;//绑定二维码
	
	@Setter
	@Getter
	private String x;//企业平面图坐标X 
	
	@Setter
	@Getter
	private String y;//企业平面图坐标y
	
	@Setter
	@Getter
	private String cycle;//换验周期(0:每月/1:每季/2:每半年)
	
	@Setter
	@Getter
	private String state;//状态(0:有效/1:过期/2:报废)
	
	@Setter
	@Getter
	private String sccs;//生产厂商
	
	@Setter
	@Getter
	private String M20;//备注
	
	@Setter
	@Getter
	private List<Tree_XfssdjEntity> children;//子

}
