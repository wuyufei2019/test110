package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_DangerProcessEntity
 * @Description: 企业基本信息-高危工艺
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_dangerprocess")
public class BIS_DangerProcessEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6955834215044303121L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M2", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//套数	单位为t/a

	@Column(name = "M3", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M3;//备注

	@Column(name = "M4", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M4;//现场照片
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M5;//图纸

	@Column(name="uuid", nullable=true, columnDefinition="varchar(36)")
	@Setter
	@Getter
	private String uuid;//唯一编码 UUID

	@Column(name="companycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companycode;//企业编码

	@Column(name="parkid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String parkid;//所属园区标识（由省厅统一下发）

	@Column(name="districtcode", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String districtcode;//所属行政区划标识（区县级行政区划编码（6位））

	@Column(name = "M1", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//工艺名称

	@Column(name="processcode", nullable=true, columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String processcode;//工艺编码（工艺编码，见工艺编码附录表）

	@Column(name="ctrlmode", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String ctrlmode;//控制方式（工艺编码，见工艺编码附录表）

	@Column(name="ctrlpara", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String ctrlpara;//控制参数

	@Column(name="ctrlmeasure", nullable=true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String ctrlmeasure;//控制措施

	@Column(name="isnationdemand", nullable=true, columnDefinition="char(1)")
	@Setter
	@Getter
	private String isnationdemand;//是否满足国家规定的控制要求（0否;1是）

	@Column(name="describe", nullable=true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String describe;//描述

	@Column(name="equipcode", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String equipcode;//设备编码

}
