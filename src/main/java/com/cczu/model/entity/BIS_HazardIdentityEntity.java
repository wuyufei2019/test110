package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_HazardIdentityEntity
 * @Description: 企业基本信息-重大危险源-重大危险源辨识信息
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_hazardidentity")
public class BIS_HazardIdentityEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -2561855149916006033L;

	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//重大危险源编号

	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M1;//类别

	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//辨识物质

	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M3;//最大储量

	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M4;//临界储量

	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M5;//计算值 (M3/M4)
	
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M6;//备注
	
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M7;//危险化分类及说明
	
}
