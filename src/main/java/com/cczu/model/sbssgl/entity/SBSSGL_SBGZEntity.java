package com.cczu.model.sbssgl.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备设施管理-设备故障
 * @author 
 */
@Entity
@Table(name = "sbssgl_sbgz")
public class SBSSGL_SBGZEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "m1", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m1;//报修日期（年月日）
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m2;//设备报修单号
	
	@Column(name = "sbid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long sbid;//设备id
	
	@Column(name = "deptid", nullable = true, columnDefinition="bigint")
	@Getter
	@Setter
	private Long deptid;//部门id
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m3;//设备型号
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(50)")
	@Getter
	@Setter
	private String m4;//设备编号
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m5;//故障现象
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m6;//原因分析
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m7;//采取对策
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m8;//维修报告附件
	
	@Column(name = "m9", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m9;//维修开始时间
	
	@Column(name = "m10", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp m10;//维修结束时间
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m11;//维修停机时间
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String m12;//更换部件记录
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m13;//备注
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(1000)")
	@Getter
	@Setter
	private String m14;//维修需求单附件
	
}
