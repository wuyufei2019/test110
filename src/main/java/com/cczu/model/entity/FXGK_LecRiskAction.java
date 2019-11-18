package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 风险管控-风险活动
 * @author XY
 *
 */

@Entity
@Table(name="fxgk_lecriskaction")
public class FXGK_LecRiskAction implements Serializable{

	private static final long serialVersionUID = 4793560532817752303L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//风险评估id
	
	@Column(name = "workaction", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String workaction;//作业活动
	
	@Column(name = "potentialhazard", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String potentialhazard;//潜在危害
	
	@Column(name = "mainresult", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String mainresult;//主要后果
	
	@Column(name = "safetymeasure", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String safetymeasure;//安全措施
	
	@Column(name = "possibility", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String possibility;//可能性
	
	@Column(name = "exposefrequency", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String exposefrequency;//暴露频率
	
	@Column(name = "severity", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String severity;//严重度
	
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
