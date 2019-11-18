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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 
 * @ClassName: Tdic_BIS_DangerProcess
 * @Description: 字典-高危工艺数据录入
 * @author bht
 * @date 2017年12月22日
 *
 */
@Entity
@Table(name="tdic_bis_dangerprocess")
@DynamicUpdate @DynamicInsert
public class Tdic_BIS_DangerProcess  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name="M0", nullable=false, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M0;//高危工艺类别值
	@Column(name="M1", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//反应类型
	
	@Column(name="M2", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M2;//重点监控单元
	
	@Column(name="M3", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M3;//工艺简介

	@Column(name="M4", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M4;//工艺危险特点

	@Column(name="M5", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M5;//典型工艺

	@Column(name="M6", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M6;//重点监控工艺参数

	@Column(name="M7", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M7;//安全控制的基本要求

	@Column(name="M8", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M8;//宜采用的控制方式

	@Column(name="M9", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M9;//高危工艺名称

}
