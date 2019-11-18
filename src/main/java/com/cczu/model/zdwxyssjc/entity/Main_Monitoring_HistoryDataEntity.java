package com.cczu.model.zdwxyssjc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: Main_Monitoring_HistoryDataEntity
 * @Description: 历史数据数据表
 * @author wbth
 * @date 2019年10月9日
 *
 */
@Entity
@Table(name="main_monitoring_historydata")
public class Main_Monitoring_HistoryDataEntity {
	
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
	
	@Column(name = "ssdata", nullable = true, columnDefinition="float")
	@Setter
	@Getter	
	private Float ssdata;//监测值

	@Column(name = "updatetime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp updatetime;//更新时间

}
