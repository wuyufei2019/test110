package com.cczu.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 报警预警
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="fmew_alarm")
public class FMEW_AlarmEntity implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -664067905704589121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业ID
	
	@Column(name = "ID2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//报警模块id(储罐,气体,高危工艺的id)
	
	@Column(name="colltime", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp colltime;//报警时间
	
	@Column(name="type", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String type;//报警类型(1:储罐,2:气体,3:高危工艺)
	
	@Column(name="situation", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String situation ;//报警情况
	
	@Column(name="reason", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String reason ;//报警原因

	@Column(name="status", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String status;//是否处理（0否1是）
}
