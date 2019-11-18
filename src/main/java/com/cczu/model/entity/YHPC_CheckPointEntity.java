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
 * 隐患排查---隐患排查点信息
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_checkpoint")
public class YHPC_CheckPointEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7310050254804623171L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID1;//企业ID
	
	@Column(name = "name", nullable = false, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String name;//巡查点名称 
	
	@Column(name = "x", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String x;//企业平面图坐标X 
	
	@Column(name = "y", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String y;//企业平面图坐标y
	
	@Column(name = "lng", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String lng;//经度
	
	@Column(name = "lat", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String lat;//纬度
	
	@Column(name = "usetype", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String usetype ;//用途（1网格点/2自查点） 

	@Column(name = "createtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createtime;//创建时间
	
	@Column(name = "bindcontent", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String bindcontent;//绑定二维码
	
	@Column(name = "area", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String  area;//rfid卡批次代码
 
	@Column(name = "rfid", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String rfid;//绑定rfid
	
	@Column(name = "iszdwxy", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String iszdwxy;////是否是重大危险源（0.否1.是）
}
