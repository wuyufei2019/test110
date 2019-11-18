package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 隐患排查--检查任务
 * @author jason
 * @date 2017年7月26日
 */
@Entity
@Table(name="yhpc_inspectiontask")
public class YHPC_InspectionTaskEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117595642022874311L;
	 
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//分配人
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M0;//检查编号

	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;//部门
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(3000)")
	@Setter
	@Getter
	private String M2;//检查人

	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M3;//检查时间
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M4;//检查内容（id集合之间用，隔开）
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M5;//类别
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M6;//状态(0:未检查，1:已检查，2:补充检查)
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M7;//任务名称
}
