package com.cczu.model.zdwxyssjc.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: Main_Monitoring_AlarmDataEntity
 * @Description: 报警数据数据表
 * @author wbth
 * @date 2019年10月9日
 *
 */
@Entity
@Table(name="main_monitoring_alarmdata")
public class Main_Monitoring_AlarmDataEntity {
	
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

	@Column(name = "pointid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long pointid;//报警点位号
	
	@Column(name = "value", nullable = true, columnDefinition="numeric(8,2)")
	@Setter
	@Getter	
	private Float value;//监测值

	@Column(name = "alarmtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp alarmtime;//报警时间

	@Column(name = "alarmtype", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String alarmtype;//报警类型(温度\液位\压力\有毒|可燃气体浓度高\高高\低\低低爆，用英文表示)

	@Column(name = "point_high_alarm", nullable = true, columnDefinition="numeric(8,2)")
	@Setter
	@Getter
	private Float point_high_alarm;//监测点位高爆值

	@Column(name = "point_high_high_alarm", nullable = true, columnDefinition="numeric(8,2)")
	@Setter
	@Getter	
	private Float point_high_high_alarm;//监测点位高高爆值

	@Column(name = "point_low_alarm", nullable = true, columnDefinition="numeric(8,2)")
	@Setter
	@Getter
	private Float point_low_alarm;//监测点位低爆值

	@Column(name = "point_low_low_alarm", nullable = true, columnDefinition="numeric(8,2)")
	@Setter
	@Getter
	private Float point_low_low_alarm;//监测点位低低爆值

}
