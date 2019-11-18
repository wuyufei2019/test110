package com.cczu.model.zdwxyssjc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 储罐液位传感器信号采集
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="main_signal_liquidlevel")

public class Main_SignalLiquidLevelEntity implements Serializable{
 
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

	@Column(name="YWCGQBH", nullable=false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String YWCGQBH;//液位传感器编号
	
	@Column(name="YWCGQWZ", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String YWCGQWZ;//液位传感器位置

	@Column(name="YWGWXBJYZ", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float YWGWXBJYZ;//液位高位限报警阈值

	@Column(name="YWGGWXBJYZ", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float YWGGWXBJYZ;//液位高高位限报警阈值

	@Column(name="YWDWXBJYZ", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float YWDWXBJYZ;//液位低位限报警阈值

	@Column(name="YWDDWXBJYZ", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float YWDDWXBJYZ;//液位低低位限报警阈值

	@Column(name="CJSJ", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp CJSJ;//采集时间

	@Column(name="BJYW", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float BJYW;//报警液位
	
	@Column(name="YWBJSJ", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp YWBJSJ;//液位报警时间

	@Column(name="SSYW", nullable=true, columnDefinition="numeric(18,2)" )
	@Setter
	@Getter
	private Float SSYW;//实时液位
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0）
	
	
}
