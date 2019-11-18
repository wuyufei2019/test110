package com.cczu.model.zdwxyssjc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 储罐温度传感器信号采集
 * @author wbth
 * @date 2019年8月20日
 */
@Entity
@Table(name="main_signal_temp")
public class Main_SignalTempEntity implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -664067905704589121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//自动编号

	@Column(name="WDCGQBH", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String WDCGQBH;//温度传感器设备编号
	
	@Column(name="WDCGQWZ", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String WDCGQWZ;//温度传感器位置

	@Column(name="WDBJYZ1", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float WDBJYZ1;//温度第一级报警阈值

	@Column(name="WDBJYZ2", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float WDBJYZ2;//温度第二级报警阈值

	@Column(name="CJSJ", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp CJSJ;//采集时间

	@Column(name="BJWD", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float BJWD;//报警温度
	
	@Column(name="WDBJSJ", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp WDBJSJ;//温度报警时间

	@Column(name="SSWD", nullable=true, columnDefinition="numeric(18,2)" )
	@Setter
	@Getter
	private Float SSWD;//实时温度
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0）
	
	
}
