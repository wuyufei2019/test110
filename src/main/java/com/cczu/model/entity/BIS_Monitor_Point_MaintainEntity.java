package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: BIS_Monitor_Point_MaintainEntity
 * @Description: 企业基本信息-监测指标基础信息(同时作为实时数据表)
 * @author wbth
 * @date 2019年10月12日
 *
 */
@Entity
@Table(name="bis_monitor_point_maintain")
public class BIS_Monitor_Point_MaintainEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1106837690821192296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "targetCode", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String targetCode;//指标编码（设备编码(19位)+指标类型编码(2位)+3位流水号 ）

	@Column(name = "equipCode", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String equipCode;//设备编码

	@Column(name = "targetName", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String targetName;//指标名称

	@Column(name = "targetType", nullable = true, columnDefinition="varchar(6)")
	@Setter
	@Getter
	private String targetType;//指标类型，WD:温度;YL:压力;YW:液位;KRQT:可燃气体;YDQT:有毒气体

	@Column(name = "unit", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String unit;//计量单位

	@Column(name = "thresholdUp", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float thresholdUp;//阈值上限

	@Column(name = "thresholdUpplus", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float thresholdUpplus;//阈值上上限

	@Column(name = "thresholdDown", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float thresholdDown;//阈值下限

	@Column(name = "thresholdDownplus", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float thresholdDownplus;//阈值下下限

	@Column(name = "rangeUp", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float rangeUp;//量程上限

	@Column(name = "rangeDown", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float rangeDown;//量程下限

	@Column(name = "targetDescribe", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String targetDescribe;//描述

	@Column(name = "bitNo", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String bitNo;//位号

	@Column(name = "status", nullable = true, columnDefinition="char(1)")
	@Setter
	@Getter
	private int status;//删除标记

	@Column(name = "createDate", nullable = false, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp createDate;//创建时间

	@Column(name = "createBy", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String createBy;//创建人

	@Column(name = "updateDate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp updateDate;//最后修改时间

	@Column(name = "updateBy", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String updateBy;//最后修改人

	/* 实时数据 */

	@Column(name = "alarmValue", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float alarmValue;//报警值

	@Column(name = "alarmTime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp alarmTime;//报警时间

	@Column(name = "alarmtype", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String alarmtype;//报警类型

	@Column(name = "value", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float value;//实时值

	@Column(name = "cjsj", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp cjsj;//采集时间

	@Column(name = "cgqbh", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String cgqbh;//传感器编号

}
