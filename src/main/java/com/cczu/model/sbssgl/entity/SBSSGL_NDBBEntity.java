package com.cczu.model.sbssgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-年度报表
 * @author 
 */
@Entity
@Table(name = "sbssgl_ndbb")
public class SBSSGL_NDBBEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m1;//年度
	
	@Column(name = "deptid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long deptid;//部门id
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m2;//标题
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m3;//附件
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(10)")
	@Getter
	@Setter
	private String m4;//类别（0.设备大项修计划1.设备更新计划）
	
	
}
