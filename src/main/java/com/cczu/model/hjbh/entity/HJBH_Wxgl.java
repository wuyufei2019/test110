package com.cczu.model.hjbh.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 环境保护--危险废物特性管理
 * @author wbth
 * @date 2018年4月11日
 */
@Entity
@Table(name="hjbh_wxgl")
public class HJBH_Wxgl extends BaseEntity{

	private static final long serialVersionUID = 1130232439817931540L;
	
	@Column(name = "name", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String name ;//废物名称 
	
	@Column(name = "kind", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String kind;//废物类别
	
	@Column(name = "danger_type", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String  danger_type  ;//危险特性 
	
	@Column(name = "content", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String content;//废物成分
	
	
	@Column(name = "express_type", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String express_type;//废物表现形态
	
	@Column(name = "store_type", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String store_type;//贮存方式
 
	@Column(name = "other_handler", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String other_handler;//是否提供/委托外单位利用/处置 1是 0 否
	
	@Column(name = "description", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	private String description;//单位内部利用/处置方式代码及方法描述
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

}
