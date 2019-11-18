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
 * 
 * @ClassName: BIS_HazardIdentityCodeEntity
 * @Description: 企业基本信息-重大危险源-重大危险源辨识信息(危险化学品名称及其临界量  字典表)
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tdic_bis_hazardidentity")
public class Tdic_BIS_HazardIdentityEntity  implements Serializable {
	private static final long serialVersionUID = -6427171122882989923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, length = 8)
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name = "S3", nullable = false )
	@Setter
	@Getter
	private int S3;//删除标识  默认0 未删除  ,1删除

	@Column(name = "M1", nullable = true, length = 100)
	@Setter
	@Getter
	private String M1;//类别

	
	@Column(name = "M2", nullable = true, length = 200)
	@Setter
	@Getter
	private String M2;//危险化学品名称

	@Column(name = "M3", nullable = true)
	@Setter
	@Getter	
	private float M3;//临界量（T）

	@Column(name = "M4", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M4;//备注
	
	@Column(name = "M5", nullable = true, length = 200)
	@Setter
	@Getter
	private String M5;//危险化分类及说明

}
