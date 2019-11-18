package com.cczu.model.zdwxyssjc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 在岗人员实时汇总表
 */
@Entity
@Table(name="main_signal_edmsummary")

public class Main_SignalEdmsummaryEntity implements Serializable{
 
	private static final long serialVersionUID = -664067905704589121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号

	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;//企业id

	@Column(name="bmname", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String bmname;//部门名称
	
	@Column(name="ryzs", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer ryzs;//人员总数
	
	@Column(name="updatetime", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp updatetime;//更新时间
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0）
}
