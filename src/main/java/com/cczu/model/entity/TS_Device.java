package com.cczu.model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ts_device")
public class TS_Device implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3095192952367607936L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name="deviceno", nullable=false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String deviceNo;//设备编号
	
	@Column(name="phoneno", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String phoneNo;//电话号码
	
	@Column(name="protocol", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String protocol;//通信协议
	
	@Column(name="innerip", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String innerIp;//内网Ipde
	
	@Column(name="innerport", nullable=true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String innerPort;//内网端口
	
	@Column(name="outerip", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String outerIp;//外网IP
	
	@Column(name="outerport", nullable=true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String outerPort;//外网端口
	
	@Column(name="logintime", nullable=true )
	@Setter
	@Getter
	private Date loginTime;//登录时间
	
	@Column(name="uptime", nullable=true )
	@Setter
	@Getter
	private Date upTime;//最新上行通信时间
	
	@Column(name="sendbyte", nullable=true )
	@Setter
	@Getter
	private int sendByte;//发送字节数
	
	@Column(name="receivebyte", nullable=true)
	@Setter
	@Getter
	private int receiveByte;//接受字节数
	
	@Column(name="pwd", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String pwd;//密码
	
	@Column(name="online", nullable=true)
	@Setter
	@Getter
	private int online;//是否在线
	
	
}
