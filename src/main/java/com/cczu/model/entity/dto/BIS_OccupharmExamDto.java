package com.cczu.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: BIS_OccupIllexamEntity
 * @Description: 企业基本信息-职业卫生_职业病体检
 * @author jason
 * @date 2017年5月27日
 *
 */
public class BIS_OccupharmExamDto{
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
	private String S3;//删除标识
	@Setter
	@Getter
	private String ID1;//企业编号

	@Setter
	@Getter
	private String M1;//类别
	
	@Setter
	@Getter
	private String M2;//名称

	@Setter
	@Getter	
	private String M3;//危害
	
	@Setter
	@Getter	
	private String M4;//备注
	
	@Setter
	@Getter	
	private String M5;//可能导致的职业病

	@Setter
	@Getter
	private String qyname;//企业名称
}
