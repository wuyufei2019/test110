package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--现场检查记录
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_safetycheckrecord")
public class AQZF_SafetyCheckRecordEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117595642022874311L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//检查方案ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//企业ID
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M0;//检查记录编号

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//地址

	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//法定代表人（负责人）

	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M3;//职务

	@Column(name = "M4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M4;//联系电话
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M5;//检查场所
	
	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M6;//检查时间起
	
	@Column(name = "M7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M7;//检查时间止
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M8;//检查人员

	@Column(name = "M9", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M9;//检查情况
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M10;//备注
	
	@Column(name = "M11", nullable = true,columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M11;//照片
	
	@Column(name = "M12", nullable = true,columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M12;//现场处理状态 （0：未 1：已）
	
	@Column(name = "M13", nullable = true,columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M13;//责令整改状态（0：未 1：已）
	
	@Column(name = "M14", nullable = true,columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M14;//立案审批状态（0：未 1：已）
	
}
