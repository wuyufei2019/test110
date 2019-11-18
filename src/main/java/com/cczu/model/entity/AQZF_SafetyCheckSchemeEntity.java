package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--现场检查方案
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="aqzf_safetycheckscheme")
public class AQZF_SafetyCheckSchemeEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117595642022874311L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//计划ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//企业ID
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M0;//检查编号

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//地址

	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//联系人

	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M3;//所属行业

	@Column(name = "M4", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M4;//检查时间
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M5;//行政执法人员
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M6;//检查内容（id集合之间用，隔开）
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M7;//检查方式
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M8;//审核意见
	
	@Column(name = "M8_1", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M8_1;//审核人
	
	@Column(name = "M8_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M8_2;//审核日期
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M9;//审批意见
	
	@Column(name = "M9_1", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M9_1;//审批人
	
	@Column(name = "M9_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M9_2;//审批日期
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M10;//备注
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M11; //操作状态 （0：未添加记录，1：已添加记录）
}
