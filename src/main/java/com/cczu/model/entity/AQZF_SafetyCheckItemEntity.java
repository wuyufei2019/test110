package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--安全检查内容
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_safetycheckitem")
public class AQZF_SafetyCheckItemEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2739094661745108104L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//添加人ID

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//检查单元

	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M2;//检查内容

	@Column(name = "M3", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M3;//检查依据

	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M4;//备注
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M5;
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M6;
 
}
