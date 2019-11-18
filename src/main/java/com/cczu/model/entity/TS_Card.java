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

@Entity
@Table(name="ts_card")
public class TS_Card implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169974756760221242L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name="cardno", nullable=false, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String cardNo;//工卡编号 :12位
	
	@Column(name="isonline", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isOnline;//是否在线 0不在线 1在线
	
	@Column(name="ip", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String ip;//Ip
	
	@Column(name="port", nullable=true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String port;//端口
	
	@Column(name="logintime", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp loginTime;//登陆时间
	
	@Column(name="isrelation", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isRelation;//是否绑定员工（0否 1是）
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业ID
	
	@Column(name="r1", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r1;//保留
	
	@Column(name="r2", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r2;//保留
}
