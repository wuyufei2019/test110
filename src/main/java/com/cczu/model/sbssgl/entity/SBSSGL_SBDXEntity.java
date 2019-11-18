package com.cczu.model.sbssgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备大修项
 * @author 
 */
@Entity
@Table(name = "sbssgl_sbdx")
public class SBSSGL_SBDXEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m1;//工作令号
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m2;//设备编号
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m3;//型号名称
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m4;//启用年月
	
	@Column(name = "m5", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long m5;//使用单位（部门id）
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m6;//计划修理费（万元）
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m7;//年度
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m8;//修理类别 （0.大修1.项修）
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m9;//定人
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m10;//定期
	
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m11;//定点
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m12;//定质
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m13;//定量
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m14;//验收附件
	
	@Column(name = "m15", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m15;//检维修方案
	
	@Column(name = "m16", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m16;//设备现状描述
	
	@Column(name = "m18", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m18;//计划修理时间
	
	@Column(name = "m19", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m19;//是否完成（0.未完成1.完成）
	
	@Column(name = "m20", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m20;//技术验收质量要求
	
	@Column(name = "m21", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m21;//检维修负责人
	
	@Column(name = "m22", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m22;//维修合同附件
}
