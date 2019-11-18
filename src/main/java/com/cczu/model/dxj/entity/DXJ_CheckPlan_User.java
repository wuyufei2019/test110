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
 * 点巡检---巡检班次人员中间表
 * @author zpc
 * @date 2018年3月02日
 */
@Entity
@Table(name="dxj_checkplan_user")
public class DXJ_CheckPlan_User implements Serializable {
 
	private static final long serialVersionUID = -9204344543115589351L;

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
	 * 用户ID 
	 */
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID2;
 

}
