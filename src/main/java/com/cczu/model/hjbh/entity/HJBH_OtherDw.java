package com.cczu.model.hjbh.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 环境保护--危险废物特性管理--外单位信息
 * @author wbth
 * @date 2018年4月11日
 */
@Entity
@Table(name="hjbh_otherdw")
public class HJBH_OtherDw extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String name ;//单位 名称
	
	@Column(name = "location", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String location;//所在地
	
	@Column(name = "permit_num", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String  permit_num  ;//危险废物经营许可证号
	
	@Column(name = "description", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String description;//利用/处置方式代码及方法描述
	
	@Column(name = "contact_name", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String contact_name;//联系人
	
	@Column(name = "contact_phone", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String contact_phone;//联系方式
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//危险废物id
 
	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//创建时间


}
