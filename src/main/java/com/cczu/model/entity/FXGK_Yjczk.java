package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 应急处置卡
 * @author XY
 *
 */
@Entity
@Table(name="fxgk_yjczk")
public class FXGK_Yjczk extends BaseEntity{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业ID
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//岗位名称
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M2;//生产安全事故处置程序及职责
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M3;//注意事项
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M4;//本企业救援队联系方式
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M5;//应急负责人联系方式
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M6;//控制室联系方式

	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M7;//工厂总经理联系方式

	@Column(name = "M8", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M8;//班长联系方式

	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M9;//当地应急响应中心联系方式

	@Column(name = "M10", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M10;//当地安监部门联系方式

	@Column(name = "M11", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M11;//当地环保部门联系方式

	@Column(name = "M12", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M12;//社会救援队联系方式

	@Column(name = "M13", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M13;//友邻单位名称

	@Column(name = "M14", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M14;//友邻单位联系方式
}
