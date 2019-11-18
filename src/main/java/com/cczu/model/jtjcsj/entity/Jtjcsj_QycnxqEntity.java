package com.cczu.model.jtjcsj.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * 静态基础数据-企业承诺详情表
 * @author Administrator
 *
 */
//@Entity
//@Table(name="jtjcsj_qycnxq")
public class Jtjcsj_QycnxqEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name="qyid", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;//企业id
	
	@Column(name="uuid", nullable=true, columnDefinition="varchar(36)")
	@Setter
	@Getter
	private String uuid;//唯一编码 UUID
	
	@Column(name="companycode", nullable=true, columnDefinition="varchar(9)")
	@Setter
	@Getter
	private String companycode;// COMPANY_CODE  企业编码
	
	@Column(name="unitsnumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer unitsnumber;// UNITS_NUMBER  生产装置套数
	
	@Column(name="runnumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer runnumber;// RUN_NUMBER  运行套数
	
	@Column(name="parknumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer parknumber;// PARK_NUMBER  停车套数
	
	@Column(name="roadworknumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer roadworknumber;// ROADWORK_NUMBER  断路作业数量
	
	@Column(name="soilworknumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer soilworknumber;// SOILWORK_NUMBER  动土作业数量
	
	@Column(name="highworknumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer highworknumber;// HIGHWORK_NUMBER  高处作业数量
	              
	@Column(name="electricityworknumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer electricityworknumber;// ELECTRICITYWORK_NUMBER   临时用电作业数量
	
	@Column(name="liftingworknumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer liftingworknumber;//  LIFTINGWORK_NUMBER  吊装作业数量
	
	@Column(name="blindplatenumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer blindplatenumber;// BLINDPLATE_NUMBER  盲板作业数量
	
	@Column(name="fire2number", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer fire2number;//  FIRE2_NUMBER  二级动火作业数量
	
	@Column(name="fire1number", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer fire1number;// FIRE1_NUMBER  一级动火作业数量
	
	@Column(name="spaceworknumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer spaceworknumber;//  SPACEWORK_NUMBER  受限空间作业数量
	
	@Column(name="inspectionnumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer inspectionnumber;//  INSPECTION_NUMBER  检维修作业数量
	
	@Column(name="firesnumber", nullable=true, columnDefinition="numeric(8)")
	@Setter
	@Getter
	private Integer firesnumber;//  FIRES_NUMBER  特级动火作业数量
	
	@Column(name="contractor", nullable=true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String contractor;// CONTRACTOR  是否有承包商作业       0否;1是
	
	@Column(name="trialproduction", nullable=true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String trialproduction;// TRIAL_PRODUCTION  是否处于试生产期       0否;1是
	
	@Column(name="openparking", nullable=true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String openparking;// OPEN_PARKING 是否处于开停车状态       0否;1是
	
	@Column(name="test", nullable=true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String test;// TEST 是否开展中（扩）试       0否;1是
	
	@Column(name="mhazards", nullable=true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String mhazards;// M_HAZARDS  有无重大隐患      0否;1是
	
	@Column(name="riskgrade", nullable=true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String riskgrade;// RISK_GRADE  风险级别      1高风险;2较大风险;3一般风险;4低风险
	
	@Column(name="commitedate", nullable=true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp commitedate;// COMMITE_DATE 承诺时间
	
	@Column(name="commitment", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String commitment;// COMMITMENT  承诺人
	
	
	
	
	
	
}
