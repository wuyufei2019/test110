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
 * 隐患排查---巡检班次巡检点中间表
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_checkplan_point")
public class YHPC_CheckPlan_Point implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5739935339538232699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;
	
	/**
	 * 巡检班次ID 
	 */
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;
	
	/**
	 * 巡检点ID 
	 */
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID2;
 
	/**
	 * 巡查点类型（1风险点/2隐患排查点） 
	 */
	@Column(name = "checkpointtype", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String checkpointtype ;
}
