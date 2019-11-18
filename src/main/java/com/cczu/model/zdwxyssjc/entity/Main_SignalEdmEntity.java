package com.cczu.model.zdwxyssjc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 在岗人员二道门进出记录表
 */
@Entity
@Table(name="main_signal_edm")

public class Main_SignalEdmEntity implements Serializable{
 
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

	@Column(name="ygcode", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ygcode;//员工工号
	
	@Column(name="ygname", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ygname;//员工姓名
	
	@Column(name="type", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String type;//操作类型

	@Column(name="edmname", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String edmname;//二道门名称

	@Column(name="updatetime", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp updatetime;//更新时间
}
