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
@Table(name="ts_cardpos")
public class TS_CardPos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4627303192842954515L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号
	
	@Column(name="ID_card", nullable=false, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID_card;//设备ID ts_card
	
	@Column(name="gpstime", nullable=true,columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp gpstime;//gps时间
	
	@Column(name="lng", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private float lng;//经度
	
	@Column(name="lat", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private float lat;//纬度
	
	@Column(name="speed", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private float speed;//速度
	
	@Column(name="direct", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private float direct;//方向
	
	@Column(name="hight", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private float hight;//高度
	
	@Column(name="locstate", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private int locState;//定位状态
	
	@Column(name="alarmstate", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private int alarmState;//报警状态
	
	@Column(name="elecval", nullable=true, columnDefinition="float")
	@Setter
	@Getter
	private float elecVal;//电量
	
	
	
}
