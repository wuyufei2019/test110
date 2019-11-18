package com.cczu.model.zdwxyssjc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 储罐压力传感器信号采集
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="main_signal_pressure")

public class Main_SignalPressureEntity implements Serializable{
 
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

	@Column(name="YLCGQBH", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String YLCGQBH;//压力传感器设备编码
	
	@Column(name="YLCGQWZ", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String YLCGQWZ;//压力传感器位置

	@Column(name="YLBJYZ1", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float YLBJYZ1;//压力第一级报警阈值

	@Column(name="YLBJYZ2", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float YLBJYZ2;//压力第二级报警阈值

	@Column(name="CJSJ", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp CJSJ;//采集时间

	@Column(name="BJYL", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float BJYL;//报警压力
	
	@Column(name="YLBJSJ", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp YLBJSJ;//压力报警时间

	@Column(name="SSYL", nullable=true, columnDefinition="numeric(18,2)" )
	@Setter
	@Getter
	private Float SSYL;//实时压力
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0）
	
	
}
