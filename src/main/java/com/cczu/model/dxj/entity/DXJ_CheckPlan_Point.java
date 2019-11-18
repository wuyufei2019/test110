package com.cczu.model.dxj.entity;

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
 * 点巡检---巡检班次巡检点中间表
 * @author zpc
 * @date 2018年3月02日
 */
@Entity
@Table(name="dxj_checkplan_point")
public class DXJ_CheckPlan_Point implements Serializable {

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
}
