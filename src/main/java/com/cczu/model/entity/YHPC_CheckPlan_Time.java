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
 * 隐患排查---巡检班次巡检时间中间表
 * @author zpc
 * @date 2017年9月22日
 */
@Entity
@Table(name="yhpc_checkplan_time")
public class YHPC_CheckPlan_Time implements Serializable {

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
	 * 起始时间(日：时间，周：1-7标识周一到周日，月：天，年：月)
	 */
	@Column(name = "starttime", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String  starttime;
 
	/**
	 * 结束时间
	 */
	@Column(name = "endtime", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String endtime ;
}
