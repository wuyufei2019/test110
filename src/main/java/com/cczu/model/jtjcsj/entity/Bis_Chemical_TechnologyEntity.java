package com.cczu.model.jtjcsj.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 静态基础数据-重点危险化学工艺表
 * @author Administrator
 *
 */
//@Entity
//@Table(name="bis_chemical_technology")
public class Bis_Chemical_TechnologyEntity extends BaseEntity {
	
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

	@Column(name="companyCode", nullable=false, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companyCode;//企业编码

	@Column(name="parkId", nullable=false, columnDefinition="char(32)")
	@Setter
	@Getter
	private String parkId;//所属园区标识（由省厅统一下发）

	@Column(name="districtCode", nullable=false, columnDefinition="char(32)")
	@Setter
	@Getter
	private String districtCode;//所属行政区划标识（区县级行政区划编码（6位））

	@Column(name="processCode", nullable=false, columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String processCode;//工艺编码（工艺编码，见工艺编码附录表）

	@Column(name="ctrlMode", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String ctrlMode;//控制方式（工艺编码，见工艺编码附录表）

	@Column(name="ctrlPara", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String ctrlPara;//控制参数

	@Column(name="ctrlMeasure", nullable=true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String ctrlMeasure;//控制措施

	@Column(name="isNationDemand", nullable=false, columnDefinition="char(1)")
	@Setter
	@Getter
	private String isNationDemand;//是否满足国家规定的控制要求（0否;1是）
}
