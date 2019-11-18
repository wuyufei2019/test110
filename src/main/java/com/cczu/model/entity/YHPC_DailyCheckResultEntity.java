package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 隐患排查---日常检查记录信息
 * @author jason
 * @date 2017年8月17日
 */
@Entity
@Table(name="yhpc_dailycheckresult")
public class YHPC_DailyCheckResultEntity extends BaseEntity {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8266862137595575763L;
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID1  ;//计划ID 
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  qyid  ;//企业id

	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//检查时间(用于统计)
	
	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M1;//检查时间
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//责任部门
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(MAX)")
	@Setter
	@Getter
	private String M3;//现场照片

	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String M4;//隐患内容（id集合之间用，隔开）
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M5;//备注信息
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M6;//状态
	
	@Column(name = "M7", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  M7  ;//检查人ID 
}
