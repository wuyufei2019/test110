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
 * 蓝牙定位-设备管理-房间表实体类
 */
@Entity
@Table(name = "Pub_Room")
public class Pub_Room implements Serializable {

	private static final long serialVersionUID = -3538633466186133067L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roomid", nullable = false, columnDefinition="int")
	@Setter
	@Getter
	public int roomid;//房间号
	
	@Column(name = "roomcode", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter
	public String roomcode;//房间编码
	
	@Column(name = "intime", nullable = true, columnDefinition="datetime")
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
	public int rstatus;//数据采集过滤次数
	
	@Column(name = "reader", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	public int reader;//数据采集基站号
	
	@Column(name = "roomname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String roomname;//房间名
	
	@Column(name = "floor", nullable = true,length=1, columnDefinition="int")
	@Setter
	@Getter
	public int floor;//楼层
}
