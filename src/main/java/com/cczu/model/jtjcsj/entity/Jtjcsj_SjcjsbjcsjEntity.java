package com.cczu.model.jtjcsj.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
/**
 * 静态基础数据-数据采集设备基础数据
 * @author Administrator
 *
 */
@Entity
@Table(name="jtjcsj_sjcjsbjcsj")
public class Jtjcsj_SjcjsbjcsjEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name="qyid", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;//企业id
	
	@Column(name="gatewaycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String gatewaycode;// gatewayCode 网关编码
	
	@Column(name="gatewayname", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String gatewayname;// gatewayName 网关名称
	
	@Column(name="supplier", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String supplier;//供应商
	
	@Column(name="gatewaytype", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String gatewaytype;// gatewayType  网关类别
	
	@Column(name="gatewaymodel", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String gatewaymodel;// gatewayModel 网关型号
	
	@Column(name="ipaddr", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String ipaddr;// ipAddr IP地址
	
	@Column(name="portno", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String portno;// portNo 访问端口
	
	@Column(name="netmask", nullable=true, columnDefinition="varchar(52)")
	@Setter
	@Getter
	private String netmask;// netMask 子网掩码
	
	@Column(name="gateway", nullable=true, columnDefinition="varchar(52)")
	@Setter
	@Getter
	private String gateway;// gateWay 网关地址
	
	@Column(name="installloc", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String installloc;// installLoc 安装位置
	
	@Column(name="installdate", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp installdate;// installDate 安装日期
	
	@Column(name="frequncey", nullable=true, columnDefinition="numeric(6)")
	@Setter
	@Getter
	private Float frequncey;//采集频率
	
	@Column(name="status", nullable=true, columnDefinition="char(1)")
	@Setter
	@Getter
	private String status;//删除标记        0未删除，1已删除
	
	@Column(name="createdate", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createdate;// createDate 创建时间
	
	@Column(name="createby", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String createby;// createBy 创建人
	
	@Column(name="updatedate", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp updatedate;// updateDate 最后修改时间
	
	@Column(name="updateby", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String updateby;// updateBy 最后修改人

}
