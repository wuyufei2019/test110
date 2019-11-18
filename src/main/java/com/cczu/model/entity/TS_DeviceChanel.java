package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ts_devicechannel")
public class TS_DeviceChanel implements Serializable{
	
	private static final long serialVersionUID = -2559637042116233339L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@Column(name="ID1", nullable=false)
	@Setter
	@Getter
	private long ID1;//设备ID ts_device
	
	@Column(name="channelno", nullable=true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String channelNo;//信道号码：AI01 AI02
	
	@Column(name="minelec", nullable=true )
	@Setter
	@Getter
	private Float minElec;//电流下限
	
	@Column(name="maxelec", nullable=true)
	@Setter
	@Getter
	private Float maxElec;//电流上限
	
	@Column(name="minval", nullable=true )
	@Setter
	@Getter
	private Float minVal;//范围下限
	
	@Column(name="maxval", nullable=true)
	@Setter
	@Getter
	private Float maxVal;//范围下限
	
	@Column(name="minlmt", nullable=true )
	@Setter
	@Getter
	private Float minLmt;//阈值下限
	
	@Column(name="maxlmt", nullable=true)
	@Setter
	@Getter
	private Float maxLmt;//阈值上限
	
	@Column(name="ord", nullable=true )
	@Setter
	@Getter
	private int ord;//通道号
	
	@Column(name="unit", nullable=true )
	@Setter
	@Getter
	private String unit;//单位
}
