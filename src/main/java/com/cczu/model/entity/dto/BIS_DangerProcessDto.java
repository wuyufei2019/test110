package com.cczu.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: BIS_DangerProcessEntity
 * @Description: 企业基本信息-高危工艺
 * @author jason
 * @date 2017年5月27日
 *
 */
public class BIS_DangerProcessDto{

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
	private String M1;//高危工艺

	@Setter
	@Getter
	private String M2;//套数	单位为t/a

	@Setter
	@Getter	
	private String M3;//备注

	@Setter
	@Getter
	private String qyname;//企业名称
}
