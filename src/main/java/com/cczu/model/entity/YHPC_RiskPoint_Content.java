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
 * 隐患排查---风险点检查内容中间表
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_riskpoint_content")
public class YHPC_RiskPoint_Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -929927705529199930L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;
	
	/**
	 * 风险点ID 
	 */
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;
	
	/**
	 * 检查内容ID 
	 */
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID2;
 

}
