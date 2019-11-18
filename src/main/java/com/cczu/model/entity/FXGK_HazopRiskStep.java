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
 * 风险管控-分析过程、步骤
 * @author XY
 *
 */

@Entity
@Table(name="fxgk_hazopriskstep")
public class FXGK_HazopRiskStep implements Serializable{

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
	
	@Column(name = "guidanceword", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String guidanceword;//引导词
	
	@Column(name = "factor", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String factor;//要素
	
	@Column(name = "deviation", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String deviation;//偏差
	
	@Column(name = "possiblereason", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String possiblereason;//可能原因
	
	@Column(name = "result", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String result;//后果
	
	@Column(name = "safetymeasure", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String safetymeasure;//安全措施
	
	@Column(name = "note", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String note;//注释
	
	@Column(name = "suggestion", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String suggestion;//建议安全措施
	
	@Column(name = "executor", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String executor;//执行人
	
}
