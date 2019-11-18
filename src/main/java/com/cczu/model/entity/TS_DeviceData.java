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
@Table(name="ts_devicedata")
public class TS_DeviceData implements Serializable{

	private static final long serialVersionUID = 3394817184833173976L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name="ID1", nullable=false)
	@Setter
	@Getter
	private long ID1;//关联ts_devicechannel  ID
	
	@Column(name="acceptdatetime", nullable=true )
	@Setter
	@Getter
	private Date acceptDatetime;//采集时间

	@Column(name="data1", nullable=true)
	@Setter
	@Getter
	private float data1;//吨位
	
	@Column(name="data2", nullable=true)
	@Setter
	@Getter
	private float data2;//百分比
	
	@Column(name="data3", nullable=true)
	@Setter
	@Getter
	private float data3;//温度
	
	@Column(name="data4", nullable=true)
	@Setter
	@Getter
	private float data4;//压力
	
	@Column(name="data5", nullable=true)
	@Setter
	@Getter
	private float data5;
}
