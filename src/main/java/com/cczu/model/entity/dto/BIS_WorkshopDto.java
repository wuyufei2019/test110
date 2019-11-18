package com.cczu.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: BIS_WorkshopEntity
 * @Description: 企业基本信息-车间信息
 * @author jason
 * @date 2017年5月27日
 *
 */
public class BIS_WorkshopDto {

	@Setter
	@Getter
	public long ID;//编号
	
	@Setter
	@Getter
	public String S1;//创建时间
	
	@Setter
	@Getter
	public String S2;//更新时间
	
	@Setter
	@Getter
	private int S3;//删除标识

	@Setter
	@Getter
	private String ID1;//企业名称
	
	@Setter
	@Getter
	private String M1;//车间名称
	
	@Setter
	@Getter
	private String M2;//车间编号
	
	@Setter
	@Getter	
	private String M3;//建筑面积
	
	@Setter
	@Getter	
	private String M4;//火灾危险等级
	
	@Setter
	@Getter	
	private String M5;//建筑结构
	
	@Setter
	@Getter	
	private String M6;//层数
	
	@Setter
	@Getter	
	private String M7;//备注
	@Setter
	@Getter	
	private String M8;//耐火等级
	@Setter
	@Getter
	private String qyname;//企业名称

}
