package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 风险管控-风险评估Hazop
 * @author XY
 *
 */

@Entity
@Table(name="fxgk_hazopriskassessment")
public class FXGK_HazopRiskAssessment extends BaseEntity{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业ID
	
	@Column(name = "question", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String question;//分析题目
	
	@Column(name = "drawingnumber", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String drawingnumber;//图纸编号
	
	@Column(name = "revision", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String revision;//修订号
	
	@Column(name = "analysistime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp analysistime;//分析日期
	
	@Column(name = "meetingtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp meetingtime;//会议日期
	
	@Column(name = "member", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String member;//小组成员
	
	@Column(name = "analysispart", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String analysispart;//分析部分
	
	//设计目的
	@Column(name = "material", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String material;//物料
	
	@Column(name = "[function]", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String function;//功能
	
	@Column(name = "source", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String source;//来源
	
	@Column(name = "purpose", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String purpose;//目的地
}
