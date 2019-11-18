package com.cczu.model.sbssgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备更新计划
 * @author 
 */
@Entity
@Table(name = "sbssgl_sbgx")
public class SBSSGL_SBGXEntity extends BaseEntity{

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
	private String m2;//设备名称
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m3;//规格型号
	
	@Column(name = "m4", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long m4;//使用单位（部门id）
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m5;//单价（万元）
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m6;//数量（台）
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String m7;//合计（万元）
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m8;//设备更新（0.替换1.新增）
	
	@Column(name = "m9", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m9;//设备替换原因描述
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m10;//设备新增原因描述
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m11;//设备更新后作用描述
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m12;//是否完成（0.未完成1.已完成）
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m13;//验收附件
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m14;//年度
	
	@Column(name = "m15", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m15;//合同附件
	
}
