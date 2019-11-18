package com.cczu.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 风险管控-重大风险
 * @author XY
 *
 */

@Entity
@Table(name="fxgk_majorrisk")
public class FXGK_MajorRisk implements Serializable{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业ID
	
	@Column(name = "deptid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long deptid;//部门ID
	
	@Column(name = "id1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long id1;//关联风险评估的id，用于修改和删除
	
	@Column(name = "name", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String name;//类型名称
	
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
	
	@Column(name = "riskvalue", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Float riskvalue;//风险值
	
	@Column(name = "risklevel", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String risklevel;//风险等级
	
	@Column(name = "improvemeasure", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String improvemeasure;//改进措施
	
}
