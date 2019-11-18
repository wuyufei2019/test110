package com.cczu.model.lydw.entity;

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

/*
 * 蓝牙定位-设备管理-阅读器表实体类
 */
@Entity
@Table(name = "Pub_Reader")
public class Pub_Reader implements Serializable {

	private static final long serialVersionUID = 5181449989328192118L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "readerid", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public int readerid;
	
	@Column(name = "readercode", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String readercode;//信标MAC地址
	
	@Column(name = "room", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String room;//
	
	@Column(name = "tcpip", nullable = true, columnDefinition="varchar(15)")
	@Setter
	@Getter
	public String tcpip;//保留
	
	@Column(name = "intime", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp intime;//载入时间
	
	@Column(name = "uptime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp uptime;//更新时间
	
	@Column(name = "rstatus", nullable = true, columnDefinition="int default 1")
	@Setter
	@Getter
	public int rstatus;//信标rssi过滤阀值
	
	@Column(name = "online", nullable = true, columnDefinition="int default 1")
	@Setter
	@Getter
	public int online;//信标在线状态
	
	@Column(name = "battery", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int battery;//信标电量
	
	@Column(name = "state", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int state;//信标是否欠电状态
	
	@Column(name = "sendpower", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int sendpower;//信标发射成功率
	

}
