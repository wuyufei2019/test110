package com.cczu.model.zdwxyssjc.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @ClassName: Main_MonitoringDataEntity
 * @Description: 实时数据表（每个点位只保留最新的一条数据）
 * @author wbth
 * @date 2019年9月16日
 *
 */
@Entity
@Table(name="main_monitoringdata")
public class Main_MonitoringDataEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1106837690821192296L;

	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业id

	@Column(name = "DWH", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String DWH;//点位号
	
	@Column(name = "ssdata", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private String ssdata;//监测值
	
	@Column(name = "updatetime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private String updatetime;//更新时间
	
	@Column(name = "isbj", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String isbj;//是否报警（0：是 1：否）

}
