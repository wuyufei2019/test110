package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 风险管控-风险评估危险度风险分析
 * @author XY
 *
 */

@Entity
@Table(name="fxgk_wxdriskassessment")
public class FXGK_WxdRiskAssessment extends BaseEntity{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业ID
	
	@Column(name = "deptid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long deptid;//部门ID
	
	@Column(name = "analysisper", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String analysisper;//分析人
	
	@Column(name = "analysistime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp analysistime;//分析时间
	
	@Column(name = "reviewer", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String reviewer;//审核人
	
	@Column(name = "auditor", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String auditor;//审定人
	
	@Column(name = "unit", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String unit;//单元名称
	
	@Column(name = "material", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String material;//涉及物料
	
	@Column(name = "matter", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer matter;//物质分值
	
	@Column(name = "capacity", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer capacity;//容量分值
	
	@Column(name = "temperature", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer temperature;//温度分值
	
	@Column(name = "pressure", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer pressure;//压力分值
	
	@Column(name = "operation", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer operation;//操作分值
	
	@Column(name = "riskvalue", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer riskvalue;//风险值
	
	@Column(name = "risklevel", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String risklevel;//风险等级
	
	@Column(name = "improvemeasure", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String improvemeasure;//改进措施
	
}
