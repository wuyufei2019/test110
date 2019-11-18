package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--安全检查计划
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_safetycheckplan")
public class AQZF_SafetyCheckPlanEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4015632975619765618L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//添加人ID

	@Column(name = "M1", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String M1;//年度

	@Column(name = "M2", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String M2;//月份

	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M3;//属地

	@Column(name = "M4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M4;//行业类型
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M5;//检查科室
	
 
}
