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
 * 隐患排查---巡检班次信息
 * @author jason
 * @date 2017年8月17日
 */
@Entity
@Table(name="yhpc_checkplan")
public class YHPC_CheckPlanEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1528033644109760656L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID1  ;//当为企业自查班次是为企业ID  当为网格巡检班次的时候为0
	
	@Column(name = "name", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String name;//班次名称
	
	@Column(name = "type", nullable = true, columnDefinition="char(1)")
	@Setter
	@Getter
	private String type;//班次类型 (1.日2.周3.月4.年)
	
	@Column(name = "startmin", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String startmin;//开始时分秒
	
	@Column(name = "endmin", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String endmin;//结束时分秒
	
	@Column(name = "weeknum", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String weeknum;//周几
	
	@Column(name = "month", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String month;//月份
	
	@Column(name = "startdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp startdate;//开始日期
	
	@Column(name = "enddate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp enddate;//开始日期
	
	@Column(name = "userid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  userid  ;//建立人ID
	
	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//建立时间
 
	@Column(name = "wgid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  wgid  ;//网格id
}
