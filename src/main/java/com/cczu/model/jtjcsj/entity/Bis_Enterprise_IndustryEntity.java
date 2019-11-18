package com.cczu.model.jtjcsj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 静态基础数据-企业化学品信息
 * @author Administrator
 *
 */
//@Entity
//@Table(name="bis_enterprise_industry")
public class Bis_Enterprise_IndustryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name="UUID", nullable=false, columnDefinition="varchar(36)")
	@Setter
	@Getter
	private String UUID;//唯一编码 UUID

	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name="companycode", nullable=false, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companycode;//企业编码

	@Column(name="chemicalindustrycode", nullable=false, columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String chemicalindustrycode;//化工行业编码（化工行业编码，见化工行业附录表）
	
}
