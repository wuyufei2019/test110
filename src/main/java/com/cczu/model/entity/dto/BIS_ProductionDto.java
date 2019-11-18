package com.cczu.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: BIS_ProductionEntity
 * @Description: 企业基本信息-企业产品
 * @author jason
 * @date 2017年5月27日
 *
 */
public class BIS_ProductionDto{

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
	private String ID2;//生产车间编号
	
	@Setter
	@Getter
	private String M1;//产品名称

	@Setter
	@Getter
	private String M2;//年产量		单位为t/a
	
	@Setter
	@Getter	
	private String M3;//最大储量	单位为t/a

	@Setter
	@Getter	
	private String M4;//状态

	@Setter
	@Getter	
	private String M5;//是否领证    是/否

	@Setter
	@Getter	
	private String M6;//CAS号
	
	@Setter
	@Getter	
	private String M7;//备注
	
	@Setter
	@Getter	
	private String qyname;//企业名称

}
