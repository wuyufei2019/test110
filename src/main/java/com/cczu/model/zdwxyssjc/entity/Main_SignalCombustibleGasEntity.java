package com.cczu.model.zdwxyssjc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 储罐可燃气体传感器信号采集
 * @author jason
 * @date 2017年6月26日
 */
@Entity
@Table(name="main_signal_combustiblegas")

public class Main_SignalCombustibleGasEntity implements Serializable{
 
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
	public long ID1;//企业ID

	@Column(name="KRQTCGQBH", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String KRQTCGQBH;//可燃气体传感器编号
	
	@Column(name="KRQTCGQWZ", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String KRQTCGQWZ;//可燃气体传感器位置

	@Column(name="KRQTMC", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String KRQTMC;//可燃气体名称

	@Column(name="KRQTBJYZ1", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float KRQTBJYZ1;//可燃气体浓度第一级报警阈值

	@Column(name="KRQTBJYZ2", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float KRQTBJYZ2;//可燃气体浓度第二级报警阈值

	@Column(name="CJSJ", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp CJSJ;//当前时间

	@Column(name="KRQTBJND", nullable=true, columnDefinition="numeric(18,2)")
	@Setter
	@Getter
	private Float KRQTBJND;//可燃气体报警浓度
	
	@Column(name="KRQTBJSJ", nullable=true, columnDefinition="datetime" )
	@Setter
	@Getter
	private Timestamp KRQTBJSJ;//可燃气体报警时间

	@Column(name="KRQTSSND", nullable=true, columnDefinition="numeric(18,2)" )
	@Setter
	@Getter
	private Float KRQTSSND;//可燃气体实时浓度
	
	@Column(name="isupload", nullable=true, columnDefinition="int")
	@Setter
	@Getter
	private Integer isupload;//是否上传（0未上传 1已上传  默认0）
	
	
}
