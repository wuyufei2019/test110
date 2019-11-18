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
 * 隐患排查---风险隐患点停产表
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_stoppoint")
public class YHPC_StopPoint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1087021908576678683L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;
	
	/**
	 * 隐患点ID/风险点ID
	 */
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;
	
	/**
	 * 巡查点类型（1风险点/2隐患排查点）
	 */
	@Column(name = "type", nullable = false, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String  type;
 
	/**
	 * 开始日期
	 */
	@Column(name = "startdate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp startdate ;
	
	/**
	 * 结束日期
	 */
	@Column(name = "enddate", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp enddate ;
}
