package com.cczu.model.sbssgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备申请审核
 * @author 
 */
@Entity
@Table(name = "sbssgl_shjl")
public class SBSSGL_SHJLEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	

	@Column(name = "m1", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m1;//审核结果（0.不通过1.通过）
	
	@Column(name = "shrid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long shrid;//审核人id
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m2;//不通过原因
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String m3;//附件
	
	@Column(name = "id2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long id2;//设备申请id 或者 设备验收id 或者 设备报废id
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m4;//类别（0.设备申请1.设备验收2.设备报废3.设备以及保养计划4.设备二级保养计划）
	
}
