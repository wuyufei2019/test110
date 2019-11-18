package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 安全生产执法--安全检查计划与企业关联中间表
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_plan_enterprise")
public class AQZF_Plan_EnterpriseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 391570082515724016L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID; 
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1; //检查计划id
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2; //企业id

	@Column(name = "M1", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M1; //操作状态 （0：未添加 1：已添加）
}
