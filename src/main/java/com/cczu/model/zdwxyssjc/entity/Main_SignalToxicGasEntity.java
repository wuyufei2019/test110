package com.cczu.model.zdwxyssjc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 储罐有毒气体传感器信号采集
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="main_signal_toxicgas")

public class Main_SignalToxicGasEntity implements Serializable{
 
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

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID1;//企业ID

	@Column(name="YDQTCGQBH", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String YDQTCGQBH;//有毒气体传感器编号
	
	@Column(name="YDQTCGQWZ", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String YDQTCGQWZ;//有毒气体传感器位置

	@Column(name="YDQTMC", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String YDQTMC;//有毒气体名称

	@Column(name="YDQTBJYZ1", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float YDQTBJYZ1;//有毒气体浓度第一级报警阈值

	@Column(name="YDQTBJYZ2", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float YDQTBJYZ2;//有毒气体浓度第二级报警阈值

	@Column(name="CJSJ", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp CJSJ;//采集时间

	@Column(name="YDQTBJND", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float YDQTBJND;//有毒气体报警浓度
	
	@Column(name="YDQTBJSJ", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp YDQTBJSJ;//有毒气体报警时间

	@Column(name="YDQTSSND", nullable=true, columnDefinition="numeric(18,2)" )
	@Setter
	@Getter
	private Float YDQTSSND;//有毒气体实时浓度
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0）
	
	
}
