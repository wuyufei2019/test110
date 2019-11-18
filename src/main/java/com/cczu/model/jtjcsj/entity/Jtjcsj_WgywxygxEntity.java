package com.cczu.model.jtjcsj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * 静态基础数据-网关与危险源关系表
 * @author Administrator
 *
 */
//@Entity
//@Table(name="jtjcsj_wgywxygx")
public class Jtjcsj_WgywxygxEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name="qyid", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;//企业id
	
	@Column(name="gatewaycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String gatewaycode;//网管编码
	
	@Column(name="hazardCode", nullable=true, columnDefinition="varchar(14)")
	@Setter
	@Getter
	private String hazardCode;//危险源编码
	
	
	
	
	
	
	
	
	

}
