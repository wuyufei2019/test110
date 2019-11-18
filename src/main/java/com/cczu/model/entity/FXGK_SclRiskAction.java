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
@Table(name="fxgk_sclriskaction")
public class FXGK_SclRiskAction implements Serializable{

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
	
	@Column(name = "project", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String project;//检查项目
	
	@Column(name = "standard", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String standard;//检查标准
	
	@Column(name = "mainresult", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String mainresult;//主要后果
	
	@Column(name = "safetymeasure", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String safetymeasure;//安全措施
	
	@Column(name = "possibility", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer possibility;//可能性
	
	@Column(name = "severity", nullable = true, columnDefinition="int")
	@Setter
	@Getter
	private Integer severity;//严重度
	
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
