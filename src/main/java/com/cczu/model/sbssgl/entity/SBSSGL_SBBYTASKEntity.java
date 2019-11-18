package com.cczu.model.sbssgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备保养任务
 * @author ZPC
 */
@Entity
@Table(name = "sbssgl_sbbytask")
public class SBSSGL_SBBYTASKEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id
	
	@Column(name = "deptid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long deptid;//部门id
	
	@Column(name = "year", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String year;//年度
	
	@Column(name = "month", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String month;//月度
	
	@Column(name = "bztime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp bztime;//编制日期
	
	@Column(name = "bzrid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long bzrid;//编制人
	
	@Column(name = "type", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String type;//计划类别（0.一级1.二级）
	
	@Column(name = "sbtype", nullable = true, columnDefinition="varchar(20)")
	@Getter
	@Setter
	private String sbtype;//设备类别（0.普通设备1.特种设备）
	
	@Column(name = "fj", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String fj;//附件
}
